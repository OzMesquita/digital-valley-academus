<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ page import="br.ufc.russas.n2s.academus.dao.*"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	MatrizCurricular matriz = new MatrizCurricular();
	MatrizCurricularDao daoMC = new MatrizCurricularDao();
	int id_matriz = Integer.parseInt(request.getParameter("id_matriz"));
	matriz.setIdMatriz(id_matriz);
	matriz = daoMC.buscarPorId(id_matriz);
	System.out.println("aqui!!!!");
	System.out.println(matriz.getComponentes().size());
	
	DisciplinaDao daoD = new DisciplinaDao();
	ArrayList<Disciplina> disciplinas = daoD.ListarDisciplinas();
	ArrayList<ComponenteCurricular> listaComps = matriz.getComponentes();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
		rel="stylesheet">
	<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/css/design.css" />
	<title>Atualização da dados da Disciplina</title>
</head>
<body>
	<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="jsp/elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Você está em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">Início</a></li> <!-- PREENCHER HREF -->					
					<li class="breadcrumb-item active" aria-current="page">Cadastrar Disciplina</li>
				</ol>
				</nav>
				<h1>Atualizar Matriz Curricular</h1>
	
				<br>
				<div class="form-group">						
					<form action="AtualizarMatrizController" method="post">
					<label for="nomeMatrizInput">Id*</label>
					<input type="text" name="id_matriz" value="<%=matriz.getIdMatriz()%>" class="form-control" id="idMatrizInput" aria-describedby="tituloHelp" placeholder="Digite o código da matriz curricular" readonly required>
					<small id="tituloHelp" class="form-text text-muted"> Exemplo: ENGENHARIA DE SOFTWARE</small>		
         <br>
					
						<label for="nomeMatrizInput">Nome*</label> 
						<input type="text" name="nome" value="<%=matriz.getNome()%>" class="form-control" id="nomeMatrizInput" aria-describedby="tituloHelp" placeholder="Digite o nome da matriz curricular" required> 
						<small
							id="tituloHelp" class="form-text text-muted"> Exemplo:
							ENGENHARIA DE SOFTWARE</small> <br> <label for="periodoInput"> Período Letivo*</label>
		 <input type="text" name="periodo_letivo" value="<%=matriz.getPeriodoLetivo()%>" class="form-control" id="periodoInput" aria-describedby="tituloHelp" placeholder="Digite o período letivo" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 2018.1 </small>		
		 	
         <br>
         
		 <label for="cargaMatrizInput"> Carga horária total*</label>
		 <input type="text" name="carga_horaria" value="<%=matriz.getCarga()%>" class="form-control" id="cargaMatrizInput" aria-describedby="tituloHelp" placeholder="Digite a carga horária total" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 3200 </small>		
        	
         <br>	
			
		 <label for="prazoMinimoInput"> Prazo minímo para conclusão*</label>
		 <input type="text" name="prazo_minimo" value="<%=matriz.getPrazoMinimo()%>" class="form-control" id="prazoMinimoInput" aria-describedby="tituloHelp" placeholder="Digite o número minímo  de semestres para concluir o curso" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 8 </small>		
	
         <br>	
		 <label for="prazoMaximoInput"> Prazo máximo para conclusão*</label>
		 <input type="text" name="prazo_maximo" value="<%=matriz.getPrazoMaximo()%>" class="form-control" id="prazoMaximoInput" aria-describedby="tituloHelp" placeholder="Digite o número máximo de semestres para concluir o curso" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 12 </small>		
	
         <br>
		
		 <label for="vigenteInput">Vigente*</label>
                        <select name="vigente" class="form-control custom-select" onchange="ativoController()" id="vigenteInput" required>
                            <option value="true" selected="<%=matriz.isVigente()?"selected":""%>">Sim</option>
                            <option value="false"selected="<%=matriz.isVigente()?"":"selected"%>">Não</option>             
                        </select>
         <br>
		
		<label for="ativoInput">Ativo*</label>
                        <select name="ativo" class="form-control custom-select" onchange="vigenteController()" id="ativoInput" required>
                            <option value="true" selected="<%=matriz.isAtivo()?"selected":""%>">Sim</option>
                            <option value="false"selected="<%=matriz.isAtivo()?"":"selected"%>">Não</option>              
                        </select>
         <br>
		
		<label for="cursoInput">Curso*</label>
		<select name="id_curso" class="form-control custom-select" id="cursoInput" required>
						<option value="<%=matriz.getIdCurso()%>" selected="selected">Curso</option>
						<option value="1">Engenharia de Software</option>	
						<option value="2">Ciencia da Computação</option>											
						
						
						</select> <br> <br>
		
		<br>
		<br>
           <label for="listaComponentes">Componentes</label>
           <div class="form-group">
	           <table class="table table-responsive" id="listaComponentes">
			       <thead>    	
			           	<tr>
			           		<th scope="col">Código</th>
			           		<th scope="col">Nome</th>
			           		<th scope="col">Natureza</th>
			           		<th scope="col">Pré-Requisitos</th>
			           		<th scope="col"></th>
			           	</tr>
			       </thead>
	           </table>
	           <%
					int i=0;
					for(ComponenteCurricular cc : listaComps){%>
						<input type="hidden" id="comp-id-<%=i%>" value="<%=cc.getDisciplina().getId()%>">
						<input type="hidden" id="comp-nome-<%=i%>" value="<%=cc.getDisciplina().getNome()%>">
						<input type="hidden" id="comp-natureza-<%=i%>" value="<%=cc.getNatureza()%>">
						<input type="hidden" id="comp-preRequisitos-<%=i%>" value="<%=cc.getStringPreRequisitos()%>">
					<%i++;}%>
	           <input type="hidden" id="tam" value="<%=i%>">
	           <%System.out.println(i);%>
           </div>
                                      
        <br>
		
		<label for="componentesInput">Disciplinas</label>
		<div class="form-row">
			<select id="componentesInput" class="form-control col-md-8" style="margin-left: 3px">
				<option value="" selected="selected" disabled="disabled">Selecione as disciplinas que compõem esta matriz</option>
				<%for(Disciplina disciplina : disciplinas){%>
					<option id="disciplinaOption-<%=disciplina.getId()%>" value="<%=disciplina.getId()%> - <%=disciplina.getNome()%>"><%=disciplina.getNome()%></option>
				<%}%>
			</select>
			&nbsp;&nbsp;
			<input type="button" class="btn btn-secondary btn-sm" onclick="adicionarComponente()" id="addComp" value="Adicionar">
		</div>
		<label for="naturezaInput">Natureza do Componente</label>
		<div class="form-row">
			<select id="naturezaInput" class="form-control col-md-8" style="margin-left: 3px">
				<option value="OBRIGATÓRIA" selected="selected">OBRIGATÓRIA</option>
				<option value="OPTATIVA">OPTATIVA</option>
			</select>
		</div>
		<label for="preRequisitosInput">Disciplinas Pré-Requisitadas</label>
		<div class="form-row">
			<select id="preRequisitosInput" class="form-control col-md-8" style="margin-left: 3px">
				<option value="" selected="selected" disabled="disabled">Selecione as disciplinas previamente requisitadas por este componente</option>
				<%for(Disciplina disciplina : disciplinas){%>
					<option id="disciplinaOption-<%=disciplina.getId()%>" value="<%=disciplina.getId()%> - <%=disciplina.getNome()%>"><%=disciplina.getNome()%></option>
				<%}%>
			</select>
			&nbsp;&nbsp;
			<input type="button" class="btn btn-secondary btn-sm" onclick="adicionarPreRequisito()" value="Adicionar">
		</div>
		<br>
	          <ul class="list-group col-md-8" id="listaPreRequisitos"></ul>                                
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
<script>
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

var listaComponentes = [];
var numComponentes = 0;
var listaPreRequisitos = [];
var numPreRequisitos = 0;
adicionarComponentesExistentes()

function adicionarComponentesExistentes(){
	for(i=0;i<document.getElementById("tam").value;i++){
			var disciplina  = new Object();
			disciplina.id = document.getElementById("comp-id-"+i).value;
			disciplina.nome = document.getElementById("comp-nome-"+i).value;
			disciplina.natureza = document.getElementById("comp-natureza-"+i).value;
			disciplina.preRequisitos = document.getElementById("comp-preRequisitos-"+i).value;
			listaComponentes[numComponentes] = disciplina;
			numComponentes++;		
	}
	atualizarComponente();
}

function adicionarComponente(){
	if(document.getElementById("componentesInput").value !== ""){
		var disciplina = new Object();
		disciplina.id = document.getElementById("componentesInput").value.substring(0,7);
		disciplina.nome = document.getElementById("componentesInput").value.substring(10);
		disciplina.natureza = document.getElementById("naturezaInput").value;
		disciplina.preRequisitos = "";
		if(listaPreRequisitos.length>0){
			for(j=0;j<listaPreRequisitos.length;j++){
				if(listaPreRequisitos[j] !== ""){
					if(j>0){disciplina.preRequisitos += "; ";}
					disciplina.preRequisitos += listaPreRequisitos[j].substring(0,7);
				}
			}
		}
		if(disciplina.preRequisitos == ""){disciplina.preRequisitos = "-";}
	}
	document.getElementById("componentesInput").selectedIndex = -1;
	document.getElementById("componentesInput").selectedIndex = 0;
	document.getElementById("naturezaInput").selectedIndex = -1;
	document.getElementById("naturezaInput").selectedIndex = 0;
	listaPreRequisitos = [];
	numPreRequisitos = 0;
}
function atualizarComponente(){
	var listpr = document.getElementById("listaPreRequisitos");
	listpr.innerHTML = '';
	var list = document.getElementById("listaComponentes");
	list.innerHTML = '<tr><th scope="col">Código</th><th scope="col">Nome</th><th scope="col">Natureza</th><th scope="col">Pré-Requisitos</th><th scope="col"></th></tr>';
	for(i=0;i<listaComponentes.length;i++){
		if(listaComponentes[i] !== null){
			list.innerHTML += '<tr name="teste+'+i+'">'+
							  '<td>'+listaComponentes[i].id+'<input type="hidden" name="comp-id-'+i+'" value="'+listaComponentes[i].id+'"></td>'+
							  '<td>'+listaComponentes[i].nome+'<input type="hidden" name="comp-nome-'+i+'" value="'+listaComponentes[i].nome+'"></td>'+
							  '<td>'+listaComponentes[i].natureza+'<input type="hidden" name="comp-natureza-'+i+'" value="'+listaComponentes[i].natureza+'"></td>'+
							  '<td>'+listaComponentes[i].preRequisitos+'<input type="hidden" name="comp-preRequisitos-'+i+'" value="'+listaComponentes[i].preRequisitos+'"></td>'+
							  '<td><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeComponente('+i+')">clear</button></td>'+
							  '</tr>';
		}
	}
}
function removeComponente(i){
    listaComponentes[i] = null;
    atualizarComponente();
}
function adicionarPreRequisito(){
	if(document.getElementById("preRequisitosInput").value !== ""){
		var preRequisito = document.getElementById("preRequisitosInput").value;
		listaPreRequisitos[numPreRequisitos] = preRequisito;
		numPreRequisitos++;
	}
	document.getElementById("preRequisitosInput").selectedIndex = -1;
	document.getElementById("preRequisitosInput").selectedIndex = 0;
	atualizarPreRequisito();
}
function atualizarPreRequisito(){
	var listp = document.getElementById("listaPreRequisitos");
	listp.innerHTML = '';
	for(i=0;i<listaPreRequisitos.length;i++){
		if(listaPreRequisitos[i] !== ""){
			listp.innerHTML += '<li class="list-group-item">'+
							   '<input type="hidden" name="listaPreRequisito" value="'+listaPreRequisitos[i]+'" style="display: none;">'+listaPreRequisitos[i]+
					 		   '<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePreRequisito('+i+')">clear</button>'+
					 		   '</li>';
		}
	}
}

function removePreRequisito(i){
    listaPreRequisitos[i] = "";
    atualizarPreRequisito();
}

function verificarComponentesExistentes(disciplina){
	aux = true;
	for(i=0;i<listaComponentes.length; i++){
		if((listaComponentes[i] != null) && (disciplina.id == listaComponentes[i].id)){
			aux = false;
		}
	}
	if(aux == true){
		listaComponentes[numComponentes] = disciplina;
		numComponentes++;
		atualizarComponente();
	}
}

</script>

</html>