<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Status</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
a {
	color: #ffb3b3
}

h1 {
	color: #c2c2d6
}

body {
	background-color: #323030
}

.container {
	margin: 5% 0 0 8%;
}

.footer {
	position: fixed;
	color: #f2f2f2;
	width: 50%;
	margin: 38% 40% 0;
}

.navbar li a, .navbar .navbar-brand {
	color: #d5d5d5 !important;
}

.navbar {
	font-family: Montserrat, sans-serif;
	margin-bottom: 0;
	background-color: #2d2d30;
	border: 0;
	font-size: 11px !important;
	letter-spacing: 4px;
	opacity: 0.9;
}

.navbar-nav li a:hover {
	color: #fff !important;
}

.navbar-nav li.active a {
	color: #fff !important;
	background-color: #29292c !important;
}

.navbar-default .navbar-toggle {
	border-color: transparent;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar"></button>
			<a class="navbar-brand" href="#"> UNIVERSITY</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="black.jsp">BACK</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container">
		<center>
		<c:set var="status1" value="Awaited"/>
		<c:set var="status" value="confirmed"/>
		<c:set var="status2" value="accepted"/>
		<c:if test="${ applicant.status eq status }">
			  <h1>Application Confirmed!!</h1>
			</c:if>
		<c:if test="${ applicant.status eq status2 }">
			  <h1>Application Accepted!!</h1>
			  <br>
			  <h1>Interview Date : ${applicant.dateOfInterview} </h1>
			</c:if>	
			
		<c:if test="${applicant.status eq status1 }">
			  <h1>Application Awaited!!</h1>
			</c:if>
			
		
			
			
		</center>
	</div>
	<div class="footer">
		<h5>&#9400; 2017 CAPGEMINI PRIVATE LIMITED</h5>
	</div>

</body>
</html>