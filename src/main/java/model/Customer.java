package model;

public class Customer {
	
	int customerID;
	String username;
	String password;
	int number;
	int pincode;



	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Customer()
	{
		super();
	}
	
	public Customer(String username, String password, int number, int pincode)
	{
		//this.customerID = customerID;
		this.username = username;
		this.password = password;	
		this.number = number;
		this.pincode = pincode;
	}
}
