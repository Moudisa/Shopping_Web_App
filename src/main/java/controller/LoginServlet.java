package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.CustomerDAO;
import model.Product;
import model.ProductDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int temp = Integer.parseInt(request.getParameter("temp"));
		
		if(temp == 1)
		{
			int cid = Integer.parseInt(request.getParameter("cid"));
			ProductDAO productdao = new ProductDAO();              
     		ArrayList<Product> productList = productdao.allProducts();
     		 
     		CustomerDAO customerdao = new CustomerDAO();
     		Customer customer = customerdao.getCustomerById(cid);
     		request.setAttribute("customer", customer);
     		       		
     		request.setAttribute("productList", productList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewProductsToCustomer.jsp");
            requestDispatcher.forward(request, response);
		}
		
		else
		{
		CustomerDAO customerdao = new CustomerDAO();
		Customer customer = customerdao.getAllCustomers(username, password);
		
		response.setContentType("text/html");
		PrintWriter pww = response.getWriter();
		
		
		 
			 if(customer != null)
			 {				 
				 request.getSession().setAttribute("auth", customer);
                                  
                 //customerID = customer.getCustomerID();
                // System.out.println(customer.getCustomerID());
                 ProductDAO productdao = new ProductDAO();              
         		 ArrayList<Product> productList = productdao.allProducts();
         		
         		request.setAttribute("customer", customer);
         		String str = "Welcome " + customer.getUsername() + "!";
         		request.setAttribute("str", str);        		
         		request.setAttribute("productList", productList);
    			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewProductsToCustomer.jsp");
                requestDispatcher.forward(request, response);
         		        		
			 }
			 else
				 pww.println("wrong username or password\n");
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
