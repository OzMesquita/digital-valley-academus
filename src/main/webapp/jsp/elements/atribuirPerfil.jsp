<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ page import="br.ufc.russas.n2s.academus.dao.DisciplinaDao"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.Disciplina"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Disciplina disciplina = new Disciplina();
	DisciplinaDao dao = new DisciplinaDao();
	disciplina.setId(request.getParameter("id"));
	
	disciplina = dao.buscaDisciplina(disciplina);
	
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
		rel="stylesheet">
	<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/css/design.css" />
	<title>Altribuição de nivel ao Perfil</title>
</head>
<body>
	<c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Você está em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="elements/aviso.jsp">Início</a></li> <!-- PREENCHER HREF -->					
					<li class="breadcrumb-item active" aria-current="page">Cadastrar
						Disciplina</li>
				</ol>
				</nav>
				<h1>Atribuir Perfil</h1>
	
				<br>
				<div class="form-group">						
					<form action="AtualizarDisciplinaController" method="post">
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
								<button type="submit" class="btn btn-primary">Confirmar</button>
								<button type="button" class="btn btn-primary">Cancelar</button>
							</div>
						</div>
					</form>	
				</div>
			</div>
		</div>
	</div>
</body>
</html>