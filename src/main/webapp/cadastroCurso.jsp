<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Cadastrar Curso</title>
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
						<li class="breadcrumb-item"><a href="Inicio">Início</a></li>					
						<li class="breadcrumb-item active" aria-current="page">Cadastrar Curso</li>
					</ol>
					</nav>
					<h1>Cadastrar Curso</h1>
					<p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
					<br>
					<div class="form-group">
						<form action="CadastrarCursoController" method="post">
							
							<label for="nomeInput"> Nome do Curso*</label> 
							<input type="text" name="nome" class="form-control" id="nomeInput" aria-describedby="tituloHelp" placeholder="Digite o nome do curso" required> 
							<small id="tituloHelp" class="form-text text-muted"> Exemplo: ENGENHARIA DE SOFTWARE </small> 
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							<div class="modal-footer">
								<div id="botoes" class="controls">
									<button type="submit" class="btn btn-primary">Confirmar</button>
									<button type="button" class="btn btn-primary">Cancelar</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
</html>