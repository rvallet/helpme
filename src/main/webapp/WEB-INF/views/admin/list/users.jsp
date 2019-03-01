<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />
<meta charset="UTF-8">
<title>Liste des Utilisateurs</title>
</head>
<body>
	<div
		class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
		<div
			class="container-fluid col-12 d-flex flex-column justify-content-center align-items-center text-center advicecontainer">
			<!-- Liste des utilisateurs avec modifications possibles -->
			<h1>Liste des utilisateurs</h1>
			<a href="admin/add-user">Ajouter un utilisateur</a>
			<fieldset>
				<legend>Recherche</legend>
				<form:form action="admin/user-research" method="post"
					modelAttribute="search">
					<form:label path="pseudo">Recherche (Email ou Pseudo)</form:label>
					<br />
					<form:input path="pseudo" class="form-control" />
					<br />
					<input type="submit" value="Rechercher"
						class="row btn btn-outline-primary" />
					<br />
				</form:form>
			</fieldset>

			<c:choose>
				<c:when test="${ res }">
					<p>Aucun élément présent</p>
				</c:when>
				<c:otherwise>
					<div class="container-fluid" style="margin: 35px auto">
						<div class="table-responsive" style="background: rgba(237, 238, 233, 0.8);">
							<table class="table table-hover">
								<!-- 			border="1" style="width: 50%; margin-top: 30px -->
								<thead class="thead-light">
									<tr>
										<th scope="col">Pseudo</th>
										<th scope="col">eMail</th>
										<th scope="col">Statut</th>
										<th scope="col">Date Inscription</th>
										<th scope="col">Problèmes</th>
										<th scope="col">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${ liUser }" varStatus="st">
										<tr>
											<td><c:out value="${ user.pseudo }" /></td>
											<td><c:out value="${ user.email }" /></td>
											<td>${user.statusStr}</td>
											<td>${user.creationDate}</td>
											<td><c:choose>
													<c:when test="${ !empty user.liProblem }">
														<c:forEach items="${user.liProblem}" var="problem">
															<c:out value="${ problem.problemType.title }" />
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:out value="Aucun problème créé" />
													</c:otherwise>
												</c:choose></td>
											<td><a href="admin/users/update/${ user.idUser }"
												title="Update" type="button" class="btn btn-outline-primary">Modif.</a>

												<button type="button" class="btn btn-outline-primary"
													data-toggle="modal" data-target="#exampleModalCenter${st.index}">
													Suppr.</button> <!--  Start modal on remove -->
												<div class="modal fade" id="exampleModalCenter${st.index}"
													tabindex="-1" role="dialog"
													aria-labelledby="exampleModalCenterTitle"
													aria-hidden="true">
													<div class="modal-dialog modal-dialog-centered"
														role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLongTitle">Suppression
																	de l'utilisateur</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																Souhaitez-vous confirmer la suppression de l'utilisateur
																<c:out value="${ user.pseudo }" /> (<c:out value="${ user.email }" />)
																?
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Annuler</button>
																<a href="admin/users/remove/${ user.idUser }"
																	type="button" class="btn btn-primary">Confirmer</a>
															</div>
														</div>
													</div>
												</div> <!-- End modal on remove --></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="row justify-content-around">
				<div class="col-12">
					<p>
						<c:choose>
							<c:when test="${ page>1 }">
								<a href="admin/users?page=${page-1 }&max=${max}"> Précédent
								</a>
							</c:when>
							<c:otherwise> Précédent </c:otherwise>
						</c:choose>
						| <span>${ page }</span> |
						<c:choose>
							<c:when test="${ suivExist }">
								<a href="admin/users?page=${page+1 }&max=${max}"> Suivant </a>
							</c:when>
							<c:otherwise> Suivant </c:otherwise>
						</c:choose>
					</p>
				</div>
				<div class="w-100"></div>
				<div class="col-12">
					<p>
						<a href="admin/export-users">Télécharger la liste</a>
					</p>
					<h4>Importer une liste</h4>
					<form:form method="post" action="admin/import-users"
						enctype="multipart/form-data">
						<input type="file" name="file" />
						<br />
						<input type="Submit" value="Importer">
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>