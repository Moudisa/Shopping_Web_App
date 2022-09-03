package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;




public class CustomerDAO {
	
	String dbName;
	String serverName;
	String connectionURL;
	
	int customerID;
	ArrayList<Customer> allCustomerList = new ArrayList<Customer>();
	
	public CustomerDAO()
	{
		super();
		
	}
		
	
	public void connection()
	{
		dbName = "SET_MoudisaJanDB";
		
		serverName = "punv730f.egeng.info";
		
		connectionURL = "jdbc:sqlserver://" + serverName + ":1433;integratedSecurity=false;databaseName=" + dbName;
	}
	
	public void addCustomer(Customer customer)
	{
		connection();
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
								
			String str = "INSERT INTO customer VALUES (?,?,?,?);";
						
			PreparedStatement ps = con.prepareStatement(str);
			
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setInt(3, customer.getNumber());
			ps.setInt(4, customer.getPincode());
			
			ps.executeUpdate();
			
			
			ps.close();
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public Customer getAllCustomers(String username, String password) {
		
		connection();
		
		Customer customer = null;
		//boolean temp = false;
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			
			String str = "SELECT * FROM customer where username=? and password=?";
			PreparedStatement pst = con.prepareStatement(str);
			pst.setString(1, username);
			pst.setString(2, password);			
	
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
			
            	customer = new Customer();
            	customer.setCustomerID(rs.getInt("customerID"));
            	customer.setUsername(rs.getString("username"));
            	customer.setPassword(rs.getString("password"));
            	           				
                                  	            
			}
			
			
			pst.close();
			con.close();
			
			
			
			
		}
		catch (Exception e) 
		{
			System.out.println("Error " + e);
		}
		return customer;
	}
	
	public ArrayList<Customer> allCustomers()
	{
		connection();
		
		String str="SELECT * FROM customer";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery(str);										
			while(rs.next())
								
			{
				Customer customer = new Customer();
				customer.setCustomerID(rs.getInt("customerID"));
				customer.setUsername(rs.getString("username"));
				
				
				allCustomerList.add(customer);  
			}
			
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		
		return allCustomerList;
		
	}
	
public Customer getCustomerById(int customerID) {
		
		connection();
		Customer customer = new Customer(); 
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");	
			String str = "SELECT * FROM customer WHERE customerID=" + customerID;
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(str);
						
				while(rs.next()) {
					
					customer.setCustomerID(rs.getInt("customerID"));
					customer.setUsername(rs.getString("username"));
					
				}										
			
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e);
		}
		return customer;
	}



}
