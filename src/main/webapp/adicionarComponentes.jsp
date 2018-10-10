<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Adicionar Componentes </title>
</head>
<body>
	<form action="ServletCadastroComponente" method="post">
		<table>
			<tr>
				<td>Id Matriz:</td>
				<td><input type="text" name="matriz">
			</tr>
			<tr>
				<td>Id Disciplina:</td>
				<td><input type="text" name="disciplina">
			</tr>
			<tr>
				<td>Natureza:</td>
				<td><input type="text" name="natureza">
			</tr>
			<tr>
				<td>Pré-requisitos:</td>
				<td><input type="text" name="pre_requisito">
				<button type="button" class="btn">Adicionar</button>
			</tr>			
		</table>
			<label for="comment">Pré-requisitos adicionados:</label>
				<textarea rows="5" id="comment" disabled></textarea>
		<div class="control-group">
			<div id="botoes" class="controls">
				<button type="submit" class="btn btn-primary">Confirmar</button>
				<button type="button" class="btn btn-primary">Cancelar</button>
			</div>
		</div>
	</form>
</body>
</html>