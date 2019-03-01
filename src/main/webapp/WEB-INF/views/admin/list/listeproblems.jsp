<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div
	class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
	<div
		class="container-fluid col-12 d-flex flex-column justify-content-center align-items-center text-center advicecontainer">
		<h1>Liste des Problèmes</h1>
		<c:choose>
			<c:when test="${ res }">
				<p>Aucun élément présent</p>
			</c:when>
			<c:otherwise>
				<div class="container">
					<div class="table-responsive" style="background: rgba(237, 238, 233, 0.8);">
						<table class="table table-hover">
							<!-- 			border="1" style="width: 50%; margin-top: 30px -->
							<thead class="thead-light">
								<tr>
									<th>ID</th>
									<th>Date de Creation</th>
									<th>Objectif</th>
									<th>Frequence du problème</th>
									<th>Titre</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="problem" items="${ problems }" varStatus="st">
									<tr>
										<td><c:out value="${ problem.idProblem }" /></td>
										<td><c:out value="${ problem.creationDate }" /></td>
										<td><c:out value="${problem.objective}" /></td>
										<td><c:out value="${problem.problemFrequence}" /></td>
										<td><c:out value="${ problem.title }" /></td>
										<td>
<%-- 										<a href="admin/problem/remove/${ problem.idProblem }" title="Remove" type="button" class="btn btn-outline-primary"> Remove</a> --%>

											<button type="button" class="btn btn-outline-primary"
												data-toggle="modal" data-target="#exampleModalCenter${st.index}">
												Suppr.</button>
											<div class="modal fade" id="exampleModalCenter${st.index}" tabindex="-1"
												role="dialog" aria-labelledby="exampleModalCenterTitle"
												aria-hidden="true">
												<div class="modal-dialog modal-dialog-centered"
													role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="exampleModalLongTitle">Suppression
																du problème</h5>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
															Souhaitez-vous confirmer la suppression de ce problème :
															<br />
															ID = 
															<c:out value="${ problem.idProblem }" />
															(
															<c:out value="${ problem.creationDate }" />
															) ?
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary"
																data-dismiss="modal">Annuler</button>
															<a href="admin/problem/remove/${problem.idProblem}"
																type="button" class="btn btn-primary">Confirmer</a>
														</div>
													</div>
												</div>
											</div> 
											<!-- End modal on remove -->
										</td>
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