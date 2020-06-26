<%@page import="br.ufc.russas.n2s.academus.dao.JDBCCursoDAO"%>
<%@page import="br.ufc.russas.n2s.academus.dao.CursoDAO"%>
<%@page import="br.ufc.russas.n2s.academus.model.Curso"%>
<%@page import="br.ufc.russas.n2s.academus.model.Professor"%>
<%@page import="br.ufc.russas.n2s.academus.dao.JDBCProfessorDAO"%>
<%@page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%

	List<Curso> curso = new ArrayList<Curso>();
	CursoDAO cursodao = new JDBCCursoDAO();
	JDBCProfessorDAO professorDao = new JDBCProfessorDAO();
	List<Professor> professores = professorDao.listar();
	List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	DisciplinaDAO disciplinadao = new JDBCDisciplinaDAO();
	
	
	boolean deuCerto = true;
	
	try{
		
		curso = cursodao.listar();
		
			
	} catch(Exception e){
		deuCerto = false;
	}
	
%>

<!DOCTYPE html>
<html lang=pt-br>
	<head>
		<title>Associar Disciplinas ao professor</title>
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
		
		<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css" rel="stylesheet" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/js/select2.min.js"></script>
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
						<li class="breadcrumb-item active" aria-current="page">Associar Disciplinas</li>
					</ol>
					</nav>
					<h1>Associar Disciplinas</h1>
					
					<!-- Caso a edição seja bem sucedida vem para essa tela com uma mensagem de sucesso -->
					<% if (request.getAttribute("success") != null){ %>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<%= request.getAttribute("success") %>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    	<span aria-hidden="true">&times;</span>
						  	</button>
						</div>
					<% } %>
					
					<!-- Caso a edição seja mal sucedida vem para essa tela com uma mensagem de erro -->
					<% if (request.getAttribute("erro") != null){ %>
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
							<%= request.getAttribute("erro") %>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    	<span aria-hidden="true">&times;</span>
						  	</button>
						</div>
					<% } %>
					
					<br>
					<%
						if(deuCerto){
					
					%>	
					
					<div class="component-feedback">
	          		</div>
										
					<form action="AssociarDisciplina" method="post">
							
							<input type="hidden" name="observador" value="1">
							
							<div class="form-group">
								<label for="cursoInput">Curso</label>
								<select id="cursoInput" name="curso" class="form-control">
								<option></option>
									<%
										for(Curso p: curso){
									%>
											<option value="<%=p.getIdCurso() %>"> <%= p.getNome() %></option> 
									<%
										}
									%>
								</select>
							</div>
							
							<div class="form-group">
								<label for="professorInput">Professor</label>
								<select id="professorInput" name="professor" class="form-control">
								<option></option>
									<%
										for(Professor p: professores){
									%>
											<option value="<%=p.getId() %>"> <%= p.getNome() %></option> 
									<%
										}
									%>
								</select>
								
							</div>
							
							<div class="form-group">
								<label for="discplinaInput">Lista Disciplinas</label>
								<select id="disciplinaInput" name="disciplina" class="form-control">
								<option></option>
									
								</select>
							</div>
							
						
						
						<div class="form-group">
							<h3 id="listaCabecalho"></h3>
					        <ul class="list-group col-md-12" id="listaDisciplinas"></ul>                                
					    	<br>
						</div>
						<div class="modal-footer">
							<a href="ListarDisciplinas">
								<button type="button" class="btn btn-primary btn-sm">Cancelar</button></a>
							
							<button type="submit" name="button" class="btn btn-primary btn-sm">Confirmar</button>
							
						</div>
					</form>
					<%
						} else {
							%>
							Disciplinas e matrizes não encontradas.
							<%
						}
					%>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
	<script>
	
	//variáveis para auxiliar na listagem das disciplinas
	var listaDisciplinas = [];
	var numDisciplinas = 0;
	
	
	$(document).ready(function(){
		
		$("#disciplinaInput").change(function (){
			adicionarDisciplina();
		});
		
		$(document).on("click", ".remove-disciplina", function (){
			$(this).parents("li").remove();
			
		});
		$('#professorInput').select2({
			placeholder: 'Selecione um professor',
			allowClear: true,
			minimumInputLength: 2
		});
		
		$('#professorInput').change(function (){
			
			$("#listaCabecalho").text("Disciplinas Associadas");
			
			$("#listaDisciplinas").html("");
			listaDisciplinas = [];
			numDisciplinas = 0;
			
			$.get('/Academus/BuscarDisciplinaPorProfessor?id='+$("#professorInput").val(), function(data){
				
				data.forEach(function(disc, index){
					$("#listaDisciplinas").append(
						'<li class="list-group-item">'+
							'<div class="row">'+
								'<div class="col-sm-10">'+
									'<input type="text" readonly class="form-control-plaintext" name="disciplinas[]" value="'+disc.text+'">'+
								'</div>'+
								'<div class="col-sm-2">'+
									//'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePreRequisito(\'#'+preRequisito+'\')">clear</button>'+ 
									'<button type="button" class="btn btn-light btn-sm material-icons float-right remove-disciplina" style="font-size: 15px;">clear</button>'+ 
								'</div>'+
							 '</div>'+
					 	'</li>'		
					);
					listaDisciplinas[numDisciplinas] = disc.text;
					numDisciplinas++;
				});
			});
		});
		
		$('#cursoInput').select2({
			placeholder: 'Selecione um curso',
			allowClear: true
		});
		
		$('#disciplinaInput').select2({
			placeholder: 'Selecione uma disciplina',
			allowClear: true,
			minimumInputLength: 2
		});
		
		$('#cursoInput').change(function() {
			$('#disciplinaInput').select2({
				placeholder: 'Selecione uma disciplina',
				allowClear: true,
				dataType: 'json',
				minimumInputLength: 2,
				ajax: {
					url: function(){
							return '/Academus/BuscarDisciplinaPorCurso?id='+$("#cursoInput").val();
						},
					processResults: function (data) {
			      		return {
			       			results: data
			      		};
					},
					data: function (params) {
				      var query = {
				        q: params.term
				      }
				      
				      return query;
				    }
				}
			});
		});
	});
	
	
	function adicionarDisciplina(){	
		
		if (listaDisciplinas.includes($("#disciplinaInput option:selected").text())){
			$('.component-feedback').append(
				'<div class="alert alert-warning alert-dismissible fade show" role="alert">'+
				'	A disciplina já está associada.'+
				'	<button type="button" class="close" data-dismiss="alert" aria-label="Close">'+
			    '		<span aria-hidden="true">&times;</span>'+
			  	'	</button>'+
				'</div>'
			);
		} else if($("#disciplinaInput").val() !== ""){
			var disc = $("#disciplinaInput option:selected").text();
			$("#listaDisciplinas").append(
				'<li class="list-group-item list-group-item-success">'+
					'<div class="row">'+
						'<div class="col-sm-10">'+
							'<input type="text" readonly class="form-control-plaintext" name="disciplinas[]" value="'+disc+'">'+
						'</div>'+
						'<div class="col-sm-2">'+
							//'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePreRequisito(\'#'+preRequisito+'\')">clear</button>'+ 
							'<button type="button" class="btn btn-light btn-sm material-icons float-right remove-disciplina" style="font-size: 15px;">clear</button>'+ 
						'</div>'+
					 '</div>'+
			 	'</li>'		
			);
			listaDisciplinas[numDisciplinas] = disc;
			numDisciplinas++;
		}
		document.getElementById("disciplinaInput").selectedIndex = -1;
		document.getElementById("disciplinaInput").selectedIndex = 0;
		//atualizarPreRequisito();
	}
	
	function atualizarPreRequisito(){
		console.log("atualizou");
		var listp = document.getElementById("listaPreRequisitos");
		listp.innerHTML = '';
		for(i=0;i<listaPreRequisitos.length;i++){
			if(listaPreRequisitos[i] !== ""){
				listp.innerHTML += '<li class="list-group-item">'+
								   '<input type="hidden" name="listaPreRequisito[]" value="'+listaPreRequisitos[i]+'" style="display: none;">'+listaPreRequisitos[i]+
						 		   '<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePreRequisito('+i+')">clear</button>'+
						 		   '</li>';
			}
		}
	}
	
	function removePreRequisito(pr){
		//document.getElementById(pr).remove();
		console.log(pr);
		console.log($(pr));
		console.log($("#CAD0001 - CÁLCULO I"));
		console.log($(pr) == $("#CAD0001 - CÁLCULO I"));
		$("#CAD0001 - CÁLCULO I").remove();
	}
	
	function verificarPreRequisitosExistentes(disciplina){
		aux = true;
		for(i=0;i<listaPreRequisitos.length; i++){
			if((listaPreRequisitos[i] != null) && (disciplina.id == listaPreRequisitos[i].id)){
				aux = false;
			}
		}
		if(aux == true){
			listaPreRequisitos[numPreRequisitos] = disciplina;
			numPreRequisitos++;
			atualizarPreRequisito();
		}
	}
	</script>
</html>