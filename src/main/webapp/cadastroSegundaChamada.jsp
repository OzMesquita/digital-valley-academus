<%@page import="br.ufc.russas.n2s.academus.dao.JDBCAlunoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.AlunoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Aluno"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="util.Constantes" %>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	PerfilAcademus usuario = (PerfilAcademus) request.getSession().getAttribute("userAcademus");
	
	JDBCAlunoDAO alunoDAO = new JDBCAlunoDAO();
	Aluno aluno = alunoDAO.buscarPorId(usuario.getId());
%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Cadastro de Solicitação de Segunda Chamada</title>
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
						<li class="breadcrumb-item active" aria-current="page">Cadastro de Solicitação de Segunda Chamada</li>
					</ol>
					</nav>
					<br>
					<div class="form-group">
						<h1>Cadastrar Solicitação de Segunda Chamada</h1>
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
						
						
						<form action="#" method="post">
							<div class="form-group">
								<label for="alunoInput">Aluno</label>
								<input type="text" id="alunoInput" name="nomeAluno" class="form-control" value="<%= aluno.getNome() %>" readonly>
							</div>
							
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label for="matriculaInput">Matricula</label>
										<input type="text" id="matriculaInput" name="matricula" class="form-control" value="<%= aluno.getMatricula() %>" readonly>
									</div>
								</div>
								<div class="col-md-9">
									<div class="form-group">
										<label for="cursoInput">Curso</label>
										<input type="text" id="cursoInput" name="curso" class="form-control" value="<%= aluno.getCurso().getNome() %>" readonly>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label for="professorInput">Professor</label>
								<input type="text" id="professorInput" name="professor" class="form-control" placeholder="Digite o nome do professor">
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="disciplinaInput">Disciplina</label>
										<input type="text" id="disciplinaInput" name="disciplina" class="form-control" placeholder="Selecione a disciplina pelo seu nome ou código">
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label for="dataDaProvaInput">Data da Prova</label>
										<input type="date" id="dataDaProvaInput" name="dataDaProva" class="form-control">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label for="justificativaInput">Justificativa</label>
								<textarea id="justificativaInput" rows="4" name="justificativa" class="form-control" placeholder="Digite sua justificativa da solicitação"></textarea>
							</div>
							
							<div class="modal-footer">
								<div id="botoes" class="controls">
									<button type="button" class="btn btn-primary btn-sm" onclick="funcao()">Cancelar</button>
									<button type="submit" class="btn btn-primary btn-sm">Confirmar</button>
								</div>
							</div>
							<!-- Modal -->
							<div class="modal fade" id="Voltar" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="modalLabel">Cancelar</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<h2>Deseja mesmo cancelar essa operação?<br>Você irá perder os dados informados!</h2>
										</div>
										<div class="modal-footer">
											<button type="button" id="modal-nao" autofocus class="btn btn-primary btn-sm active" data-dismiss="modal" >Não</button>
											<a href="Inicio"><button type="button" class="btn btn-primary btn-sm active">Sim</button></a>
										</div>
									</div>
								</div>
							</div>
							<!-- Fim de Modal -->
						</form>
					</div>	                
				</div>
			</div>
		</div>
		
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
	<script>
	
		var modificado = 0;
		$(document).ready(function(){
			$('input').change(function(){
				modificado = 1;
			});
			$('select').change(function(){
				modificado = 1;
			});
		});
		
		function funcao(){
			if(modificado === 1){
				$(document).ready(function(){
		            $("#Voltar").modal();
		        });
			} else{
				window.location.href = "Inicio";
			}
		}
		
	</script>
</html>