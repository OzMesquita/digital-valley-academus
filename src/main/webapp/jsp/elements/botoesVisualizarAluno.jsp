
	<!-- Botoes de Aluno 
		- Editar: redireciona para a pagina EditarSolicitacao.jsp
		- Cancelar: redireciona a pagina para Inicio.jsp  -->
	
	<!-- Botao Editar -->
	<form method="POST" action="EditarSolicitacao" id="form<%=(String)request.getAttribute("id")%>">
		<button  class="btn btn-primary btn-sm" form="form<%=(String)request.getAttribute("id")%>"
			class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id")%>" > Editar
		</button>
	</form>
