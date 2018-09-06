<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
.error {
color: red;
font-style: italic;
}


body{
	background-color: #323030;
	color: white;
}



</style>
</head>
<body>

<%@ include file = "Header.jsp" %>
 <br>
<center>
<h1>Register Page</h1>

<c:url var="myAction" value="checkRegister.obj" />

<form:form method="post" modelAttribute="applicant" action="${myAction}">
<table>
<tr><td>
  Enter Full Name:</td><td>
  <form:input path="fullName"/><form:errors path="fullName" cssClass="error"></form:errors></td></tr>

  <tr><td> Enter Date Of Birth ("dd/MM/yyyy"):</td><td>
  <form:input    path="dateOfBirth"/><br/><form:errors path="dateOfBirth" cssClass="error"></form:errors>
</td></tr>
  <tr><td> Enter your highest qualification:</td><td>
  <form:input path="highestQualification"/><form:errors path="highestQualification" cssClass="error"></form:errors>
</td></tr>
 <tr><td> Enter your marks obtained:</td><td>
  <form:input path="marksObtained"/>
 <br>
  <tr><td> Enter your goals:</td><td>
  <form:input path="goals"/>
 </td></tr>
  <tr><td> Enter your Email Id:</td><td>
  <form:input path="emailID"/>
 </td></tr>
  
<tr><td> Select Your Scheduled Id:</td><td>
  
  <form:select path="scheduledProgramID"> 
	<form:option value="" label="Please Select"/>
	<form:options items="${ids}" />
  </form:select></td></tr>
 </table>
 

 

  <input type="submit" value="Add"/>
  
</form:form>
<center>
<br>
  <br>
  <br>
  <br>
 <br>
 <br>
    <%@ include file = "Footer.jsp" %>
</body>
</html>