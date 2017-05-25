<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home Page</title>
		<jsp:include page="layout.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>	
		<div id="home">
			<div class="header_modules">
				<div class="parallax">
					<div>
						<div class="container">
							Start your day with the best cup of coffee
						</div>
					</div>
				</div>
			</div>
				<div id="breakfast">
					<h3>BREAKFAST</h3>
					<div class="content">
						<c:forEach items="${breakfastProducts}" var="product" varStatus="loop">
							<div class="item">
								<c:choose>
									<c:when test="${loop.count == 1}">
										<img src="../resources/image/product1.png"/>
									</c:when>
									<c:when test="${loop.count == 2}">
										<img src="../resources/image/product2.png"/>
									</c:when>
									<c:when test="${loop.count == 3}">
										<img src="../resources/image/product3.png"/>
									</c:when>
									<c:when test="${loop.count == 4}">
										<img src="../resources/image/product4.png"/>
									</c:when>
									<c:otherwise>
										<img src="../resources/image/product1.png"/>
									</c:otherwise>
								</c:choose>
								<div class="caption">
									<div class="name">${product.productName}</div>
									<div class="price">$${product.price}</div>
									<sec:authorize access="hasRole('USER')">
										<a class="btn btn-primary" href="product/addToCart?productId=${product.id}">Add To Cart</a>
									</sec:authorize>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div id="lunch">
					<h3>LUNCH</h3>
					<div class="content">
						<c:forEach items="${lunchProducts}" var="product" varStatus="loop">
							<div class="item">
								<c:choose>
									<c:when test="${loop.count == 1}">
										<img src="../resources/image/product5.png"/>
									</c:when>
									<c:when test="${loop.count == 2}">
										<img src="../resources/image/product6.png"/>
									</c:when>
								</c:choose>
								<div class="caption">
									<div class="name">${product.productName}</div>
									<div class="price">$${product.price}</div>
									<sec:authorize access="hasRole('USER')">
										<a class="btn btn-primary" href="product/addToCart?productId=${product.id}">Add To Cart</a>
									</sec:authorize>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div id="dinner">
					<h3>DINNER</h3>
					<div class="content">
						<c:forEach items="${dinnerProducts}" var="product" varStatus="loop">
							<div class="item">
								<c:choose>
									<c:when test="${loop.count == 1}">
										<img src="../resources/image/product7.png"/>
									</c:when>
									<c:when test="${loop.count == 2}">
										<img src="../resources/image/product8.png"/>
									</c:when>
									<c:when test="${loop.count == 3}">
										<img src="../resources/image/product9.png"/>
									</c:when>
									<c:when test="${loop.count == 4}">
										<img src="../resources/image/product10.png"/>
									</c:when>
									<c:when test="${loop.count == 5}">
										<img src="../resources/image/product11.png"/>
									</c:when>
								</c:choose>
								<div class="caption">
									<div class="name">${product.productName}</div>
									<div class="price">$${product.price}</div>
									<sec:authorize access="hasRole('USER')">
										<a class="btn btn-primary" href="product/addToCart?productId=${product.id}">Add To Cart</a>
									</sec:authorize>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
	</body>
</html>