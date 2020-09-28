<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Merkle Application</title>
</head>
<body>
	
    <div align="center">
       <h3 align="center"> User Table </h3>

	<table border="1" width="70%" align="center">

	<tr>

	<th>firstName</th>
	<th>lastName</th>
	<th>address1</th>
	<th>address2</th>
	<th>city</th>
	<th>state</th>
	<th>ZipcCode</th>
	<th>country</th>
	<th>Date</th>

	</tr>
<c:if test = "${listPeople != null}">
	<c:forEach items="${listPeople}" var="user">

<tr>

<td>${user.firstName }</td>
<td>${user.lastName }</td>
<td>${user.address1 }</td>
<td>${user.address2 }</td>
<td>${user.city }</td>
<td>${user.state }</td>
<td>${user.zip }</td>
<td>${user.country }</td>
<td>${user.date }</td>


</tr>

</c:forEach>
</c:if>

<c:if test = "${listPeople == null}">
<strong>The list is empty! Please Enter Register first!</strong>
</c:if>
</table>
    </div>   
</body>
</html>