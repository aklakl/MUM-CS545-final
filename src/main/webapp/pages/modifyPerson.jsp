<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modidy Person</title>
		<jsp:include page="layout.jsp"></jsp:include>
	</head>
	<body>
		<div id="modifyPerson">
			<h3>Add Or Update Person</h3>
			<c:choose>
				<c:when test='${person.id ==0}'><c:url var="personACtion" value="modify" /></c:when>
				<c:otherwise><c:url var="personACtion" value="person/modify" /></c:otherwise>
			</c:choose>
			<form:form modelAttribute="person" action="modify" method="post">
				<form:errors path="*" cssClass="error" element="div" />
					<div id="group1">
						<form:hidden path="id"/>
						
						<div class="form-group">
							<form:label path="firstName">
								<spring:message text="FirstName"/>
							</form:label>
							<form:input path="firstName" id="firstName" class="form-control" cssErrorClass="form-control errorInput"/>
							<form:errors path="firstName" cssClass="error"/>
						</div>
						
						<div class="form-group">
							<form:label path="lastName">
								<spring:message text="LastName"/>
							</form:label>
							<form:input path="lastName" id="lastName" class="form-control" cssErrorClass="form-control errorInput"/>
							<form:errors path="lastName" cssClass="error"/>
						</div>
						
						<div class="form-group">
							<form:label path="phone">
								<spring:message text="Phone"/>
							</form:label>
							<form:input path="phone" id="phone" class="form-control" cssErrorClass="form-control errorInput"/>
							<form:errors path="phone" cssClass="error"/>
						</div>
						<div class="form-group">
							<form:label path="email">
								<spring:message text="Email"/>
							</form:label>
							<form:input path="email" id="email" class="form-control" cssErrorClass="form-control errorInput"/>
							<form:errors path="email" cssClass="error"/>
						</div>
					</div>
					<div id="group2">
						
						<div class="form-group">
							<form:label path="address.city">
								<spring:message text="City"/>
							</form:label>
							<form:input path="address.city" id="city" class="form-control" cssErrorClass="form-control errorInput"/>
							<form:errors path="address.city" cssClass="error"/>
						</div>
						
						<div class="form-group">
							<form:label path="address.state">
								<spring:message text="State"/>
							</form:label>
							<form:input path="address.state" id="state" class="form-control" cssErrorClass="form-control errorInput"/>
							<form:errors path="address.state" cssClass="error"/>
						</div>
						
						<div class="form-group">
							<form:label path="address.zipcode">
								<spring:message text="ZipCode"/>
							</form:label>
							<form:input path="address.zipcode" id="zipcode" class="form-control" cssErrorClass="form-control errorInput"/>
							<form:errors path="address.zipcode" cssClass="error"/>
						</div>
						
						<div class="form-group">
							<form:label path="address.country">
								<spring:message text="Country"/>
							</form:label>
							<form:input path="address.country" id="country" class="form-control" cssErrorClass="form-control errorInput"/>
							<form:errors path="address.country" cssClass="error"/>
						</div>
					</div>
				<div id="group3">
					<c:choose>
						<c:when test="${person.id > 0}">
							<button type="submit" class="btn btn-success">Update</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-success">Insert</button>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${person.id > 0}">
							<a href="/home" class="btn btn-info">Back</a>
						</c:when>
						<c:otherwise>
							<a href="list" class="btn btn-info">Back</a>
						</c:otherwise>
					</c:choose>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
	 					value="${_csrf.token}"/>
			</form:form>
		</div>
	</body>
</html>