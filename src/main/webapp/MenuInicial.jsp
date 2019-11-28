<%@ page import="util.Constantes"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Início</title>
		<meta charset="utf-8"/>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/design.css" />
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/bootstrap-datepicker.css" />
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/bootstrap-datepicker.standalone.css" />
	
	</head>
	<body>
		<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
		<div class="container-fluid">
			<div class="row">
				<c:import url="jsp/elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
				<div class="col-md-10">
					<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Você está em:</li>
						<li class="breadcrumb-item active" aria-current="page">Início</li>
					</ol>
					</nav>
					
					<style>
						.card-text {
							color: #000;
						}
						
						.card:hover {
							text-decoration: none;
						}
					</style>
					<div class="row">
						<h1>Início</h1>
					</div>
					<div class="row justify-content-center">
						<div class="col-md-3 d-inline">
							
							<a href="Inicio" class="card">
								<div class="card-body text-center">
									<strong class="card-text">Aproveitamento de Disciplina</strong>
									<img alt="Ícone de aproveitamento de disciplina" src="resources/images/logoaproveitamento.png" width="50%">
								</div>
							</a>
						</div>
						<div class="col-md-3 d-inline">
							<a href="HistoricoSegundaChamada" class="card">
								<div class="card-body text-center">
									<strong class="card-text text-center">Segunda Chamada</strong>
									<img alt="Ícone de segunda chamada" src="resources/images/logosegundachamada.png" width="50%">
								</div>
							</a>
						</div>
						<div class="col-md-3 d-inline">
							<a href="HistoricoRecorrecaoDeProva" class="card">
								<div class="card-body text-center">
									<strong class="card-text text-center">Recorreção de Prova</strong>
									<img alt="Ícone de recorreção de prova" src="resources/images/logorecorrecao.png" width="50%">
								</div>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
</html>