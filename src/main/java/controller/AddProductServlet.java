package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/add.do")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));	
		String category = request.getParameter("category");
		int stockUnits = Integer.parseInt(request.getParameter("stockUnits"));
		
		Product product;
		ProductDAO productdao = new ProductDAO();
		productdao.addNewProduct(name, price, category, stockUnits);
		
		ArrayList<Product> productList = productdao.allProducts();
		String str = "Product Added Sucessfully!";
		request.setAttribute("str", str);
		request.setAttribute("productList", productList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewProducts.jsp");
        requestDispatcher.forward(request, response);
		
	}
}
