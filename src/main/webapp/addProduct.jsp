<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Product"%>
<%@page import="model.ProductDAO"%>
<%@ page import = "java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/header.jsp"%>
<title>Insert title here</title>
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
					<li class="nav-item"><a class="nav-link" onclick="history.back()">All Products</a></li>
					
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Add Product</div>
			<div class="card-body">
				<form action="add.do" method="post">
					<div class="form-group">
						<label>Product Name</label> <input type="text" name="name"
							class="form-control">
					</div>
					<div class="form-group">
						<label>Price</label> <input type="text" name="price"
							class="form-control" >
					</div>
					<div class="form-group">
						<label>Category</label>
						<select name="category" class="form-control" >
						<option>T-Shirt</option>
						<option>Mobile Cover</option>
						<option>Backpack</option>
						<option>Mugs</option>
						<option>Bobble Head</option>
						<option>Cap</option>
						<option>Accessories </option>
						</select>
					</div>
					<div class="form-group">
						<label>Stock Units</label> <input type="text" name="stockUnits"
							class="form-control" >
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Add Product</button>
					</div>
					

				</form>
			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>