<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao"%>
<%@ page import="java.util.*"%>

<div class="dropdown">
	<button type="button" class="btn dropdown-toggle btn-sm btn-icon filtro_tela" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<i class="material-icons">filter_list</i>
		Filtrar
	</button>
	<div class="dropdown-menu dropdown-menu-left">
		<a class="dropdown-item" href="Inicio">Todas as seleções</a>
		<a class="dropdown-item" href="Inicio?solicitacao=andamento">Solicitações em andamento</a>
		<a class="dropdown-item" href="Inicio?solicitacao=finalizado">Solicitações finalizadas</a>
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
 
	
		List<Solicitacao> solicitacoes = (List<Solicitacao>) request.getAttribute("listaSol");
		
		//SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
		//List<Solicitacao> solicitacoes = sodao.listar();
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
				<button  class="btn btn-primary btn-sm active" form="form<%=soli.getIdSolicitacao()%>" class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=soli.getIdSolicitacao() %>" >
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