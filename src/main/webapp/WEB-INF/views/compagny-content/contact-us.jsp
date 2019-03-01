<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
	<div class="container-fluid col-12 d-flex justify-content-center">
		<div class="jumbotron jumbotron-form col-5">
			<h1 class="text-center">
				<spring:message code="contact.title" />
			</h1>
			<br />
			<form:form method="post" action="send" modelAttribute="contact-form">
				<form:label path="name" class="form-group row">
					<spring:message code="contact.lblName" />
					<spring:message code="contact.phName" var="phName" />
					<form:input path="name" placeholder="${phName}"
						class="form-control" />
				</form:label>
				<td><form:errors path="name" cssClass="error" /></td>
				<br />
				<form:label path="email" class="form-group row">
					<spring:message code="contact.lblEmail" />
					<spring:message code="contact.phEmail" var="phEmail" />
					<form:input path="email" placeholder="${phEmail}"
						class="form-control" />
				</form:label>
				<td><form:errors path="email" cssClass="error" /></td>
				<br />
				<form:label path="type" class="form-group row">
					<spring:message code="contact.lblDemande" />
					<form:select path="type" class="custom-select">
						<form:options items="${TypeDemande}" itemLabel="name" />
					</form:select>
				</form:label>
				<br />
				<form:label path="message" class="form-group row">
					<spring:message code="contact.lblMessage" />
					<spring:message code="contact.phMessage" var="phMessage" />
					<form:textarea placeholder="${ phMessage}" path="message" cols="50" rows="4" class="form-control"></form:textarea>
				</form:label>
				<td>
					<form:errors path="message" cssClass="error" />
				</td>
				<br />
				<input type="submit" value="<spring:message code="contact.lblSubmit" />" class="row btn btn-outline-primary" />
			</form:form>
		</div>
	</div>
	</div>
</body>