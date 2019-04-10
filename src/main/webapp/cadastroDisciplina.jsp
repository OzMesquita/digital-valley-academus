<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Cadastro de Disciplina</title>
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
						<li class="breadcrumb-item active" aria-current="page">Cadastra Disciplina</li>
					</ol>
					</nav>
					<h1>Cadastrar Disciplina</h1>
					<p>Atenção: Os campos abaixo (*) são de preenchimento
						obrigatório</p>
					<br>
					<div class="form-group">
						<form action="CadastrarDisciplina" method="post">
							<label for="idInput">Código da Disciplina*</label> <input type="text" name="id_disciplina" class="form-control" id="idInput" textTransform="uppercase" aria-describedby="tituloHelp" placeholder="Digite o código da Disciplina" required > 
							<small id="tituloHelp" class="form-text text-muted"> Exemplo: RUS1001</small> 
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="nomeInput"> Nome da Disciplina*</label> 
							<input type="text" name="nome" class="form-control" id="nomeInput" aria-describedby="tituloHelp" placeholder="Digite o nome da Disciplina" required> 
							<small id="tituloHelp" class="form-text text-muted"> Exemplo: FUNDAMENTOS DE PROGRAMAÇÃO </small> 
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="cargaInput">  Carga Horária*</label> 
							<input type="text" name="carga" class="form-control" id="cargaInput" aria-describedby="tituloHelp" placeholder="Digite a carga horária" required> 
							<small id="tituloHelp" class="form-text text-muted"> Exemplo: 64</small> 
							 <div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="creditosInput"> Créditos da Disciplina*</label> 
							<input type="text" name="creditos" class="form-control" id="creditosInput" aria-describedby="tituloHelp" placeholder="Digite os créditos da disciplina" required> 
							<small id="tituloHelp" class="form-text text-muted"> Exemplo: 4 </small> 
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