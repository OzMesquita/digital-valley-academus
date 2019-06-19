<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Curso"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.PerfilAcademusDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCPerfilAcademusDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.CursoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCCursoDAO"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	PerfilAcademus perfil = new PerfilAcademus();
	PerfilAcademusDAO dao = new JDBCPerfilAcademusDAO();
	
	boolean deuCerto = true;
	
	try{
		
		perfil = dao.buscarPorId(Integer.parseInt(request.getParameter("id")));
		
			
	} catch(Exception e){
		deuCerto = false;
		e.printStackTrace();
	}
	
%>

<!DOCTYPE html>
<html lang=pt-br>
	<head>
		<title>Editar permissões do perfil</title>
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
						<li class="breadcrumb-item"><a href="Inico">Início</a></li>
						<li class="breadcrumb-item active" aria-current="page">Editar Permissão</li>
					</ol>
					</nav>
					<h1>Editar Permissão</h1>
					<br>
					
					
					<!-- Caso a edição seja bem sucedida vem para essa tela com uma mensagem de sucesso -->
					<% if (request.getAttribute("success") != null){ %>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<%= request.getAttribute("success") %>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    	<span aria-hidden="true">&times;</span>
						  	</button>
						</div>
					<% } %>
					
					<%
						if(deuCerto){
					
					%>
					
					<form action="EditarPerfis" method="post">
					
						<input type="hidden" name="id_pessoa_usuario" value="<%= perfil.getIdGuardiao() %>">
						
						<div class="form-group">
							<label for="idInput">Código do Perfil*</label> 
							<input type="text" name="id_perfil" value="<%= perfil.getId() %>" class="form-control" id="idInput" aria-describedby="tituloHelp" readonly required > 
							
							<div class="invalid-feedback">
	                            
	                        </div>
						</div>
						
						
						<div class="form-group">
							<label for="nomeInput"> Nome*</label> 
							<input type="text" name="nome" value="<%= perfil.getNome() %>" class="form-control" id="nomeInput" required> 
							
							<div class="invalid-feedback">
	                            
	                        </div>
						</div>
						
						
						<div class="form-group">
							<label for="emailInput"> E-mail*</label> 
							<input type="email" name="email" value="<%= perfil.getEmail() %>" class="form-control" id="emailInput" required> 
							
							<div class="invalid-feedback">
	                            
	                        </div>
						</div>
						
						
						<div class="form-group">
							<label for="cpfInput"> CPF*</label> 
							<input type="text" name="cpf" value="<%= perfil.getCPF() %>" class="form-control" id="cpfInput" required> 
							
							<div class="invalid-feedback">
	                            
	                        </div>
						</div>
						
						
						<div class="form-group">
							<label for="nivelInput">  Nível de Permissão*</label>
							<select name="nivel" id="nivelInput" class="form-control">
								<%
									for (NivelAcademus n : NivelAcademus.values()){
										if(n.equals(perfil.getNivel())) {
											%>
												<option value="<%= n.ordinal() %>" selected><%= n.name() %></option>
											<%
										} else {
											%>
												<option value="<%= n.ordinal() %>"><%= n.name() %></option>
										<%
										}
										
									}
								%>
							</select>
							 <div class="invalid-feedback">
	                            
	                        </div>
						</div>
						
						
						<div class="form-group">
							<label for="cursoInput">  Curso*</label>
							<select name="curso" id="cursoInput" class="form-control">
								<%
									CursoDAO cursodao = new JDBCCursoDAO();
									for (Curso c : cursodao.listar()){
										if(c.equals(perfil.getCurso())) {
											%>
												<option value="<%= c.getIdCurso() %>" selected><%= c.getNome() %></option>
											<%
										} else {
											%>
												<option value="<%= c.getIdCurso() %>"><%= c.getNome() %></option>
										<%
										}
										
									}
								%>
							</select>
							 <div class="invalid-feedback">
	                            
	                        </div>
						</div>
						
						
						<div class="form-group">
							<div class="form-check">
								
								<%
									if(perfil.getIsAdmin()) {
										%>
											<input id="adminInput" type="checkbox" name="is_admin" value="true" class="form-check-input" id="nomeInput" checked>
										<%
									} else {
										%>
											<input id="adminInput" type="checkbox" name="is_admin" value="true" class="form-check-input" id="nomeInput">
										<%
									}
								%>
								<label class="form-check-label" for="adminInput"> Administrador</label>
							</div>
							<div class="invalid-feedback">
                            
	                        </div>
						</div>
						
						
						
						
						<div class="modal-footer">
							<div id="botoes" class="controls">
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Voltar">Cancelar</button>
								<button type="submit" class="btn btn-primary">Confirmar</button>
							</div>
						</div>
						<!-- Modal -->
							<div class="modal fade" id="Voltar" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="modalLabel">Cancelar</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<h2>Deseja mesmo cancelar essa operação?<br>Você irá perder os dados informados!</h2>
										</div>
										<div class="modal-footer">
											<button type="button" id="modal-nao" autofocus class="btn btn-primary btn-sm active" data-dismiss="modal" >Não</button>
											<a href="GerenciarPerfis"><button type="button" class="btn btn-primary btn-sm active">Sim</button></a>
										</div>
									</div>
								</div>
							</div>
							<!-- Fim de Modal -->
					</form>	
					<%
						} else{
							%>
							Perfil não encontrado.
							<%
						}
					%>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
</html>