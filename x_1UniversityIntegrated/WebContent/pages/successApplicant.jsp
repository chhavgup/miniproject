<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
body{
	background-color: #323030;
	color:white;
}

</style>
</head>
<body>
 <%@ include file = "Header.jsp" %>

<h2>Congratulations. You are Successfully Registered!!!!</h2>
  <h2>Your Details</h2>  
                                                                       
         
  <table class="table">
    <thead>
      <tr>
      		<th bgcolor="#c2c2d6">Application Id</th>
			<th bgcolor="#c2c2d6">Full Name</th>
			
			<th bgcolor="#c2c2d6">Status</th>
      </tr>
      </thead>
      <tbody>	
		<tr>
			<td> ${applicant.applicationId}</td>
			<td> ${applicant.fullName}</td>
			
			<td> ${applicant.status}</td>
		</tr>
		</tbody>
		
      </table>
   <br>
  <br>
<br>
 <br>
 <br>
  <%@ include file = "Footer.jsp" %>
      
</body>
</html>

<%-- <h1> your details </h1>
<h1><c:out value="${applicant.applicationId }"></c:out></h1>
<h2><c:out value="${applicant.fullName }"></c:out></h2>
<h3><c:out value="${applicant.dateOfBirth }"></c:out></h3>
<h3><c:out value="${ applicant.status}"></c:out></h3>
<a href="showHomePage.obj">GO BACK</a> --%>