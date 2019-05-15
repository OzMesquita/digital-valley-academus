<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao" %>
<%@ page import="br.ufc.russas.n2s.academus.model.Aluno" %>
<%@ page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada" %>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.model.Status" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.SolicitacaoDAO" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC" %>
<%@ page import="util.Constantes" %>
<%@ page import="java.util.*"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	
	Solicitacao solicitacao = new Solicitacao();
	SolicitacaoDAO dao = new DAOFactoryJDBC().criarSolicitacaoDAO();
	
	boolean deuCerto = true;
	try{
		System.out.print((String)(request.getAttribute("id")));
		solicitacao = dao.buscarPorId(Integer.parseInt((String)(request.getAttribute("id"))));
	} catch(Exception e){
		deuCerto = false;
		System.out.print("Deu nao");
	}
	
%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Registrar Entrega</title>
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
						<li class="breadcrumb-item active" aria-current="page">Registrar Entrega</li>
					</ol>
					</nav>
					<h1>Registrar Entrega de Documentação</h1>
					
					<br>
					<%
					if (solicitacao != null && deuCerto){
					%>
					<!--  <form action="SalvarAnexos" method="Post" enctype="multipart/form-data"> -->
					<div class="form-group">						
						<!-- <form action="VisualizarSolicitacao" method="post"> -->
							<label for="idInput"><b> Código da Solicitação </b></label>
							<input type="text" name="id_solicitacao" id="id_solicitacao" value="<%= solicitacao.getIdSolicitacao() %>" class="form-control" id="idInput" aria-describedby="tituloHelp" readonly  required> 
							
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
										
							<label for="disciplinasCursadasInput"><b> Lista de Disciplinas Cursadas</b></label>
								<div class="form-group table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th scope="col">Nome</th>
												<th scope="col">Instituição</th>
												<th scope="col">Carga Horária</th>
												<th scope="col">Semestre</th>
												<th scope="col">Nota</th>
												<th colspan="2">Anexos</th>
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
												<input type="button" class="btn btn-secondary btn-sm" value="Ementa" data-toggle="modal" data-target="#anexarEmenta-<%=disCursada.getId()%>">
												<input type="button" class="btn btn-secondary btn-sm" value="Histórico" data-toggle="modal" data-target="#anexarHistorico-<%=disCursada.getId()%>">
											</td>
										</tr>
				
										<!-- Modal -->
										<div class="modal fade" id="anexarEmenta-<%=disCursada.getId()%>" tabindex="1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
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
															        <input type="hidden" id="matricula" name="matricula" value="<%=solicitacao.getSolicitante().getMatricula()%>">
										        					<input type="hidden" id="id_solicitacao" name="id_solicitacao" value="<%=solicitacao.getIdSolicitacao()%>">
										        					<input type="hidden" id="id_disciplina_cursada" name="id_disciplina_cursada" value="<%=disCursada.getId()%>">
															        <input type="hidden" id="tipo_arquivo" name="tipo_arquivo" value="1">
															        <input type="hidden" id="chave1" name="chave" value="1">
															        <%if(disCursada.getEmenta().getIdArquivo() > 0){%>
															        	<%=disCursada.getEmenta().getNome()%>
															        <%}else{%>
					    												Nenhum arquivo anexado
																	<%}%>
																	<br>
																	<input type="file" id="anexo1-<%=disCursada.getId()%>" name="anexo" accept=".pdf" class="form-control-file" onchange="teste1(<%=disCursada.getId()%>)">
																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-danger btn-sm active" data-dismiss="modal">Cancelar</button>
														<input type="submit" id="submitEmenta-<%=disCursada.getId()%>" name="button" disabled="disabled" value="Confirmar" class="btn btn-primary btn-sm active" onclick="atribuirValor1(1)">
														<%if(disCursada.getEmenta().getIdArquivo() > 0){%>
															<!-- <a href="C://n2s//academus//anexo//375102//21//8//ementa-375102-21-8.pdf" class="btn btn-primary btn-sm active" onclick="atribuirValor1(2)" download>Download</a>-->
															<!--  <input type="submit" value="Download" name="button" class="btn btn-primary btn-sm active" onclick="atribuirValor1(2)">-->
															<!-- <button value="2" name="button" onclick="atribuirValor1(2)" class="btn btn-primary btn-sm active">Download</button>-->
														<%}%>
														
													</div>
												</form>
											</div>
											</div>
										</div>
										
										<!-- Modal -->
										<div class="modal fade" id="anexarHistorico-<%=disCursada.getId()%>" tabindex="1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
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
															        <input type="hidden" id="matricula" name="matricula" value="<%=solicitacao.getSolicitante().getMatricula()%>">
										        					<input type="hidden" id="id_solicitacao" name="id_solicitacao" value="<%=solicitacao.getIdSolicitacao()%>">
										        					<input type="hidden" id="id_disciplina_cursada" name="id_disciplina_cursada" value="<%=disCursada.getId()%>">
															        <input type="hidden" id="tipo_arquivo" name="tipo_arquivo" value="2">
															        <input type="hidden" id="chave2" name="chave" value="1">
															        <%if(disCursada.getHistorico().getIdArquivo() > 0){%>
															        	<%=disCursada.getHistorico().getNome()%>
															        <%}else{%>
					    												Nenhum arquivo anexado
																	<%}%>
																	<br>
																	<input type="file" id="anexo2-<%=disCursada.getId()%>" name="anexo" accept=".pdf" class="form-control-file" onchange="teste2(<%=disCursada.getId()%>)">
																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-danger btn-sm active" data-dismiss="modal">Cancelar</button>
														<input type="submit" id="submitHistorico-<%=disCursada.getId()%>" name="button" disabled="disabled" value="Confirmar" class="btn btn-primary btn-sm active" onclick="atribuirValor2(1)">
														<%if(disCursada.getHistorico().getIdArquivo() > 0){%>
															<!--  <input type="submit" value="Download" class="btn btn-primary btn-sm active" onclick="atribuirValor2(2)">-->
															<!-- <button value="2" name="button" onclick="atribuirValor1(2)" class="btn btn-primary btn-sm active">Download</button>-->
														<%}%>
														
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
							<br>
						     	
						     	<c:import url="jsp/elements/botoesVisualizarSecretarioAnexarDocumentos.jsp" charEncoding="UTF-8"></c:import>
				     
					</div>
					<!-- </form> -->
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
	<script>
		function atribuirValor1(i){
			
			document.getElementById("chave1").value = i;
		}
		function atribuirValor2(i){
			document.getElementById("chave2").value = i;
		}
		function teste1(id){
			if(document.getElementById("anexo1-"+id).value != ""){
				document.getElementById("submitEmenta-"+id).disabled = "";
			}
			if(document.getElementById("anexo1-"+id).value == ""){
				document.getElementById("submitEmenta-"+id).disabled = "disabled";
			}
		}
		function teste2(id){
			if(document.getElementById("anexo2-"+id).value != ""){
				document.getElementById("submitHistorico-"+id).disabled = "";
			}
			if(document.getElementById("anexo2-"+id).value == ""){
				document.getElementById("submitHistorico-"+id).disabled = "disabled";
			}
		}
	</script>
</html>