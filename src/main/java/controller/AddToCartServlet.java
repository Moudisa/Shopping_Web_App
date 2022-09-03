package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartDAO;
import model.Customer;
import model.CustomerDAO;
import model.Product;
import model.ProductDAO;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/addtocart.do")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
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
					
		int quantity = 1;
		double totalAmount = 0;
		String str = null;
				
		ProductDAO productdao = new ProductDAO();
		ArrayList<Product> productList = new ArrayList<Product>();
		productList = productdao.allProducts();
		
		CustomerDAO customerdao = new CustomerDAO();	
		Customer customer = customerdao.getCustomerById(cid);
		
		
		CartDAO cartdao = new CartDAO();
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		cartList = cartdao.allProductsInCart(cid);	
		
		
		if(cartList.size()<1)
		{
			for(Product product : productList) 
			{
				if(product.getProductID() == pid)
					totalAmount = product.getPrice();
				
			}
			
			Cart cart = new Cart(pid, cid, quantity, totalAmount);
			cartdao.addToCart(cart);
			str = "Product Added To Cart Sucessfully!";
		}
		else
		{
			for(Cart cart : cartList)
			{			
						if(pid == cart.getProductID())
						{						
							str = "Product Already Exists In Cart!";
							//productExist = true;
						}
						else
						{
							for(Product product : productList) 
							{
								if(pid == product.getProductID())
								{
									totalAmount = product.getPrice();
								}																				
								
							}
							
							Cart cart1 = new Cart(pid, cid, quantity, totalAmount);
							cartdao.addToCart(cart1);
							str = "Product Added To Cart Sucessfully!";
							//productExist = true;
						}
						//cartExist = true;				
				
			}
		}
		
				
		
		
		
		//str = "Nothing";
		
		request.setAttribute("productList", productList);
		request.setAttribute("customer", customer);
		request.setAttribute("str", str);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewProductsToCustomer.jsp");
        requestDispatcher.forward(request, response);
		
		/*
		for(Customer customer : customerList)
		{
			if(customer.getCustomerID() == cid)
			{
				for (Product product : productList) 
				{
					if(product.getProductID() == pid)
					{
						quantity = 1;
						totalAmount = product.getPrice()*quantity;
					}
				}
			}
		}
		
		
		
		//double totalAmount = (product.getPrice())*quantity;
		Cart cart = new Cart(customerID, productID, quantity, totalAmount);
		/*cart.setProductID(productID);
		cart.setCustomerID(customerID);
		cart.setQuantity(quantity);
		
		CartDAO cartdao = new CartDAO();
		cartdao.addToCart(cart);
		
		 ArrayList<Cart> cartList = cartdao.allProductsInCart();
		 List<Cart> cartProduct = null;
		 if (cartList != null) {			
				try {
					cartProduct = productdao.getCartProducts(cartList);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					totalAmount = productdao.getTotalCartPrice(cartList);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				request.setAttribute("total", totalAmount);
				request.setAttribute("cart_list", cartList);
				}
		
		
									
			String str = "Product Added to Cart Sucessfully!";
			request.setAttribute("str", str);
			request.setAttribute("cartList", cartList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cart.jsp");
	        requestDispatcher.forward(request, response);
	        
	        */
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
