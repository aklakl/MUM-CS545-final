<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cart</title>
		<jsp:include page="layout.jsp"></jsp:include>
	</head>
	<body>
		<div id="shoppingCart">
			<div class="orderList">
				<h1>Shopping Cart</h1>
				<c:forEach items="${sessionScope['cart'].products}" var="product">
					<div class="goodCheckout">
						<a><img alt="productImage"
							src="<c:url value="resources/image/product1.png"/>"></a>
						<h3>${product.productName}</h3>
						<p>$${product.price}</p>
						<a class="delete"
							href="<c:url value='order?action=delete&productId=${product.id}'/>">Delete</a>
					</div>
				</c:forEach>
			</div>
			<div class="total">
				Sub total (${sessionScope['cart'].size} item(s)):
				<span style="font-weight: 700; color: #b12704; font-size: 20px;">${sessionScope['cart'].totalMoney}$</span>
			</div>
			<div>
				<c:if test="${not empty sessionScope['cart']}">
					<a href="/order/checkout" id="checkoutBtn">Checkout</a>
				</c:if>
			</div>
		</div>
	</body>
</html>