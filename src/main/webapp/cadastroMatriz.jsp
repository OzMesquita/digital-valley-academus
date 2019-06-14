<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Cadastro de Matriz Curricular </title>
		<meta charset="utf-8"/>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
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
					<li class="breadcrumb-item active" aria-current="page">Cadastrar Matriz Curricular</li>
				</ol>
				</nav>
				<h1>Cadastrar Matriz Curricular </h1>
			                <p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
			                <br>
			                
			    
			    <!-- Caso o cadastro seja bem sucedido vem para essa tela com uma mensagem de sucesso -->
				<% if (request.getAttribute("success") != null){ %>
					<div class="alert alert-success alert-dismissible fade show" role="alert">
						<%= request.getAttribute("success") %>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
				<% } %>
				
				<!-- Caso o cadastro não seja bem sucedido vem para essa tela com uma mensagem de erro -->
				<% if (request.getAttribute("erro") != null){ %>
					<div class="alert alert-danger alert-dismissible fade show" role="alert">
						<%= request.getAttribute("erro") %>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
				<% } %>
			    
			    
				<div class="form-group">
				<form action="CadastrarMatriz" method="post">
					<label for="nomeMatrizInput">Nome*</label>
					 <input type="text" name="nome" class="form-control" id="nomeMatrizInput" aria-describedby="tituloHelp" placeholder="Digite o nome da matriz curricular" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: ENGENHARIA DE SOFTWARE</small>		
			         <br>
			         
			         <label for="periodoInput"> Período Letivo*</label>
					 <input type="text" pattern="[0-9]{4}.[1-2]{1}$" name="periodo_letivo" class="form-control" id="periodoInput" aria-describedby="tituloHelp" placeholder="Digite o período letivo" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 2018.1 </small>		
					 	
			         <br>
			         
					 <label for="cargaMatrizInput"> Carga horária total*</label>
					 <input type="number" min="1" step="1" name="carga_horaria" class="form-control" id="cargaMatrizInput" aria-describedby="tituloHelp" placeholder="Digite a carga horária total" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 3200 </small>		
			        	
			         <br>	
						
					 <label for="prazoMinimoInput"> Prazo mínimo para conclusão*</label>
					 <input type="number" min="1" step="1" name="prazo_minimo" class="form-control" id="prazoMinimoInput" aria-describedby="tituloHelp" placeholder="Digite o número minímo  de semestres para concluir o curso" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 8 </small>		
				
			         <br>	
					 <label for="prazoMaximoInput"> Prazo máximo para conclusão*</label>
					 <input type="number" min="1" step="1" name="prazo_maximo" class="form-control" id="prazoMaximoInput" aria-describedby="tituloHelp" placeholder="Digite o número máximo de semestres para concluir o curso" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 12 </small>		
				
			         <br>
					
					 <label for="vigenteInput">Vigente*</label>
			                        <select name="vigente" class="form-control custom-select" onchange="ativoController()" id="vigenteInput" required>
			                            <option value="" selected="selected" disabled="disabled">Selecione uma opção</option>
			                            <option value="true">Sim</option>
			                            <option value="false">Não</option>              
			                        </select>
			         <br>
					<br>
					<label for="ativoInput">Ativo*</label>
			                        <select name="ativo" class="form-control custom-select" onchange="vigenteController()" id="ativoInput" required>
			                            <option value="" selected="selected" disabled="disabled">Selecione uma opção</option>
			                            <option value="true">Sim</option>
			                            <option value="false">Não</option>              
			                        </select>
			         <br>
					<br>
					<label for="cursoInput">Curso*</label>
			                        <select name="id_curso" class="form-control custom-select" id="cursoInput" required>
			                            <option value="" selected="selected" disabled="disabled">Selecione o curso</option>
			                            <option value="1">CIÊNCIA DA COMPUTAÇÃO</option>
			                            <option value="2">ENGENHARIA CIVIL</option>
			                            <option value="3">ENGENHARIA DE PRODUÇÃO</option>
			                            <option value="4">ENGENHARIA DE SOFTWARE</option> 
			                            <option value="5">ENGENHARIA MECÂNICA</option>             
			                        </select>
			         <br>
			
					
					<div class="modal-footer">
						<div id="botoes" class="controls">
							<a href="Inicio"><button type="button" class="btn btn-primary">Cancelar</button></a>
							<button type="submit" class="btn btn-primary">Confirmar</button>
						</div>
					</div>
				</form>
				</div>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
	<script type="text/javascript">
	
	var vigente = document.getElementById("vigenteInput");
	var ativo = document.getElementById("ativoInput");
	
	function ativoController(){
		if(vigente.options[vigente.selectedIndex].value == "true" && ativo.options[ativo.selectedIndex].value != "true"){
			ativo.selectedIndex = -1;
			ativo.selectedIndex = 1;
		}
	}
	
	function vigenteController(){
		if(ativo.options[ativo.selectedIndex].value == "false" && vigente.options[vigente.selectedIndex].value != "false"){
			vigente.selectedIndex = -1;
			vigente.selectedIndex = 2;
		}
	}
	
	$(document).ready(function(){
	    $('#periodoInput').mask('0000.x', {
	    										translation: {
	    											'x': {pattern: /[1-2]/}
	    										}
	    									});
	});
	
	</script>
</html>