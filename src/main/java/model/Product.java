package model;

public class Product {

	int productID;
	String name;
	double price;
	String category;
	int stockUnits;
	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int i) {
		this.productID = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStockUnits() {
		return stockUnits;
	}

	public void setStockUnits(int stockUnits) {
		this.stockUnits = stockUnits;
	}

	
	
	public Product()
	{
		super();
	}
	
	public Product(int productID, String name, double price, String category, int stockUnits )
	{
		this.productID = productID;
		this.name = name;
		this.price = price;
		this.category = category;
		this.stockUnits = stockUnits;
	}
	
	
}

