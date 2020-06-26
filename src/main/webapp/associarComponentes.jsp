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

	List<MatrizCurricular> matrizes = new ArrayList<MatrizCurricular>();
	MatrizCurricularDAO matrizdao = new JDBCMatrizCurricularDAO();

	List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	DisciplinaDAO disciplinadao = new JDBCDisciplinaDAO();
	
	boolean deuCerto = true;
	
	try{
		
		matrizes = matrizdao.listar();
		disciplinas = disciplinadao.listar();
		
			
	} catch(Exception e){
		deuCerto = false;
	}
	
%>

<!DOCTYPE html>
<html lang=pt-br>
	<head>
		<title>Associar Componentes a Matriz</title>
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
						<li class="breadcrumb-item active" aria-current="page">Associar Componentes</li>
					</ol>
					</nav>
					<h1>Associar Componentes</h1>
					
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
						if(!disciplinas.isEmpty() && deuCerto){
					
					%>	
					
					<div class="component-feedback">
	          		</div>
										
					<form action="AssociarComponentes" method="post">
						
						<div class="form-group">
							<label for="matrizesInput">Matrizes</label>
								<select id="matrizesInput" name="matriz" class="form-control">
									<option value="" selected="selected" disabled="disabled">Selecione a matriz para adição dos componentes</option>
									<%for(MatrizCurricular matriz : matrizes){%>
										<option id="matrizOption-<%=matriz.getIdMatriz()%>" value="<%=matriz.getIdMatriz()%>"><%=matriz.getNome()%> - <%=matriz.getPeriodoLetivo()%></option>
									<%}%>
								</select>
								&nbsp;&nbsp;
						</div>
						<div class="form-row">
							<div class="form-group col-md-8">
								<label for="componentesInput">Disciplinas</label>
									<select id="componentesInput" name="componente" class="form-control">
										<option value="" selected="selected" disabled="disabled">Selecione as disciplinas que compõem esta matriz</option>
										<%for(Disciplina disciplina : disciplinas){%>
											<option id="disciplinaOption-<%=disciplina.getId()%>" value="<%=disciplina.getId()%>"><%=disciplina.getNome()%></option>
										<%}%>
									</select>
							</div>
							<div class="form-group col-md-4">
								<label for="naturezaInput">Natureza do Componente</label>
								<div class="form-row">
								<select id="naturezaInput" name="natureza" class="form-control col-md-7">
									<option value="OBRIGATÓRIA" selected="selected">OBRIGATÓRIA</option>
									<option value="OPTATIVA">OPTATIVA</option>
								</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="preRequisitosInput">Disciplinas Pré-Requisitadas</label>
							<div class="form-row">
								<select id="preRequisitosInput" class="form-control" style="margin-left: 6px">
									<option value="" selected="selected" disabled="disabled">Selecione as disciplinas previamente requisitadas por este componente</option>
									<%for(Disciplina disciplina : disciplinas){%>
										<option id="disciplinaOption-<%=disciplina.getId()%>" value="<%=disciplina.getId()%> - <%=disciplina.getNome()%>"><%=disciplina.getNome()%></option>
									<%}%>
								</select>
							</div>
						<br>
					          <ul class="list-group col-md-12" id="listaPreRequisitos"></ul>                                
					    <br>
						</div>
						<div class="modal-footer">
							<a href="ListarMatrizes">
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
	
	$(document).ready(function(){
		
		$("#preRequisitosInput").change(function (){
			adicionarPreRequisito();
		});
		
		$(document).on("click", ".remove-pre-requisito", function (){
			$(this).parents("li").remove();
		});
	});
	
	var listaPreRequisitos = [];
	var numPreRequisitos = 0;
	
	function adicionarPreRequisito(){	
		
		if (listaPreRequisitos.includes(document.getElementById("preRequisitosInput").value)){
			$('.component-feedback').append(
				'<div class="alert alert-warning alert-dismissible fade show" role="alert">'+
				'	O Pré-requisito já foi adicionado.'+
				'	<button type="button" class="close" data-dismiss="alert" aria-label="Close">'+
			    '		<span aria-hidden="true">&times;</span>'+
			  	'	</button>'+
				'</div>'
			);
		} else if(document.getElementById("preRequisitosInput").value == document.getElementById("componentesInput").value){
			$('.component-feedback').append(
				'<div class="alert alert-danger alert-dismissible fade show" role="alert">'+
				'	A disciplina não pode ter ela própria como pré requisito.'+
				'	<button type="button" class="close" data-dismiss="alert" aria-label="Close">'+
			    '		<span aria-hidden="true">&times;</span>'+
			  	'	</button>'+
				'</div>'
			);
		} else if(document.getElementById("preRequisitosInput").value !== ""){
			var preRequisito = document.getElementById("preRequisitosInput").value;
			$("#listaPreRequisitos").append(
				'<li class="list-group-item">'+
					'<div class="row">'+
						'<div class="col-sm-10">'+
							'<input type="text" readonly class="form-control-plaintext" name="preRequisitos[]" value="'+preRequisito+'">'+
						'</div>'+
						'<div class="col-sm-2">'+
							//'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePreRequisito(\'#'+preRequisito+'\')">clear</button>'+ 
							'<button type="button" class="btn btn-light btn-sm material-icons float-right remove-pre-requisito" style="font-size: 15px;">clear</button>'+ 
						'</div>'+
					 '</div>'+
			 	'</li>'		
			);
			listaPreRequisitos[numPreRequisitos] = preRequisito;
			numPreRequisitos++;
		}
		document.getElementById("preRequisitosInput").selectedIndex = -1;
		document.getElementById("preRequisitosInput").selectedIndex = 0;
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