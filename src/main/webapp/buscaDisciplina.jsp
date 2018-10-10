<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listando Disciplinas</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/design.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.standalone.css" />

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
					<li class="breadcrumb-item" aria-current="page"><a href="listaDisciplinas.jsp">Lista de disciplinas</a></li>				
					<li class="breadcrumb-item active" aria-current="page">Busca Disciplina</li>
				</ol>
				</nav>
				
				<div class="form-group">
				<form class="form-inline" method="get" action="buscaDisciplina.jsp">
					<input class="form-control" style="width: 250px;" type="search"
						name="id_disciplina" placeholder="Código da disciplina" required>&nbsp;
					<button class="btn btn-sm btn-primary" type="submit">Procurar</button>
				</form>	
					<table class="table table-responsive">
						<thead>
							<tr>
								<th scope="col">Código</th>
								<th scope="col">Nome</th>
								<th scope="col">Carga</th>
								<th scope="col">Créditos</th>
								<th scope="col">Opções</th>
							</tr>
						</thead>
						<%
							Disciplina disciplina = new Disciplina();
							JDBCDisciplinaDAO dao = new JDBCDisciplinaDAO();
							disciplina.setId(request.getParameter("id_disciplina"));
							
							disciplina = dao.buscaDisciplina(disciplina);
							if (disciplina!=null){
						%>
						<tr>
							<td><%=disciplina.getId()%></td>
							<td><%=disciplina.getNome()%></td>
							<td><%=disciplina.getCarga()%></td>
							<td><%=disciplina.getCreditos()%></td>
							<td><a href="editarDisciplina.jsp?id=<%=disciplina.getId()%>" class="btn btn-primary btn-sm active"
								class="btn btn-primary btn-sm" style="height: 30px;"> Editar
							</a></td>
							<td><a href="jsp/elements/aviso.jsp" class="btn btn-primary btn-sm active"
								class="btn btn-primary btn-sm" style="height: 30px;"> Remover
							</a></td>
						</tr>
						<%
						} else {%>						
							<tr><td><p>Nenhuma disciplina foi encontrada! </p></td></tr>
						<%
						}
							
						%>	
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>