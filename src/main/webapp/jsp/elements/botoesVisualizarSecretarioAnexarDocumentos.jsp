
<div class="modal-footer">
		<!-- Botao Cancelar -->
	<a href="Inicio"><input type="button" class="btn btn-primary btn-sm"
	style="height: 30px;" value="Cancelar"></a>
		
	<!-- Botao Avaliar -->
	<input type="button" class="btn btn-primary btn-sm"
		style="height: 30px;" value="Avaliar" onclick="verificaAnexos(<%=(String)request.getAttribute("id") %>)"
		data-toggle="modal" data-target="#testeCoor">
	
</div>
					
					<!-- Modal -->
					<div class="modal fade" id="testeCoor" tabindex="1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
						<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="modalLabel">Confirmar registro da solicita��o</h5>
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
									
										<input type="hidden" id="ementaAnexada" name="ementaAnexada" value="0"/>
										<input type="hidden" id="historicoAnexado" name="historicoAnexado" value="0"/>
										<div class="form-group">						
										<label for="resultadoInput"><b> Resultado </b></label>
									        <select type="text" name="resultado" class="form-control custom-select" id="resultado" required>
									            <option value="" selected="selected" disabled="disabled">Selecione uma op��o</option>
									            <option value="Valido">V�lido</option>
									        	<option value="Invalido">Inv�lido</option>              
									        </select>
									        <br>
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
									<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">Cancelar</button>
									<td>
									<button  class="btn btn-primary btn-sm active" form="form<%=(String)request.getAttribute("id")%>"
										class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id") %>" > Confirmar
									</button>
									</td>
									
										
									
							</div>
							
							</form>
						</div>
						</div>
					</div>
					
					<script>
					
					//Verifica se todos os anexos foram colocados
					function verificaAnexos(){
						if($("#anexoEmenta").attr("title") != null){
							$("#ementaAnexada").val("1");
						}
						if($("#anexoHistorico").attr("title") != null){
							$("#historicoAnexado").val("1");
						}
					}
					
					
					</script>