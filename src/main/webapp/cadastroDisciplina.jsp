<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
		rel="stylesheet">
	<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/css/design.css" />
	<title>Cadastro de Disciplina</title>
</head>
<body>
	<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="jsp/elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Voc� est� em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">In�cio</a></li> <!-- PREENCHER HREF -->					
					<li class="breadcrumb-item active" aria-current="page">Cadastrar
						Disciplina</li>
				</ol>
				</nav>
				<h1>Cadastrar Disciplina</h1>
				<p>Aten��o: Os campos abaixo (*) s�o de preenchimento
					obrigat�rio</p>
				<br>
				<div class="form-group">
					<form action="ServletCadastroDisciplina" method="post">
						<label for="idInput">C�digo da Disciplina*</label> <input type="text" name="id_disciplina" class="form-control" id="idInput" textTransform="uppercase" aria-describedby="tituloHelp" placeholder="Digite o c�digo da Disciplina" required > 
						<small id="tituloHelp" class="form-text text-muted"> Exemplo: RUS1001</small> 
						<div class="invalid-feedback">
                            
                        </div>
						<br> 
						
						<label for="nomeInput"> Nome da Disciplina*</label> 
						<input type="text" name="nome" class="form-control" id="nomeInput" aria-describedby="tituloHelp" placeholder="Digite o nome da Disciplina" required> 
						<small id="tituloHelp" class="form-text text-muted"> Exemplo: FUNDAMENTOS DE PROGRAMA��O </small> 
						<div class="invalid-feedback">
                            
                        </div>
						<br> 
						
						<label for="cargaInput">  Carga Hor�ria*</label> 
						<input type="text" name="carga" class="form-control" id="cargaInput" aria-describedby="tituloHelp" placeholder="Digite a carga hor�ria" required> 
						<small id="tituloHelp" class="form-text text-muted"> Exemplo: 64</small> 
						 <div class="invalid-feedback">
                            
                        </div>
						<br> 
						
						<label for="creditosInput"> Cr�ditos da Disciplina*</label> 
						<input type="text" name="creditos" class="form-control" id="creditosInput" aria-describedby="tituloHelp" placeholder="Digite os cr�ditos da disciplina" required> 
						<small id="tituloHelp" class="form-text text-muted"> Exemplo: 4 </small> 
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