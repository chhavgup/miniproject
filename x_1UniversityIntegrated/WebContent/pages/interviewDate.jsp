<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Interview</title>
	<style>
body{
	background-color: #323030;
	color:white;
}

</style>
</head>
<body>
 <%@ include file = "Header.jsp" %>

		<div>
			<form action="interview.obj" method="post">
				<h2>Confirm Interview </h2>
				Application ID: <input type="text" name="appId" value="${applicant}">    
				Interview date: <input type="date" name="dateOfInterview">    
				<h5><input type="submit" value="Interview"></h5>
			</form>
        </div>
		<a href="pages/MACHome.jsp">Homepage</a>
		 <br>
  <br>
<br>
 <br>
 <br>
  <%@ include file = "Footer.jsp" %>
	</body>
</html>