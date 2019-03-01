<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
	<div
		class="container-fluid col-12 d-flex justify-content-center">
		<div class="jumbotron jumbotron-form col-xl-5 col-sm-12">
			<h1 class="text-center">
				<spring:message code="account-creation.title" />
			</h1>
			<br />
			<form:form action="account-creation/save-user" method="post"
				modelAttribute="u">
				<form:hidden path="idUser" />

				<form:label path="pseudo" class="form-group row">
					<spring:message code="account-creation.lblPseudo" />
					<spring:message code="account-creation.phPseudo" var="phPseudo" />
					<br />
					<form:input path="pseudo" placeholder="${ phPseudo }"
						class="form-control" />
				</form:label>
				<td><form:errors path="pseudo" cssClass="error" /></td>

				<br />
				<form:label path="email" class="form-group row">
					<spring:message code="account-creation.lblEmail" />
					<spring:message code="account-creation.phEmail" var="phEmail" />
					<br />
					<form:input type="email" id="email" path="email"
						placeholder="${ phEmail }" class="form-control" />
				</form:label>
				<td><form:errors path="email" cssClass="error" /></td>
				<br />
				<form:label path="password" class="form-group row">
					<spring:message code="account-creation.lblPassword" />
					<spring:message code="account-creation.phPassword" var="phPassword" />
					<form:password id="password" path="password" showPassword="true"
						placeholder="${ phPassword }" class="form-control" />
					<br />
				</form:label>
				<td><form:errors path="password" cssClass="error" /></td>

				<label class="form-group row" for="verifpwd"><spring:message
						code="account-creation.lblConfPassword" /> <input type="password"
					name="verifpwd" id="verifpwd" class="form-control"
					placeholder="${ phPassword }" /> <br /> </label>

				<c:if test="${pwdnotmatch != null}">
					<div class="error">
						<c:out value="${ pwdnotmatch }" />
					</div>
				</c:if>

				<br />
				<p class="form-group row">
					<spring:message code="account-creation.lblSexe" />
				</p>
				<div class="form-check">
					<label for="H" class="form-check-label"><spring:message
							code="account-creation.lblHomme" var="lblHomme" /> </label>
					<form:radiobutton class="form-check-input" path="sexe" value="H"
						label="${ lblHomme }" />
				</div>
				<div class="form-check">
					<label for="F" class="form-check-label"><spring:message
							code="account-creation.lblFemme" var="lblFemme" /></label>
					<form:radiobutton class="form-check-input" path="sexe" value="F"
						label="${ lblFemme }" />
				</div>
				<td><form:errors path="sexe" cssClass="error" /></td>
				<br />
				<div class="form-group row d-flex justify-content-center">
					<input type="submit"
						value="<spring:message code="account-creation.lblSubmit" />"
						class="btn btn-outline-primary row" />
				</div>
			</form:form>
			<c:if test="${msg != null}">
				<div class="error">
					<c:out value="${ msg }" />
				</div>
				<p>
					<a href="authenticate"> <spring:message
							code="account-creation.lblAuthenticate" /></a> | <a
						href="password-recovery"> <spring:message
							code="account-creation.lblPassword-recovery" /></a>
				</p>
			</c:if>
		</div>
	</div>
	</div>
</body>