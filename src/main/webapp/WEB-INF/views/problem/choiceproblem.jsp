<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta charset="UTF-8" />
<title>Ajouter un problème</title>
<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />
</head>
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div class="container-fluid col-12 d-flex justify-content-center ">
		<form:form method="post" action="problem/choice"
			modelAttribute="problemType" class="jumbotron jumbotron-form col-5">
			<div class="form-group row d-flex justify-content-around ">
				<label class="input-group-text col-3"> <spring:message
						code="Problemtype-form.lblchoice" />
				</label> <br />
				<form:select path="idProblemTypes" class="custom-select col-3">
					<c:forEach var="pbT" items="${ typeproblems }">
						<form:option value="${pbT.idProblemTypes}">${pbT.title}</form:option>
					</c:forEach>
				</form:select>
				<input type="submit" value="Choisir" class="btn btn-outline-primary btn-sm col-2"/>
			</div>
		</form:form>
	</div>
	</div>
</body>
</html>