<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de Disciplinas</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/design.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.standalone.css" />
</head>
<body>
	<div class="form-group">
		<label for="vigenteInput">Resultado</label>
            <select type="text" name="resultado" class="form-control custom-select" id="resultado" required>
                <option value="" selected="selected" disabled="disabled">Selecione uma opção</option>
                <option value="Deferido">Deferido</option>
                <option value="Indeferido">Indeferido</option>              
            </select>
        <label for="justificativaInput"><b> Justificativa </b></label> 
			<textarea rows="8" id="justificativaInput" name="justificativaInput" class="form-control" aria-describedby="tituloHelp" required> <%= %></textarea>
			<div class="invalid-feedback">
                            
            </div>
		<form method="POST" action="AnalizarSolicitacao" id="form<%=(String)request.getAttribute("id")%>">
			<td><button  class="btn btn-primary btn-sm active" form="form<%=(String)request.getAttribute("id")%>"
				class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=(String)request.getAttribute("id") %>" > Confirmar
			</button></td>
		</form>
		<form method="POST" action="Inicio">
			<td><button  class="btn btn-primary btn-sm active"
				class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" > Cancelar
			</button></td>
		</form>
	</div>
</body>
</html>