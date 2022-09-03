<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/header.jsp"%>
<title>Insert title here</title>
<style>
body {
  background-image: url('abc3.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;  
  background-size: cover;
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
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				
				<li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>		
				<li class="nav-item"><a class="nav-link" href="adminlogin.jsp">Admin</a></li>						
			</ul>
		</div>
	</div>
</nav>

 <!--  <img src="abc3.jpg" width = 100% height = 100%> -->


<%@include file="/includes/footer.jsp"%>
</body>
</html>