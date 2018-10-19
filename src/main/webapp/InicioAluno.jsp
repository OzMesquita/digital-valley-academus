<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Início</title>

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
	<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="jsp/elements/menu-lateral-esquerdo-Aluno.jsp"
				charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Você está em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">Início</a></li>
					<!-- PREENCHER HREF -->
				</ol>
				</nav>
				<div class="dropdown right"
                             position: absolute;">
                            <button class="btn dropdown-toggle btn-sm btn-icon filtro_tela"
                                type="button" id="dropdownMenuButton" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                                <i class="material-icons">filter_list</i> <span>Filtrar</span>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right"
                                aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="/Chronos">Todas as atividade</a>
                                <a class="dropdown-item" href="/Chronos/estado/aberta">Atividades
                                    abertas</a> <a class="dropdown-item"
                                    href="/Chronos/estado/andamento">Atividades em andamento</a> <a
                                    class="dropdown-item" href="/Chronos/estado/finalizada">Atividades
                                    finalizadas</a>
                            </div>
                </div>
                               
				<c:import url="VisualizarSolicitacoes" charEncoding="UTF-8"></c:import>
			</div>
		</div>
	</div>
</body>
</html>