<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet" href="<c:url value="/ressources/style/tabac-dashboard.css"/>" />
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
<div
	class="dashboard container-fluid d-flex flex-column conteneurbis col-12 justify-content-around align-items-center">
	<div class="testemony text-center col-xl-7 col-sm-12 jumbotron jumbotron-form">	
	<h2 class="text-center">
		<spring:message code="testemonies.title" />
	</h2>
	<br />
	<h3>
		<c:out value="${ selectedTestemonyTitle }"></c:out>
	</h3>
	<br />
	<p class="text-left">
		<c:out value="${ selectedTestemonyContent }"></c:out>
	</p>
	<br />
		<p class="text-right small">
		<c:out value="${ selectedTestemonyPseudo }"></c:out> , <c:out value="${ selectedTestemonyDate }"></c:out>
	</p>
	<br />
	</div>
	<c:if test="${ canSpeak }">
		<div class="row d-flex flex-column justify-content-center align-items-center">
		<p><i><i><spring:message code="testemonies.canSpeak" /></i></p>
			<a class="btn btn-outline-success btn-lg" href="./testemony" role="button"><spring:message code="testemonies.lblButton" /></a>
		</div>
	</c:if>
	</div>
	</div>
</body>
