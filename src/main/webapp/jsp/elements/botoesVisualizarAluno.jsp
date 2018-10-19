<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de Disciplinas</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/design.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.standalone.css" />
</head>
<body>
	<div class="form-group">
		<form method="POST" action="EditarSolicitacao" id="form">
			<td><button  class="btn btn-primary btn-sm active" form="form"
				class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="" > Editar
			</button></td>
		</form>
	</div>
</body>
</html>