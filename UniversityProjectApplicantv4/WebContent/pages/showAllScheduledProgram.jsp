<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Programs</title>
</head>
<body>
<table border=1>
<tr>
		<th bgcolor="bisque">Program Id</th>
		<th bgcolor="bisque">Program Name</th>
		<th bgcolor="bisque">Location</th>
		<th bgcolor="bisque">Start Date</th>
		<th bgcolor="bisque">End Date</th>
		<th bgcolor="bisque">Sessions Per Week</th>
</tr>
<c:forEach var="scheduleList" items="${scheduleList}">
<tr>
			<td>${scheduleList.scheduledProgramID}</td>
			<td>${scheduleList.programName}</td>
		   <td>${scheduleList.location}</td>
		   <td>${scheduleList.startDate}</td>
		 <td>${scheduleList.endDate}</td>
			<td>${scheduleList.sessionsPerWeek}</td>
			
</tr>
</c:forEach>
</table>
</body>
</html>