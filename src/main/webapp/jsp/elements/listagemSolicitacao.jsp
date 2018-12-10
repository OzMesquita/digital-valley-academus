<%@page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao"%>
<%@ page import="java.util.*"%>

<div class="dropdown">
	<button type="button" class="btn dropdown-toggle btn-sm btn-icon filtro_tela" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<i class="material-icons">filter_list</i>
		Filtrar
	</button>
	<div class="dropdown-menu dropdown-menu-left">
		<%
		PerfilAcademus usuario = (PerfilAcademus)session.getAttribute("usuario"); 
		
		if (usuario.getNivel() == NivelAcademus.ALUNO){
		%>
		<a class="dropdown-item" href="Inicio">Todas as solicita��es</a>
		<a class="dropdown-item" href="Inicio?solicitacao=submetido">Solicita��es poss�veis de editar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=andamento">Solicita��es em andamento</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicita��es finalizadas</a>
		
		<% } else if (usuario.getNivel() == NivelAcademus.SECRETARIO){
		%>
		<a class="dropdown-item" href="Inicio">Todas as solicita��es</a>
		<a class="dropdown-item" href="Inicio?solicitacao=submetido">Solicita��es submetidas</a>
		<a class="dropdown-item" href="Inicio?solicitacao=validado">Solicita��es para validar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicita��es finalizadas</a>
		
		<% } else if (usuario.getNivel() == NivelAcademus.COORDENADOR){ 
		%>
		<a class="dropdown-item" href="Inicio">Todas as solicita��es</a>
		<a class="dropdown-item" href="Inicio?solicitacao=analizado">Solicita��es para analizar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicita��es finalizadas</a>
		
		<% } else if (usuario.getNivel() == NivelAcademus.PROFESSOR) {
		%>
		<a class="dropdown-item" href="Inicio">Todas as solicita��es</a>
		
		<% } else if(usuario.getIsAdmin()){
		%>
		<a class="dropdown-item" href="Inicio">Todas as solicita��es</a>
		<a class="dropdown-item" href="Inicio?solicitacao=submetido">Solicita��es submetidas</a>
		<a class="dropdown-item" href="Inicio?solicitacao=validado">Solicita��es para validar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=analizado">Solicita��es para analizar</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicita��es finalizadas</a>
		<% } else { %>
		<a class="dropdown-item" href="Inicio">Todas as solicita��es</a>
		<% } %>
	</div>
</div>
			         <br>

<div class="table-responsive">
	<table class="table">
		<thead>
			
			<tr>
				<th scope="col">Codigo de Solicita��o</th>
				<th scope="col">Status</th>
				<th scope="col">Solicitante</th>
				<th scope="col">Disciplina</th>
				<th scope="col">Resultado</th>
				<th> </th>
			</tr>
		</thead>
				<%
 
	
		List<Solicitacao> solicitacoes = (List<Solicitacao>) request.getAttribute("listaSol");

		if(solicitacoes == null){
			solicitacoes = (List<Solicitacao>) session.getAttribute("listaSol");
			if (solicitacoes == null){
			}
		}
		
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
		</tr>
		
		<%
			}
			
		}
		%>
	</table>
</div>