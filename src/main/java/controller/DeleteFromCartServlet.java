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

/**
 * Servlet implementation class DeleteFromCartServlet
 */
@WebServlet("/deleteFromCart.do")
public class DeleteFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		CartDAO cartdao = new CartDAO();
		cartdao.deleteProductFromCart(cid, pid);
		
		String str = "Product Deleted Sucessfully from the cart.";
		
		//CartDAO cartdao = new CartDAO();
		ArrayList<Cart> cartList = cartdao.allProductsInCart(cid);
		int cartSize = 1;
		request.setAttribute("cartSize", cartSize);
		request.setAttribute("cartList", cartList);
		request.setAttribute("cid", cid);
		request.setAttribute("str", str);
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
