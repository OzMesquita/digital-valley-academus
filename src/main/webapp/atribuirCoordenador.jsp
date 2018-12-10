<%@ page import="br.ufc.russas.n2s.academus.dao.CursoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCCursoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.ProfessorDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCProfessorDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.CoordenadorDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCCoordenadorDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Curso"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Professor"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Coordenador"%>
<%@ page import="br.ufc.russas.n2s.academus.util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Atribuir Coordenador</title>
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
		<%
		CursoDAO cd = new JDBCCursoDAO();
		ProfessorDAO pd = new JDBCProfessorDAO();
		CoordenadorDAO cod = new JDBCCoordenadorDAO();
		List<Curso> lc = cd.listar();
		List<Professor> lp = pd.listar();
		%>
		
		<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
		
		<div class="container-fluid">
			<div class="row">
				<c:import url="jsp/elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
				<div class="col-md-10">
					<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Você está em:</li>
						<li class="breadcrumb-item"><a href="Inicio">Início</a></li>
						<li class="breadcrumb-item active" aria-current="page">Cadastrar Disciplina</li>
					</ol>
					</nav>
					<h1>Coordenadores</h1>
					<!-- <p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>  -->
					<br>
					<table class="table" id="listaCursos">
						<thead>
							<tr>
								<th scope="col">Curso</th>
								<th scope="col">Coordenador</th>
							</tr>
						</thead>
						<%for(Curso c : lc){ Coordenador co = null; co = cod.buscarPorCurso(c.getIdCurso());%>
							<tr>
								<td><%=c.getNome()%></td>
								<td>
								<form method="POST" action="AtribuirCoordenadores">
								<div class="form-row">
									<div class="form-group-col-md-9">
										<select name="cursoInput-<%=c.getIdCurso()%>" class="form-control">
											<option id="professorOption-default" selected="selected" value="<%=!co.equals(null) ? co.getId() : -1%>"><%=co.getId() > 0 ? co.getNome() : "Sem Coordenador"%></option>
											<%for(Professor p : lp){ %>
												<option id="professorOption-<%=p.getId()%>" value="<%=p.getId()%>" ><%=p.getNome()%></option>
											<%}%>
										</select>
									</div>
									<div class="form-group-col-md-3">
										<button type="submit" name="buttonCurso" class="btn btn-secondary" value="<%=c.getIdCurso()%>">Atribuir</button>
									</div>
								</div>
								</form>
								</td>
							</tr>
						<%}%>
					</table>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
</html>