<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div
	class="dashboard container-fluid d-flex flex-column justify-content-center align-items-center">
<div class="height jumbotron jumbotron-form col-xl-6 col-sm-12">
	<h2>La longueur cumulée des cigarettes que vous n'avez pas fumé représentent</h2>
		</div>
		<div class="d-flex justify-content-around align-items-center text-center tableau" style="background: rgba(237, 238, 233, 0.8);">
	<table class="table table-bordered">
		<tr>
			<c:forEach var="entry" items="${ height }">
				<td><c:out value="${ entry.key }" /></td>
			</c:forEach>
		</tr>
		<tr>
			<td><img src="<c:url value='ressources/image/Pikachu.png'/>"
				alt="Pikachu" height="100px"></td>
			<td><img src="<c:url value='ressources/image/Frodon.jpg'/>"
				alt="Frodon" height="100px"></td>
			<td><img src="<c:url value='ressources/image/Chewbacca.jpg'/>"
				alt="Chewbacca" height="100px"></td>
			<td><img src="<c:url value='ressources/image/Tardis.jpg'/>"
				alt="Tardis" height="100px"></td>
			<td><img src="<c:url value='ressources/image/TRex.png'/>"
				alt="T-Rex" height="100px"></td>
			<td><img src="<c:url value='ressources/image/Basilic.png'/>"
				alt="Basilic d'Harry Potter" height="100px"></td>
			<td><img src="<c:url value='ressources/image/Millenium.jpg'/>"
				alt="Millenium" height="100px"></td>
			<td><img src="<c:url value='ressources/image/BaradDur.jpeg'/>"
				alt="Barad-Dur" height="100px"></td>
		</tr>
		<tr>
			<td>0,40 m</td>
			<td>1,10 m</td>
			<td>2,28 m</td>
			<td>3,05 m</td>
			<td>5 m</td>
			<td>15 m</td>
			<td>34,75 m</td>
			<td>1 400 m</td>
		</tr>
		<tr>
			<c:forEach var="entry" items="${ height }">
				<td><c:out value="${ entry.value }" />%</td>
			</c:forEach>
		</tr>
	</table>
		</div>
</div>
</div>