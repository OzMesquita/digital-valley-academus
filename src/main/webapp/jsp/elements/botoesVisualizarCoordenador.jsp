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
         <div class="modal-footer">
			<form method="POST" action="AnaliseCoordenador" id="form<%=(String)request.getAttribute("id")%>">
				<td>
				<button  class="btn btn-primary btn-sm active" form="form<%=(String)request.getAttribute("id")%>"
					class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id") %>" > Confirmar
				</button>
				</td>
			</form>
			<form method="POST" action="Inicio">
				<td>
				<button  class="btn btn-primary btn-sm active"
					class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" > Cancelar
				</button>
				</td>
			</form>
		</div>