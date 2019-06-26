<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="br.ufc.russas.n2s.academus.model.ComponenteCurricular" %>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%

	MatrizCurricular matriz = new MatrizCurricular();
	MatrizCurricularDAO daoMC = new JDBCMatrizCurricularDAO();
	
	DisciplinaDAO daoD = new JDBCDisciplinaDAO();
	List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	List<ComponenteCurricular> listaComps = new ArrayList<ComponenteCurricular>();
	
	boolean deuCerto = true;
	try{
		int id_matriz = Integer.parseInt((String) request.getAttribute("id"));
		matriz = daoMC.buscarPorId(id_matriz);
		disciplinas = daoD.listar();
		listaComps = matriz.getComponentes();
	
	}catch (Exception e){
		deuCerto = false;
		e.printStackTrace();
	}
	
%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Atualização da dados da Matriz</title>
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
						<li class="breadcrumb-item active" aria-current="page">Editar Matriz</li>
					</ol>
					</nav>
					<h1>Editar Matriz Curricular</h1>
						<%
							if( deuCerto && matriz.getIdMatriz() != -1 ) {
						%>
						
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
						
						 <form action="AtualizarMatriz" method="post">
						 
						 <label for="nomeMatrizInput">Id*</label>
						 <input type="text" name="id_matriz" value="<%=matriz.getIdMatriz()%>" class="form-control" id="idMatrizInput" aria-describedby="tituloHelp" placeholder="Digite o código da matriz curricular" readonly required>
						 		
	         
				         <br>
									
						 <label for="nomeMatrizInput">Nome*</label> 
						 <input type="text" name="nome" value="<%=matriz.getNome()%>" class="form-control" id="nomeMatrizInput" aria-describedby="tituloHelp" placeholder="Digite o nome da matriz curricular" required> 
						 <small id="tituloHelp" class="form-text text-muted"> Exemplo: ENGENHARIA DE SOFTWARE</small> 
						 
						 <br> 
					
						 <label for="periodoInput"> Período Letivo*</label>
						 <input type="text" name="periodo_letivo" id="periodo_letivo" pattern="[0-9]{4}.[1-2]{1}$" value="<%=matriz.getPeriodoLetivo()%>" class="form-control" id="periodoInput" aria-describedby="tituloHelp" placeholder="Digite o período letivo" required>
						 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 2018.1 </small>		
						 	
				         <br>
				         
						 <label for="cargaMatrizInput"> Carga horária total*</label>
						 <input type="number" name="carga_horaria" value="<%=matriz.getCarga()%>" class="form-control" id="cargaMatrizInput" aria-describedby="tituloHelp" placeholder="Digite a carga horária total" required>
						 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 3200 </small>		
				        	
				         <br>	
							
						 <label for="prazoMinimoInput"> Prazo mínimo para conclusão*</label>
						 <input type="number" name="prazo_minimo" value="<%=matriz.getPrazoMinimo()%>" class="form-control" id="prazoMinimoInput" aria-describedby="tituloHelp" placeholder="Digite o número minímo  de semestres para concluir o curso" required>
						 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 8 </small>		
					
				         <br>	
						 <label for="prazoMaximoInput"> Prazo máximo para conclusão*</label>
						 <input type="number" name="prazo_maximo" value="<%=matriz.getPrazoMaximo()%>" class="form-control" id="prazoMaximoInput" aria-describedby="tituloHelp" placeholder="Digite o número máximo de semestres para concluir o curso" required>
						 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 12 </small>		
					
				         <br>
						
						 <label for="vigenteInput">Vigente*</label>
				                        <select name="vigente" class="form-control custom-select" onchange="ativoController()" id="vigenteInput" required>
				                            <option value="true" selected="<%=matriz.isVigente()?"selected":""%>">Sim</option>
				                            <option value="false"selected="<%=matriz.isVigente()?"":"selected"%>">Não</option>             
				                        </select>
				                        <small id="tituloHelp" class="form-text text-muted">Matriz mais atual adotada pelo Campus para um determinado curso (Deve haver somente uma matriz vigente por curso)</small>
				         <br>
						
						<label for="ativoInput">Ativo*</label>
				                        <select name="ativo" class="form-control custom-select" onchange="vigenteController()" id="ativoInput" required>
				                            <option value="true" selected="<%=matriz.isAtivo()?"selected":""%>">Sim</option>
				                            <option value="false"selected="<%=matriz.isAtivo()?"":"selected"%>">Não</option>              
				                        </select>
				                        <small id="tituloHelp" class="form-text text-muted">Matriz não vigente mas que ainda possui alunos matriculados</small>
				         <br>
						
						<label for="cursoInput">Curso*</label>
						<select name="id_curso" class="form-control custom-select" id="cursoInput" required>
										<option value="1"<%if(matriz.getIdCurso() == 1){%> selected="selected"<%}%>>CIÊNCIA DA COMPUTAÇÃO</option>
			                            <option value="2"<%if(matriz.getIdCurso() == 2){%> selected="selected"<%}%>>ENGENHARIA CIVIL</option>
			                            <option value="3"<%if(matriz.getIdCurso() == 3){%> selected="selected"<%}%>>ENGENHARIA DE PRODUÇÃO</option>
			                            <option value="4"<%if(matriz.getIdCurso() == 4){%> selected="selected"<%}%>>ENGENHARIA DE SOFTWARE</option> 
			                            <option value="5"<%if(matriz.getIdCurso() == 5){%> selected="selected"<%}%>>ENGENHARIA MECÂNICA</option>  											
									</select> 
									<small id="tituloHelp" class="form-text text-muted"></small>
						
						<br>
			<br>
			<div id="botoes" class="modal-footer">
				<button type="button" class="btn btn-primary btn-sm" onclick="funcao()">Cancelar</button>
				<button type="submit" name="button" class="btn btn-primary btn-sm">Confirmar</button>
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
											<a href="ListarMatrizes"><button type="button" class="btn btn-primary btn-sm active">Sim</button></a>
										</div>
									</div>
								</div>
							</div>
							<!-- Fim de Modal -->
		</form>
						<%
							} else {
								%>
								Matriz Curricular não encontrada.
								<%
							}
						%>
					</div>
				</div>
			</div>
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
			window.location.href = "ListarMatrizes";
		}
	}
	
	var vigente = document.getElementById("vigenteInput");
	var ativo = document.getElementById("ativoInput");
	
	function ativoController(){
		if(vigente.options[vigente.selectedIndex].value == "true" && ativo.options[ativo.selectedIndex].value == "false"){
			ativo.selectedIndex = -1;
			ativo.selectedIndex = 0;
		}
	}
	
	function vigenteController(){
		if(ativo.options[ativo.selectedIndex].value == "false" && vigente.options[vigente.selectedIndex].value == "true"){
			vigente.selectedIndex = -1;
			vigente.selectedIndex = 1;
		}
	}
	function verificarDisciplinaAproveitada(){
		var ano = document.getElementByName("periodo_letivo").value.slice(0,4);
		var semestre = document.getElementByName("periodo_letivo").value.slice(5);
		
		var data = new Date();
		var anoAtual = data.getFullYear();
		var mesAtual = data.getMonth();
		var semestreAtual = 0;
		if(mesAtual < 6)
			semestreAtual = 1;
		else 
			semestreAtual = 2;
		
		if(anoAtual < ano){
			if(semestreAtual < semestre){
				alert("O Período Letivo está no futuro");
			}
		}
	}
	
	</script>

</html>