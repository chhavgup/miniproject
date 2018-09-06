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
</style>
<body>

<h1>Register Page</h1>

<c:url var="myAction" value="checkRegister.obj" />

<form:form method="post" modelAttribute="applicant" action="${myAction}">
  Enter Full Name:
  <form:input path="fullName"/><form:errors path="fullName" cssClass="error"></form:errors>
 <br>
   Enter Date Of Birth ("dd/MM/yyyy"):
  <form:input    path="dateOfBirth"/><br/><form:errors path="dateOfBirth" cssClass="error"></form:errors>
 <br>
   Enter your highest qualification:
  <form:input path="highestQualification"/><form:errors path="highestQualification" cssClass="error"></form:errors>
 <br>
  Enter your marks obtained:
  <form:input path="marksObtained"/>
 <br>
   Enter your goals:
  <form:input path="goals"/>
 <br>
   Enter your Email Id:
  <form:input path="emailID"/>
  <br>
  
 Select Your Scheduled Id:
   <form:input path="scheduledProgramID"/>
 

  <br/>

  <input type="submit" value="Add"/>
</form:form>

</body>
</html>