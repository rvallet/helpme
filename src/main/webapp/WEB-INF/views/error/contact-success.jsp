<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div class="container col-12 d-flex justify-content-center">
		<div class="col-7 homeConnect text-center jumbotron jumbotron-form" style="border-radius: 10px 100px/120px;">
			<h1>
				<spring:message code="error.contact-success.title" />
			</h1>
			<p>
				<spring:message code="error.contact-success.msg" />
			</p>
			<p>
			<spring:message code="error.contact-success.p1.1" /><a href="./"><spring:message code="error.contact-success.p1.2" /></a> ?
			</p>
		</div>
	</div>
	</div>
</body>