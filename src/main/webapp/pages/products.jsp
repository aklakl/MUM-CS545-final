<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product Management</title>
		<jsp:include page="layout.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>	
		<div id="products">
				<h3>Product Management</h3>
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Product Name</th>
							<th>Price</th>
							<th>Description</th>
							<th>Type</th>
							<sec:authorize access="hasRole('ADMIN')">
								<th colspan="2" id="action">Action</th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${products}">
							<tr>
								<td>${product.id}</td>
								<td>${product.productName}</td>
								<td>${product.price}</td>
								<td>${product.description}</td>
								<td>${product.productType}</td>
								<sec:authorize access="hasRole('ADMIN')">
									<td><a href="/product?action=update&productId=${product.id}" id="updateLnk" class="btn btn-info">Update</a></td>
									<td><a href="/product?action=delete&productId=${product.id}" id="deleteLnk" class="btn btn-danger">Delete</a></td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<sec:authorize access="hasRole('ADMIN')">
					<a href="/product/add" id="addLnk" class="btn btn-success">Add Product</a>
				</sec:authorize>
		</div>
	</body>
</html>