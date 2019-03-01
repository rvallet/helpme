<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div class="container-fluid col-12 d-flex justify-content-center">
		<div class="jumbotron jumbotron-form col-5">
		<h1 class="text-center">
			<spring:message code="error.pwd-reset.title" />
		</h1>
		<fieldset>
			<legend></legend>
			<form method="post" action="password-changed">
				<label for="password" class="form-group row"> <spring:message
						code="error.pwd-reset.lblPassword" /> :
				</label>
				<spring:message code="error.pwd-reset.phPassword" var="phPassword" />
				<input type="password" name="password" id="password" placeholder="${phPassword}" class="form-control" />
				<br />
				<form:errors path="password" cssClass="error" />
				<!-- START Vérif pwd (TODO: méthode control double saisie dans PwdResetcontroller -->
<!-- 				<label class="form-group row" for="verifpwd"> -->
<%-- 				<spring:message code="account-creation.lblConfPassword" /> --%>
<%-- 				<input type="password" name="verifpwd" id="verifpwd" class="form-control" placeholder="${ phPassword }" /> --%>
<!-- 				<br /> -->
<!-- 				</label> -->
<%-- 				<c:if test="${pwdnotmatch != null}"> --%>
<!-- 					<div class="error"> -->
<%-- 						<c:out value="${ pwdnotmatch }" /> --%>
<!-- 					</div> -->
<%-- 				</c:if>				 --%>
				<!-- END Vérif pwd -->
				
				<input type="hidden" value="${ resetToken }" name="resetToken" />
				<input type="submit" value="<spring:message code="error.pwd-reset.lblSubmit" />" class="btn btn-outline-primary" />
			</form>
		</fieldset>
		</div>
	</div>
</div>