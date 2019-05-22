
	<!-- Botoes de Aluno 
		- Gerar PDF: faz donwload do PDF de solicitacao
		- Editar: redireciona para a pagina EditarSolicitacao.jsp  -->
	
	<!-- Botao Gerar PDF de Solicitação -->
	<form method="POST" action="GerarPDF" id="pdf<%=(String)request.getAttribute("id")%>">
		<button class="btn btn-primary btn-sm" form="pdf<%=(String)request.getAttribute("id")%>" 
			style="height: 30px;" type="submit" name="id" value="<%=(String)request.getAttribute("id")%>"> Gerar PDF
		</button>
	</form>
	<!-- Botao Editar -->
	<form method="POST" action="EditarSolicitacao" id="form<%=(String)request.getAttribute("id")%>">
		<button  class="btn btn-primary btn-sm" form="form<%=(String)request.getAttribute("id")%>"
			style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id")%>" > Editar
		</button>
	</form>
