<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listando Matrizes</title>

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
	<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="jsp/elements/menu-lateral-esquerdo.jsp"
				charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Você está em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="listaMatrizes.jsp">Lista de Matriz</a></li>
					<!-- PREENCHER HREF -->
					<li class="breadcrumb-item active" aria-current="page">Busca Matriz</li>
				</ol>
				</nav>
				<div class="form-group"> <!-- Buscar por nome também -->
					    <form class="form-inline" method="get" action="buscaMatriz.jsp">
                           <input class="form-control" style="width: 250px;" type="number" name="id_matriz" placeholder="Código da matriz" required>&nbsp;
                           <button class="btn btn-sm btn-primary" type="submit">Procurar</button>
                       </form>
					<table class="table table-responsive">
						<thead>
							<tr>
								<th scope="col">Código Matriz</th>
								<th scope="col">Nome</th>
								<th scope="col">Período Letivo</th>
								<th scope="col">Carga</th>
								<th scope="col">Prazo minímo</th>
								<th scope="col">Prazo máximo</th>
								<th scope="col">Vigente</th>		
								<th scope="col">Ativo</th>
								<th scope="col">Curso</th>						
							</tr>
						</thead>                                                                                           
						<%
						
						if(request.getParameter("id_matriz") != null){
							JDBCMatrizCurricularDAO dao = new JDBCMatrizCurricularDAO();
							MatrizCurricular matriz = new MatrizCurricular();
							int id_matriz = Integer.parseInt(request.getParameter("id_matriz"));
							matriz.setIdMatriz(id_matriz);						
							matriz = dao.buscaMatriz(matriz); 
							
							if (matriz!=null){
								
							%>
							<tr>
								<td><%=matriz.getIdMatriz()%></td>
								<td><%=matriz.getNome().toUpperCase()%></td>
								<td><%=matriz.getPeriodoLetivo()%></td>
								<td><%=matriz.getCarga()%></td>
								<td><%=matriz.getPrazoMinimo()%></td>
								<td><%=matriz.getPrazoMaximo()%></td>
								<td><%=matriz.isVigente()%></td>
								<td><%=matriz.isAtivo()%></td>
								<td><%=matriz.getIdCurso()%></td>
								<td><a href="editarMatriz.jsp?id_matriz=<%=matriz.getIdMatriz()%>" class="btn btn-primary btn-sm active"
									class="btn btn-primary btn-sm" style="height: 30px;"> Editar
								</a></td>
								<form method="POST" action="VisualizarSolicitacao" id="form<%=matriz.getIdMatriz()%>">
								<td><button  class="btn btn-primary btn-sm active" form="form<%=matriz.getIdMatriz()%>"
									class="btn btn-primary btn-sm" style="height: 30px;" type="submit" name="button" value="<%=matriz.getIdMatriz() %>" > Editar
								</button></td>
								</form>
								<td><a href="jsp/elements/aviso.jsp" class="btn btn-primary btn-sm active"
									class="btn btn-primary btn-sm" style="height: 30px;"> Remover
								</a></td>
							</tr>
							<%
							} else {%>						
								<tr><td><p> Nenhuma matriz foi encontrada! </p></td></tr>
							<%
							}		
						}
						else{
							%>
							<tr><td><p> Informe a matriz desejada</p></td></tr>
							<%
						}
						%>
									
					</table>					
				</div>
			</div>
		</div>
	</div>
</body>
</html>