<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
<div class="container-fluid col-12 d-flex justify-content-center">
	<c:if test="${ error != null }">
		<p style="color: red">
			<c:out value="${ error.field }" />
			:
			<c:out value="${ error.defaultMessage }" />
		</p>
	</c:if>
	<form:form action="problem/save-problem" method="post"
		modelAttribute="pb" class="jumbotron jumbotron-form col-6">
		<form:hidden path="idProblem"/>
				<h2 class="row">
					<spring:message code="${ prefix }-form.lblTitle" />
				</h2>
				<br />
				<div class="form-group row">
					<label class="col-9"><spring:message
							code="${ prefix }-form.lblStartQuantity" /></label>
					<form:input type="number" path="startQuantity" min="1" max="60"
						step="1" class="form-control col-3" />
				</div>

				<br />
				<div class="form-group row">
					<label class="col-9"> <spring:message
							code="${prefix }-form.lblPackPrice" />
					</label>
					<form:input type="number" path="PackPrice" min="0.1" max="200"
						step="0.01" class="form-control col-3" />
				</div>
				<br />
				<div class="form-group row">
					<label class="col-9"> <spring:message
							code="${ prefix }-form.lblPackQuantity" />
					</label>
					<form:input path="PackQuantity" type="number" min="1" max="300"
						step="1" class="form-control col-3" />
				</div>
				<br />
				<div class="form-group row">
					<label class="col-9"> <spring:message
							code="${ prefix }-form.lblObjective" />
					</label> <br />
					<form:select path="objective" class="custom-select col-3">
						<form:options items="${ProblemObjective}" itemLabel="name" />
					</form:select>

				</div>
				<br />
				<br />
				<div class="form-group row">
					<label class="col-4" for="motivation"> <spring:message
							code="${ prefix }-form.motivation" />
					</label>
					<form:input type="text" id="motivation" placeholder="(Ex : Je souhaite retrouver l'odorat et le goût)"
						path="motivation" class="col-7 form-control" />
				</div>
		<br />
		<div class="form-group row d-flex justify-content-center">
		<input type="hidden" name="problemType"
			value="${problemType.idProblemTypes }" />
		<input type="submit" value="Sauvegarder"
			class="btn btn-outline-primary col-4" />
		</div>
	</form:form>
	</div>
</div>