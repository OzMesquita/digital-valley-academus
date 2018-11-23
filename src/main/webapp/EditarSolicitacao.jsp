<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.SolicitacaoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC"%>
<%@ page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Aluno"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.util.Constantes" %>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	
	PerfilAcademus usuario = (PerfilAcademus) request.getSession().getAttribute("usuario");

	MatrizCurricularDAO daoMC = new JDBCMatrizCurricularDAO();
	Aluno a = (Aluno) usuario.getPessoa();
	List<MatrizCurricular> matrizes = daoMC.buscarPorCurso(a.getCurso().getIdCurso());	
	
	Solicitacao solicitacao = new Solicitacao();
	SolicitacaoDAO daoS = new DAOFactoryJDBC().criarSolicitacaoDAO();
	MatrizCurricular matrizSol = new MatrizCurricular();
	
	boolean deuCerto = true;
	try{
		System.out.print((String)(request.getAttribute("id")));
		int id = Integer.parseInt((String)request.getAttribute("id"));
		solicitacao = daoS.buscarPorId(id);
		matrizSol = daoMC.buscarPorSolicitacao(solicitacao.getIdSolicitacao());
	} catch(Exception e){
		deuCerto = false;
	}
	
%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Editar Soliçitação</title>
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
						<li class="breadcrumb-item" ><a href="Inicio">Início</a></li>
						<li class="breadcrumb-item active" aria-current="page">Editar Solicitação</li>
					</ol>
					</nav>
					<br>
					<div class="form-group">
						<form action="AtualizarSolicitacao" method="post">
							<div class="form-group">
								<label for="matrizInput">Matriz escolhida</label>
								<select id="matrizInput" class="form-control" onchange="listarComponentes()">
									<option value="<%=matrizSol.getNome() %>" selected="selected" disabled="disabled"><%=matrizSol.getNome() %></option>
									<%for(MatrizCurricular matriz : matrizes){%>
										<option id="matrizOption-<%=matriz.getIdMatriz()%>" value="<%=matriz.getInfoComponentes()%>"><%=matriz.getNome()%></option>
									<%}%>
								</select>
							</div>
							<div class="form-group">
								<label for="componenteInput">Disciplina Alvo</label>
								<select id="componenteInput" name="componenteInput" class="form-control">
									<option value="<%=solicitacao.getDisciplinaAlvo().getDisciplina().getId() %>" selected="selected" disabled="disabled"><%=solicitacao.getDisciplinaAlvo().getDisciplina().getNome() %></option>
								</select>
							</div>
							
							<div class="card">
					           <div class="card-header">
					           <label for="listaDisciplinasAproveitadas" class="card-title text-uppercase font-weight-bold">Disciplinas Aproveitadas</label>
					           </div>
					           <div class="card-body">
						           <table class="table" id="listaDisciplinasAproveitadas">
								        <thead> 
								           	<tr>
								           		<th scope="col">Nome</th>
								           		<th scope="col">Carga Horária</th>
								           		<th scope="col">Nota</th>
								           		<th scope="col">Ano/Semestre</th>
								           		<th scope="col">Instituição</th>
								           		<th scope="col"></th>
								           	</tr>
								        <thead>
								        <%
											for (DisciplinaCursada disCursada : solicitacao.getDisciplinasCursadas()) {
										%>
										<tr>
											<td><%=disCursada.getNome()%><input name="disc-nome" type="hidden" value="<%=disCursada.getNome()%>"></td>
											<td><%=disCursada.getCarga()%><input name="disc-carga" type="hidden" value="<%=disCursada.getCarga()%>"></td>
											<td><%=disCursada.getNota()%><input name="disc-nota" type="hidden" value="<%=disCursada.getNota()%>"></td>
											<td><%=disCursada.getSemestre()%><input name="disc-semestre" type="hidden" value="<%=disCursada.getSemestre()%>"></td>
											<td><%=disCursada.getInstituicao()%><input name="disc-instituicao" type="hidden" value="<%=disCursada.getInstituicao()%>"></td>
											<td><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removerDA('<%=disCursada.getNome()%>','<%=disCursada.getCarga()%>','<%=disCursada.getNota()%>','<%=disCursada.getSemestre()%>','<%=disCursada.getInstituicao()%>')">clear</button></td>
										</tr>
										
										<%
											}
										%>
						           </table>
					           </div>
					        </div>   
							<br>
							<div class="form-row">
								<div class="form-group col-md-3">
									<label for="disciplinaAproveitada">Nome da Disciplina Aproveitada</label>
									<input type="text" id="disciplinaAproveitada" style='text-transform:uppercase' class="form-control">
								</div>
								&nbsp;&nbsp;
								<div class="form-group col-md-1">
									<label for="cargaHoraria">Carga Horária</label>
									<input type="number" min="1" id="cargaHoraria" class="form-control">
								</div>
								&nbsp;&nbsp;
								<div class="form-group col-md-1">
									<label for="nota">Nota</label>
									<input type="number" min="0" max="10" id="nota" class="form-control">
								</div>
								&nbsp;&nbsp;
								<div class="form-group col-md-1">
									<label for="ano">Ano</label>
									<input type="number" min="1900" id="ano" class="form-control">
								</div>
								&nbsp;&nbsp;
								<div class="form-group col-md-1">
									<label for="semestre">Semestre</label>
									<input type="number" min="1" max="2" id="semestre" class="form-control">
								</div>
								<div class="form-group col-md-3">
									<label for="instituicao">Instituição</label>
									<input type="text" id="instituicao" style='text-transform:uppercase' class="form-control">
								</div>
								<div class="form-group col-md-2">
									<input type="button" class="btn btn-secondary col-md-8" onclick="adicionarDisciplinaAproveitada()" value="Adicionar">
								</div>
							</div>
							<div class="modal-footer">
								<div id="botoes" class="controls">
									<button type="submit" name="button" value="<%=solicitacao.getIdSolicitacao()%>" class="btn btn-primary">Confirmar</button>
									<a href="Inicio">
										<input type="button" class="btn btn-primary" value="Cancelar"></a>
								</div>
							</div>
						</form>
					</div>	                
				</div>
			</div>
		</div>
	</body>
	<script>
		var componentes = [];
		var disciplinas = [];
		var tam = 0;
		
		function listarComponentes(){
			var matriz = document.getElementById("matrizInput");
			var comps = []
			comps =	matriz.options[matriz.selectedIndex].value.split(";");
			var list = document.getElementById("componenteInput");
			list.innerHTML = '';
			if(comps.length > 0 && comps[0] != ""){
				list.innerHTML = '<option value="" selected="selected" disabled="disabled">Selecione a disciplina para a solicitação</option>';
				for(i=0;i<comps.length;i++){
					var info = [];
					info = comps[i].split("-");
					list.innerHTML += '<option value="'+info[0]+'">'+info[1]+'</option>';
				}
			}else{
				list.innerHTML = '<option value="" selected="selected" disabled="disabled">Esta matriz ainda não possui componentes</option>';
			}
		}
		
		function adicionarDisciplinaAproveitada(){
			var disciplina = new Object();
			disciplina.nome = document.getElementById("disciplinaAproveitada").value.toUpperCase();
			disciplina.carga = document.getElementById("cargaHoraria").value;
			disciplina.nota = document.getElementById("nota").value;
			disciplina.ano = document.getElementById("ano").value;
			disciplina.semestre = document.getElementById("semestre").value;
			disciplina.instituicao = document.getElementById("instituicao").value.toUpperCase();
			atualizarListaDisciplinasAproveitadas();
			disciplinas[tam] = disciplina;
			if(verificarDisciplinaAproveitada(disciplina)){
				tam++;
				document.getElementById("disciplinaAproveitada").value = "";
				document.getElementById("cargaHoraria").value = "";
				document.getElementById("nota").value = "";
				document.getElementById("ano").value = "";
				document.getElementById("semestre").value = "";
				document.getElementById("instituicao").value = "";
				atualizarDisciplinaAproveitada();
			}
		}
		
		function atualizarListaDisciplinasAproveitadas(){
			disciplinas = [];
			tam = 0;
			var listaNome = [];
			listaNome = document.getElementsByName("disc-nome");
			var listaCarga = [];
			listaCarga = document.getElementsByName("disc-carga");
			var listaNota = [];
			listaNota = document.getElementsByName("disc-nota");
			var listaSemestre = [];
			listaSemestre = document.getElementsByName("disc-semestre");
			var listaInstituicao = [];
			listaInstituicao = document.getElementsByName("disc-instituicao");
			var i;
			for(i = 0; i < listaNome.length;i++){
				var disciplina = new Object();
				disciplina.nome = listaNome[i].value;
				disciplina.carga = listaCarga[i].value;
				disciplina.nota = listaNota[i].value;
				var aux = listaSemestre[i].value.split('.');
				disciplina.ano = aux[0];
				disciplina.semestre = aux[1];
				disciplina.instituicao = listaInstituicao[i].value;
				
				disciplinas[tam] = disciplina;
				tam++;
				
			}
		}
		
		function atualizarDisciplinaAproveitada(){
			var list = document.getElementById("listaDisciplinasAproveitadas");
			if (disciplinas.lenght == 0){
				atualizarListaDisciplinasAproveitadas();
			}
			list.innerHTML = '<tr><th scope="col">Disciplina Aproveitada</th><th scope="col">Carga Horária</th><th scope="col">Nota</th><th scope="col">Ano/Semestre</th><th scope="col">Instituição</th></tr>';
			for(i=0;i<disciplinas.length;i++){
				if(disciplinas[i] !== null){
					list.innerHTML += '<tr>'+
									  '<td>'+disciplinas[i].nome+'<input type="hidden" id="disc-nome-'+i+'" name="disc-nome" value="'+disciplinas[i].nome+'"></td>'+
									  '<td>'+disciplinas[i].carga+'<input type="hidden" id="disc-carga-'+i+'" name="disc-carga" value="'+disciplinas[i].carga+'"></td>'+
									  '<td>'+disciplinas[i].nota+'<input type="hidden" id="disc-nota-'+i+'" name="disc-nota" value="'+disciplinas[i].nota+'"></td>'+
									  '<td>'+disciplinas[i].ano+'.'+disciplinas[i].semestre+'<input type="hidden" id="disc-semestre-'+i+'" name="disc-semestre" value="'+disciplinas[i].ano+'.'+disciplinas[i].semestre+'"></td>'+
									  '<td>'+disciplinas[i].instituicao+'<input type="hidden" id="disc-instituicao-'+i+'" name="disc-instituicao" value="'+disciplinas[i].instituicao+'"></td>'+
									  '<td><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removerDisciplinaAproveitada('+i+')">clear</button></td>'+
									  '</tr>';
				}
			}
		}
		
		function removerDisciplinaAproveitada(i){
			disciplinas[i] = null;
		    atualizarDisciplinaAproveitada();
		}
		
		function removerDA(nome, carga, nota, semestre, instituicao){
			var list = document.getElementById("listaDisciplinasAproveitadas");
			
				var listaNome = [];
				listaNome = document.getElementsByName("disc-nome");
				var listaCarga = [];
				listaCarga = document.getElementsByName("disc-carga");
				var listaNota = [];
				listaNota = document.getElementsByName("disc-nota");
				var listaSemestre = [];
				listaSemestre = document.getElementsByName("disc-semestre");
				var listaInstituicao = [];
				listaInstituicao = document.getElementsByName("disc-instituicao");
				var i;
				for(i = 0; i < listaNome.length;i++){
					var disciplina = new Object();
					disciplina.nome = listaNome[i].value;
					disciplina.carga = listaCarga[i].value;
					disciplina.nota = listaNota[i].value;
					var aux = listaSemestre[i].value.split('.');
					disciplina.ano = aux[0];
					disciplina.semestre = aux[1];
					disciplina.instituicao = listaInstituicao[i].value;
					
					if( (nome !== disciplina.nome) & (carga != disciplina.carga) & (nota != disciplina.nota) & (semestre !== disciplina.semestre) & (instituicao !== disciplina.instituicao)){
						disciplinas[tam] = disciplina;
						tam++;
					}
				
			}
			list.innerHTML = '<tr><th scope="col">Disciplina Aproveitada</th><th scope="col">Carga Horária</th><th scope="col">Nota</th><th scope="col">Ano/Semestre</th><th scope="col">Instituição</th></tr>';
			for(i=0;i<disciplinas.length;i++){
				if(disciplinas[i] !== null){
					list.innerHTML += '<tr>'+
									  '<td>'+disciplinas[i].nome+'<input type="hidden" id="disc-nome-'+i+'" name="disc-nome" value="'+disciplinas[i].nome+'"></td>'+
									  '<td>'+disciplinas[i].carga+'<input type="hidden" id="disc-carga-'+i+'" name="disc-carga" value="'+disciplinas[i].carga+'"></td>'+
									  '<td>'+disciplinas[i].nota+'<input type="hidden" id="disc-nota-'+i+'" name="disc-nota" value="'+disciplinas[i].nota+'"></td>'+
									  '<td>'+disciplinas[i].ano+'.'+disciplinas[i].semestre+'<input type="hidden" id="disc-semestre-'+i+'" name="disc-semestre" value="'+disciplinas[i].ano+'.'+disciplinas[i].semestre+'"></td>'+
									  '<td>'+disciplinas[i].instituicao+'<input type="hidden" id="disc-instituicao-'+i+'" name="disc-instituicao" value="'+disciplinas[i].instituicao+'"></td>'+
									  '<td><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removerDisciplinaAproveitada('+i+')">clear</button></td>'+
									  '</tr>';
				}
			}
			
		}
		
		function mensagemConfirmacao(){
			alert("Solicitação atualizada com sucesso");
		}
		
		function verificarDisciplinaAproveitada(obj){
			var data = new Date;
			if(obj.nome == ""){
				alert("Campo Nome da Disciplina Aproveitada deve ser preenchido");
				return false;
			}else if(!(obj.carga >= 0 )){
				alert("Campo Carga Horária não pode ser negativo");
				return false;
			}else if((obj.carga == "")){
				alert("Campo Carga Horária deve ser preenchido");
				return false;
			}else if(!(obj.nota >= 0 && obj.nota <= 10)){
				alert("Campo de Nota deve está entre 0 e 10");
				return false;
			}else if((obj.nota == "")){
				alert("Campo de Nota deve ser preenchido");
				return false;
			}else if((obj.ano == "")){
				alert("Ano deve ser preenchido");
				return false;
			}else if(!(obj.ano >= 1900 && obj.ano <= data.getFullYear())){
				alert("O Ano deve ser anterior ao atual e cabível");
				return false;
			}else if((obj.semestre == "")){
				alert("Semestre deve ser preenchido");
				return false;
			}else if(!(obj.semestre > 0 && obj.semestre <= 2)){
				alert("O Semestre deve ser 1 ou 2");
				return false;
			}
			return true;
		}
	</script>
</html>