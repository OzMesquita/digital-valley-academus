<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.NivelAcessoDao"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.PerfilDao"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.NivelAcesso"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.Perfil"%>
<%@ page import="br.ufc.russas.n2s.academus.modelo.Servidor"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de Perfis</title>
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
<%

%>

<div class="form-group">
	<form action="ServletPerfil" method="post">				
					<table class="table table-responsive">
						<thead>
							<tr>
								<th scope="col">Id Usuario</th>
								<th scope="col">CPF</th>
								<th scope="col">Nome</th>
								<th scope="col">Siape</th>
								<th scope="col">Nivel</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<%
						NivelAcessoDao daoNivel = new NivelAcessoDao();
						ArrayList<NivelAcesso> niveis = daoNivel.getLista();
						PerfilDao daoUsuario = new PerfilDao();
						ArrayList<Servidor> usuarios = daoUsuario.ListarPerfisServidores();
						for (Servidor usuario : usuarios) {
						%>
						
						<tr>
							<td><%=usuario.getIdPessoaUsuario()%></td>
							<td><%=usuario.getCpf()%></td>
							<td><%=usuario.getNome()%></td>
							<td><%=usuario.getSiape()%></td>
							<td>
							 <label for="vigenteInput"></label>
					         	<div class="form-row">
					         	<select type="number" name="<%=usuario.getIdPessoaUsuario()%>" class="form-control custom-select col-md-7" id="nivelInput" required>
					            	<option value=<%=usuario.getNivelAcesso() %> selected="selected" disabled="disabled"><%=NivelAcesso.getRotulo(usuario.getNivelAcesso())%></option>
					            	<option value=2 >Servidor</option>
					            	<option value=3 >Secretário</option>
					            	<option value=4 >Coordenador</option>              
					            </select>
         						&nbsp;&nbsp;
         						<button type="submit" name="usuario" value="<%=usuario.getIdPessoaUsuario() %>" class="btn btn-secondary btn-sm">Atribuir Perfil</button>
							</div>
							</td>
						</tr>
						
						<%
							}
						%>

					</table>
					</form>				
				</div>
</body>
</html>