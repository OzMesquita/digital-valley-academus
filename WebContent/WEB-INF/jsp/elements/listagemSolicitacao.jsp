<%@page import="br.ufc.russas.n2s.academus.modelo.DisciplinaCursada"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.SolicitacaoDao"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.Solicitacao"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.Solicitacao"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de Disciplinas</title>
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
<div class="form-group">
					<table class="table table-responsive">
						<thead>
							<tr>
								<th scope="col">Codigo de Solicitação</th>
								<th scope="col">Status</th>
								<th scope="col">Solicitante</th>
								<th scope="col">Disciplina</th>
								<th scope="col">Resultado</th>
							</tr>
						</thead>
						<%
						/* 
							ArrayList<Solicitacao> soli = (ArrayList<Solicitacao>) session.getAttribute("listaSol");
							ArrayList<Solicitacao> soli2 = (ArrayList<Solicitacao>) request.getAttribute("listaSol");
						*/
							SolicitacaoDao dao = new SolicitacaoDao();
							List<Solicitacao> solicitacoes = dao.listarSolicitacoes();
							for (Solicitacao soli : solicitacoes) {
								
						%>
						<tr>
							<td><%=soli.getIdSolicitacao()%></td>
							<td><%=soli.getStatus()%></td>
							<td><%//=soli.getSolicitante().getNome()%></td>
							<td><%=soli.getDisciplinaAlvo().getDisciplina().getNome()%></td>
							<td><%=soli.getResultado()%></td>
							<form method="POST" action="VisualizarSolicitacao" id="form<%=soli.getIdSolicitacao()%>">
							<td><button  class="btn btn-primary btn-sm active" form="form<%=soli.getIdSolicitacao()%>"
								class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=soli.getIdSolicitacao() %>" > Visualizar
							</button></td>
							</form>
							<!--  <td>
								<input type="button" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;" onclick="visualizarSolicitacao(<%=soli.getIdSolicitacao() %>)" value="Adicionar">
							
							</td>-->
							<!-- <td><a href="elements/aviso.jsp" class="btn btn-primary btn-sm active"
								class="btn btn-primary btn-sm" style="height: 30px;"> Remover
							</a></td> -->
						</tr>

						<%
							}
						%>

					</table>
				</div>
</body>
<script>

</script>
</html>