<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="java.util.*"%>

<div class=" table-responsive">
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
			List<Disciplina> contatos = dao.listar();
			for (Disciplina contato : contatos) {
		%>
		<tr>
			<td><%=contato.getId()%></td>
			<td><%=contato.getNome()%></td>
			<td><%=contato.getCarga()%></td> 
			<td><%=contato.getCreditos()%></td>
			<form method="post" action="EditarDisciplina" id="form<%=contato.getId()%>">
				<td>
					<button  class="btn btn-primary btn-sm " form="form<%=contato.getId()%>" class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=contato.getId()%>" >
					Editar
					</button>
				</td>
			</form>
			<form method="post" action="ExcluirDisciplina" id="formEx<%=contato.getId()%>">
				<td>
					<button  class="btn btn-primary btn-sm " form="formEx<%=contato.getId()%>" class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="buttonEx" value="<%=contato.getId()%>" >
					Remover
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
