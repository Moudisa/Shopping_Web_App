package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.CustomerDAO;
import model.Order;
import model.OrderDAO;
import model.Product;
import model.ProductDAO;

/**
 * Servlet implementation class OrdersServlet
 */
@WebServlet("/orders.do")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		OrderDAO orderdao = new OrderDAO();
		ArrayList<Order> orderList = new ArrayList<Order>();
		orderList = orderdao.getOrderHistory(cid);
		
		String str = null;
		
		if(orderList.size()<1)
		{
			 str = "You haven't placed any orders yet.";
		}
			
		//int cartSize = 1;
		//request.setAttribute("cartSize", cartSize);
		request.setAttribute("orderList", orderList);
		request.setAttribute("str", str);
		request.setAttribute("cid", cid);
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
