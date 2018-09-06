<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update/Delete Programs Offfered Page</title>
<style type="text/css">
table{
	border: 2px solid black;
}
tr,th,td{
	border:1px solid black;
}



body{
	background-color: #323030;
	color:white;
}

</style>
</head>
<body>
 <%@ include file = "Header.jsp" %>


<h1> List of Programs Offered:</h1>
		<c:if test="${list ne null}">
			<table>
				<tr>
					<th>Program Name</th>
					<th>Description</th>
					<th>Application Eligibility</th>
					<th>Duration of course(Months)</th>
					<th>Degree certificate offered</th>
				</tr>
				<c:forEach items="${list}" var="program">
					<tr>
						<td>${program.programName}</td>
						<td>${program.description}</td>
						<td>${program.applicantEligibility}</td>
						<td>${program.duration}</td>
						<td>${program.degreeCertificateOffered}</td>
						<td><a href="update.obj?programName=${program.programName }">update</a></td>
						<td><a href="delete.obj?programName=${program.programName }">delete</a></td>
						
						
					</tr>
				</c:forEach>
			</table>
		</c:if>
<a href="adminHome.obj">Go to home page</a>

 <br>
  <br>
<br>
 <br>
 <br>
  <%@ include file = "Footer.jsp" %>




</body>
</html>