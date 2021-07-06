<%@page import="br.ufc.russas.n2s.academus.model.SegundaChamada"%>
<%@page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="java.util.*"%>


<!-- Essa pagina filtra e faz a listagem de todas as solicitacoes de Recorreção de Provas -->
<%PerfilAcademus usuario = (PerfilAcademus)session.getAttribute("userAcademus"); %>
<div class="dropdown">
	<button type="button" class="btn dropdown-toggle btn-sm btn-icon filtro_tela" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<i class="material-icons">filter_list</i>
		Filtrar
	</button>
	<div class="dropdown-menu dropdown-menu-left">
		
		<a class="dropdown-item" href="HistoricoSegundaChamada">Todas as solicitações</a>
		<a class="dropdown-item" href="HistoricoSegundaChamada?tipoSolicitacao=submetido">Solicitações submetidas</a>
		<a class="dropdown-item" href="HistoricoSegundaChamada?tipoSolicitacao=finalizado">Solicitações finalizadas</a>
		
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
				<th> </th>
			</tr>
		</thead>
				<%
 
		
		List<SegundaChamada> listaSC = (List<SegundaChamada>) session.getAttribute("listaSC");
		int numSolicitacoes = (Integer) session.getAttribute("numSolicitacoes");
		
		if(listaSC != null){
			for (SegundaChamada sc : listaSC) {
				
		%>
		<tr>
			<td><%=sc.getIdSegundaChamada()%></td>
			<td>SOLICITADA</td>
			<td><%=sc.getAluno().getNome()%></td>
			<td><%=sc.getDisciplina().getNome()%></td>
			<td>
				<a href="VisualizarSegundaChamada?id=<%=sc.getIdSegundaChamada()%>" class="btn btn-primary btn-sm">Visualizar</a>
			</td>
		</tr>
		
		
		
			<% if(usuario.getNivel() == NivelAcademus.PROFESSOR && sc.getStatus()==null){ %>
		<td>
		<form method="POST" action="RegistrarEntrega" id="res<%=sc.getIdSegundaChamada()%>">
			<button class="btn btn-primary btn-sm" form="res<%=sc.getIdSegundaChamada()%>" class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="registro" value="<%=sc.getIdSegundaChamada() %>" >
			Registrar Entrega
			</button>
		</form>
		</td>
		<%} %>
		<% }
			
		}
		%>
		
	</table>
</div>

<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
  
  	<li class="page-item <%if((request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0)){%>disabled<%}%>">
      
      <form method="post" action="HistoricoSegundaChamada" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
      	Anterior
      	</button>
      </form>
      
    </li>
  
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 2)){%>
    <li class="page-item">
      
      <form method="post" action="HistoricoSegundaChamada" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -3);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -2);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 1)){%>
    <li class="page-item">
      
      <form method="post" action="HistoricoSegundaChamada" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -2);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0)){%>
    <li class="page-item">
      
      <form method="post" action="HistoricoSegundaChamada" id="formPag">
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
    
      <form method="post" action="HistoricoSegundaChamada" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
      	<%if(request.getParameter("pagina") == null){%>2<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +2);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    <%if(numSolicitacoes > 20){%>
    <li class="page-item">
    
      <form method="post" action="HistoricoSegundaChamada" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>2<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +2);}%>">
      	<%if(request.getParameter("pagina") == null){%>3<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +3);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    <%if(numSolicitacoes > 30){%>
    <li class="page-item">
    
      <form method="post" action="HistoricoSegundaChamada" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>3<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +3);}%>">
      	<%if(request.getParameter("pagina") == null){%>4<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +4);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
    <li class="page-item <%if(numSolicitacoes <= 10){%>disabled<%}%>">
    
      <form method="post" action="HistoricoSegundaChamada" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
      	Próximo
      	</button>
      </form>
      
    </li>
    
  </ul>
</nav>
