<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div
	class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div
		class="dashboard container-fluid d-flex flex-column conteneurbis col-12 justify-content-center align-items-center text-center">
			<div class="text-center jumbotron jumbotron-form">
			<c:choose>
				<c:when test="${ boolEnd }">
					<h2>L'heure du bilan est arrivée pour cette semaine de
						diminution. Veuillez vérifier les données avant de poursuivre.</h2>
				</c:when>
				<c:otherwise>
					<h2>Veuillez rentrer ici votre consommation :</h2>
					<h5>(Il est de base à votre consommation avant diminution)</h5>
				</c:otherwise>
			</c:choose>
				</div>
			<c:choose>
			<c:when test="${!consoIsEmpty}">
				<div class="text-center tableau">
					<form action="save-tabac-conso" method="post">
						<table class="table table-bordered tableau" style="background: rgba(237, 238, 233, 0.8);">
							<thead>
								<tr>
									<th>Le jour</th>
									<th>Votre consommation</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ conso }" var="jour">
									<tr>
										<td><c:out value="Jour ${ jour.key }" /></td>
										<td><input type="number" step="1" min="1"
											max="${ pb.startQuantity+5 }" name="map"
											value="${jour.value}" required /></td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						<input type="submit" value="Sauvegarder"
							class="btn btn-outline-primary" />
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<p><i>Revenez demain pour compléter votre consommation sur la
					première journée de votre programme de diminution !</i></p>
			</c:otherwise>
		</c:choose>

	</div>
</div>