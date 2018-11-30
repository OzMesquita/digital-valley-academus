<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.model.ComponenteCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="br.ufc.russas.n2s.academus.util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Visualizar Matriz e Componentes</title>
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
		
		<style type="text/css">
		thead#componente{
	    display: block;
	    margin-left: auto;
	    margin-right: auto 
	    }
		</style>
	
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
						<!-- PREENCHER HREF -->
						<li class="breadcrumb-item active" aria-current="page">Visualizar Matriz e Componentes</li>
					</ol>
					</nav>
					<%
								MatrizCurricular matriz = new MatrizCurricular();
								MatrizCurricularDAO dao = new JDBCMatrizCurricularDAO();
								//int a = Integer.parseInt(request.getParameter("id"));
								//matriz = dao.buscarPorId(a);
								boolean deuCerto = true;
								try{
									int id = Integer.parseInt((String) request.getAttribute("id_matriz"));
									matriz = dao.buscarPorId(id);
									
								}catch(Exception e){
									deuCerto = false;
									e.printStackTrace();
								}
								
								
								
								if(matriz != null && deuCerto){
								
							%>
					<div class="form-group">
						<table class="table table-responsive">
							<thead>
								<tr>
									<th scope="col">Código Matriz</th>
									<th scope="col">Nome</th>
									<th scope="col">Periodo Letivo</th>
									<th scope="col">Carga Horaria</th>
									<th scope="col">Prazo Minimo</th>
									<th scope="col">Prazo Maximo</th>
									<th scope="col">Vigente</th>		
									<th scope="col">Ativo</th>
									<th scope="col">Código Curso</th>	
								</tr>
							</thead>
							
							<tr>
								<td><%=matriz.getIdMatriz()%></td>
								<td><%=matriz.getNome()%></td>
								<td><%=matriz.getPeriodoLetivo()%></td>
								<td><%=matriz.getCarga()%></td>
								<td><%=matriz.getPrazoMinimo()%></td>
								<td><%=matriz.getPrazoMaximo()%></td>
								<td><%=(matriz.isVigente())? "Sim" : "Não"%></td>
								<td><%=(matriz.isAtivo())? "Sim" : "Não"%></td>
								<td><%=matriz.getIdCurso()%></td>
							</tr>		
						</table>
						
						<h3><b>Componentes Curriculares</b></h3>
						
						<table class="table table-responsive">
							<thead>	
								<tr>
									<th scope="col">Código do Componente</th>
									<th scope="col">Código da Disciplina</th>
									<th scope="col">Nome da Disciplina</th>
									<th scope="col">Carga da Disciplina</th>
									<th scope="col">Créditos da Disciplina</th>
									<th scope="col">Natureza do Componente</th>
									<th scope="col">Código do Pré Requisito</th>
								</tr>
							</thead>
							
							<%
								List <ComponenteCurricular> cc = matriz.getComponentes();
								for (ComponenteCurricular aux : cc ) {
							%>
							
							<tr>
								<td><%=aux.getIdComponente()%></td>
								<td><%=aux.getDisciplina().getId()%></td>
								<td><%=aux.getDisciplina().getNome()%></td>
								<td><%=aux.getDisciplina().getCarga()%></td>
								<td><%=aux.getDisciplina().getCreditos()%></td>
								<td><%=aux.getNatureza()%></td>
								<td>
								<%
									List <Disciplina> prerequisito = aux.getPreRequisitos();
									for (Disciplina d : prerequisito ) {
										out.print(d.getId() + "; ");
									}
								%>
								</td>
							</tr>
							
							<%
								}
							%>
						</table>	
					</div>
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
</html>