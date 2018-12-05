<%@ page import="br.ufc.russas.n2s.academus.dao.ArquivoDAO" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCArquivoDAO" %>
		<%
			//ArquivoDAO adao = new JDBCArquivoDAO();
			DisciplinaCursadaDAO dcdao = new JDBCDisciplinaCursadaDAO();
			DisciplinaCursada dc = dcdao.
		%>
		
		<input type="button" class="btn btn-secondary btn-sm" value="Ementa" data-toggle="modal" data-target="#anexarEmenta">
		<input type="button" class="btn btn-secondary btn-sm" value="Historico" data-toggle="modal" data-target="#anexarHistorico">
					
					<!-- Modal -->
					<div class="modal fade" id="anexarEmenta" tabindex="1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
						<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="modalLabel">Anexar Documentos</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
							<form action="SalvarAnexos" method="Post" enctype="multipart/form-data">
								
								<div class="modal-body">
									<div class="card">
										<div class="card-header">
											<label for="listaDisciplinasAproveitadas" class="card-title text-uppercase font-weight-bold">Ementa</label>
										</div>
										<div class="card-body">
											<div class="form-row">
										        <input type="hidden" id="matricula" name="matricula" value="<%=request.getParameter("matricula")%>">
										        <input type="hidden" id="id_solicitacao" name="id_solicitacao" value="<%=request.getParameter("id_solicitacao")%>">
										        <input type="hidden" id="id_disciplina_cursada" name="id_disciplina_cursada" value="<%=request.getParameter("id_disciplina_cursada")%>">
										      	<input type="file" id="anexo" name="anexo" accept=".pdf">
												<input type="submit" value="Anexar">
												<input type="hidden" id="teste" value="deu certo" >
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button  class="btn btn-primary btn-sm active"
										class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" > Cancelar
									</button>	
								</div>
							</form>
						</div>
						</div>
					</div>
					
					<!-- Modal -->
					<div class="modal fade" id="anexarHistorico" tabindex="1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
						<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="modalLabel">Anexar Documentos</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
							<form action="SalvarAnexos" method="Post" enctype="multipart/form-data">
								<div class="modal-body">
									<div class="card">
										<div class="card-header">
											<label for="listaDisciplinasAproveitadas" class="card-title text-uppercase font-weight-bold">Histórico</label>
										</div>
										<div class="card-body">
											<div class="form-row">
										        <input type="hidden" id="matricula" name="matricula" value="<%=request.getParameter("matricula")%>">
										        <input type="hidden" id="id_solicitacao" name="id_solicitacao" value="<%=request.getParameter("id_solicitacao")%>">
										        <input type="hidden" id="id_disciplina_cursada" name="id_disciplina_cursada" value="<%=request.getParameter("id_disciplina_cursada")%>">
										        <%if()%>
										        
										        <input type="file" id="anexo" name="anexo" accept=".pdf">
												<input type="submit" value="Anexar">
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button  class="btn btn-primary btn-sm active"
										class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" > Cancelar
									</button>	
								</div>
							</form>
						</div>
						</div>
					</div>