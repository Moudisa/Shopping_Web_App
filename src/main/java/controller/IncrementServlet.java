package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.CartDAO;
import model.Product;
import model.ProductDAO;

/**
 * Servlet implementation class IncrementServlet
 */
@WebServlet("/increment.do")
public class IncrementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncrementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pid = Integer.parseInt(request.getParameter("pid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		int quantity = Integer.parseInt(request.getParameter("number"));
		

		ProductDAO productdao = new ProductDAO();
		Product product = productdao.getProductById(pid);
		
		double totalAmount = quantity*product.getPrice();
		CartDAO cartdao = new CartDAO();
		cartdao.updateProduct(pid, cid, quantity, totalAmount);
		String str = "Updated Sucessfully!";
		
		request.setAttribute("cid", cid);
		request.setAttribute("str", str);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.do");
        requestDispatcher.forward(request, response);
      
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
