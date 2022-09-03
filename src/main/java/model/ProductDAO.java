package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	
	ArrayList<Product> allProductList = new ArrayList<Product>();
	
	String dbName;
	String serverName;
	String connectionURL;
	
	public ProductDAO()
	{
		super();
		//allProductList = allProducts();
	}
	
	public void connection()
	{
		dbName = "SET_MoudisaJanDB";
		
		serverName = "punv730f.egeng.info";
		
		connectionURL = "jdbc:sqlserver://" + serverName + ":1433;integratedSecurity=false;databaseName=" + dbName;
	}
	
	public ArrayList<Product> allProducts()
	{
		connection();
		
		String str="SELECT * FROM product";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery(str);										
			while(rs.next())
								
			{
				Product product = new Product();
				product.setProductID(rs.getInt("productID"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setCategory(rs.getString("category"));
				product.setStockUnits(rs.getInt("stockUnits"));
				
				allProductList.add(product);  
			}
			
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
		return allProductList;
		
	}


	public void delete(int productID) {
		
		connection();
		
		try {
								
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");			
			Statement stmt=con.createStatement();
			String str = "DELETE FROM product WHERE productID=" + productID;					
			stmt.executeUpdate(str);
								        		        
	
			con.close();		
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
	}

	public void update(Product product) {
		connection();
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");	
			String str = "UPDATE product SET name =?,price=?,category=?,stockUnits=? WHERE productID=" + product.getProductID();
			
			PreparedStatement ps = con.prepareStatement(str);
			
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getCategory());
			ps.setInt(4, product.getStockUnits());
			
			ps.executeUpdate();
			ps.close();
			
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
	}

	public Product getProductById(int productID) {
		
		connection();
		Product product = new Product(); 
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");	
			String str = "SELECT * FROM product WHERE productID=" + productID;
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(str);
						
				while(rs.next()) {
					
					product.setProductID(rs.getInt("productID"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					product.setCategory(rs.getString("category"));
					product.setStockUnits(rs.getInt("stockUnits"));
					//System.out.println(rs.getString("name"));
				}										
			
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		return product;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) throws ClassNotFoundException {
		
		connection();
		List<Cart> book = new ArrayList<>();
        try {
        	
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");	
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    String query = "select * from product where productID=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setInt(1, item.getProductID());
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setProductID(rs.getInt("productID"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) throws ClassNotFoundException {
        double sum = 0;
        connection();
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");	
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    String query = "select price from product where productID=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setInt(1, item.getProductID());
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

	public void addNewProduct(String name, double price, String category, int stockUnits) {
		connection();
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			
			String str = "INSERT INTO product VALUES(?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setString(3, category);
			ps.setInt(4, stockUnits);
			
			ps.executeUpdate();
			
			/*Statement st = con.createStatement(); 
            st.executeUpdate("INSERT INTO product VALUES('" + request.getParameter("name") + "'," + 
            		Integer.valueOf(request.getParameter("price")) + ",'" + 
            				request.getParameter("category") + "'," + 
            				Integer.valueOf(request.getParameter("stockUnits")) + ");" );*/
               													 	          	          
          
            ps.close();
            con.close();
 	           

	} 
	catch (Exception e) {
		System.out.println("Error " + e);
	}
		
	}

	public void decrementStockUnits(int productID, int quantity) {
		connection();
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			
			String str = "UPDATE product SET stockUnits=? WHERE productID=?";
			Product product = getProductById(productID);
			PreparedStatement ps = con.prepareStatement(str);
			int stockUnits = product.getStockUnits()-quantity;
			ps.setInt(1, stockUnits);
			ps.setInt(2, productID);
			ps.executeUpdate();
			
			ps.close();
            con.close();
 	           

	} 
	catch (Exception e) {
		System.out.println("Error " + e);
	}
		
	}
	
	public void incrementStockUnits(int productID, int quantity) {
		connection();
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			
			String str = "UPDATE product SET stockUnits=? WHERE productID=?";
			Product product = getProductById(productID);
			PreparedStatement ps = con.prepareStatement(str);
			int stockUnits = product.getStockUnits()+quantity;
			ps.setInt(1, stockUnits);
			ps.setInt(2, productID);
			ps.executeUpdate();
			
			ps.close();
            con.close();
 	           

	} 
	catch (Exception e) {
		System.out.println("Error " + e);
	}
		
	}

	
}
