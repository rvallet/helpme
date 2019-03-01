<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet"
	href="<c:url value="/ressources/style/tabac-dashboard.css" />" />
	<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div
	class="dashboard container-fluid d-flex flex-column conteneurbis col-12 justify-content-around align-items-center">
<h1>
	<spring:message code="addiction.testemony-success.title" />
</h1>
<p>
	<spring:message code="addiction.testemony-success.msg" />
</p>
<br />
<div class="row d-flex justify-content-center">
	<a class="btn btn-outline-success btn-lg" href="./" role="button">Retour</a>
</div>
</div>
</div>