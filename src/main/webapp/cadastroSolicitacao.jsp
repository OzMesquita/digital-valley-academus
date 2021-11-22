<%@ page import="br.ufc.russas.n2s.academus.dao.AlunoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCAlunoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Aluno"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="util.Constantes" %>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	PerfilAcademus usuario = (PerfilAcademus) request.getSession().getAttribute("userAcademus");

	MatrizCurricularDAO daoMC = new JDBCMatrizCurricularDAO();
	List<MatrizCurricular> matrizes = daoMC.buscarAtivosPorCurso(usuario.getCurso().getIdCurso());
	AlunoDAO alunodao= new JDBCAlunoDAO();
	List<Aluno> alunos = alunodao.listar();
	
%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Cadastro de Solicitação de Aproveitamento</title>
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
				<div class="col-md-10" style="padding-bottom: 200px; ">
					<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Você está em:</li>
						<li class="breadcrumb-item"><a href="Inicio">Início</a></li>
						<li class="breadcrumb-item active" aria-current="page">Cadastro de Solicitação de Aproveitamento</li>
					</ol>
					</nav>
					<br>
					<div class="form-group">
						<h1>Cadastrar Solicitação de Aproveitamento</h1>
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
						
						<% if (usuario.getNivel() == NivelAcademus.ALUNO){
						%>
								<form action="CadastrarSolicitacao" method="post">
									<div class="form-group">
										<label for="matrizInput">Matriz</label>
										<select id="matrizInput" class="form-control" onchange="listarComponentes()" required>
											<option value="" selected="selected" disabled="disabled">Selecione a matriz para a solicitação</option>
											<%for(MatrizCurricular matriz : matrizes){%>
												<option id="matrizOption-<%=matriz.getIdMatriz()%>" value="<%=matriz.getInfoComponentes()%>"><%=matriz.getNome()%></option>
											<%}%>
										</select>
									</div>
									<div class="form-group">
										<label for="componenteInput">Disciplina Alvo</label>
										<select id="componenteInput" name="componenteInput"  class="form-control" required>
											<option value="" selected="selected" disabled="disabled">Selecione a disciplina para a solicitação</option>
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
										           		<th scope="col" colspan="1"></th>
										           	</tr>
										        <thead>
								           </table>
							           </div>
							        </div>   
									<br>
									<div class="form-row">
										<div class="form-group col-md-4">
											<label for="disciplinaAproveitada">Nome da Disciplina Aproveitada</label>
											<input type="text" id="disciplinaAproveitada" pattern="[a-zA-Z\sÇçÁáÀàÉéÍíÓóÚúÃãõÕêÊâÂôÔ]+$" style='text-transform:uppercase' class="form-control">
										</div>
										&nbsp;&nbsp;
										<div class="form-group col-md-1">
											<label for="cargaHoraria">Carga Horária</label>
											<input type="number" min="16" step="16" id="cargaHoraria"  class="form-control">
										</div>
										&nbsp;&nbsp;
										<div class="form-group col-md-1">
											<label for="nota">Nota</label>
											<input type="number" min="5" step="0.01" max="10" id="nota" class="form-control">
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
											<input type="text" id="instituicao"  pattern="[a-zA-Z\sÇçÁáÀàÉéÍíÓóÚúÃãõÕêÊâÂôÔ]+$" style='text-transform:uppercase' class="form-control">
										</div>
									</div>
									<div class="row justify-content-end">
										<div class="form-group col-md-2 text-left p-md-0">
											<input type="button" class="btn btn-secondary btn-sm" onclick="adicionarDisciplinaAproveitada()" value="Adicionar">
										</div>
									</div>
									<div class="modal-footer">
										<div id="botoes" class="controls">
											<button type="button" class="btn btn-primary btn-sm" onclick="funcao()">Cancelar</button>
											<button type="submit" class="btn btn-primary btn-sm" >Confirmar</button>
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
													<h5>Deseja mesmo cancelar essa operação?<br>Você irá perder os dados informados!</h5>
												</div>
												<div class="modal-footer">
													<button type="button" id="modal-nao" autofocus class="btn btn-secondary btn-sm active" data-dismiss="modal" >Não</button>
													<a href="MenuInicial"><button type="button" class="btn btn-primary btn-sm active">Sim</button></a>
												</div>
											</div>
										</div>
									</div>
									<!-- Fim de Modal -->
								</form>
						<%
						}else{
						%>
							<div class="form-group">
								
								<div class="form-group">
								
									<label for="alunoInput">Aluno</label>
												<select id="alunoInput" name="aluno" class="form-control">
													<option></option>
											<%
												for(Aluno aluno: alunos){
											%>		
													<option value="<%=aluno.getId()  %>"> <%= aluno.getNome() %></option> 
											<%
												}
											%>
												</select>
								
								</div>
								<div class="form-group">
									<label for="matrizInput">Matriz</label>
									<select id="matrizInput" class="form-control" onchange="listarComponentes()" required>
										<option value="" selected="selected" disabled="disabled">Selecione a matriz para a solicitação</option>
										<%for(MatrizCurricular matriz : matrizes){%>
											<option id="matrizOption-<%=matriz.getIdMatriz()%>" value="<%=matriz.getInfoComponentes()%>"><%=matriz.getNome()%></option>
										<%}%>
									</select>
								</div>
								<div class="form-group">
									<label for="componenteInput">Disciplina Alvo</label>
									<select id="componenteInput" name="componenteInput"  class="form-control" required>
										<option value="" selected="selected" disabled="disabled">Selecione a disciplina para a solicitação</option>
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
									           		<th scope="col" colspan="1"></th>
									           	</tr>
									        <thead>
							           </table>
						           </div>
						        </div>   
								<br>
								<!-- Disciplinas Aproveitadas -->
								<div class="form-row">
									<div class="form-group col-sm-4">
										<label for="disciplinaAproveitada">Nome da Disciplina Aproveitada</label>
										<input type="text" id="disciplinaAproveitada" pattern="[a-zA-Z\sÇçÁáÀàÉéÍíÓóÚúÃãõÕêÊâÂôÔ]+$" style='text-transform:uppercase' class="form-control">
									</div>
									&nbsp;&nbsp;
									<div class="form-group col-sm">
										<label for="cargaHoraria">Carga Horária</label>
										<input type="number" min="16" step="16" id="cargaHoraria"  class="form-control">
									</div>
									&nbsp;&nbsp;
									<div class="form-group col-sm">
										<label for="nota">Nota</label>
										<input type="number" min="5" step="0.01" max="10" id="nota" class="form-control">
									</div>
									&nbsp;&nbsp;
									<div class="form-group col-sm">
										<label for="ano">Ano</label>
										<input type="number" min="1900" id="ano" class="form-control">
									</div>
									&nbsp;&nbsp;
									<div class="form-group col-sm">
										<label for="semestre">Semestre</label>
										<input type="number" min="1" max="2" id="semestre" class="form-control">
									</div>
									<div class="form-group col-sm">
										<label for="instituicao">Instituição</label>
										<input type="text" id="instituicao"  pattern="[a-zA-Z\sÇçÁáÀàÉéÍíÓóÚúÃãõÕêÊâÂôÔ]+$" style='text-transform:uppercase' class="form-control">
									</div>
								</div>
								<!-- Final de disciplinas aproveitadas -->
								<div class="row justify-content-end">
									<div class="form-group col-md-2 text-left p-md-0">
										<input type="button" class="btn btn-primary btn-sm" onclick="adicionarDisciplinaAproveitada()" value="Adicionar">
									</div>
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
												<a href="MenuInicial"><button type="button" class="btn btn-primary btn-sm active">Sim</button></a>
											</div>
										</div>
									</div>
								</div>
								<!-- Fim de Modal -->
							</div>
						
						<% 
						}
						%>
						
					</div>	                
				</div>
			</div>
		</div>
		<% 
		String mensagem = (String) request.getAttribute("mensagem");
		if(mensagem != null){
			if (mensagem.equals("CN")){
		%>
			<script type="text/javascript">
      				alert("Cadastro não realizado!\nRequer pelo menos uma disciplina cursada");
    		</script>
		<% 
			}
		 } %>	
	
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
				window.location.href = "MenuInicial";
			}
		}
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
			//disciplinas[tam] = disciplina;
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
		
		function atualizarDisciplinaAproveitada(){
			var list = document.getElementById("listaDisciplinasAproveitadas");
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
		
		function disciplinaRepetida(nome, carga, nota, semestre, ano, instituicao){
			var repetida = false;
			for(i = 0; i< disciplinas.length; i++){
				if(disciplinas[i].nome === nome){
					if (disciplinas[i].carga == carga){
						if(disciplinas[i].nota == nota){
							if(disciplinas[i].semestre == semestre){
								if(disciplinas[i].ano == ano){
									if (disciplinas[i].instituicao === instituicao){
										repetida = true;
									}
								}
							}
						}
					}
				}
			}
			return repetida;
		}
		
		function mensagemConfirmacao(){
			alert("Solicitação Cadastrada com sucesso");
		}
		
		function verificarDisciplinaAproveitada(obj){
			var data = new Date;
			if(obj.nome == ""){
				alert("Campo Nome da Disciplina Aproveitada deve ser preenchido");
				return false;
			}else if(obj.nome.match(/[0-9]/g)){
				alert("Não é permitido usar números no Nome da Disciplina Aproveitada");
				return false;
			}else if(!(obj.carga >= 0 )){
				alert("Campo Carga Horária não pode ser negativo");
				return false;
			}else if((obj.carga == "")){
				alert("Campo Carga Horária deve ser preenchido");
				return false;
			}else if((Number(obj.carga)% 16 != 0 || Number(obj.carga)== 0 )){
				alert("Digite um valor de carga horária válido, ex: 16, 32, 48");
				return false;
			}else if(!(obj.nota >= 0 || obj.nota <= 10)){
				alert("Campo de Nota deve está entre 0 e 10");
				return false;
			}else if((obj.nota == "")){
				alert("Campo de Nota deve ser preenchido");
				return false;
			}else if((obj.nota < 5)){
				alert("Impossível aproveitar cadeiras com nota menor que 5");
				return false;
			}else if((obj.ano == "")){
				alert("Ano deve ser preenchido");
				return false;
			}else if(!(obj.ano >= 1900 && obj.ano <= data.getFullYear())){
				alert("O Ano deve ser anterior ou igual ao atual");
				return false;
			}else if((obj.semestre == "")){
				alert("Semestre deve ser preenchido");
				return false;
			}else if(!(obj.semestre > 0 && obj.semestre <= 2)){
				alert("O Semestre deve ser 1 ou 2");
				return false;
			}  else if(obj.instituicao == ""){
				alert("Campo Instituição deve ser preenchido");
				return false;
			}else if(obj.instituicao.match(/[0-9]/g)){
				alert("Não é permitido usar números no Nome da Instituição");
				return false;
			} else if (disciplinaRepetida(obj.nome, obj.carga, obj.nota, obj.semestre, obj.ano, obj.instituicao)) {
				alert("A Disciplina Aprovada já foi adicionada");
				return false;
			}
			disciplinas[tam] = obj;
			return true;
		}
	</script>
</html>