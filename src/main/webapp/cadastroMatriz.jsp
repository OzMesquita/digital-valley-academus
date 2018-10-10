
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/design.css"/>
<title>Cadastro de Matriz Curricular </title>
</head>
<body>
	<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="jsp/elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
	<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item">Você está em:</li>
		<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">Início</a></li> <!-- PREENCHER HREF -->
		<li class="breadcrumb-item active" aria-current="page">Cadastrar Matriz Curricular </li>
	</ol>
	</nav>
	<h1>Cadastrar Matriz Curricular </h1>
                <p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
                <br>
	<div class="form-group">
	<form action="CadastrarMatriz" method="post">
		<label for="nomeMatrizInput">Nome*</label>
		 <input type="text" name="nome" class="form-control" id="nomeMatrizInput" aria-describedby="tituloHelp" placeholder="Digite o nome da matriz curricular" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: ENGENHARIA DE SOFTWARE</small>		
         <br>
         
         <label for="periodoInput"> Período Letivo*</label>
		 <input type="text" name="periodo_letivo" class="form-control" id="periodoInput" aria-describedby="tituloHelp" placeholder="Digite o período letivo" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 2018.1 </small>		
		 	
         <br>
         
		 <label for="cargaMatrizInput"> Carga horária total*</label>
		 <input type="text" name="carga_horaria" class="form-control" id="cargaMatrizInput" aria-describedby="tituloHelp" placeholder="Digite a carga horária total" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 3200 </small>		
        	
         <br>	
			
		 <label for="prazoMinimoInput"> Prazo minímo para conclusão*</label>
		 <input type="text" name="prazo_minimo" class="form-control" id="prazoMinimoInput" aria-describedby="tituloHelp" placeholder="Digite o número minímo  de semestres para concluir o curso" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 8 </small>		
	
         <br>	
		 <label for="prazoMaximoInput"> Prazo máximo para conclusão*</label>
		 <input type="text" name="prazo_maximo" class="form-control" id="prazoMaximoInput" aria-describedby="tituloHelp" placeholder="Digite o número máximo de semestres para concluir o curso" required>
		 <small id="tituloHelp" class="form-text text-muted"> Exemplo: 12 </small>		
	
         <br>
		
		 <label for="vigenteInput">Vigente*</label>
                        <select type="text" name="vigente" class="form-control custom-select" id="vigenteInput" required>
                            <option value="" selected="selected" disabled="disabled">Selecione uma opção</option>
                            <option value="true">Sim</option>
                            <option value="false">Não</option>              
                        </select>
         <br>
		
		<label for="ativoInput">Ativo*</label>
                        <select type="text" name="ativo" class="form-control custom-select" id="ativoInput" required>
                            <option value="" selected="selected" disabled="disabled">Selecione uma opção</option>
                            <option value="true">Sim</option>
                            <option value="false">Não</option>              
                        </select>
         <br>
		
		<label for="cursoInput">Curso*</label>
                        <select type="text" name="id_curso" class="form-control custom-select" id="cursoInput" required>
                            <option value="" selected="selected" disabled="disabled">Selecione o curso</option>
                            <option value="1">CIÊNCIA DA COMPUTAÇÃO</option>
                            <option value="2">ENGENHARIA CIVIL</option>
                            <option value="3">ENGENHARIA DE PRODUÇÃO</option>
                            <option value="4">ENGENHARIA DE SOFTWARE</option> 
                            <option value="5">ENGENHARIA MECÂNICA</option>             
                        </select>
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