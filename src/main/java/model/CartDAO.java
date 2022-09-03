package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CartDAO {
	

	String dbName;
	String serverName;
	String connectionURL;
	
	ArrayList<Cart> cartList = new ArrayList<Cart>();
	
	public void connection()
	{
		dbName = "SET_MoudisaJanDB";
		
		serverName = "punv730f.egeng.info";
		
		connectionURL = "jdbc:sqlserver://" + serverName + ":1433;integratedSecurity=false;databaseName=" + dbName;
	}
	
	public CartDAO()
	{
		super();
	}

	public void addToCart(Cart cart) {
		connection();
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
								
			String str = "INSERT INTO cart VALUES (?,?,?,?);";
						
			PreparedStatement ps = con.prepareStatement(str);
			
			ps.setInt(1, cart.getProductID());
			ps.setInt(2, cart.getCustomerID());
			ps.setInt(3, cart.getQuantity());
			ps.setDouble(4, cart.getTotalAmount());
			
			ps.executeUpdate();
			
			
			ps.close();
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
	}
	
	public ArrayList<Cart> allProductsInCart(int cid)
	{
		connection();
		
		String str="SELECT * FROM cart where customerID="+cid;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery(str);										
			while(rs.next())
								
			{
				Cart cart = new Cart();
				cart.setCustomerID(rs.getInt("customerID"));
				cart.setProductID(rs.getInt("productID"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setTotalAmount(rs.getInt("totalAmount"));
								
				cartList.add(cart);  
			}
			stmt.close();
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
		return cartList;
		
	}

	public void deleteProductFromCart(int cid, int pid) {
		connection();
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			//Statement stmt=con.createStatement();
			//String str = "DELETE FROM cart WHERE customerID=" + cid + "AND productID=" + pid + ";";
			String str = "DELETE FROM cart WHERE customerID=? AND productID=?;";
			//stmt.executeQuery(str);
			
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, cid);
			ps.setInt(2, pid);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public void updateProduct(int pid, int cid, int quantity, double totalAmount) {
		connection();
		
		try
		{			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			String str = "UPDATE cart SET quantity=?,totalAmount=? WHERE customerID=? AND productID=?;";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, quantity);
			ps.setDouble(2, totalAmount);
			ps.setInt(3, cid);
			ps.setInt(4, pid);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
	}

	public void emptyCart(int cid) {
		connection();
		try
		{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			String str = "DELETE FROM cart WHERE customerID=?";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, cid);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
	}

	

}
