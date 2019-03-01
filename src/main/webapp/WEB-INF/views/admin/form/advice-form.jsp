<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
	<div class="container-fluid col-12 d-flex justify-content-center">
		<div class="jumbotron jumbotron-form col-xl-5 col-sm-12">
			<h1 class="text-center">${title}</h1>
			<c:if test="${ errors != null }">
				<c:forEach items="${ errors }" var="error">
					<p style="color: red">
						<c:out value="${ error.field }" />
						:
						<c:out value="${ error.defaultMessage }" />
					</p>
				</c:forEach>
			</c:if>

			<form:form action="admin/advice/save-advice" method="post" modelAttribute="advice" class="jumbotron flex-column justify-content-center">
				<form:hidden path="idAdvice" />
				<form:label path="title" class="form-group row">
					<spring:message code="advice-form.lblTitle" />
				</form:label>
				<form:input path="title" class="form-control" />
				<br />
				<form:label path="content" class="form-group row">
					<spring:message code="advice-form.lblContent" />
				</form:label>
				<form:textarea path="content" cols="50" rows="5"
					class="form-control" />
				<br />
				<c:forEach var="pbtype" items="${lipbtypes}">
					<label><c:out value="${pbtype.title}" /></label>
					<input type="checkbox" name="pbtypes" value="${pbtype.idProblemTypes}" class="form-group" />
				</c:forEach>
				<input type="checkbox" hidden="true" name="pbtypes" value="0" checked="checked" />
				<br />
				<input type="submit" value="${title}" class="row btn btn-outline-primary" />
			</form:form>
			<div class="row d-flex justify-content-center">
				<a class="btn btn-outline-dark" href="./admin/advice"
					role="button">Retour</a>
			</div>
		</div>
	</div>
	</div>
</body>