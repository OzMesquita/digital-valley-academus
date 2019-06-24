<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Buscar Disciplina</title>
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
						<li class="breadcrumb-item" ><a href="Inicio">Início</a></li>
						<li class="breadcrumb-item" ><a href="ListarDisciplinas">Lista de disciplinas</a></li>				
						<li class="breadcrumb-item active" aria-current="page">Buscar Disciplina</li>
					</ol>
					</nav>
					
					<div class="form-group table-responsive">
					<form method="post" action="BuscarDisciplina">
						<h2><label for="buscainput"><b> Escolha forma de pesquisa </b></label></h2> 
						<h2><INPUT TYPE="radio" name="opcao" VALUE="1" CHECKED> código
						<INPUT TYPE="radio" name="opcao" VALUE="2"> nome</h2>
						<br>
					<a class="form-inline">
						<input class="form-control" style="width: 250px;" style='text-transform:uppercase'
							name="id_disciplina" placeholder="Código ou nome da disciplina" required>&nbsp;
						<button class="btn btn-sm btn-primary" type="submit">Procurar</button></a>
					</form>
					<br/>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Código</th>
								<th scope="col">Nome</th>
								<th scope="col">Carga</th>
								<th scope="col">Créditos</th>
								<th scope="col" colspan="2" style="text-align: center;">Opções</th>
							</tr>
						</thead>
						<%
							if(request.getParameter("id_disciplina") != null){
								int tipobusca = Integer.parseInt(request.getParameter("opcao"));
								String id_disciplina = request.getParameter("id_disciplina");
								
								DisciplinaDAO dao = new JDBCDisciplinaDAO();
								List<Disciplina> disciplinas = new ArrayList<Disciplina>();
								if(tipobusca == 1){
									Disciplina disciplina = dao.buscarPorId(id_disciplina);
									if(disciplina == null){
										disciplina = new Disciplina();
									}
									disciplinas.add(disciplina);
								}else if (tipobusca == 2){
									disciplinas = dao.buscarPorNome(id_disciplina);
									if(disciplinas.isEmpty())
										disciplinas.add(new Disciplina());
								}
								
								for(Disciplina disciplina : disciplinas){
									if (!disciplina.getId().equals("INDEFINIDO")){
									%>
									<tr>
										<td><%=disciplina.getId()%></td>
										<td><%=disciplina.getNome()%></td>
										<td><%=disciplina.getCarga()%></td>
										<td><%=disciplina.getCreditos()%></td>
										<td><form method="POST" action="EditarDisciplina">
											<button class="btn btn-secondary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=disciplina.getId()%>" >
												Editar
											</button>
										</form></td>
										<td><a href="jsp/elements/aviso.jsp" class="btn btn-secondary btn-sm" style="height: 30px;">Remover</a></td>
									</tr>
									<%
									} else {%>						
										<tr><td><p>Nenhuma disciplina foi encontrada! </p></td></tr>
									<%
									}
								}
								
							} else {
								%>
								<tr><td><p>Informe o código da disciplina desejada! </p></td></tr>
								<%
							}
						%>	
					</table>
					</div>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
</html>