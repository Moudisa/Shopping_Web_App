package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDAO;

/**
 * Servlet implementation class AllProductsServlet
 */
@WebServlet("/all.do")
public class AllProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String dbName;
	String serverName;
	String connectionURL;
	
    public AllProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void connection() 
	{
		dbName = "SET_MoudisaJanDB";
		
		serverName = "punv730f.egeng.info";
		
		connectionURL = "jdbc:sqlserver://" + serverName + ":1433;integratedSecurity=false;databaseName=" + dbName;
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		connection();
		boolean temp1 = false;
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionURL, "sa", "egain@123");
			
			String str = "SELECT * FROM admin";			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(str);
									
			while(rs.next())
			{
				String dbname = rs.getString("name");
                String dbpassword = rs.getString("password");
                               
                if (dbname.equalsIgnoreCase(name) && dbpassword.equals(password))
                {
                	 temp1 = true;                 	 
                	 break;
                }                                 	            
			}
			
			
			stmt.close();
			con.close();
		}
			
		catch (Exception e) 
		{
			System.out.println("Error " + e);
		}
		
		if(temp1 == true)
		{
			ProductDAO productdao = new ProductDAO();
			ArrayList<Product> productList = productdao.allProducts();
			
			request.setAttribute("productList", productList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewProducts.jsp");
            requestDispatcher.forward(request, response);
            									 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
