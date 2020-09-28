<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Merkle Application</title>
</head>
<body>
    <center>
        <h1>Admin Management</h1>
        <h2>
            <a href="new">Add New People</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All People</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of People</h2></caption>
            <tr>
                <th>ID</th>
                <th>firstName</th>
                <th>lastName</th>
                <th>address1</th>
                <th>address2</th>
                <th>City</th>
                <th>State</th>
                <th>Zip</th>
                <th>Country</th>
                <th>Date</th>
            </tr>
            <c:forEach var="people" items="${listPeople}">
                <tr>
                    <td><c:out value="${people.id}" /></td>
                    <td><c:out value="${people.firstName}" /></td>
                    <td><c:out value="${people.lastName}" /></td>
                    <td><c:out value="${people.address1}" /></td>
                    <td><c:out value="${people.address2}" /></td>
                    <td><c:out value="${people.city}" /></td>
                    <td><c:out value="${people.state}" /></td>
                    <td><c:out value="${people.zip}" /></td>
                    <td><c:out value="${people.country}" /></td>
                    <td><c:out value="${people.date}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${people.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${people.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>