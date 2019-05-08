<%@page import="br.ufc.russas.n2s.academus.model.Status"%>
<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao" %>
<%@ page import="br.ufc.russas.n2s.academus.model.Aluno" %>
<%@ page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada" %>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.SolicitacaoDAO" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC" %>
<%@ page import="java.util.*"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <%
 	
 	PerfilAcademus per = (PerfilAcademus) session.getAttribute("userAcademus");
 
	Solicitacao solicitacao = new Solicitacao();
	SolicitacaoDAO dao = new DAOFactoryJDBC().criarSolicitacaoDAO();
	
	boolean deuCerto = true;
	try{
		solicitacao = dao.buscarPorId(Integer.parseInt((String)(request.getAttribute("id"))));
	} catch(Exception e){
		deuCerto = false;
	}
	
%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Visualizando Solicitação</title>
		<meta charset="utf-8"/>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
			
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/design.css" />
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/bootstrap-datepicker.css" />
		<link type="text/css" rel="stylesheet" href="<%=Constantes.getAppCssUrl()%>/bootstrap-datepicker.standalone.css" />
	</head>
	<body>
		<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
		<div class="container-fluid">
			<div class="row">
				<c:import url="jsp/elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
				<div class="col-md-10">
					<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Você está em:</li>
						<li class="breadcrumb-item"><a href="Inicio">Início</a></li>
						<li class="breadcrumb-item active" aria-current="page">Visualizar Solicitacao</li>
					</ol>
					</nav>
					
					<h1>Visualizar Solicitação</h1>
					
					<br>
					<%
					if (solicitacao != null && deuCerto){
					%>
					<div class="form-group">						
						<!-- <form action="VisualizarSolicitacao" method="post"> -->
							<label for="idInput"><b> Código da Solicitação </b></label>
							<input type="text" name="id_solicitacao" value="<%= solicitacao.getIdSolicitacao() %>" class="form-control" id="idInput" aria-describedby="tituloHelp" readonly  required> 
							
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="nomeInput"><b> Status da Solicitação </b></label> 
							<input type="text" name="nome" value="<%= solicitacao.getStatus() %>" class="form-control" id="nomeInput" aria-describedby="tituloHelp" readonly required> 
							
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="cargaInput"><b> Nome do Solicitante </b></label> 
							<input type="text" name="componenteCurricular" value="<%=solicitacao.getSolicitante().getNome() %>" class="form-control" id="cargaInput" aria-describedby="tituloHelp" readonly required> 
							
							 <div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="creditosInput"><b> Nome da Disciplina </b></label> 
							<input type="text" name="creditos" value="<%=solicitacao.getDisciplinaAlvo().getDisciplina().getNome() %>" class="form-control" id="creditosInput" aria-describedby="tituloHelp" readonly required> 
							 
							<div class="invalid-feedback">
	                            
	                        </div>
	                        <br>
	                        
	                        <label for="justificativaInput"><b> Justificativa </b></label> 
							<textarea rows="8" id="justificativa" name="justificativa" class="form-control" aria-describedby="tituloHelp" readonly required> <%= solicitacao.getJustificativa() %></textarea>
							<div class="invalid-feedback">
	                            
	                        </div>
							<br>
														
							<label for="disciplinasCursadasInput"><b> Lista de Disciplinas Cursadas</b></label>
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th scope="col">Nome</th>
												<th scope="col">Instituição</th>
												<th scope="col">Carga</th>
												<th scope="col">Semestre</th>
												<th scope="col">Nota</th>
												<th scope="col">Anexos</th>
												
												
											</tr>
											
										</thead>
										<%
											
											for (DisciplinaCursada disCursada : solicitacao.getDisciplinasCursadas()) {
										%>
										<tr>
											<td><%=disCursada.getNome()%></td>
											<td><%=disCursada.getInstituicao()%></td>
											<td><%=disCursada.getCarga()%></td>
											<td><%=disCursada.getSemestre()%></td>
											<td><%=disCursada.getNota()%></td>
											<td>
												<input type="button" class="btn btn-secondary btn-sm" value="Download" data-toggle="modal" data-target="#anexarDownload-<%=disCursada.getId()%>">
											</td>
										</tr>
										<!-- Modal -->
										<div class="modal fade" id="anexarDownload-<%=disCursada.getId()%>" tabindex="1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
											<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="modalLabel">Downloads</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<form action="DownloadAnexos" method="Post">
												
													<div class="modal-body">
													<div class="card">
														<div class="card-header">
															<label for="listaDisciplinasAproveitadas" class="card-title text-uppercase font-weight-bold">Ementa</label>
														</div>
														<div class="card-body">
															<div class="form-row">
																<%if(disCursada.getEmenta().getIdArquivo() > 0){ %>
																	<h2><input type="checkbox" name="anexo" id="anexo" value="1" /> <%=disCursada.getEmenta().getNome()%><br/></h2>
															       	
															    <%}else{%>
					    											Nenhum arquivo anexado
																<%}%>
															       
															</div>
														</div>
															
														<div class="card-header">
															<label for="listaDisciplinasAproveitadas" class="card-title text-uppercase font-weight-bold">Historico</label>
														</div>
														<div class="card-body">
															<div class="form-row">
															<%if(disCursada.getHistorico().getIdArquivo() > 0){ %>
																<h2><input type="checkbox" name="anexo" id="anexo" value="2"/> <%=disCursada.getHistorico().getNome()%></h2><br/>
															<%}else{%>
					    										Nenhum arquivo anexado
															<%}%>
															</div>
														</div>
													</div>
													</div>
														
													<div class="modal-footer">
														<%if(disCursada.getEmenta().getIdArquivo() > 0 || disCursada.getHistorico().getIdArquivo() > 0){%>
															<input type="hidden" id="matricula" name="matricula" value="<%=solicitacao.getSolicitante().getMatricula()%>">
										        			<input type="hidden" id="id_solicitacao" name="id_solicitacao" value="<%=solicitacao.getIdSolicitacao()%>">
										        			<input type="hidden" id="id_disciplina_cursada" name="id_disciplina_cursada" value="<%=disCursada.getId()%>">
															
															<button value="1" name="button" class="btn btn-primary btn-sm active">Download</button>
															
														<%}%>
															<button type="button" class="btn btn-primary btn-sm active" data-dismiss="modal">Cancelar</button>
													</div>
												</form>
											</div>
											</div>
										</div>
										<%
											}
										%>
										
										
									</table>
								</div>
							<br>
								
							<label for="resultadoInput"><b> Resultado </b></label>
							<input type="text" name="resultadoInput" value="<%= solicitacao.getResultado() %>" class="form-control" id="resultadoInput" aria-describedby="tituloHelp" readonly required>
	
					
							<br>
							<br>
							<div class="modal-footer">
							<!-- Botao Cancelar -->
								<a href="Inicio">
								<input type="button" class="btn btn-primary btn-sm" style="height: 30px;" value="Cancelar"></a>
	
							<%if(per.getNivel() == NivelAcademus.ALUNO && solicitacao.getStatus() == Status.SOLICITADO){%>
						    	<c:import url="jsp/elements/botoesVisualizarAluno.jsp" charEncoding="UTF-8"></c:import>
							
							<% } else if(per.getNivel() == NivelAcademus.COORDENADOR && solicitacao.getStatus() == Status.ANALIZANDO){ %>
								<c:import url="jsp/elements/botoesVisualizarCoordenador.jsp" charEncoding="UTF-8"></c:import>
								
							<% } %>
							</div>
					</div>
					<%
					} else {
						%>
						Solicitação não encontrada.
						<%
					}
					%>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
	<% 
		String mensagem = (String) request.getAttribute("mensagem");
		if(mensagem != null){
			if (mensagem.equals("AN")){
		%>
				<script type="text/javascript">
     					alert("Avaliação Inválida! \nCampos de resultado e justificativa devem ser preenchidos.");
 					</script>
		<%	}
	}%>
</html>