<div class="modal-footer">
		
<input type="button" class="btn btn-primary btn-sm"
		style="height: 30px;" value="Avaliar"
		data-toggle="modal" data-target="#testeCoor">
	
<a href="Inicio"><input type="button" class="btn btn-primary btn-sm"
	style="height: 30px;" value="Cancelar"></a>
</div>
			<!-- Modal -->
			<div class="modal fade" id="testeCoor" tabindex="1"
				role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
				<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalLabel">Confirmar cadastro
								da sele��o</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					<form method="POST" action="AnaliseCoordenador" id="form<%=(String)request.getAttribute("id")%>">
					<div class="modal-body">
						<div class="card">
							<div class="card-header">
								<label for="listaDisciplinasAproveitadas" class="card-title text-uppercase font-weight-bold">INSERIR RESULTADOS</label>
							</div>
							<div class="card-body">
								<div class="form-group">
								<label for="resultadoInput"><b> Resultado </b></label>
							        <input type="radio" name="resultado" class="form-control custom-select" value="valido" required>Valido
							        <input type="radio" name="resultado" class="form-control custom-select" value="invalido" required>Invalido
						            <div class="invalid-feedback">
						                            
						        	</div>
						        	<br>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
							<button  class="btn btn-primary btn-sm" form="form<%=(String)request.getAttribute("id")%>"
								 style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id") %>" > Confirmar
							</button>
					</div>
					</form>
				</div>
				</div>
			</div>
