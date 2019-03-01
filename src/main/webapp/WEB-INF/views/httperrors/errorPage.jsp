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
table td {
	vertical-align: top;
	border: solid 1px #888;
	padding: 10px;
}
</style>
</head>
<body>
	<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
		<div class="container col-12 d-flex justify-content-center">
			<div class="col-7 homeConnect text-center jumbotron jumbotron-form" style="border-radius: 10px 100px/120px;">
				<h1 class="display-4">${httpErrorCode}- ${errorMsg}</h1>
			</div>
		</div>
		<table>
			<tr>
				<td>Code d'erreur</td>
				<td>${httpErrorCode}</td>
			</tr>
			<tr>
				<td>Message :</td>
				<td>${errorMsg}</td>
			</tr>
		</table>
		<p>
			Aller à l'<a href="${link}">accueil</a> ?
		</p>
	</div>
</body>
</html>