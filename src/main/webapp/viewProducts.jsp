<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Product"%>
<%@page import="model.ProductDAO"%>
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
	border: 5px solid #D6EEEE;
	padding: 10px;
	text-align: center;
}
</style>
</head>
<body>
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
					<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="addProduct.jsp">Add
							Product</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="center">
		<h3>All Products</h3>
	</div>
	<br>
	<br>



	<table>
		<tr>
			<th>Product ID</th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Category</th>
			<th>Stock Units</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<%
		ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");
		String str = (String)request.getAttribute("str");
		for (Product product : productList) {
		%>
		<tr>
			<td><%=product.getProductID()%></td>
			<td><%=product.getName()%></td>
			<td><%=product.getPrice()%></td>
			<td><%=product.getCategory()%></td>
			<td><%=product.getStockUnits()%></td>
			<td><a href="update.do?id=<%=product.getProductID()%>">UPDATE</a></td>
			<td><a href="delete.do?id=<%=product.getProductID()%>">DELETE</a></td>
		</tr>
		<%
		}
		%>
	</table>

<%if(str != null){ %>
<div class="center">
		<%=str%>
	</div>
<%}str = null; %>
	<%@include file="/includes/footer.jsp"%>
</body>
</html>