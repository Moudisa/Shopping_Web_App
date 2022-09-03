<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Product"%>
<%@page import="model.ProductDAO"%>
<%@page import="model.Customer"%>
<%@page import="model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/header.jsp"%>
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

tr {
	border-bottom: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #D6EEEE;
}

.center {
	margin: auto;
	width: auto;
	border: 5px solid #FFC0CB;
	padding: 10px;
	text-align: center;
}

.button {
	border: none;
	color: white;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	cursor: pointer;
}

.button1 {
	background-color: white;
	color: black;
	border: 2px solid #FFC0CB;
}

.button1:hover {
	background-color: #FFC0CB;
	color: white;
}
</style>
</head>
<body>
<%int cid = (int)request.getAttribute("cid"); %>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">Anime Merchandise</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link"
						href="login.do?temp=1&cid=<%=cid%>">All Products</a></li>
					<li class="nav-item"><a class="nav-link"
						href="checkOut.do?cid=<%=cid%>">Check
							Out</a></li>
					<li class="nav-item"><a class="nav-link" href="logout.do">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="center">
		<h5>All Products in Cart</h5>
	</div>
	<br>
	<br>



	<table>
		<tr>
			<th>Product Name</th>
			<th>Price</th>
			<th>Category</th>
			<th>Quantity</th>
			<th>Edit Quantity</th>
			<th>Submit</th>
			<th>Total Amount</th>
			<th>Action</th>
		</tr>
		<%
			ArrayList<Cart> cartList = (ArrayList<Cart>) request.getAttribute("cartList");
			String str = (String) request.getAttribute("str");
			int cartSize = (int) request.getAttribute("cartSize");
			if(cartSize == 0)
			{
				
			}
			else
			{
			for (Cart cart : cartList) {
				ProductDAO productdao = new ProductDAO();
				Product product = productdao.getProductById(cart.getProductID());
			%>

		<tr>

			<td><%=product.getName()%></td>
			<td><%=product.getPrice()%></td>
			<td><%=product.getCategory()%></td>
			<td><%=cart.getQuantity()%></td>
			<form
				action="increment.do?pid=<%=cart.getProductID()%>&cid=<%=cart.getCustomerID()%>"
				method="post">
				<td><select name="number">
						<%
						for (int i = 1; i < 10; i++) {
						%>
						<option value="<%=i%>"><%=i%></option>
						<%
						}
						%>
				</select></td>
				<td><button type="submit" class="button button1">+</button></td>
			</form>

			<td><%=cart.getTotalAmount()%></td>
			<td><a class="nav-link"
				href="deleteFromCart.do?pid=<%=cart.getProductID()%>&cid=<%=cart.getCustomerID()%>">DELETE</a></td>
		</tr>

		<%
			}
			}
			%>
	</table>

	<%
	if (str != null) {
	%>
	<div class="center">
		<%=str%>
	</div>
	<%
	}
	str = null;
	%>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>