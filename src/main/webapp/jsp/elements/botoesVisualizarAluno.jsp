<div class="form-group">
	<div class="modal-footer">
	
	<%
		if((boolean)request.getAttribute("Editar")){
	%>
	<form method="POST" action="EditarSolicitacao" id="form<%=(String)request.getAttribute("id")%>">
		<td><button  class="btn btn-primary btn-sm" form="form<%=(String)request.getAttribute("id")%>"
			class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id")%>" > Editar
		</button></td>
	</form>
	<%
		}
	%>
	<a href="Inicio">
		<input type="button" class="btn btn-primary btn-sm"
			style="height: 30px;" value="Cancelar"></a>
	</div>
</div>
