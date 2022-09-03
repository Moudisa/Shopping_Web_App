<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Product"%>
<%@page import="model.ProductDAO"%>
<%@page import="model.Customer"%>
<%@page import="model.Cart"%>
<%@page import="model.Order"%>
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
<% int cid = (int)request.getAttribute("cid"); %>
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
					<li class="nav-item"><a class="nav-link" href="logout.do">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="center">
		<h5>Order History</h5>
	</div>
	<br>
	<br>



	<table>
		<tr>
			<th>Product Name</th>
			<th>Price</th>
			<th>Category</th>
			<th>Quantity</th>
			<th>Total Amount</th>
			<th>Action</th>
		</tr>
		<%
		    //response.setIntHeader("Refresh", 1);
		   // int orderTemp = (int)request.getAttribute("orderTemp");
		   
			ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orderList");
			String str = (String) request.getAttribute("str");
			//int cartSize = (int) request.getAttribute("cartSize");
			
			for (Order order : orderList) {
				ProductDAO productdao = new ProductDAO();
				Product product = productdao.getProductById(order.getProductID());
			%>

		<tr>

			<td><%=product.getName()%></td>
			<td><%=product.getPrice()%></td>
			<td><%=product.getCategory()%></td>
			<td><%=order.getQuantity()%></td>
			<td><%=order.getTotalAmount()%></td>
			<td><a class="nav-link"
				href="cancellorder.do?pid=<%=order.getProductID()%>&cid=<%=order.getCustomerID()%>">CANCEL ORDER</a></td>
		</tr>

		<%
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