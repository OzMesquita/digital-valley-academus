<%@page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.SolicitacaoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC" %>
<%@ page import="java.util.*"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/design.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.standalone.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<form action="FiltroBuscarController" method="post">
		<div class="dropdown right" style="right:+15px; position:absolute;">
                    <button class="btn dropdown-toggle btn-sm btn-icon filtro_tela" name="filtro" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="material-icons">filter_list</i>
                        <span>Filtrar</span>
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" value="todos">Todas as seleções</a>
                        <a class="dropdown-item" value="andamento">Solicitações em andamento</a>
                        <a class="dropdown-item" value="finalizado">Solicitações finalizadas</a>
                    </div>
                </div>
					         <br><br>

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
						 
							
						//List<Solicitacao> solicitacoes = (List<Solicitacao>) request.getAttribute("listaSol");
						
						SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
						List<Solicitacao> solicitacoes = sodao.listar();
						
						if(solicitacoes != null){
							for (Solicitacao soli : solicitacoes) {
								
						%>
						<tr>
							<td><%=soli.getIdSolicitacao()%></td>
							<td><%=soli.getStatus()%></td>
							<td><%=soli.getSolicitante().getNome()%></td>
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
							
						}
						%>

					</table>
				</div>
</form>
