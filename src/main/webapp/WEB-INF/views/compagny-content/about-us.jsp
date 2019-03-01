<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<body>

<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div
		class="container-fluid h-100 col-12 justify-content-center align-items-center">
		<br />
		<h1 class="text-center col-12">Présentation du projet</h1>
		<br />
		<div class="text-justify text-center">
			<p>Vous souhaitez arréter de fumer, vous avez un problème avec
				l'argent ou vous souhaitez simplement travailler sur vos points
				faibles ? Cette application web est faite pour vous.</p>
			<p>Via votre profil, vous pourrez gérer votre tableau de bord
				personnalisé comportant tous les sujets que vous avez choisi. Chaque
				sujet permet d'accéder à des suivis selon vos données, des conseils
				personnalisés ou encore des témoignages.</p>
			<p>Nous avons essayé à traver ce projet de proposer une solution
				généraliste pour traiter différentes problematiques sans pour autant
				rendre son utilisation complexe.</p>
		</div>

		<div>
			<br />
			<h1 class="text-center col-12">Qui sommes-nous ?</h1>
			<br />
			<div class="row flex-row justify-content-around text-center flex-wrap">
				<div class="col-xl-3 col-sm-6 col-xs-12 us">
					<h4>Rémy Vallet</h4>
					<p class="bioRemy"></p>
					<a href="https://www.linkedin.com/in/valletremy/" target="_blank">
						<img src="http://www.aidemoi.net/Panda%20g%C3%A9ant.jpg" class="imgppl"/>
					</a>
					<p>
						<a href="https://www.linkedin.com/in/valletremy/" target="_blank">
							<img src="<c:url value="/ressources/image/linkedin.png" />"
							width="50px" height="50px" />
						</a>
					</p>
				</div>
				<div class="col-xl-3 col-sm-6 col-xs-12 us">
					<h4>Yohann Morin</h4>
					<p class="bioYohann"></p>
					<a href="https://www.linkedin.com/in/yohann-morin-javadeveloper/"
						target="_blank"> <img
						src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Ananas_jardin_Martinique.jpg/1024px-Ananas_jardin_Martinique.jpg" class="imgppl" />
					</a>
					<p>
						<a href="https://www.linkedin.com/in/yohann-morin-javadeveloper/"
							target="_blank"><img
							src="<c:url value="/ressources/image/linkedin.png" />"
							width="50px" height="50px" /></a>
					</p>
				</div>
				<div class="col-xl-3 col-sm-6 col-xs-12 us">
					<h4>François-Pierre Petitjean</h4>
					<p class="bioFrancois"></p>
					<a
						href="https://www.linkedin.com/in/fran%C3%A7ois-petitjean-61299451/"
						target="_blank"> <img
						src="https://www.pokepedia.fr/images/thumb/3/39/Carapuce_de_Sacha.png/300px-Carapuce_de_Sacha.png" class="imgppl"/>
					</a>
					<p>
						<a
							href="https://www.linkedin.com/in/fran%C3%A7ois-petitjean-61299451/"
							target="_blank"><img
							src="<c:url value="/ressources/image/linkedin.png" />"
							width="50px" height="50px" /></a>
					</p>
				</div>

				<div class="col-xl-3 col-sm-6 col-xs-12 us">
					<h4>Thomas Savaton</h4>
					<p class="bioThomas"></p>
					<a
						href="https://www.linkedin.com/in/thomas-savaton-developpeurjava/"
						target="_blank"> <img
						src="https://media.giphy.com/media/o0vwzuFwCGAFO/giphy.gif" class="imgppl"/>
					</a>
					<p>
						<a
							href="https://www.linkedin.com/in/thomas-savaton-developpeurjava/"
							target="_blank"><img
							src="<c:url value="/ressources/image/linkedin.png" />"
							width="50px" height="50px" /></a>
					</p>
					<br /> <br /> <br />
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
