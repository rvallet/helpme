<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
	<div class="container-fluid col-12 d-flex justify-content-center">
		<div class="jumbotron jumbotron-form col-xl-5 col-sm-12">
			<h1 class="text-center">
				<spring:message code="user-form.title" />
			</h1>
			<form:form action="admin/save-user" method="post" modelAttribute="u"
				class="jumbotron flex-column justify-content-center">
				<form:hidden path="idUser" />
				<form:hidden path="creationDate" />
				<form:label path="pseudo" class="form-group row">
					<spring:message code="user-form.lblPseudo" />
				</form:label>
				<form:input path="pseudo" class="form-control" />
				<td><form:errors path="pseudo" cssClass="error" /></td>
				<br />
				<form:label path="email" class="form-group row">
					<spring:message code="user-form.lblEmail" />
				</form:label>
				<form:input path="email" class="form-control" />
				<td><form:errors path="email" cssClass="error" /></td>
				<br />
				<br />
				<c:choose>
					<c:when test="${ isAdd }">
						<form:label path="password" class="form-group row">
							<spring:message code="user-form.lblPassword" />
						</form:label>
						<form:password path="password" showPassword="false"
							class="form-control" />
						<td><form:errors path="password" cssClass="error" /></td>
						<br />
					</c:when>
					<c:otherwise>
						<form:hidden path="password"></form:hidden>
						<form:hidden path="version"></form:hidden>
					</c:otherwise>
				</c:choose>
				<form:label path="sexe" class="form-group row">
					<spring:message code="user-form.lblSexe" />
				</form:label>
				<spring:message code="contact.lblHomme" var="lblHomme" />
				<spring:message code="contact.lblFemme" var="lblFemme" />
				<form:radiobutton path="sexe" value="H" label="${ lblHomme }" />
				<form:radiobutton path="sexe" value="F" label="${ lblFemme }" />
				<br />
				<br />
				<form:label path="statut">
					<spring:message code="user-form.lblStatut" />
				</form:label>
				<form:select path="statut" class="custom-select">
					<form:options items="${StatusUser}" />
				</form:select>
				<br />
				<br />
				<input type="submit"
					value="<spring:message code="user-form.lblSubmit" />"
					class="row btn btn-outline-primary" />
			</form:form>
			<div class="row d-flex justify-content-center">
				<a class="btn btn-outline-dark" href="./admin/users" role="button">Retour</a>
			</div>
		</div>
	</div>
	</div>
</body>