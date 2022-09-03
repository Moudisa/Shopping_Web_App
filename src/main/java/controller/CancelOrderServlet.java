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
import model.Order;
import model.OrderDAO;
import model.ProductDAO;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/cancellorder.do")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		OrderDAO orderdao = new OrderDAO();
		ArrayList<Order> orderList1 = orderdao.getOrderHistory(cid);
		
		for(Order order: orderList1)
		{			
			
			ProductDAO productdao = new ProductDAO();
			if(order.getProductID()==pid)
			{
				productdao.incrementStockUnits(order.getProductID(), order.getQuantity());
			}
			
			
		}
				
		orderdao.cancelOrder(cid,pid);		
		
		String str = "Your order has been cancelled!";
		
		//CartDAO cartdao = new CartDAO();
		
		
		ArrayList<Order> orderList = orderdao.getOrderHistory(cid);
		//int orderTemp = 1;
		//request.setAttribute("orderTemp",orderTemp);
		request.setAttribute("cid",cid);
		request.setAttribute("orderList", orderList);
		request.setAttribute("str", str);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/orders.jsp");
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
