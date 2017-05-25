<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Person Management</title>
		<jsp:include page="layout.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>	
		<div id="persons">
				<h3>Person Management</h3>
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>Phone</th>
							<th>Email</th>
							<th>City</th>
							<th>State</th>
							<th>ZipCode</th>
							<th>Country</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="person" items="${persons}">
							<tr>
								<td>${person.id}</td>
								<td>${person.firstName}</td>
								<td>${person.lastName}</td>
								<td>${person.phone}</td>
								<td>${person.email}</td>
								<td>${person.address.city}</td>
								<td>${person.address.state}</td>
								<td>${person.address.zipcode}</td>
								<td>${person.address.country}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a href="/person/add" id="addLnk" class="btn btn-success">Add Person</a>
		</div>
	</body>
</html>