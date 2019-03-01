<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div
	class="container-fluid d-flex flex-column conteneurbis col-12 justify-content-around align-items-center">
<!-- message d'encouragement -->
<div class="d-flex align-items-center justify-content-center text-center col-xl-6 col-sm-12 jumbotron jumbotron-form">
	<h2>Ne soyez pas trop dur avec vous même. Le travail sur soi-même
		est quelque chose de difficile et qui prends du temps. Ne baissez pas
		les bras. Beaucoup de personnes sont passées par là et ont fini par
		réussir à se libérer de leurs vieux démons.</h2>
</div>

<!-- choix arrêt ou diminution -->
<p>
	<a title="stopButton" href="reinit-stop" class="btn btn-outline-success btn-lg">Continuez sur l'arrêt</a>
</p>

<p>
	<a title="reductionButton" href="from-stop-to-reduc"  class="btn btn-outline-warning btn-lg">Passez sur un programme de diminution</a>
</p>
</div>
</div>