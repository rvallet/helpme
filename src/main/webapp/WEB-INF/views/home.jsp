<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div class="container col-12 d-flex justify-content-center">
		<c:choose>
			<c:when test="${sessionScope.isAuth }">
				<div class="col-xl-7 col-sm-12 homeConnect text-center jumbotron jumbotron-form" style="	border-radius: 10px 100px / 120px;">
						<h1 class="titlehome">
								<c:out value="Bienvenue ${ sessionScope.user.pseudo }  " />
							</h1>
							<br/>
					<ul class="list-unstyled list-group menuHome">
						<li ><a href="problem" class="list-group-item list-group-item-action list-group-item-success">Ajouter un problème</a></li>
						<c:if test="${ !empty sessionScope.user.liProblem }">
							<c:forEach items="${sessionScope.user.liProblem}" var="problem">
								<c:if test="${ problem.active }">
									<li><a
										href="${ problem.problemType.title }/${ problem.idProblem }" class="list-group-item list-group-item-action list-group-item-success">Tableau de bord : ${ problem.problemType.title }</a>
									</li>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${ sessionScope.status eq 'ADMIN' }">
							<li><a href="admin/dashboard" class="list-group-item list-group-item-action list-group-item-success">AdminDashboard</a></li>
						</c:if>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-xl-7 col-sm-12 homeNotConnect jumbotron jumbotron-form" style="border-radius: 10px 100px / 120px;">
					<h1 class="titlehome">HelpMe</h1>
					<br />
					<p class="lead" style="font-size: 26px;">L'application qui vous veut du bien !</p>
				</div>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</body>



