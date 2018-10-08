<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDao"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.MatrizCurricular"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listando Matrizes</title>

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
					<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">Início</a></li>
					<!-- PREENCHER HREF -->
					<li class="breadcrumb-item active" aria-current="page">Lista
						de Matrizes</li>
				</ol>
				</nav>
				<div class="form-group">
					<!-- A partir dessa divisão é o listar disciplinas -->
					<table class="table table-responsive">
						<thead>
							<tr>
								<th scope="col">Código Matriz</th>
								<th scope="col">Nome</th>
								<!-- <form id="myform" method="post" action="VisualizarMatriz">
							  			<input type="hidden" name="link" value="< %=contato.getIdMatriz()% >" /> 
							  			<a onclick="document.getElementById('myform').submit();"><font color="blue"><  %=contato.getNome()% ></font></a></td>
									</form> -->
								<th scope="col">Periodo Letivo</th>
								<th scope="col">Carga</th>
								<th scope="col">Prazo Minimo</th>
								<th scope="col">Prazo Máximo</th>
								<th scope="col">Virgente</th>		
								<th scope="col">Ativo</th>
								<th scope="col">Código Curso</th>						
							</tr>
						</thead>
						<%
							MatrizCurricularDao dao = new MatrizCurricularDao();
							List<MatrizCurricular> contatos = dao.ListarMatrizes();
							for (MatrizCurricular contato : contatos) {
						%>
						<tr>
							<td><%=contato.getIdMatriz()%></td>
							<td><%=contato.getNome()%></td>
							<td><%=contato.getPeriodoLetivo()%></td>
							<td><%=contato.getCarga()%></td>
							<td><%=contato.getPrazoMinimo()%></td>
							<td><%=contato.getPrazoMaximo()%></td>
							<td><%=(contato.isVigente())? "Sim":"Não"%></td>
							<td><%=(contato.isAtivo())? "Sim" : "Não"%></td>
							<td><%=contato.getIdCurso()%></td>
							<td><form method="post" action="VisualizarMatriz" id="form<%=contato.getIdMatriz()%>">
							<button  class="btn btn-primary btn-sm active" form="form<%=contato.getIdMatriz()%>"
								class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=contato.getIdMatriz() %>" > Visualizar
							</button>
							</form></td>
							<td><form method="post" action="AtualizarMatrizController" id="form<%=contato.getIdMatriz()%>">
							<button  class="btn btn-primary btn-sm active" form="form<%=contato.getIdMatriz()%>"
								class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=contato.getIdMatriz() %>" > Editar
							</button>
							</form></td>
							<td><a href="elements/aviso.jsp" class="btn btn-primary btn-sm active"
								class="btn btn-primary btn-sm" style="height: 30px;">Remover
							</a></td>
						</tr>

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