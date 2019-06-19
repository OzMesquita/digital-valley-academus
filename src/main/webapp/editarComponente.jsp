<%@page import="com.google.gson.JsonObject"%>
<%@page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@page import="br.ufc.russas.n2s.academus.model.ComponenteCurricular"%>
<%@page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC"%>
<%@page import="br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO"%>
<%@ page import="util.Constantes"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	ComponenteCurricularDAO ccd = new DAOFactoryJDBC().criarComponenteCurricularDAO();
	ComponenteCurricular comp = new ComponenteCurricular();
	int id_matriz = 0;
	
	boolean deuCerto = true;
	try{
		int id = Integer.parseInt((String) request.getAttribute("id_componente"));
		id_matriz =  Integer.parseInt((String)request.getAttribute("id_matriz"));
		comp = ccd.buscarPorId(id);
		
	}catch(Exception e){
		deuCerto = false;
		e.printStackTrace();
	}
%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Atualização da dados do Componente</title>
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
						<li class="breadcrumb-item active" aria-current="page">Editar Componente</li>
					</ol>
					</nav>
					<h1>Editar Componente</h1>
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
					<% if (request.getAttribute("error") != null){ %>
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
							<%= request.getAttribute("error") %>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    	<span aria-hidden="true">&times;</span>
						  	</button>
						</div>
					<% } %>
					
					<% if(deuCerto && comp != null){ %>
						<h3><b>COMPONENTE</b></h3>
						
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Código do Componente</th>
										<th scope="col">Código da Disciplina</th>
										<th scope="col">Nome da Disciplina</th>
										<th scope="col">Carga da Disciplina</th>
										<th scope="col">Créditos da Disciplina</th>
										<th scope="col">Natureza do Componente</th>
									</tr>
								</thead>
								
								<tr>
									<td><%=comp.getIdComponente()%></td>
									<td><%=comp.getDisciplina().getId()%></td>
									<td><%=comp.getDisciplina().getNome()%></td>
									<td><%=comp.getDisciplina().getCarga()%></td>
									<td><%=comp.getDisciplina().getCreditos()%></td>
									<td><%=comp.getNatureza()%></td>
									
								</tr>		
							</table>
						</div>
						<div class="component-feedback">
	          			</div>
	         <form action="AtualizarComponente" method="post" id="EdiComp<%=id_matriz%>">
					<div class="card">
	           <div class="card-header">
	           		<label for="listaComponentes" class="card-title text-uppercase font-weight-bold">Pre Requisitos</label>
	           		</div>
	           <div class="card-body">
		           <table class="table" id="listaComponentes">
				        <thead> 
				           	<tr>
				           		<th scope="col">Código</th>
				           		<th scope="col">Nome</th>
				           		<th scope="col">Carga</th>
				           		<th scope="col">Créditos</th>
				           		<th scope="col"></th>
				           	</tr>
				        </thead>
		           
		           <%
						int i=0;
	        		    List <ComponenteCurricular> componentes = ccd.listar(id_matriz); 
	        		    List <Disciplina> preRequisitos = comp.getPreRequisitos();
						for(Disciplina d : preRequisitos){%>
						<tr>
							<td><%=d.getId()%><input type="hidden" name="dis-id" value="<%=d.getId()%>"></td>
							<td><%=d.getNome()%><input type="hidden" name="dis-nome" value="<%=d.getNome()%>"></td>
							<td><%=d.getCarga()%><input type="hidden" name="dis-carga" value="<%=d.getCarga()%>"></td>
							<td><%=d.getCreditos()%><input type="hidden" name="dis-cred" value="<%=d.getCreditos()%>"></td>
							<td>
								<button class="btn btn-primary btn-sm" style="height: 30px;" type="button" onclick="removePreRequisito('<%=i%>')">
								Excluir</button>
							</td>
						</tr>
						<%i++;}%>
		           <input type="hidden" id="tam" value="<%=i%>">
		           </table>
	           	</div>
	          </div>                         
	        <br>
	        <div class="card-body">
		        <div class="form-row">
						<div class="form-group col-md-8">
							<label for="componentesInput">Disciplinas</label>
								<select id="componentesInput" class="form-control">
									<option value="" selected="selected" disabled="disabled">Selecione as disciplinas que compõem esta matriz</option>
									<%
									
									for(ComponenteCurricular cc : componentes){
										Disciplina disciplina = cc.getDisciplina();
										if(cc.getIdComponente() != comp.getIdComponente()){
										%>
										<option id="disciplinaOption-<%=disciplina.getId()%>" value="<%=disciplina.getId()%>"><%=disciplina.getNome()%></option>
										<%}
									  }%>
								</select>
								<%
									for(ComponenteCurricular cc : componentes){
										Disciplina disciplina = cc.getDisciplina();
										if(cc.getIdComponente() != comp.getIdComponente()){
										%>
										<input name="opc-id" type="hidden" value="<%=disciplina.getId()%>">
										<input name="opc-nome" type="hidden" value="<%=disciplina.getNome()%>">
										<input name="opc-carga" type="hidden" value="<%=disciplina.getCarga()%>">
										<input name="opc-cred" type="hidden" value="<%=disciplina.getCreditos()%>">
										<%
										}
									}
								%>
								&nbsp;&nbsp;
							<ul class="list-group col-md-12" id="listaPreRequisitos">
							</ul>
						</div>
					</div>
					<input type="button" class="btn btn-secondary btn-sm" onclick="adicionarPreRequisito()" id="addPre" value="Adicionar">
					</div>
						<%
								} else {
									%>
									Componente não encontrado.
									<%
								} 
						%>
						<div class="modal-footer">
								<div id="botoes" class="controls">
									<button type="button" name="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#Voltar">
									Voltar</button>
									<input type="hidden" name="id_matriz" value="<%=id_matriz%>">
									<button type="submit" name="id_comp" value="<%=comp.getIdComponente()%>" class="btn btn-primary btn-sm" form="EdiComp<%=id_matriz%>">Confirmar</button>
									
								</div>
							</div>
						</form>
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
											<form action="VisualizarMatriz" method="post">
												<button type="submit" class="btn btn-primary btn-sm active" name="button" value="<%=id_matriz%>">Sim</button>
											</form>
										</div>
									</div>
								</div>
							</div>
							<!-- Fim de Modal -->
					</div>
			</div>
		</div>
	</body>
<script>
	
	$(document).ready(function(){
		atualizarListaOpcoes();
		atualizarListaPreRequisitos();
    	$("#componentesInput").change(function (){
    		adicionarRequisitoEscolhido();
    	});
	});
	var PreRequisitos = [];
	var PreRequisitosEscolhidos = [];
	var OpcaoDisciplina = [];

	function adicionarRequisitoEscolhido(){ 
		var id = document.getElementById("componentesInput").value;
		var indice = indiceOpcao(id);
		
		var requisito = new Object();
		requisito.id = OpcaoDisciplina[indice].id;
		requisito.nome = OpcaoDisciplina[indice].nome;
		requisito.carga = OpcaoDisciplina[indice].carga;
		requisito.credito = OpcaoDisciplina[indice].credito;
		
		if (!verificarNaoRepeticao(requisito)){
	        $('.component-feedback').append(
	            '<div class="alert alert-warning alert-dismissible fade show" role="alert">'+
	            '    O Pré-requisito já foi adicionado.'+
	            '    <button type="button" class="close" data-dismiss="alert" aria-label="Close">'+
	            '        <span aria-hidden="true">&times;</span>'+
	              '    </button>'+
	            '</div>'
	        );
	    } else if( indice !== -1){
	    	var tamanho = PreRequisitosEscolhidos.length;
            PreRequisitosEscolhidos[tamanho] = requisito;
            atualizarPreRequisitoEscolhidoTela();
	            
	    }
	    document.getElementById("componentesInput").selectedIndex = -1;
	    document.getElementById("componentesInput").selectedIndex = 0;
	}
	
	function adicionarPreRequisito(){
		var i = 0;
		var tamanho = PreRequisitos.length;
		for(i = 0; i < PreRequisitosEscolhidos.length; i++){
			if(PreRequisitosEscolhidos[i] !== ""){
				var requisito = new Object();
				requisito.id = PreRequisitosEscolhidos[i].id;
				requisito.nome = PreRequisitosEscolhidos[i].nome;
				requisito.carga = PreRequisitosEscolhidos[i].carga;
				requisito.credito = PreRequisitosEscolhidos[i].credito;
				
				PreRequisitos[tamanho] = requisito;
				tamanho++;
			}
		}
		document.getElementById("componentesInput").selectedIndex = -1;
	    document.getElementById("componentesInput").selectedIndex = 0;
		PreRequisitosEscolhidos = [];
		atualizarListaPreRequisitosTela();
		atualizarPreRequisitoEscolhidoTela();
	}
	
	function indiceOpcao(id){
		var i = 0;
		for(i=0; i< OpcaoDisciplina.length; i++){
			if(OpcaoDisciplina[i].id === id){
				return i;
			}
		}
		return -1;
	}
	
	function atualizarListaPreRequisitosTela(){
		var list = document.getElementById("listaComponentes");
		list.innerHTML = '<thead><tr><th scope="col">Código</th><th scope="col">Nome</th><th scope="col">Carga</th><th scope="col">Crédito</th><th scope="col"></th></tr></thead>';
		console.log(PreRequisitos.length);
		for(i=0;i<PreRequisitos.length;i++){
			console.log("passou aqui!");
			console.log(PreRequisitos[i].id);
			if(PreRequisitos[i] !== ""){
				list.innerHTML += '<tr>'+
								  '<td>'+PreRequisitos[i].id+'<input type="hidden" id="dis-id" name="dis-id" value="'+PreRequisitos[i].id+'"></td>'+
								  '<td>'+PreRequisitos[i].nome+'<input type="hidden" id="dis-nome" name="dis-nome" value="'+PreRequisitos[i].nome+'"></td>'+
								  '<td>'+PreRequisitos[i].carga+'<input type="hidden" id="dis-carga" name="dis-carga" value="'+PreRequisitos[i].carga+'"></td>'+
								  '<td>'+PreRequisitos[i].credito+'<input type="hidden" name="dis-cred" name="dis-cred" value="'+PreRequisitos[i].credito+'"></td>'+
								  '<td><button type="button" class="btn btn-primary btn-sm" style="height: 30px;" name="button" style="font-size: 15px;" onclick="removePreRequisito('+i+')">Excluir</button></td>'+
								  '</tr>';
			}
		}
	}
	function removePreRequisito(i){
		console.log(i);
		PreRequisitos[i] = "";
	    atualizarListaPreRequisitosTela();
	}
	function atualizarPreRequisitoEscolhidoTela(){
		var listp = document.getElementById("listaPreRequisitos");
		listp.innerHTML = '';
		for(i=0;i<PreRequisitosEscolhidos.length;i++){
			if(PreRequisitosEscolhidos[i] !== ""){
				listp.innerHTML += '<li class="list-group-item">'+
								   '<input type="hidden" name="listaPreRequisito" value="'+PreRequisitosEscolhidos[i].id+'" style="display: none;">'+PreRequisitosEscolhidos[i].nome+
						 		   '<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePreRequisitoEscolhido('+i+')">clear</button>'+
						 		   '</li>';
			}
		}
	}
	function removePreRequisitoEscolhido(i){
		PreRequisitosEscolhidos[i] = "";
	    atualizarPreRequisitoEscolhidoTela();
	}
	
	function atualizarListaPreRequisitos(){
		PreRequisitos = [];
		var tam = 0;
		var listaId = [];
		listaId = document.getElementsByName("dis-id");
		var listaNome = [];
		listaNome = document.getElementsByName("dis-nome");
		var listaCarga = [];
		listaCarga = document.getElementsByName("dis-carga");
		var listaCredito = [];
		listaCredito = document.getElementsByName("dis-cred");
		var i;
		for(i = 0; i < listaNome.length;i++){
			var requisito = new Object();
			requisito.id = listaId[i].value;
			requisito.nome = listaNome[i].value;
			requisito.carga = listaCarga[i].value;
			requisito.credito = listaCredito[i].value;
			
			PreRequisitos[tam] = requisito;
			tam++;
		}
		console.log(tam);
	}
	function atualizarListaOpcoes(){
		OpcaoDisciplina = [];
		var tam = 0;
		var listaId = [];
		listaId = document.getElementsByName("opc-id");
		var listaNome = [];
		listaNome = document.getElementsByName("opc-nome");
		var listaCarga = [];
		listaCarga = document.getElementsByName("opc-carga");
		var listaCredito = [];
		listaCredito = document.getElementsByName("opc-cred");
		var i;
		for(i = 0; i < listaNome.length;i++){
			var requisito = new Object();
			requisito.id = listaId[i].value;
			requisito.nome = listaNome[i].value;
			requisito.carga = listaCarga[i].value;
			requisito.credito = listaCredito[i].value;
			
			OpcaoDisciplina[tam] = requisito;
			tam++;
		}
		console.log(tam);
	}
	
	function verificarNaoRepeticao(requisito){
		var i = 0;
		for(i = 0; i < PreRequisitosEscolhidos.length; i++){
			if(requisito.id === PreRequisitosEscolhidos[i].id)
				return false;
		}
		
		for(i = 0; i < PreRequisitos.length; i++){
			if(requisito.id === PreRequisitos[i].id){
				return false;
			}
		}
		return true;
	}

</script>
</html>