<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Disciplina"%>
<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	Disciplina disciplina = new Disciplina();
	DisciplinaDAO dao = new JDBCDisciplinaDAO();
	
	boolean deuCerto = true;
	
	try{
		disciplina.setId((String) request.getAttribute("id"));
		
		disciplina = dao.buscarPorId(disciplina.getId());
		
			
	} catch(Exception e){
		deuCerto = false;
	}
	
%>

<!DOCTYPE html>
<html lang=pt-br>
	<head>
		<title>Editar dados da Disciplina</title>
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
						<li class="breadcrumb-item active" aria-current="page">Editar Disciplina</li>
					</ol>
					</nav>
					<h1>Editar Disciplina</h1>
					<br>
					<%
						if(!disciplina.getId().equals("INDEFINIDO") && deuCerto){
					
					%>
					<div class="form-group">						
						<form action="EditarDisciplina" method="post">
							<label for="idInput">Código da Disciplina*</label> 
							<input type="text" name="id_disciplina" value="<%= disciplina.getId() %>" class="form-control" id="idInput" aria-describedby="tituloHelp" placeholder="Digite o código da Disciplina" readonly required > 
							
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="nomeInput"> Nome da Disciplina*</label> 
							<input type="text" name="nome" value="<%= disciplina.getNome() %>" class="form-control" id="nomeInput" aria-describedby="tituloHelp" placeholder="Digite o nome da Disciplina" required> 
							
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="cargaInput">  Carga Horária*</label> 
							<input type="text" name="carga" value="<%= disciplina.getCarga() %>" class="form-control" id="cargaInput" aria-describedby="tituloHelp" placeholder="Digite a carga horária" required> 
							
							 <div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="creditosInput"> Créditos da Disciplina*</label> 
							<input type="text" name="creditos" value="<%= disciplina.getCreditos() %>" class="form-control" id="creditosInput" aria-describedby="tituloHelp" placeholder="Digite os créditos da disciplina" required> 
							 
							<div class="invalid-feedback">
	                            
	                        </div>
							<br>						
							
							<div class="modal-footer">
								<div id="botoes" class="controls">
									<a class="btn btn-primary" href="ListarDisciplinas">Cancelar</a>
									<button type="submit" class="btn btn-primary">Confirmar</button>
								</div>
							</div>
						</form>	
					</div>
					<%
						} else{
							%>
							Disciplina não encontrada.
							<%
						}
					%>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
</html>