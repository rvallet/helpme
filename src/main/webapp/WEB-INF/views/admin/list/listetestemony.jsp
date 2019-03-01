<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
<div class="container-fluid col-12 d-flex flex-column justify-content-center align-items-center text-center">
<h1 class="text-center">Liste des Temoignages</h1>
<c:choose>
	<c:when test="${ res }">
		<p>Aucun élément présent</p>
	</c:when>
	<c:otherwise>
			<form action="admin/testemony/testemony-research" method="post" class="container-fluid col-xl-4	 col-sm-12 d-flex flex-row justify-content-around align-items-center" style="margin-bottom: 20px">
				<input type="text" placeholder="votre recherche ici" name="searchField" class="form-control" />
				<input type="submit" value="Recherche" class="row btn btn-outline-primary" />
			</form>
			

		<div class="container">
			<div class="table-responsive" style="background: rgba(237, 238, 233, 0.8);">
				<table class="table table-hover">
					<!-- 			border="1" style="width: 50%; margin-top: 30px -->
					<thead class="thead-light">
						<tr>
							<th>ID</th>
							<th>Titre</th>
							<th>Contenu</th>
							<th>Date</th>
							<th>Statut</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="testemony" items="${ testemonies }" varStatus="st">
							<tr>
								<td><c:out value="${ testemony.idTemoignage }" /></td>
								<td><c:out value="${ testemony.title }" /></td>
								<td><c:out value="${ testemony.content }" /></td>
								<td><c:out value="${ testemony.dateWrite }" /></td>
								<td><c:out value="${ testemony.status }" /></td>
								<td><a
									href="admin/testemony/update/${ testemony.idTemoignage }"
									title="Update" type="button" class="btn btn-outline-primary"> Modif. </a>
									
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
																	d'un témoignage</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																Souhaitez-vous confirmer la suppression de ce témoignage :
																<br />
																ID = 
																<c:out value="${ testemony.idTemoignage }" /> ?
																<br />
																(<c:out value="${ testemony.title }" />)																
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Annuler</button>
																<a href="admin/testemony/remove/${ testemony.idTemoignage }"
																	type="button" class="btn btn-primary">Confirmer</a>
															</div>
														</div>
													</div>
												</div> 
												<!-- End modal on delete --></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:otherwise>
</c:choose>
</div>
</div>
</body>

