<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.AlunoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.Disciplina"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCComponenteCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactory"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.ComponenteCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.Aluno"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.Curso"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	//MatrizCurricular matriz = new MatrizCurricular();
	MatrizCurricularDAO daoMC = new JDBCMatrizCurricularDAO();
	List<MatrizCurricular> matrizes = daoMC.listar();
	ComponenteCurricularDAO ccdao = new JDBCComponenteCurricularDAO();
	//int id_matriz = Integer.parseInt(request.getParameter("id_matriz"));
	//matriz.setId_matriz(id_matriz);
	//matriz = daoMC.buscarPorId(id_matriz);
	//System.out.println(matriz.getComponentes().size());
	DAOFactory df = new DAOFactoryJDBC();
	Aluno aluno = df.criarAlunoDAO().buscarPorMatricula("375102");
	Curso c = new Curso();
	c.setIdCurso(4);
	
	request.getSession().setAttribute("Usuario", aluno);
	DisciplinaDAO daoD = new JDBCDisciplinaDAO();
	List<Disciplina> disciplinas = daoD.listar();
	//ArrayList<ComponenteCurricular> listaComps = matriz.getComponentes();
%>
<% 
	//for(int a=0;a<matrizes.size();a++){
		//for(int b=0;b<matrizes.get(a).getComponentes().size();a++){
			//matrizes.get(a).getComponentes().get(b).getDisciplina().getNome(); 
		//}
	//}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
		rel="stylesheet">
	<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/css/design.css" />
<title>Cadastro de Soli�ita��o</title>
</head>
<body>
	<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="jsp/elements/menu-lateral-esquerdo.jsp"
				charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Voc� est� em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">In�cio</a></li>
					<!-- PREENCHER HREF -->
					<li class="breadcrumb-item active" aria-current="page">Cadastro de Soli�ita��o</li>
				</ol>
				</nav>
				<br>
				<div class="form-group">
					<form action="ServletCadastroSolicitacao" method="post">
						<div class="form-group">
							<label for="matrizInput">Matriz escolhida</label>
							<select id="matrizInput" class="form-control" onchange="listarComponentes()">
								<option value="" selected="selected" disabled="disabled">Selecione a disciplina para a solicita��o</option>
								<%for(MatrizCurricular matriz : matrizes){%>
									<option id="matrizOption-<%=matriz.getIdMatriz()%>" value="<%=matriz.getInfoComponentes()%>"><%=matriz.getIdMatriz()+"-"+matriz.getNome()%></option>
								<%}%>
							</select>
						</div>
						<div class="form-group">
							<label for="componenteInput">Disciplina Solicitada</label>
							<select id="componenteInput" name="componenteInput" class="form-control">
								<option value="" selected="selected" disabled="disabled">Selecione a disciplina para a solicita��o</option>
							</select>
						</div>
						<div class="form-group">
							<label for="instituicaoInput">Institui��o</label>
							<input type="text" id="instituicaoInput" name="instituicaoInput" class="form-control">
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
							           		<th scope="col">Carga Hor�ria</th>
							           		<th scope="col">Nota</th>
							           		<th scope="col">Ano/Semestre</th>
							           		<th scope="col"></th>
							           	</tr>
							        <thead>
					           </table>
				           </div>
				        </div>   
						<br>
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="disciplinaAproveitada">Nome da Disciplina Aproveitada</label>
								<input type="text" id="disciplinaAproveitada" class="form-control">
							</div>
							&nbsp;&nbsp;
							<div class="form-group col-md-2">
								<label for="cargaHoraria">Carga Hor�ria</label>
								<input type="number" id="cargaHoraria" class="form-control">
							</div>
							&nbsp;&nbsp;
							<div class="form-group col-md-2">
								<label for="nota">Nota</label>
								<input type="number" id="nota" class="form-control">
							</div>
							&nbsp;&nbsp;
							<div class="form-group col-md-2">
								<label for="ano/semestre">Ano/Semestre</label>
								<div class="row">
									<input type="number" id="ano" class="form-control">
									<input type="number" id="semestre" class="form-control">
								</div>
							</div>
							<div class="form-group col-md-2">
								<input type="button" class="btn btn-secondary col-md-8" onclick="adicionarDisciplinaAproveitada()" value="Adicionar">
							</div>
						</div>
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
			list.innerHTML = '<option value="" selected="selected" disabled="disabled">Selecione a disciplina para a solicita��o</option>';
			for(i=0;i<comps.length;i++){
				var info = [];
				info = comps[i].split("-");
				list.innerHTML += '<option value="'+info[0]+'">'+info[1]+'</option>';
			}
		}else{
			list.innerHTML = '<option value="" selected="selected" disabled="disabled">Esta matriz ainda n�o possui componentes</option>';
		}
	}
	
	function adicionarDisciplinaAproveitada(){
		var disciplina = new Object();
		disciplina.nome = document.getElementById("disciplinaAproveitada").value;
		disciplina.carga = document.getElementById("cargaHoraria").value;
		disciplina.nota = document.getElementById("nota").value;
		disciplina.ano = document.getElementById("ano").value;
		disciplina.semestre = document.getElementById("semestre").value;
		disciplinas[tam] = disciplina;
		if(verificarDisciplinaAproveitada(disciplina)){
			tam++;
			document.getElementById("disciplinaAproveitada").value = "";
			document.getElementById("cargaHoraria").value = "";
			document.getElementById("nota").value = "";
			document.getElementById("ano").value = "";
			document.getElementById("semestre").value = "";
			atualizarDisciplinaAproveitada();
		}
	}
	
	function atualizarDisciplinaAproveitada(){
		var list = document.getElementById("listaDisciplinasAproveitadas");
		list.innerHTML = '<tr><th scope="col">Disciplina Aproveitada</th><th scope="col">Carga Hor�ria</th><th scope="col">Nota</th><th scope="col">Ano/Semestre</th><th scope="col"></th></tr>';
		for(i=0;i<disciplinas.length;i++){
			if(disciplinas[i] !== null){
				list.innerHTML += '<tr>'+
								  '<td>'+disciplinas[i].nome+'<input type="hidden" id="disc-nome-'+i+'" name="disc-nome" value="'+disciplinas[i].nome+'"></td>'+
								  '<td>'+disciplinas[i].carga+'<input type="hidden" id="disc-carga-'+i+'" name="disc-carga" value="'+disciplinas[i].carga+'"></td>'+
								  '<td>'+disciplinas[i].nota+'<input type="hidden" id="disc-nota-'+i+'" name="disc-nota" value="'+disciplinas[i].nota+'"></td>'+
								  '<td>'+disciplinas[i].ano+'.'+disciplinas[i].semestre+'<input type="hidden" id="disc-semestre-'+i+'" name="disc-semestre" value="'+disciplinas[i].ano+'.'+disciplinas[i].semestre+'"></td>'+
								  '<td><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removerDisciplinaAproveitada('+i+')">clear</button></td>'+
								  '</tr>';
			}
		}
	}
	
	function removerDisciplinaAproveitada(i){
		disciplinas[i] = null;
	    atualizarDisciplinaAproveitada();
	}
	
	function mensagemConfirmacao(){
		alert("Solicita��o Cadastrada com sucesso");
	}
	
	function verificarDisciplinaAproveitada(obj){
		var data = new Date;
		if(obj.nome == ""){
			alert("Campo Nome da Disciplina Aproveitada deve ser preenchido");
			return false;
		}else if(!(obj.carga >= 0 )){
			alert("Campo Carga Hor�ria n�o pode ser negativo");
			return false;
		}else if((obj.carga == "")){
			alert("Campo Carga Hor�ria deve ser preenchido");
			return false;
		}else if(!(obj.nota >= 0 && obj.nota <= 10)){
			alert("Campo de Nota deve est� entre 0 e 10");
			return false;
		}else if((obj.nota == "")){
			alert("Campo de Nota deve ser preenchido");
			return false;
		}else if(!(obj.ano >= 1900 && obj.ano <= data.getFullYear())){
			alert("O Ano deve ser anterior ao atual");
			return false;
		}else if((obj.ano == "")){
			alert("Ano deve ser preenchido");
			return false;
		}else if(!(obj.semestre > 0 && obj.semestre <= 2)){
			alert("O Semestre deve ser 1 ou 2");
			return false;
		}else if((obj.semestre == "")){
			alert("Semestre deve ser preenchido");
			return false;
		}
		return true;
	}
</script>
</html>
