<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.model.ComponenteCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
		rel="stylesheet">
	<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/css/design.css" />
	<title>Visualizar Matriz e Componentes</title>
	
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
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="jsp/elements/menu-lateral-esquerdo.jsp"
				charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Você está em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">Início</a></li>
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