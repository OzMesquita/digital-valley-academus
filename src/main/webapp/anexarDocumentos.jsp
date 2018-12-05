<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao" %>
<%@ page import="br.ufc.russas.n2s.academus.model.Aluno" %>
<%@ page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada" %>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.SolicitacaoDAO" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC" %>
<%@ page import="br.ufc.russas.n2s.academus.util.Constantes" %>
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
		//solicitacao = dao.buscarPorId(2);
	} catch(Exception e){
		deuCerto = false;
		System.out.print("Deu nao");
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
						<li class="breadcrumb-item active" aria-current="page">Visualizar Solicitação</li>
					</ol>
					</nav>
					<h1>Visualizar Solicitação</h1>
					
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
								<div class="form-group table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th scope="col">Nome</th>
												<th scope="col">Instituição</th>
												<th scope="col">Carga</th>
												<th scope="col">Semestre</th>
												<th scope="col">Nota</th>
												<th scope="col">Anexos</th>
												<th colspan="2">Operações</th>
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
												<%=solicitacao.getSolicitante().getMatricula()%>
											</td>
											<td>
												<jsp:include page="jsp/elements/botoesAnexarDocumentos.jsp">
													<jsp:param value="<%=solicitacao.getSolicitante().getMatricula()%>" name="matricula"/>
													<jsp:param value="<%=solicitacao.getIdSolicitacao()%>" name="id_solicitacao"/>
													<jsp:param value="<%=disCursada.getId()%>" name="id_disciplina_cursada"/>
												</jsp:include>
											</td>
										</tr>
				
										<%
											}
										%>
									</table>
								</div>
							<br>
								
							<label for="resultadoInput"><b> Resultado </b></label>
							<input type="text" name="resultadoInput" value="<%= solicitacao.getResultado() %>" class="form-control" id="resultadoInput" aria-describedby="tituloHelp" readonly required>
	
							<div class="invalid-feedback">
	                            
	                        </div>
							<br>
							<br>
						     	
						     	<!-- <input type="hidden" name="id_disciplina_cursada" id="id_disciplina_cursada" value="0">
						     	<input type="hidden" name="matricula" id="matricula" value="<%=solicitacao.getSolicitante().getMatricula()%>">
						     	<input type="hidden" name="id_solicitacao" id="id_solicitacao" value="<%=solicitacao.getIdSolicitacao()%>">  -->
						     	<a href="Inicio" type="button" id="enviar" class="btn btn-secondary"> Cancelar </a>
				     
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
	</body>
	<script type="text/javascript">
		function atribuirValor(i){
			document.getElementById("id_disciplina_cursada").value = i;
		}
	</script>
</html>