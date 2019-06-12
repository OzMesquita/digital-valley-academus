<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="java.util.*"%>

<div class="table-responsive">
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
			List<Disciplina> contatos;
			
			if(request.getParameter("pagina") == null){
				contatos = dao.listar(0, 10);
			}
			else{
				int pag = Integer.parseInt(request.getParameter("pagina"));
				contatos = dao.listar(pag*10, 10);
			}
			
			for (Disciplina contato : contatos) {
		%>
		<tr>
			<td><%=contato.getId()%></td>
			<td><%=contato.getNome()%></td>
			<td><%=contato.getCarga()%></td> 
			<td><%=contato.getCreditos()%></td>
			<form method="post" action="EditarDisciplina" id="form<%=contato.getId()%>">
				<td class="text-center">
					<button  class="btn btn-primary btn-sm " form="form<%=contato.getId()%>" style="height: 30px;" type="submit" name="button" value="<%=contato.getId()%>" >
					Visualizar
					</button>
				</td>
			</form>
			<form method="post" action="ExcluirDisciplina" id="formEx<%=contato.getId()%>">
				<td class="text-center">
					<button  class="btn btn-primary btn-sm " form="formEx<%=contato.getId()%>" style="height: 30px;" type="submit" name="buttonEx" value="<%=contato.getId()%>" >
					Excluir
					</button>
					</a>
				</td>
			</form>
		</tr>
		
		<%
		}
		%>

	</table>
</div>

<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item <%if(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0){%>disabled<%}%>">
      
      <form method="post" action="ListarDisciplinas" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
      	Anterior
      	</button>
      </form>
      
    </li>
    <li class="page-item <%if(contatos.size() < 10){%>disabled<%}%>">
    
      <form method="post" action="ListarDisciplinas" id="formPag">
      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
      	Proximo
      	</button>
      </form>
      
    </li>
  </ul>
</nav>