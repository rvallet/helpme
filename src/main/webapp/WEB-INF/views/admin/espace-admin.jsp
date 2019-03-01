<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="espace-admin.title" /></title>
<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />
<style>
</style>
</head>
<body>
<div class="container-fluid d-flex justify-content-center align-items-center conteneursmall">
	<div class="container col-12 d-flex flex-column justify-content-center align-items-center text-center">
		<div class="col-xl-6 col-sm-12">
			<h1>
				<spring:message code="espace-admin.h1" />
			</h1>
			</div>
			<div class="col-xl-6 col-sm-12">
				<div class="list-group">
					<a href="admin/users?page=1&max=5" title="Gestion des utilisateurs"
						class="list-group-item list-group-item-action"> <spring:message
							code="espace-admin.lblUserManage" />
					</a> <a href="admin/problems"
						class="list-group-item list-group-item-action"> <spring:message
							code="espace-admin.lblPbManage" />
					</a> <a href="admin/problemstypes"
						class="list-group-item list-group-item-action"><spring:message
							code="espace-admin.lblPbTypeManage" /> </a> <a href="admin/advice"
						class="list-group-item list-group-item-action"><spring:message
							code="espace-admin.lblAdviceManage" /> </a> <a
						href="admin/testemony"
						class="list-group-item list-group-item-action"><spring:message
							code="espace-admin.lblTestemonyManage" /> </a>
				</div>
			</div>
		</div>
		</div>
</body>
</html>