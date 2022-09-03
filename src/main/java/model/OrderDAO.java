package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDAO {
	
	String dbName;
	String serverName;
	String connectionURL;
	
	ArrayList<Order> orderList = new ArrayList<Order>();
	
	public void connection()
	{
		dbName = "SET_MoudisaJanDB";
		
		serverName = "punv730f.egeng.info";
		
		connectionURL = "jdbc:sqlserver://" + serverName + ":1433;integratedSecurity=false;databaseName=" + dbName;
	}
	
	public OrderDAO()
	{
		super();
	}

	public void checkOut(Order order) {
		connection();
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
								
			String str = "INSERT INTO orders VALUES (?,?,?,?);";
						
			PreparedStatement ps = con.prepareStatement(str);
			
			ps.setInt(1, order.getProductID());
			ps.setInt(2, order.getCustomerID());
			ps.setInt(3, order.getQuantity());
			ps.setDouble(4, order.getTotalAmount());
			
			ps.executeUpdate();
			
			
			ps.close();
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
	}

	public ArrayList<Order> getOrderHistory(int cid) {
		connection();
		
		String str="SELECT * FROM orders WHERE customerID="+cid;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery(str);										
			while(rs.next())
								
			{
				Order order = new Order();
				order.setProductID(rs.getInt("productID"));
				order.setCustomerID(rs.getInt("customerID"));
				order.setQuantity(rs.getInt("quantity"));
				order.setTotalAmount(rs.getInt("totalAmount"));
				
				
				
				orderList.add(order);  
			}
			
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
		return orderList;
	}

	public void cancelOrder(int cid, int pid) {
		connection();
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			
			String str = "DELETE FROM orders WHERE customerID=? AND productID=?;";
		
			
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


}
