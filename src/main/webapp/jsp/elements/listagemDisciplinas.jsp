<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="java.util.*"%>

<div class="table-responsive">
	<form method="post">
		<label for="buscainput"><h2><b> Escolha forma de pesquisa </b></h2></label> 
		<h2><INPUT TYPE="radio" name="opcao" VALUE="1"> código
		<INPUT TYPE="radio" name="opcao" VALUE="2"> nome</h2>
		<a class="form-inline">
		<input class="form-control" style="width: 250px;" style='text-transform:uppercase'
			name="id_disciplina" placeholder="Código ou nome da disciplina" <%if(request.getParameter("id_disciplina")!= null){%>value="<%=request.getParameter("id_disciplina")%>"<%}%>>&nbsp;
		<button class="btn btn-sm btn-primary" type="submit">Procurar</button></a>
	</form>
	<br>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Código</th>
				<th scope="col">Nome</th>
				<th scope="col">Carga</th>
				<th scope="col">Créditos</th>
				<th scope="col" colspan="2" style="text-align: center;">Opções</th>
			</tr>
		</thead>
		<%
			DisciplinaDAO dao = new JDBCDisciplinaDAO();
			List<Disciplina> contatos = new ArrayList<>();
			
			int tipobusca = 0;
			int pagina = 0;
			String id_disciplina = "";
			int numSolicitacoes = 0;
			
			if(request.getParameter("opcao") != null){
				tipobusca = Integer.parseInt(request.getParameter("opcao"));
			}
			if(request.getParameter("pagina") != null){
				pagina = Integer.parseInt(request.getParameter("pagina"));
			}
			if(request.getParameter("id_disciplina") != null){
				id_disciplina = request.getParameter("id_disciplina");
			}
			if(tipobusca == 1){
				Disciplina disciplina = dao.buscarPorId(id_disciplina);
				if(disciplina != null){
					contatos.add(disciplina);
				}
			}else if (tipobusca == 2 || !id_disciplina.equals("")){
				contatos = dao.buscarPorNome( id_disciplina, pagina*10, 10);
				numSolicitacoes = dao.countDisciplina(pagina, id_disciplina);
				
				
			} else{
				contatos = dao.listar(pagina*10, 10);
				numSolicitacoes = dao.countDisciplina(pagina);
			}
			
			System.out.println("num soli:" + numSolicitacoes);
			System.out.println("Pagina:" + pagina);
			
			for (Disciplina contato : contatos) {
		%>
		<tr>
			<td><%=contato.getId()%></td>
			<td><%=contato.getNome()%></td>
			<td><%=contato.getCarga()%></td> 
			<td><%=contato.getCreditos()%></td>
			<td class="text-center">
				<form method="post" action="EditarDisciplina" id="form<%=contato.getId()%>">
					<button  class="btn btn-primary btn-sm " form="form<%=contato.getId()%>" style="height: 30px;" type="submit" name="button" value="<%=contato.getId()%>" >
					Visualizar
					</button>
				</form>
			</td>
			<td class="text-center">
				<form method="post" action="ExcluirDisciplina" id="formEx<%=contato.getId()%>">
					<button  class="btn btn-primary btn-sm " form="formEx<%=contato.getId()%>" style="height: 30px;" type="submit" name="buttonEx" value="<%=contato.getId()%>" >
					Excluir
					</button>
				</form>
			</td>
		</tr>
		
		<%
		}
			if(contatos.isEmpty()){
				%>
				<tr><td><p>Nenhuma disciplina foi encontrada! </p></td></tr>
			<% }%>

	</table>
</div>

<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
  
  	<%if(!(request.getParameter("pagina") == null || pagina <= 2)){%>
    <li class="page-item">
      
      <form method="post" action="ListarDisciplinas" id="formPag">
      	<input type="hidden" name="id_disciplina" value="<%=id_disciplina%>">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -3);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(pagina-2);}%>
      	</button>
      </form>
      
    </li>
    <% } %>
  	
  	
  	<%if(!(request.getParameter("pagina") == null || pagina <= 1)){%>
    <li class="page-item">
      
      <form method="post" action="ListarDisciplinas" id="formPag">
      	<input type="hidden" name="id_disciplina" value="<%=id_disciplina%>">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -2);}%>">
      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(pagina-1);}%>
      	</button>
      </form>
      
    </li>
    <% } %>
  	
  	<%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0)){%>
    <li class="page-item">
      
      <form method="post" action="ListarDisciplinas" id="formPag">
      	<input type="hidden" name="id_disciplina" value="<%=id_disciplina%>">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
      	Anterior
      	</button>
      </form>
      
    </li>
    <% } %>
    
    <li class="page-item disabled">
    	<span class="page-link"><%if(request.getParameter("pagina") == null){%>1<%}else{out.print(pagina+1);}%></span>
    </li>
    
    <% if(numSolicitacoes >= 10){ %>
    <li class="page-item">
    
      <form method="post" action="ListarDisciplinas" id="formPag">
      	<input type="hidden" name="id_disciplina" value="<%=id_disciplina%>">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
      	Próximo
      	</button>
      </form>
    </li>
    <% } %>
    
    <% if(numSolicitacoes >= 20){ %>
    <li class="page-item">
    
      <form method="post" action="ListarDisciplinas" id="formPag">
      	<input type="hidden" name="id_disciplina" value="<%=id_disciplina%>">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>2<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +2);}%>">
      	<%if(pagina == 0){%>3<%}else{out.print(pagina +3);}%>
      	</button>
      </form>
    </li>
    <% } %>
    
    <% if(numSolicitacoes >= 30){ %>
    <li class="page-item">
    
      <form method="post" action="ListarDisciplinas" id="formPag">
      	<input type="hidden" name="id_disciplina" value="<%=id_disciplina%>">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>3<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +3);}%>">
      	<%if(pagina == 0){%>4<%}else{out.print(pagina +4);}%>
      	</button>
      </form>
    </li>
    <% } %>
  </ul>
</nav>