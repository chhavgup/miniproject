<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
           
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding Programs Offered Page</title>
</head>
<body>

<h1>Add Program Details:</h1>
<c:url var="myAction" value="validateProgramOfferedDetails.obj" />

<form:form method="post" modelAttribute="programOfferedBean" action="${myAction}">
<table>
<tr>
	<td>  Enter Program Name:</td>
	<td><form:input path="programName"    />
    	<form:errors path="programName" style="color:red;size:12px " />
	</td>
</tr>
<tr>
	<td>Enter Description:</td>
  	<td><form:input path="description"/>
    	<form:errors path="description" style="color:red" />
	</td>
</tr>  
<tr>
	<td>Enter Applicant Eligibility:</td>
  	<td><form:input path="applicantEligibility"/>
    	<form:errors path="applicantEligibility" style="color:red" />
  	</td>
</tr>
<tr>
	<td>Enter Duration:(In months)</td>
  	<td><form:input path="duration"/>
    	<form:errors path="duration" style="color:red" />
	</td>
</tr>
<tr>
	<td>Enter Degree Certificate Offered:</td>
  	<td><form:input path="degreeCertificateOffered"/>
    	<form:errors path="degreeCertificateOffered" style="color:red" />
  	</td>
</tr>
<tr>  
	<td><input type="submit" value="Add Program"/></td>
</tr>
  
</table>
</form:form>
<br>
 <c:if test="${programName ne null}">
			<div>
			Program ${programName} is addded successfully.
			</div>
</c:if>

<br>
<br>
<a href="adminHome.obj">Go to Admin Home Page</a>
</body>
</html>