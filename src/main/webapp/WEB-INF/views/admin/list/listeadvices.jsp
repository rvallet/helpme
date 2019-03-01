<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
	<div
		class="container-fluid col-12 d-flex flex-column justify-content-center align-items-center text-center advicecontainer">
		<h1>Liste des conseils</h1>
		<c:choose>
			<c:when test="${ res }">
				<p>Aucun élément présent</p>
			</c:when>
			<c:otherwise>
				<a href="admin/advice/add-advice">Ajouter un conseil</a>

				<fieldset>
					<legend></legend>
					<form action="admin/advice/advice-research" method="post">
						<label>Recherche</label> <br /> <input type="text"
							placeholder="votre recherche ici" name="searchField"
							class="form-control" /> <br /> <input type="submit"
							value="Rechercher" class="row btn btn-outline-primary" style="margin-bottom: 20px;"/>
					</form>
				</fieldset>
				<div class="table-responsive" style="background: rgba(237, 238, 233, 0.8);">
					<table class="table table-hover">
						<!-- 			border="1" style="width: 50%; margin-top: 30px -->
						<thead class="thead-light">
							<tr>
								<th>ID</th>
								<th>Titre</th>
								<th>Contenu</th>
								<th>Utilisé le :</th>
								<th>Lié à :</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="advice" items="${ advices }" varStatus="st">
								<tr>
									<td><c:out value="${ advice.idAdvice }" /></td>
									<td><c:out value="${ advice.title }" /></td>
									<td><c:out value="${advice.content}" /></td>
									<td><c:out value="${advice.useDate}" /></td>
									<td><c:forEach var="problemtype"
											items="${ advice.liProblemType }">
											<c:out value="${ problemtype.title }">
												<br />
											</c:out>
										</c:forEach></td>
									<td><a href="admin/advice/update/${ advice.idAdvice }"
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
																	d'un conseil</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																Souhaitez-vous confirmer la suppression de ce conseil :
																<br />
																ID = 
																<c:out value="${ advice.idAdvice }" /> ?
																<br />
																(<c:out value="${ advice.title }" />)																
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Annuler</button>
																<a href="admin/advice/remove/${ advice.idAdvice }"
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
			</c:otherwise>
		</c:choose>
		</div>
		</div>