<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet"
	href="<c:url value="/ressources/style/tabac-dashboard.css" />" />
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
	<div
	class="dashboard container-fluid d-flex flex-column conteneurbis col-12 justify-content-around align-items-center">
	<div class="container-fluid col-12 d-flex justify-content-center">
		<div class="jumbotron jumbotron-form col-5">
			<h1 class="text-center">
				<spring:message code="testemony-form.title" />
			</h1>
			<form:form action="testemony/save" method="post" modelAttribute="testemony" class="jumbotron flex-column justify-content-center">
				<form:hidden path="idTemoignage" />
				<form:label path="title" class="form-group row">
				<spring:message code="testemony-form.lblTitle" />
				</form:label>
				<form:input path="title" class="form-control" />
				<br />
				<form:label path="content" class="form-group row">
				<spring:message code="testemony-form.lblContent" />
				</form:label>
				<form:textarea path="content" cols="50" rows="5"
					class="form-control" />
				<br />
				<form:hidden path="problemTypes" /> 
				<br />
				<input type="submit" value="Envoyer" class="row btn btn-outline-primary" />
			</form:form>
			<div class="row d-flex justify-content-center">
				<a class="btn btn-outline-dark" href="./testemonies" role="button"><spring:message code="testemony-form.lblButtonBack" /></a>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>