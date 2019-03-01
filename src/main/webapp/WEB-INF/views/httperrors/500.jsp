<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false"%> --%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:url value="/" var="link" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${httpErrorCode}- ${errorMsg}</title>
<meta http-equiv="refresh" content="15; URL=${link}">
<style>
</style>
</head>
<body>
	<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
		<div class="container col-12 d-flex justify-content-center">
			<div class="col-7 homeConnect text-center jumbotron jumbotron-form"
				style="border-radius: 10px 100px/120px;">
				<h1 class="display-4">${httpErrorCode}- ${errorMsg}</h1>
				<br />
				<p class="lead">
				Erreur interne du serveur : Le serveur HTTP a rencontré une condition inattendue qui l'a empêché de traiter la requête.
				</p>
				<p>
					Aller à l'<a href="${link}">accueil</a> ?
				</p>
			</div>
		</div>
	</div>
</body>
</html>