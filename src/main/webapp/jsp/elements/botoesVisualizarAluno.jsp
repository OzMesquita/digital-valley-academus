<div class="form-group">
	<div class="modal-footer">
	<form method="POST" action="Inicio">
		<td><button  class="btn btn-primary btn-sm active" form="form"
			class="btn btn-primary btn-sm" style="height: 30px;" type="submit" > Cancelar
		</button></td>
	</form>
	
	<form method="POST" action="EditarSolicitacao.jsp" id="form<%=(String)request.getAttribute("id")%>">
		<td><button  class="btn btn-primary btn-sm active" form="form<%=(String)request.getAttribute("id")%>"
			class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id")%>" > Editar
		</button></td>
	</form>
	</div>
</div>
