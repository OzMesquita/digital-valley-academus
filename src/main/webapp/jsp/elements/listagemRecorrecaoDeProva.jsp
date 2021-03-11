<%@page import="br.ufc.russas.n2s.academus.model.StatusRecorrecao"%>
<%@page import="br.ufc.russas.n2s.academus.model.RecorrecaoDeProva"%>
<%@page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="java.util.*"%>

<%
	PerfilAcademus usuario = (PerfilAcademus)session.getAttribute("userAcademus");
	String statusSoli = (request.getParameter("solicitacao") != null) ? request.getParameter("solicitacao") : "null";
%>

<!-- Essa pagina filtra e faz a listagem de todas as solicitacoes de Recorreção de Provas -->
<div class="dropdown">
	<button type="button" class="btn dropdown-toggle btn-sm btn-icon filtro_tela" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<i class="material-icons">filter_list</i>
		Filtrar
	</button>
	<div class="dropdown-menu dropdown-menu-left">
		
		<a class="dropdown-item" href="HistoricoRecorrecaoDeProva">Todas as solicitações</a>
		<a class="dropdown-item" href="HistoricoRecorrecaoDeProva?tipoSolicitacao=submetido">Solicitações submetidas</a>
		<a class="dropdown-item" href="HistoricoRecorrecaoDeProva?tipoSolicitacao=finalizado">Solicitações finalizadas</a>
		
	</div>
</div>

<div class="table-responsive">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Código da Solicitação</th>
				<th scope="col">Status</th>
				<th scope="col">Solicitante</th>
				<th scope="col">Disciplina</th>
				<th></th>
			</tr>
		</thead>
				<%
 
		
		List<RecorrecaoDeProva> listaRP = (List<RecorrecaoDeProva>) session.getAttribute("listaRP");
		int numSolicitacoes = (Integer) session.getAttribute("numSolicitacoes");
		
		if(listaRP != null){
			for (RecorrecaoDeProva rp : listaRP) {
		%>
		<tr>
			<td><%=rp.getIdRecorrecao()%></td>
			<td><%=StatusRecorrecao.getDescricao(rp.getStatus()) %></td>
			<td><%=rp.getAluno().getNome()%></td>
			<td><%=rp.getDisciplina().getNome()%></td>
			<td>
				<a href="VisualizarRecorrecaoDeProva?id=<%=rp.getIdRecorrecao() %>" class="btn btn-primary btn-sm">Visualizar</a>
			</td>
		</tr>
		
		<%
			}
		} else {
			%>
			<tr>
				<td><p>Nenhuma solicitação encontrada</p></td>
			</tr>
			<%
		}
		%>
	</table>
</div>

<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
  
  	<li class="page-item <%if((request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0)){%>disabled<%}%>">
      
      <form method="post" action="HistoricoRecorrecaoDeProva" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
      	Anterior
      	</button>
      </form>
      
    </li>
  
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 2)){%>
    <li class="page-item">
      
      <form method="post" action="HistoricoRecorrecaoDeProva" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -3);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -2);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 1)){%>
    <li class="page-item">
      
      <form method="post" action="HistoricoRecorrecaoDeProva" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -2);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0)){%>
    <li class="page-item">
      
      <form method="post" action="HistoricoRecorrecaoDeProva" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")));}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    <li class="page-item disabled">
    	<span class="page-link"><%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina"))+1);}%></span>
    </li>
    <%if(numSolicitacoes > 10){%>
    <li class="page-item">
    
      <form method="post" action="HistoricoRecorrecaoDeProva" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
      	<%if(request.getParameter("pagina") == null){%>2<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +2);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    <%if(numSolicitacoes > 20){%>
    <li class="page-item">
    
      <form method="post" action="HistoricoRecorrecaoDeProva" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>2<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +2);}%>">
      	<%if(request.getParameter("pagina") == null){%>3<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +3);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    <%if(numSolicitacoes > 30){%>
    <li class="page-item">
    
      <form method="post" action="HistoricoRecorrecaoDeProva" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>3<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +3);}%>">
      	<%if(request.getParameter("pagina") == null){%>4<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +4);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
    <li class="page-item <%if(numSolicitacoes <= 10){%>disabled<%}%>">
    
      <form method="post" action="HistoricoRecorrecaoDeProva" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
      	Próximo
      	</button>
      </form>
      
    </li>
    
  </ul>
</nav>
