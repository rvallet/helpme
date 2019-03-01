<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div class="dashboard container-fluid d-flex flex-column conteneurbis col-12 justify-content-around align-items-center">
		<div class="d-flex flex-column justify-content-center align-items-center col-7 homeConnect text-center jumbotron jumbotron-form" style="border-radius: 10px 100px/120px;">
			<h1>
				<spring:message code="error.pwd-resetsuccess.title" />
			</h1>
			<p>
				<spring:message code="error.pwd-resetsuccess.msg" />
			</p>
			<p>
			<spring:message code="error.pwd-resetsuccess.p1.1" /><a href="./authenticate"><spring:message code="error.pwd-resetsuccess.p1.2" /></a> ?
			</p>
		</div>
	</div>
</div>
</body>