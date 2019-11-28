<%@page import="br.ufc.russas.n2s.academus.dao.DAOFactory"%>
<%@page import="br.ufc.russas.n2s.academus.model.RecorrecaoDeProva"%>
<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Aluno" %>
<%@ page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.ProfessorDAO" %>
<%@ page import="br.ufc.russas.n2s.academus.model.Professor" %>

<%@ page import="java.util.*"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <%
 	
 	RecorrecaoDeProva rdp = (RecorrecaoDeProva) session.getAttribute("recorrecaoDeProva");
 	
 	DAOFactory df = new DAOFactoryJDBC();
 	List<Professor> professores = new ArrayList<Professor>();
 
 	try {
 		ProfessorDAO professorDao = df.criarProfessorDAO();
 		professores = professorDao.listar();
 	} catch (Exception e){
 		e.printStackTrace();
 	}
	
%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Visualizando Segunda Chamada</title>
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
						<li class="breadcrumb-item active" aria-current="page">Visualizar Recorreção de prova</li>
					</ol>
					</nav>
					
					<h1>Visualizar Recorreção de prova</h1>
					
					<br>
					<!-- Caso a mensagem bem sucedido vem para essa tela com uma mensagem de sucesso -->
						<% if (request.getAttribute("success") != null){ %>
							<div class="alert alert-success alert-dismissible fade show" role="alert">
								<%= request.getAttribute("success") %>
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							    	<span aria-hidden="true">&times;</span>
							  	</button>
							</div>
						<% } %>
						
						<!-- Caso a mensagem não seja bem sucedido vem para essa tela com uma mensagem de erro -->
						<% if (request.getAttribute("erro") != null){ %>
							<div class="alert alert-danger alert-dismissible fade show" role="alert">
								<%= request.getAttribute("erro") %>
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							    	<span aria-hidden="true">&times;</span>
							  	</button>
							</div>
						<% } %>
						
					<%
					if (rdp != null){
					%>
						
					<form action="#" method="post">
							<div class="form-group">
								<label for="alunoInput">Aluno</label>
								<input type="text" id="alunoInput" name="nomeAluno" class="form-control" value="<%= rdp.getAluno().getNome() %>" readonly>
							</div>
							
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label for="matriculaInput">Matricula</label>
										<input type="text" id="matriculaInput" name="matricula" class="form-control" value="<%= rdp.getAluno().getMatricula() %>" readonly>
									</div>
								</div>
								<div class="col-md-9">
									<div class="form-group">
										<label for="cursoInput">Curso</label>
										<input type="text" id="cursoInput" name="curso" class="form-control" value="<%= rdp.getAluno().getCurso().getNome() %>" readonly>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label for="professorInput">Professor</label>
								<select id="professorInput" name="professor" class="form-control" disabled>
								<option></option>
									<%
										for(Professor p: professores){
											if (p.getId() == rdp.getProfessor().getId()){
									%>
									
												<option value="<%=p.getId()%>" selected> <%= p.getNome() %></option> 
									
									<%
											} else {
									%>
									
												<option value="<%=p.getId()%>"> <%= p.getNome() %></option> 
									
									<%
											}
										}
									%>
								
								</select>
								
							</div>
							<div class="col-md-7">
									<div class="form-group">
										<label for="discplinaInput">Disciplina</label>
										<select id="disciplinaInput" name="disciplina" class="form-control" disabled>
											<option value="<%=rdp.getDisciplina().getId()%>" selected><%=rdp.getDisciplina().getId()%> - <%=rdp.getDisciplina().getNome()%></option>
										</select>
									</div>
							</div>
							
							<div class="row">
								<div class="col-md-2">
									<div class="form-group">
										<label for="dataDaProvaInput">Data da Prova realizada</label>
										<input type="date" id="dataDaProvaInput" name="dataDaProva" class="form-control" value="<%=rdp.getDataProva()%>" readonly>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label for="horarioDaProvaInput">Horário da Prova</label>
										<input type="time" id="horarioDaProvaInput" name="horarioDaProva" class="form-control" value= "<%=rdp.getHorarioDaProva()%>" readonly>

									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="dataRecebimentoInput">Data que recebeu o resultado</label>
										<input type="date" id="dataRecebimentoInput" name="dataRecebimento" class="form-control" value = "<%=rdp.getDataRecebimento()%>" readonly>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label for="horarioDoRecebimentoInput">Horário do recebimento</label>
										<input type="time" id="horarioRecebimentoInput" name="horarioRecebimento" class="form-control" value="<%=rdp.getHorarioRecebimento()%>" readonly>
										
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-lg-6">
									<div class="custom-file">
									 	<input type="file" class="custom-file-input" id="customFileLang" lang="pt" readonly value="">
								<!--Arrumar depois --> <label class="custom-file-label" for="customFile">Anexe aqui a prova</label>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label for="justificativaInput">Justificativa</label>
								<textarea id="justificativaInput" rows="4" name="justificativa" class="form-control" readonly><%=rdp.getJustificativa()%></textarea>
							</div>
							
							<div class="modal-footer">
								<div id="botoes" class="controls">
									<a href="HistoricoSegundaChamada" class="btn btn-primary btn-sm">Voltar</a>
									<!-- <button type="submit" class="btn btn-primary btn-sm">Confirmar</button> -->
								</div>
							</div>
							
					</form>
					<%
					} else {
						%>
						Recorreção de prova não encontrada.
						<%
					}
					%>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
	<script>
	
		var modificado = 0;
		$(document).ready(function(){
			$.get('/Academus/BuscarDisciplinaPorProfessor?id='+$("#professorInput").val(), function(data){
				
				var padrao = $("#disciplinaInput").val();
				
				data.forEach(function(disc, index){
					if (padrao != disc.id){
						$("#disciplinaInput").append(
							'<option value="'+disc.id+'">'+disc.text+'</option>'
						);
					}
				});
			});
			
			$('input').change(function(){
				modificado = 1;
			});
			$('select').change(function(){
				modificado = 1;
			});
			
			$('#professorInput').change(function (){
				
				$("#disciplinaInput").removeAttr('disabled');
				
				$.get('/Academus/BuscarDisciplinaPorProfessor?id='+$("#professorInput").val(), function(data){
					
					data.forEach(function(disc, index){
						$("#disciplinaInput").append(
							'<option value="'+disc.id+'">'+disc.text+'</option>'
						);
					});
				});
			});
			
			$('#professorInput').select2({
				allowClear: true,
				minimumInputLength: 2
			});
		});
		
	</script>
</html>