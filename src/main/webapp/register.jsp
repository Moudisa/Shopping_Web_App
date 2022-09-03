<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/header.jsp"%>

<title></title>
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
				
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>							
			</ul>
		</div>
	</div>
</nav>
<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Customer Registration</div>
			<div class="card-body">
				<form action="register.do" method="post">
					<div class="form-group">
						<label>User Name</label> <input type="text" name="username"
							class="form-control" placeholder="Enter username">
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password" name="password"
							class="form-control" placeholder="Enter Password">
					</div>
					<div class="form-group">
						<label>Phone Number</label> <input type="text" name="number"
							class="form-control" placeholder="Enter Phone Number">
					</div>					
					<div class="form-group">
						<label>Pin Code</label> <input type="text" name="pincode"
							class="form-control" placeholder="Enter Pincode">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Register</button>
					</div>									
				</form>
			</div>
		</div>
	</div>

<%@include file="/includes/footer.jsp"%>
</body>
</html>