<%@page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao"%>
<%@ page import="java.util.*"%>
<!-- Essa pagina faz a listagem de todas as solicitacoes que podem ser vistas pelos usuários -->
<div class="dropdown">
	<button type="button" class="btn dropdown-toggle btn-sm btn-icon filtro_tela" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<i class="material-icons">filter_list</i>
		Filtrar
	</button>
	<div class="dropdown-menu dropdown-menu-left">
		<!-- Mostra as opcoes de filtro pelos usuarios -->
		<%
		PerfilAcademus usuario = (PerfilAcademus)session.getAttribute("userAcademus");
		String statusSoli = (request.getParameter("solicitacao") != null) ? request.getParameter("solicitacao") : "null";
		
		if (usuario.getNivel() == NivelAcademus.ALUNO){
		%>
		<!-- Filtro do usuario "Aluno"
			 - Todas: Todas as solicitações do Aluno
			 - Submetidas: Todas as solicitações que foram submetidas pelo aluno
			 - Andamento: Todas as solicitações do aluno que já passaram pelo Secretário
			 - Finalizadas: Todas as solicitações do aluno que estejam finalizadas
		-->
		<a class="dropdown-item" href="Inicio">Todas as solicitações</a>
		<a class="dropdown-item" href="Inicio?solicitacao=submetido">Solicitações possíveis de editar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=andamento">Solicitações em andamento</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicitações finalizadas</a>
		
		<% } else if (usuario.getNivel() == NivelAcademus.SECRETARIO){
		%>
		<!-- Filtro do usuario "Secretário"
			 - Todas: Todas as solicitações do Sistema
			 - Submetidas: Todas as solicitações que foram submetidas do sistema
			 - Validadas: Todas as solicitações validadas pelo Secretário
			 - Finalizadas: Todas as solicitações que estejam finalizadas
		-->
		<a class="dropdown-item" href="Inicio">Todas as solicitações</a>
		<a class="dropdown-item" href="Inicio?solicitacao=submetido">Solicitações submetidas</a>
		<a class="dropdown-item" href="Inicio?solicitacao=validado">Solicitações para validar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicitações finalizadas</a>
		
		<% } else if (usuario.getNivel() == NivelAcademus.COORDENADOR){ 
		%>
		<!-- Filtro do usuario "Coordenador"
			 - Todas: Todas as solicitações referentes as curso do Coordenador
			 - Analisando: Todas as solicitações do curso que o coordenador tem que avaliar
			 - Finalizadas: Todas as solicitações do curso que estejam finalizadas
		-->
		<a class="dropdown-item" href="Inicio">Todas as solicitações</a>
		<a class="dropdown-item" href="Inicio?solicitacao=analizado">Solicitações para analizar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicitações finalizadas</a>
		
		<% } else if (usuario.getNivel() == NivelAcademus.PROFESSOR) {
		%>
		<a class="dropdown-item" href="Inicio">Todas as solicitações</a>
		
		<% } else if(usuario.getIsAdmin()){
		%>
		<!-- Filtro do usuario Admim
			- Todas os status de todas as solicitaçoes
		-->
		<a class="dropdown-item" href="Inicio">Todas as solicitações</a>
		<a class="dropdown-item" href="Inicio?solicitacao=submetido">Solicitações submetidas</a>
		<a class="dropdown-item" href="Inicio?solicitacao=validado">Solicitações para validar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=analizado">Solicitações para analizar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicitações finalizadas</a>
		<% } else { %>
		<a class="dropdown-item" href="Inicio">Todas as solicitações</a>
		<% } %>
	</div>
</div>
			         <br>

<div class="table-responsive">
	<table class="table">
		<thead>
			
			<tr>
				<th scope="col">Codigo de Solicitação</th>
				<th scope="col">Status</th>
				<th scope="col">Solicitante</th>
				<th scope="col">Disciplina</th>
				<th scope="col">Resultado</th>
				<th> </th>
			</tr>
		</thead>
				<%
 
		
		List<Solicitacao> solicitacoes = (List<Solicitacao>) session.getAttribute("listaSol");
		int numSolicitacoes = (Integer) session.getAttribute("numSolicitacoes");
		
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
				<td>
				<button  class="btn btn-primary btn-sm" form="form<%=soli.getIdSolicitacao()%>" class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=soli.getIdSolicitacao() %>" >
				Visualizar
				</button>
				</td>
			</form>
			<% if(usuario.getNivel() == NivelAcademus.SECRETARIO){ %>
			<form method="POST" action="RegistarEntrega" id="form-<%=soli.getIdSolicitacao()%>">
				<td>
				<button  class="btn btn-primary btn-sm" form="form<%=soli.getIdSolicitacao()%>" class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="registroId" value="<%=soli.getIdSolicitacao() %>" >
				Registrar Entrega
				</button>
				</td>
			</form>
			<%} %>
		</tr>
		
		<%
			}
			
		}
		%>
	</table>
</div>

<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
  
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 2)){%>
    <li class="page-item">
      
      <form method="post" action="Inicio<%if(!statusSoli.equals("null")){%>?solicitacao=<%out.print(statusSoli);}%>" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -3);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -2);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 1)){%>
    <li class="page-item">
      
      <form method="post" action="Inicio<%if(!statusSoli.equals("null")){%>?solicitacao=<%out.print(statusSoli);}%>" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -2);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
    <%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0)){%>
    <li class="page-item">
      
      <form method="post" action="Inicio<%if(!statusSoli.equals("null")){%>?solicitacao=<%out.print(statusSoli);}%>" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")));}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    <li class="page-item disabled">
    	<span class="page-link"><%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina"))+1);}%></span>
    </li>
    <%if(numSolicitacoes >= 10){%>
    <li class="page-item">
    
      <form method="post" action="Inicio<%if(!statusSoli.equals("null")){%>?solicitacao=<%out.print(statusSoli);}%>" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
      	<%if(request.getParameter("pagina") == null){%>2<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +2);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    <%if(numSolicitacoes >= 20){%>
    <li class="page-item">
    
      <form method="post" action="Inicio<%if(!statusSoli.equals("null")){%>?solicitacao=<%out.print(statusSoli);}%>" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>2<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +2);}%>">
      	<%if(request.getParameter("pagina") == null){%>3<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +3);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    <%if(numSolicitacoes >= 30){%>
    <li class="page-item">
    
      <form method="post" action="Inicio<%if(!statusSoli.equals("null")){%>?solicitacao=<%out.print(statusSoli);}%>" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>3<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +3);}%>">
      	<%if(request.getParameter("pagina") == null){%>4<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +4);}%>
      	</button>
      </form>
      
    </li>
    <%}%>
    
  </ul>
</nav>
