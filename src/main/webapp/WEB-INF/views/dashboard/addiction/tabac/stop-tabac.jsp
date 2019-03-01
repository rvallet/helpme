<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet"
	href="<c:url value="/ressources/style/tabac-dashboard.css" />" />
<style>
body {
	background-image: none;
	background: rgba(237, 236, 232, 0.5);
}
</style>
<script>
var abs = ${ abstinence };

function HeureCheckEJS()
	{
	var days = 0, hours = 0, min = 0, sec = 0;
	
	// Si le compteur est à plus d'un jour
	if (abs > 86400) {
		days = Math.floor(abs / 86400);
		hours = Math.floor((abs - days * 86400) / 3600);
		min = Math.floor((abs - days * 86400 - hours * 3600) / 60);
		sec = Math.floor(abs - days * 86400 - hours * 3600 - min * 60);
		
		// Si le compteur est à plus d'une heure
	} else if (abs > 3600) {
		days = 0;
		hours = Math.floor(abs / 3600);
		min = Math.floor((abs - hours * 3600) / 60);
		sec = Math.floor(abs - hours * 3600 - min * 60);

		// Si le compteur est à plus d'une minute
	} else if (abs > 60) {
		days = 0;
		hours = 0;
		min = Math.floor(abs / 60);
		sec = abs - min * 60;

	} else {
		days = 0;
		hours = 0;
		min = 0;	
		sec = abs;
	}
	
	if (days > 0){
		dyn = days + "j " + hours + "h " +  min + "min " + sec + "sec ";
	} else {
		dyn = hours + "h " +  min + "min " + sec + "sec ";
	}
	if (document.getElementById){
		document.getElementById("ejs_heure").innerHTML=dyn;
	}
	abs++;
	setTimeout("HeureCheckEJS()", 1000)
	}
window.onload = HeureCheckEJS;
</script>

<div
	class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
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
				<div
					class="case2 abstinenceTime col-4 h-100  d-flex flex-column justify-content-center align-items-center" >
					<p>Temps d'arrêt :</p>
										
					<p id = "ejs_heure"></p>
					<p style="font-size: 0.8em;">(depuis <c:out value="${stopDate}" />)</p>
					<i class="material-icons"> access_time </i>
				</div>

				<a href="height"
					class="case3 quantityAvoid col-4 h-100  d-flex flex-column justify-content-center align-items-center">Cigarette
					non fumées : <c:out value="${ cigaretteNotSmoke }" /> <br />Temps
					gagné : <c:out value="${ time }" /><br /> <span style="font-size: 0.8em"> (~4min/cigarette)</span><i class="material-icons">
						smoke_free </i>
				</a>
			</div>
		</div>
		<div class="row container-fluid justify-content-center">
			<div
				class="d-flex flex-row middle-dashboard col-xl-10 col-sm-12 justify-content-around align-items-center row2">
				<div
					class="case4 tips col-12 text-center h-100 d-flex flex-column justify-content-center align-items-center">
					<div class="d-flex flex-row align-items-center justify-content-center">
						<i class="material-icons"> mood </i>
						<p class="title-advice"><c:out value="${ advice.title }" /></p>
						<i class="material-icons"> mood </i>
					</div>
					<p>
						<c:out value="${ advice.content }" />
					</p>
				</div>
			</div>
		</div>
		<div class="row container-fluid justify-content-center">
			<div
				class="d-flex flex-row text-center bot-dashboard col-xl-10 col-sm-12 align-items-center justify-content-around row3">


				<a href="dashboard/cracked"
					class="case5 cracked col-4 h-100 d-flex flex-column justify-content-center align-items-center">J'ai
					craqué<i class="material-icons"> sentiment_very_dissatisfied </i>
				</a> <a href="./testemonies"
					class="case6 objective col-4 h-100  d-flex flex-column justify-content-center align-items-center">

					Témoignage <i class="material-icons"> thumb_up </i>
				</a> <a href="dashboard/motivation"
					class="case7 ImGoingTocrack col-4 h-100  d-flex flex-column justify-content-center align-items-center">Je
					vais craquer<i class="material-icons"> whatshot </i>
				</a>

			</div>
		</div>
	</div>
</div>

