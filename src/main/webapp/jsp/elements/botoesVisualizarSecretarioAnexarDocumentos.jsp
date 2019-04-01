
<div class="modal-footer">
			<%
		if((boolean)request.getAttribute("Avaliar")){
	%>
	<!-- Botao Avaliar -->
<input type="button" class="btn btn-primary btn-sm"
		style="height: 30px;" value="Avaliar"
		data-toggle="modal" data-target="#testeCoor">
	<%
		}
	%>
	<!-- Botao Cancelar -->
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
										da seleção</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
							<form method="POST" action="AnaliseSecretario" id="form<%=(String)request.getAttribute("id")%>">
							<div class="modal-body">
								<div class="card">
									<div class="card-header">
										<label for="listaDisciplinasAproveitadas" class="card-title text-uppercase font-weight-bold">INSERIR RESULTADOS</label>
									</div>
									<div class="card-body">
										<div class="form-group">
										<label for="resultadoInput"><b> Resultado </b></label>
									        <select type="text" name="resultado" class="form-control custom-select" id="resultado" required>
									            <option value="" selected="selected" disabled="disabled">Selecione uma opção</option>
									            <option value="Valido">Valido</option>
									        	<option value="Invalido">Invalido</option>              
									        </select>
								            <div class="invalid-feedback">
								                            
								        	</div>
								        	<br>
								
								        <label for="justificativaInput"><b> Justificativa </b></label> 
											<textarea rows="8" id="justificativaInput" name="justificativaInput" class="form-control" aria-describedby="tituloHelp" required> </textarea>
											<div class="invalid-feedback">
									                            
								            </div>
											</div>
										</div>
									</div>
							</div>
							<div class="modal-footer">
									<td>
									<button  class="btn btn-primary btn-sm active" form="form<%=(String)request.getAttribute("id")%>"
										class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id") %>" > Confirmar
									</button>
									</td>
									
									<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">Cancelar</button>
										
									
							</div>
							</form>
						</div>
						</div>
					</div>