<%@page import="br.ufc.russas.n2s.academus.util.Constantes"%>
<%@page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="utf-8"/>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
		<title>Início</title>
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/design.css" />
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/bootstrap-datepicker.css" />
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/bootstrap-datepicker.standalone.css" />
	
		
	</head>
	<body>
		<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
		<div class="container-fluid">
			<div class="row row-offcanvas row-offcanvas-right">
				<% 
				PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");
				if (per != null){
					if (per.getNivel() == NivelAcademus.ALUNO){
						%>
						<c:import url="jsp/elements/menu-lateral-esquerdo-Aluno.jsp"
						charEncoding="UTF-8"></c:import>
						<%
					} else if (per.getNivel() == NivelAcademus.COORDENADOR){
						%>
						<c:import url="jsp/elements/menu-lateral-esquerdo-Coordenador.jsp"
						charEncoding="UTF-8"></c:import>
						<%
					} else if (per.getNivel() == NivelAcademus.SECRETARIO){
						%>
						<c:import url="jsp/elements/menu-lateral-esquerdo-Secretario.jsp"
						charEncoding="UTF-8"></c:import>
						<%
					} else{
						%>
						<c:import url="jsp/elements/menu-lateral-esquerdo.jsp"
						charEncoding="UTF-8"></c:import>
						<%
					}
				} else {
					%>
					<c:import url="jsp/elements/menu-lateral-esquerdo.jsp"
					charEncoding="UTF-8"></c:import>
					<%
				}
				%>
				<div class="col-sm-8">
					<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Você está em:</li>
						<li class="breadcrumb-item" aria-current="page">Início</li>
					</ol>
					</nav>
					
					<c:import url="jsp/elements/listagemSolicitacao.jsp" charEncoding="UTF-8"></c:import>
				</div>
			</div>
		</div>
	</body>
</html>