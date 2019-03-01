<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div
	class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div
		class="container-fluid d-flex flex-column col-12 justify-content-around align-items-center">
		<div class="text-center jumbotron jumbotron-form col-xl-6 col-sm-12">
			<h2>Sur le point de craquer ?</h2>

			<p class="motivation">Souvenez-vous pourquoi vous faites tout ces
				efforts ! Il y a quelques temps, vous nous avez confié pourquoi :</p>

			<p class="textemotivation" style="color: red;">
				<strong><c:out value="${ motivation }" /></strong>
			</p>
		</div>


		<div class="col-xl-12 col-sm-12">
			<h2 class="text-center" style="margin-top: 50px;">Prenez quelques minutes en allant vous détendre sur l'un des
				sites suivants</h2>
				<div class="row d-flex flex-row justify-content-center">
				<div class="col-3">
			<ul
				class="container-fluid list-unstyled list-group menuHome text-center d-flex flex-column col-12 h-100">
				<li><a
					class="list-group-item list-group-item-action list-group-item-success"
					href="https://open.spotify.com/playlist/37i9dQZF1DWWMOmoXKqHTD"
					target="_blank">Spotify <i class="material-icons">
							music_note </i></a></li>
				<li><a
					class="list-group-item list-group-item-action list-group-item-success"
					href="https://www.youtube.com/watch?v=gJLIiF15wjQ&start_radio=1&list=RDgJLIiF15wjQ&t=3"
					target="_blank">Youtube <i
						class="material-icons"> video_library </i></a></li>
				<li><a
					class="list-group-item list-group-item-action list-group-item-success"
					href="https://www.9gag.com" target="_blank">9Gag <i
						class="material-icons"> mood </i></a></li>
				<li><a
					class="list-group-item list-group-item-action list-group-item-success"
					href="https://www.facebook.com" target="_blank">Facebook <i
						class="material-icons"> thumb_up_alt </i></a></li>
						</ul>
				</div>
				<div class="col-3">
						<ul
				class="container-fluid list-unstyled list-group menuHome text-center d-flex flex-column col-12 h-100">
				<li><a
					class="list-group-item list-group-item-action list-group-item-success"
					href="https://www.twitter.com" target="_blank">Twitter <i
						class="material-icons"> send </i></a></li>
				<li><a
					class="list-group-item list-group-item-action list-group-item-success"
					href="https://www.instagram.com" target="_blank">Instagram <i
						class="material-icons"> photo_camera </i></a></li>
				<li><a
					class="list-group-item list-group-item-action list-group-item-success"
					href="https://www.lemonde.fr" target="_blank">Le Monde <i
						class="material-icons"> fiber_new </i></a></li>
				<li><a
					class="list-group-item list-group-item-action list-group-item-success"
					href="http://www.zebest-3000.com/portail/mini-jeux.html"
					target="_blank">Mini-jeux <i
						class="material-icons"> videogame_asset </i></a></li>
			</ul>
				</div>
			</div>
		</div>


	</div>
</div>