<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.PerfilAcademusDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCPerfilAcademusDAO"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>		

<!DOCTYPE html>
<html lang=pt-br>
	<head>
		<title>Gerenciar Perfis</title>
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
						<li class="breadcrumb-item active" aria-current="page">Gerenciar Perfis</li>
					</ol>
					</nav>
					
					<div class="form-group table-responsive">
					<form class="form-inline" method="post" action="GerenciarPerfis">
						<input class="form-control" style="width: 250px;" type="text"
							name="nome_perfil" placeholder="Perfil" required>&nbsp;
						<button class="btn btn-sm btn-primary" type="submit">Procurar</button>
					</form>
					<br/>
					
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Código</th>
								<th scope="col">Nome</th>
								<th scope="col">Nível de Permissão</th>
								<th scope="col" style="text-align: center;">Opções</th>
							</tr>
						</thead>
						<%
							if(request.getParameter("nome_perfil") != null){
								List<PerfilAcademus> lista_perfil;
								PerfilAcademusDAO perfilDAO = new JDBCPerfilAcademusDAO();
								
								lista_perfil = perfilDAO.buscarPorNome(request.getParameter("nome_perfil").trim().toUpperCase());
								
								if(!lista_perfil.isEmpty()){
									for (PerfilAcademus perfil : lista_perfil){
									%>
									<tr>
										<td><%=perfil.getId()%></td>
										<td><%=perfil.getNome()%></td>
										<td><%=perfil.getNivel()%></td>
										<td><a href="editarPerfil.jsp?id=<%=perfil.getId()%>" class="btn btn-secondary btn-sm" style="height: 30px;">Editar</a></td>
									</tr>
									<%
									}
								} else {%>						
									<tr><td><p>Nenhum perfil foi encontrado! </p></td></tr>
								<%
								}
								
							} else {
								%>
								<tr><td><p>Informe o nome do perfil! </p></td></tr>
								<%
							}
						%>	
					</table>
					</div>
					
				</div>
			</div>
		</div>
		
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
</html>