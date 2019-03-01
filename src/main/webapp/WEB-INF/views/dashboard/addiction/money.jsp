<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div
	class="container-fluid d-flex flex-column col-12 justify-content-center align-items-center">
<div class="moneySave text-center jumbotron jumbotron-form col-xl-6 col-sm-12">
	<h2>Grâce à vos efforts, vous avez économisé : <c:out value="${ AmountOfMoneySave }" />€</h2>
	<h3>Regardez ce que vous pouvez vous acheter avec vos économies</h3>
	</div>
	<div class="d-flex justify-content-around align-items-center text-center tableau col-xl-10 col-sm-12" style="background: rgba(237, 238, 233, 0.8);"> 
	<table class="table table-bordered">
	<thead>
		<tr>
			<th>Casque Sony</th>
			<th>Console Switch</th>
			<th>Honor view 20</th>
			<th>PC Asus</th	>
			<th>Scooter Yamaha</th>
			<th>Peugeot 108</th>
		</tr>
		</thead>
		<tr>
			<td><img src="<c:url value='ressources/image/headphone.jpg'/>"
				alt="Headphone" height="100px"></td>
			<td><img src="<c:url value='ressources/image/nintendo.jpg'/>"
				alt="Nintendo" height="100px"></td>
			<td><img src="<c:url value='ressources/image/phone.jpg'/>"
				alt="Phone" height="100px"></td>
			<td><img src="<c:url value='ressources/image/computer.jpg'/>"
				alt="Computer" height="100px"></td>
			<td><img src="<c:url value='ressources/image/scooter.jpg'/>"
				alt="Scooter" height="100px"></td>
			<td><img src="<c:url value='ressources/image/car.jpg'/>"
				alt="Car" height="100px"></td>
		</tr>
		<tr>
			<td>150€</td>
			<td>300€</td>
			<td>550€</td>
			<td>1 400€</td>
			<td>3 100€</td>
			<td>11 350€</td>
		</tr>
		<tr>
			<c:forEach var="entry" items="${ whatToBuy }">
				<td><c:out value="${ entry.value }" />%</td>
			</c:forEach>
		</tr>
	</table>
	</div>
</div>
</div>