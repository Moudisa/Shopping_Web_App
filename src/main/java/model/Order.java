package model;

public class Order extends Cart {
	int quantity;
	double totalAmount;
	int customerID;
	int productID;
	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Order()
	{
		
	}
	
	public Order(int customerID, int productID, int quantity, double totalAmount)
	{
		this.customerID = customerID;
		this.productID = productID;		
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}
}
