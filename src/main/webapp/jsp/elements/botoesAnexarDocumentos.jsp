<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaCursadaDAO" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaCursadaDAO" %>
<%@ page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
		//ArquivoDAO adao = new JDBCArquivoDAO();
		DisciplinaCursadaDAO dcdao = new JDBCDisciplinaCursadaDAO();
		DisciplinaCursada dc = dcdao.buscarPorId(Integer.parseInt(request.getParameter("id_disciplina_cursada")));
%>

<!DOCTYPE html>
<html lang="pt-br">
	<body>
		
		<input type="button" class="btn btn-primary btn-sm" value="Ementa" data-toggle="modal" data-target="#anexarEmenta">
		<input type="button" class="btn btn-primary btn-sm" value="Historico" data-toggle="modal" data-target="#anexarHistorico">
					
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
										        <input type="hidden" id="tipo_arquivo" name="tipo_arquivo" value="1">
										        <input type="hidden" id="chave" name="chave" value="0">
										        <%if(dc.getEmenta().getIdArquivo() > 0){%>
										        	<%=dc.getEmenta().getNome()%>
										        <%}else{%>
    												Nenhum arquivo anexado
												<%}%>
												<br>
												<input type="file" id="anexo1" name="anexo" accept=".pdf" class="form-control-file">
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<%if(dc.getEmenta().getIdArquivo() > 0){%>
										<input type="submit" value="Download" class="btn btn-primary btn-sm active" onclick="atribuirValor(2)">
									<%}%>
									<input type="submit" disabled="disabled" value="AnexarEmenta" class="btn btn-primary btn-sm active" onclick="atribuirValor(1)">
									<button type="button" class="btn btn-primary btn-sm active" data-dismiss="modal">Cancelar</button>
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
										        <input type="hidden" id="tipo_arquivo" name="tipo_arquivo" value="2">
										        <input type="hidden" id="chave" name="chave" value="0">
										        <%if(dc.getHistorico().getIdArquivo() > 0){%>
										        	<%=dc.getHistorico().getNome()%>
										        <%}else{%>
    												Nenhum arquivo anexado
												<%}%>
												<br>
												<input type="file" id="anexo2" name="anexo" accept=".pdf" class="form-control-file">
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<%if(dc.getHistorico().getIdArquivo() > 0){%>
										<input type="submit" value="Download" class="btn btn-primary btn-sm active" onclick="atribuirValor(2)">
									<%}%>
									<input type="submit" disabled="disabled" value="AnexarHistorico" class="btn btn-primary btn-sm active" onclick="atribuirValor(1)">	
									<button type="button" class="btn btn-primary btn-sm active" data-dismiss="modal">Cancelar</button>
								</div>
							</form>
						</div>
						</div>
					</div>
	</body>
	<script>
		function atribuirValor(i){
			document.getElementById("chave").value = i;
		}
		function abilitarAnexacaoEmenta(){
			if(document.getElementById("anexo"))
		}
	</script>
</html>
					