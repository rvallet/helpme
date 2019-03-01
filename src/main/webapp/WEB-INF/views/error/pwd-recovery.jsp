<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
<div class="container-fluid col-12 d-flex justify-content-center">
	<div class="jumbotron jumbotron-form col-5">
		<h1 class="text-center">
			<spring:message code="error.pwd-recovery.title" />
		</h1>
		<fieldset>
			<legend></legend>
			<form:form method="post" action="sendpwd" modelAttribute="prf-recovery">
				<form:label path="email" class="form-group row">
					<spring:message code="error.pwd-recovery.lblEmail" />
					<spring:message code="error.pwd-recovery.phEmail" var="phEmail" />
					<form:input path="email" class="form-control" placeholder="${phEmail}" />
				</form:label>
				<td>
					<form:errors path="email" cssClass="error" />
				</td>
				<br />
				<div class="form-group row d-flex justify-content-center">
					<input type="submit" value="<spring:message code="error.pwd-recovery.lblSubmit" />" class="btn btn-outline-primary" />
				</div>
			</form:form>
		</fieldset>
		<p class="error">
		<c:out value="${ sessionScope.errorMessage }"></c:out>
		<% request.getSession().removeAttribute("errorMessage"); %>
		</p>
			<div class="error">
			<c:if test= "${msg != null }">${msg }</c:if>
			</div>
		</div>
	</div>
</div>
