<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.CursoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCCursoDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.ProfessorDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCProfessorDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.CoordenadorDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCCoordenadorDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Curso"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Professor"%>
<%@ page import="br.ufc.russas.n2s.academus.model.Coordenador"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/css/design.css" />
<title>Insert title here</title>
</head>
<body>
	<%CursoDAO cd = new JDBCCursoDAO();
	ProfessorDAO pd = new JDBCProfessorDAO();
	CoordenadorDAO cod = new JDBCCoordenadorDAO();
	List<Curso> lc = cd.listar();
	List<Professor> lp = pd.listar();%>
	
	<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="jsp/elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Você está em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">Início</a></li> <!-- PREENCHER HREF -->					
					<li class="breadcrumb-item active" aria-current="page">CadastrarDisciplina</li>
				</ol>
				</nav>
				<h1>Coordenadores</h1>
				<p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
				<br>
				<table class="table" id="listaCursos">
					<thead>
						<tr>
							<th scope="col">Curso</th>
							<th scope="col">Coordenador</th>
						</tr>
					</thead>
					<%for(Curso c : lc){ Coordenador co = null; co = cod.buscarPorCurso(c);%>
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
</body>
</html>