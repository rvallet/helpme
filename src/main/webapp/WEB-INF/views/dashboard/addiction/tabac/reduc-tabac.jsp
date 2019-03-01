<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet"
	href="<c:url value="/ressources/style/tabac-dashboard.css" />" />
	<style>
		body {
		background-image:none;
		background: rgba(238, 237, 233, 0.7);
		}
	</style>
	<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
<div
	class="dashboard container-fluid d-flex flex-column conteneurbis col-12 justify-content-center align-items-center">
	<div class="container-fluid row justify-content-center">
		<div
			class="d-flex flex-row text-center top-dashboard col-xl-10 col-sm-12 justify-content-around align-items-center row1">

			<a href="money"
				class="case1 col-4 h-100 d-flex flex-column justify-content-center align-items-center">
				Argent économisé : <c:out value="${ money }" /> <i
				class="material-icons">euro_symbol</i>
			</a>
			<p class="case2 abstinenceTime col-4 h-100  d-flex flex-column justify-content-center align-items-center text-center">
	
			Votre objectif <br /><c:out value="${ objStart.goalQuantity } cigarettes/jour" />
				
		
			</p>
			<a href="height"
				class="case3 quantityAvoid col-4 h-100  d-flex flex-column justify-content-center align-items-center">Cigarette
				non fumées : <c:out value="${ cigaretteNotSmoke }" /> <br />Temps
				gagné : <c:out value="${ time }" /> <i class="material-icons">
					smoke_free </i>
			</a>
		</div>
	</div>
	<div class="row container-fluid justify-content-center">
		<div class="d-flex flex-row middle-dashboard col-xl-10 col-sm-12 justify-content-around align-items-center row2">
			<div class="case4 tips col-12 text-center h-100 d-flex flex-column justify-content-center align-items-center">
				<div class="d-flex flex-row align-items-center justify-content-center">
					<i class="material-icons"> mood </i>
				<p class="title-advice"><c:out value="${ advice.title }" /></p>
					<i class="material-icons"> mood </i>
				</div>
				<br />
				<p>
					<c:out value="${ advice.content }" />
				</p>
			</div>
		</div>
	</div>
	<div class="row container-fluid justify-content-center">
		<div
			class="d-flex flex-row text-center bot-dashboard col-xl-10 col-sm-12 align-items-center justify-content-around row3">

		<a href="reduc-tabac-cancel" class="case5 cracked col-4 h-100 d-flex flex-column justify-content-center align-items-center">Annuler le programme de réduction en cours<i class="material-icons"> sentiment_very_dissatisfied </i></a>


		<a href="reduc-tabac-conso" class="case6 cracked col-4 h-100 d-flex flex-column justify-content-center align-items-center">Rentrer ici votre consommation quotidienne<i class="material-icons"> thumb_up </i>	</a>
	


		<a href="dashboard/motivation" class="case7 cracked col-4 h-100 d-flex flex-column justify-content-center align-items-center">J'ai envie de fumer...<i class="material-icons"> whatshot </i></a>

</div>
</div>
</div>
</div>