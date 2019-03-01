<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div class="container-fluid col-12 d-flex justify-content-center align-items-center">
		<div class="jumbotron jumbotron-form col-xl-5 col-sm-12 ">
		<h1 class="text-center">
			<spring:message code="login.title" />
		</h1>
		<fieldset>
			<legend></legend>
			<form:form method="post" modelAttribute="login-form"
				action="check-login">
				<form:label path="email" class="form-group row">
					<spring:message code="login.lblEmail" />
					<spring:message code="login.phEmail" var="phEmail" />
					<form:input path="email" class="form-control" placeholder="${ phEmail }"/>
				</form:label>
				<td><form:errors path="email" cssClass="error" /></td>
				<form:label path="password" class="form-group row">
					<spring:message code="login.lblPassword" />
					<spring:message code="login.phPassword" var="phPassword" />
					<form:password path="password" class="form-control" placeholder="${ phPassword }" />
				</form:label>
				<td><form:errors path="password" cssClass="error" /></td>
				<br />
				<div class="form-group row d-flex justify-content-center">
					<input type="submit"
						value="<spring:message code="login.lblSubmit" />"
						class="btn btn-outline-primary" />
				</div>
				<br />
				<div class="form-group row d-flex justify-content-center">
					<a href="password-recovery"><spring:message code="login.lblPassword-recovery" /></a>
				</div>
			</form:form>
		</fieldset>
		<!-- Start  : Affichage de confirmation de mise à jour du mot de passe -->
<!-- 		<p class="error"> -->
<%-- 		<c:out value="${ sessionScope.success-resetpwd }"></c:out> --%>
<%-- 		<% request.getSession().removeAttribute("success-resetpwd"); %> --%>
<!-- 		</p> -->
		<!-- End  : Affichage de confirmation de mise à jour du mot de passe -->			
			<c:if test="${msg != null}" />
			<div style="color: red;">
				<c:out value="${ msg }" />
			</div>
		</div>
	</div>
</div>