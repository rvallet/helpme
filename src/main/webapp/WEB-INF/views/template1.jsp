<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title><spring:message code="template1.title" /></title>
<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<c:url value="/ressources/style/css/normalize.css" />" />
<link rel="stylesheet"
	href="<c:url value="/ressources/style/css/bootstrap.min.css" />" />
<link rel="stylesheet"
	href="<c:url value="/ressources/style/css/style.css" />" />
	<link href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons" />"
      rel="stylesheet">
<style>
@font-face{
    font-family : "Pacifio";
    src : url('<c:url value="/ressources/style/css/police/Pacifico.ttf" />') format('truetype');
}
body {
	background-image: url("<c:url value="/ressources/image/fondtest2.jpg" />");
	background-size: cover; 
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-color: background: linear-gradient(45deg, rgba(254,255,237,0.7) 0%, rgba(250,250,250,0.7) 100%);
	
}
.error {
	color: #ff0000;
	font-size: 0.7em;
	}
.brand{
	font-family: "Pacifio", sans-serif;
	font-size: 30px;
}

</style>
</head>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
			<a class="navbar-brand brand" href="#">HelpMe </a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="">Accueil
							<span class="sr-only">(current)</span>
					</a></li>
					<c:if test="${!sessionScope.isAuth}">
						<li class="nav-item"><a class="nav-link" href="authenticate">LogIn</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="account-creation">Créer un compte</a></li>
					</c:if>

					<c:if test="${sessionScope.isAuth}">
						<c:choose>
							<c:when test="${ !empty sessionScope.user.liProblem }">
								<c:forEach items="${sessionScope.user.liProblem}" var="problem">
									<c:if test="${ problem.active }">
										<li class="nav-item"><a class="nav-link"
											href="${ problem.problemType.title }/${ problem.idProblem }">${ problem.problemType.title }</a>
										</li>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="problem">Ajouter
										un problème</a></li>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${ sessionScope.status eq 'ADMIN' }">
						<li class="nav-item"><a class="nav-link"
							href="admin/dashboard">AdminDashboard</a></li>
					</c:if>
				</ul>
				<c:if test="${sessionScope.isAuth }">
					<div class="navbar-nav">
						<a class="nav-link nav-item" href="disconnect">Déconnexion</a>
					</div>
				</c:if>
			</div>
			<div class="navbar-nav d-none d-lg-block">
				<c:if test="${sessionScope.isAuth }">
					<a class="nav-text-lg" style=""><i><c:out
								value="Bienvenue ${ sessionScope.user.pseudo }  " /></i></a>
				</c:if>
			</div>
			
		</nav>
		<!--     <form class="form-inline my-2 my-lg-0"> -->
		<!--       <input class="form-control mr-sm-2" type="text" placeholder="Search"> -->
		<!--       <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button> -->
		<!--     </form> -->
		<!--   </div> -->
		<!--  -->

		<!-- 		A REACTIVER SSI PROPERTIES READY -->
		<!-- 		<form> -->
		<!-- 			<select onchange="location = this.value;"> -->
		<!-- 				<option value="null" selected>Changer de langue</option> -->
		<!-- 				<option -->
		<%-- 					value="${requestScope['javax.servlet.forward.request_uri']}?lang=fr">Fr --%>
		<!-- 					(Français)</option> -->
		<!-- 				<option -->
		<%-- 					value="${requestScope['javax.servlet.forward.request_uri']}?lang=en">En --%>
		<!-- 					(English)</option> -->
		<!-- 			</select> -->
		<!-- 		</form> -->
		<!-- 		A REACTIVER SSI PROPERTIES READY -->
	</header>
	<hr />
		<sitemesh:write property="body" />
	<footer>
		<nav
			class="navbar navbar-expand navbar-dark bg-primary fixed-bottom justify-content-around">

			<a class="nav-link" href="about-us"
				style="color: rgba(255, 255, 255, 0.75);">A propos du projet<span
				class="sr-only">(current)</span>
			</a> <a class="nav-link" href="contact-us"
				style="color: rgba(255, 255, 255, 0.75);">Contactez-nous<span
				class="sr-only">(current)</span></a> <a class="nav-link"
				href="privacy-policy" style="color: rgba(255, 255, 255, 0.75);">Politique
				de confidentialité<span class="sr-only">(current)</span>
			</a>
		</nav>
	</footer>
</html>
