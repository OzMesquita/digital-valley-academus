<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="SalvarAnexos" method="Post" enctype="multipart/form-data">
			<input type="file" name="anexo" id="anexo" accept=".pdf">
			<input type="submit" value="testar" onclick="atribuirValor(1)">
		<br>
			<input type="file" name="anexo" id="anexo" accept=".pdf">
			<input type="submit" value="testar" onclick="atribuirValor(2)">
		<br>
			<input type="file" name="anexo" id="anexo" accept=".pdf">
			<input type="submit" value="testar" onclick="atribuirValor(3)">
		<br>
		<input type="hidden" name="id_disciplina_cursada" id="id_disciplina_cursada" value="0">
		<input type="hidden" name="id_solicitacao" id="id_solicitacao" value="1">
		<input type="hidden" name="matricula" id="matricula" value="375119">
	</form>
</body>
<script type="text/javascript">
	function atribuirValor(i){
		document.getElementById("id_disciplina_cursada").value = i;
	}
</script>
</html>