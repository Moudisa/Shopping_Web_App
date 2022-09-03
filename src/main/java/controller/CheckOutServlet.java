package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.CartDAO;
import model.Customer;
import model.CustomerDAO;
import model.Order;
import model.OrderDAO;
import model.Product;
import model.ProductDAO;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/checkOut.do")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		
		CartDAO cartdao = new CartDAO();
		ArrayList<Cart> cartList = cartdao.allProductsInCart(cid);
		
		OrderDAO orderdao = new OrderDAO();
		double bill = 0;
		for(Cart cart: cartList)
		{
			
			Order order = new Order(cart.getCustomerID(), cart.getProductID(), cart.getQuantity(), cart.getTotalAmount());
			orderdao.checkOut(order);
			ProductDAO productdao = new ProductDAO();
			productdao.decrementStockUnits(cart.getProductID(), cart.getQuantity());
			bill = bill + cart.getTotalAmount();
		}
		
		cartdao.emptyCart(cid);
		
		
		int cartSize = 0;
		String str = "Order Placed Sucessfully! Your Billing amount is: " + bill + " INR.";
		request.setAttribute("str", str);
		request.setAttribute("cid", cid);
		request.setAttribute("cartSize", cartSize);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cart.jsp");
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
