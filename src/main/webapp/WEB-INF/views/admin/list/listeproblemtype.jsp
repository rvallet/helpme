<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container-fluid d-flex justify-content-center align-items-center conteneurbis">
<div class="container-fluid col-12 d-flex flex-column justify-content-center align-items-center conteneurbis">
<h1>Liste des types de problème</h1>
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
							<th>Problème Catégorie</th>
							<th>Titre</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="problemtype" items="${ problemtype }">
							<tr>
								<td><c:out value="${ problemtype.idProblemTypes }" /></td>
								<td><c:out value="${ problemtype.pbCategory }" /></td>
								<td><c:out value="${problemtype.title}" /></td>
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