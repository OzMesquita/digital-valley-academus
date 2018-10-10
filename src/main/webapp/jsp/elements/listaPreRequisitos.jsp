<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ page import="br.ufc.russas.n2s.academus.dao.*"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
	<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/css/design.css" />
<title></title>
</head>
<body>
<%
	DisciplinaDao daoD = new DisciplinaDao();
	ArrayList<Disciplina> disciplinas = daoD.ListarDisciplinas();
%>
<br>
	
	<label for="PRInput+componente">Componentes</label>
	<div class="form-row">
		<select id="PRInput" class="form-control col-md-8" style="margin-left: 3px">
			<option value="" selected="selected" disabled="disabled">Selecione as disciplinas que compõem esta matriz</option>
			<%for(Disciplina disciplina : disciplinas){%>
				<option id="disciplinaOption-<%=disciplina.getId()%>" value="<%=disciplina.getId()%>-<%=disciplina.getNome()%>"><%=disciplina.getNome()%></option>
			<%}%>
		</select>
		&nbsp;&nbsp;
		<input type="button" class="btn btn-secondary btn-sm" onclick="adicionarPreRequisito()" value="Adicionar">
	</div>
	
	<br>
       <ul class="list-group col-md-8" id="listaPreRequisitos">
       </ul>
                                  
    <br>
</body>
</html>