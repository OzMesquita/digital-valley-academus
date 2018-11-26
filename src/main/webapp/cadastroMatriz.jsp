<%@ page import="br.ufc.russas.n2s.academus.util.Constantes"%>
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
				<div class="form-group">
				<form action="CadastrarMatriz" method="post">
					<label for="nomeMatrizInput">Nome*</label>
					 <input type="text" name="nome" class="form-control" id="nomeMatrizInput" aria-describedby="tituloHelp" placeholder="Digite o nome da matriz curricular" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: ENGENHARIA DE SOFTWARE</small>		
			         <br>
			         
			         <label for="periodoInput"> Período Letivo*</label>
					 <input type="text" name="periodo_letivo" class="form-control" id="periodoInput" aria-describedby="tituloHelp" placeholder="Digite o período letivo" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 2018.1 </small>		
					 	
			         <br>
			         
					 <label for="cargaMatrizInput"> Carga horária total*</label>
					 <input type="text" name="carga_horaria" class="form-control" id="cargaMatrizInput" aria-describedby="tituloHelp" placeholder="Digite a carga horária total" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 3200 </small>		
			        	
			         <br>	
						
					 <label for="prazoMinimoInput"> Prazo minímo para conclusão*</label>
					 <input type="text" name="prazo_minimo" class="form-control" id="prazoMinimoInput" aria-describedby="tituloHelp" placeholder="Digite o número minímo  de semestres para concluir o curso" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 8 </small>		
				
			         <br>	
					 <label for="prazoMaximoInput"> Prazo máximo para conclusão*</label>
					 <input type="text" name="prazo_maximo" class="form-control" id="prazoMaximoInput" aria-describedby="tituloHelp" placeholder="Digite o número máximo de semestres para concluir o curso" required>
					 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 12 </small>		
				
			         <br>
					
					 <label for="vigenteInput">Vigente*</label>
			                        <select type="text" name="vigente" class="form-control custom-select" id="vigenteInput" required>
			                            <option value="" selected="selected" disabled="disabled">Selecione uma opção</option>
			                            <option value="true">Sim</option>
			                            <option value="false">Não</option>              
			                        </select>
			         <br>
					
					<label for="ativoInput">Ativo*</label>
			                        <select type="text" name="ativo" class="form-control custom-select" id="ativoInput" required>
			                            <option value="" selected="selected" disabled="disabled">Selecione uma opção</option>
			                            <option value="true">Sim</option>
			                            <option value="false">Não</option>              
			                        </select>
			         <br>
					
					<label for="cursoInput">Curso*</label>
			                        <select type="text" name="id_curso" class="form-control custom-select" id="cursoInput" required>
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
							<button type="submit" class="btn btn-primary">Confirmar</button>
							<button type="button" class="btn btn-primary">Cancelar</button>
						</div>
					</div>
				</form>
				</div>
				</div>
	</div>
	</div>
	</body>
</html>