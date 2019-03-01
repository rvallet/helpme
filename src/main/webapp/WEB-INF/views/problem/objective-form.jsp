<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
<div class="container-fluid col-12 d-flex justify-content-center">
	<div
		class="container jumbotron-form jumbotron d-flex flex-column justify-content-center">
		<h1 class="text-center  inline-block">
			<c:out value="${pbType.title}"></c:out>
		</h1>


		<p class="text-center inline-block">
			<spring:message code="objective-form.WelcomeDecreaseTabac" />
		</p>
		<p class="text-center inline-block">
			<spring:message code="objective-form.AdviceDecreaseTabac" />
		</p>
		<br />
		<form:form action="problem/save-objective" method="post"
			modelAttribute="obj">
			<form:label path="goalQuantity" class="form-group text-center col-12"><u>
				<spring:message code="objective-form.lblQuantity" /></u>
			</form:label>
			<div class="form-group row justify-content-center">
				<form:input type="number" class="form-control col-1"
					path="goalQuantity" min="1" max="${pb.startQuantity -1}" step="1" />
			</div>
			<br />
			<div class="form-group row justify-content-center">
				<input type="submit" class="btn btn-outline-primary col-3"
					value="<spring:message code="submit"/>" />
			</div>
			<input type="hidden" value="${pb.idProblem }" name="idPb" />
		</form:form>
	</div>

</div>
</div>