<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 		
		<a href="Inicio" type="button" id="enviar"
						class="btn btn-secondary"> Cancelar </a>
		<input type="button"
						class="btn btn-primary" value="Avaliar"
						data-toggle="modal" data-target="#testeCoor">
					
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
							<form method="POST" action="AnaliseCoordenador" id="form<%=(String)request.getAttribute("id")%>">
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
									            <option value="Deferido">Deferido</option>
									        	<option value="Indeferido">Indeferido</option>              
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
									<form method="POST" action="Inicio">
										<td>
										<button  class="btn btn-primary btn-sm active"
											class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" > Cancelar
										</button>
										</td>
									</form>
							</div>
							</form>
						</div>
						</div>
					</div>
		