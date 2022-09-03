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
import model.Product;
import model.ProductDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
		String str = (String)request.getAttribute("str");
		if(cartList.size()<1)
		{
			 str = "Your cart is empty!";
		}
			
		int cartSize = 1;
		request.setAttribute("cartSize", cartSize);
		request.setAttribute("cartList", cartList);
		request.setAttribute("str", str);
		request.setAttribute("cid", cid);
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
