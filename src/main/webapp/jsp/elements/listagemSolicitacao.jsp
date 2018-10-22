<%@page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao"%>
<%@ page import="java.util.*"%>

<div class="dropdown right" style="right:+15px; position:absolute;">
                    <button class="btn dropdown-toggle btn-sm btn-icon filtro_tela" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="material-icons">filter_list</i>
                        <span>Filtrar</span>
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="buscaDisciplina.jsp">Todas as seleções</a>
                        <a class="dropdown-item" href="jsp/elements/listagemSolicitacao.jsp">Solicitações em andamento</a>
                        <a class="dropdown-item" href="cadastroSolicitacao.jsp">Solicitações finalizadas</a>
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
						 
							
						List<Solicitacao> solicitacoes = (List<Solicitacao>) request.getAttribute("listaSol");
								
						if(solicitacoes != null){
							for (Solicitacao soli : solicitacoes) {
								
						%>
						<tr>
							<td><%=soli.getIdSolicitacao()%></td>
							<td><%=soli.getStatus()%></td>
							<td><%=soli.getSolicitante().getNome()%></td>
							<!-- <td>< % =//soli.getDisciplinaAlvo().getDisciplina().getNome()% ></td>-->
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
