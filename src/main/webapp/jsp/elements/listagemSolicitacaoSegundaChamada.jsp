<%@page import="br.ufc.russas.n2s.academus.model.SegundaChamada"%>
<%@page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="java.util.*"%>

<div class="table-responsive">
	<table class="table">
		<thead>
			
			<tr>
				<th scope="col">C�digo da Solicita��o</th>
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
		
		<%
			}
			
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
      	Pr�ximo
      	</button>
      </form>
      
    </li>
    
  </ul>
</nav>
