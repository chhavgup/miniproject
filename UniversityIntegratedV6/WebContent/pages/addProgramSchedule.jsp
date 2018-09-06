<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body{
	background-color: #323030;
	color:white;
}

</style>
</head>
<body>
 <%@ include file = "Header.jsp" %>

<h1></h1>
<c:url var = "myAction" value="addProgramSchedule.obj"></c:url>

<form:form method="post" modelAttribute="programScheduledBean" action="${myAction}">

  Enter Program Schedule ID:<form:input path="scheduledProgramID"/>
  <form:errors path="scheduledProgramID" style="color:red"> </form:errors><br/>
  
  Enter Program Name:<form:input path="programName" value="${programName}" readonly="true"/>
  <form:errors path="programName" style="color:red"> </form:errors><br/>
  
  Enter Location:<form:input path="location"/>
  <form:errors path="location" style="color:red"> </form:errors><br/>
  
  Enter Start date:<form:input path="startDate" placeholder = "dd/mm/yyyy"/>
  <form:errors path="startDate" style="color:red" ></form:errors><br/>
  
  Enter End Date:<form:input path="endDate" placeholder = "dd/mm/yyyy"/>
  <form:errors path="endDate" style="color:red"></form:errors> <br/>
  
  Enter Sessions Per Week:<form:input path="sessionsPerWeek" placeholder = "i.e 1  2  3"/>
  <form:errors path="sessionsPerWeek" style="color:red"></form:errors><br/>
  
  <input type="submit" value="Add"/>
</form:form>

<c:if test="${programAdded ne null}" >
	<h2>Program schedule added successfully.</h2> <br>
</c:if>
<a href="adminHome.obj">Go to home page</a>

</body>
</html>