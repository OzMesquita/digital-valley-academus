<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.util.Constantes" %>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Buscar Matrizes</title>
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
						<li class="breadcrumb-item"><a href="listaMatrizes.jsp">Listar de Matriz</a></li>
						<li class="breadcrumb-item active" aria-current="page">Buscar Matriz</li>
					</ol>
					</nav>
					
					<div class="form-group"> <!-- Buscar por nome também -->
						<form  class="form-inline" method="post" action="BuscarMatriz">
                           <input class="form-control" style="width: 250px;" type="number" name="id_matriz" placeholder="Código da matriz" required>&nbsp;
                           <button class="btn btn-primary btn-sm" type="submit">Procurar</button>
	                    </form>
	                    
	                    <form id="buscarMatrizForm" method="post" action="BuscarMatriz"></form>
	                    
	                    <br/>
	                    <div class="table-responsive">
	                    	<table class="table">
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
										<th scope="col" colspan="3" style="text-align: center;">Operações</th>					
									</tr>
								</thead>                                                                                           
								<%
								
								if(request.getParameter("id_matriz") != null){
									JDBCMatrizCurricularDAO dao = new JDBCMatrizCurricularDAO();
									MatrizCurricular matriz = new MatrizCurricular();
									int id_matriz = Integer.parseInt(request.getParameter("id_matriz"));
									matriz.setIdMatriz(id_matriz);						
									matriz = dao.buscarPorId(id_matriz); 
									
									if (matriz.getIdMatriz() != -1 ){
										
									%>
									<tr>
										<td><%=matriz.getIdMatriz()%></td>
										<td><%=matriz.getNome().toUpperCase()%></td>
										<td><%=matriz.getPeriodoLetivo()%></td>
										<td><%=matriz.getCarga()%></td>
										<td><%=matriz.getPrazoMinimo()%></td>
										<td><%=matriz.getPrazoMaximo()%></td>
										<td>${matriz.isVigente() ? "Não" : "Sim"}</td>
										<td>${matriz.isAtivo() ? "Não" : "Sim"}</td>
										<td><%=matriz.getIdCurso()%></td>
										
										<td><form method="post" action="VisualizarMatriz" id="formVis<%=matriz.getIdMatriz()%>">
										<button  class="btn btn-primary btn-sm" form="formVis<%=matriz.getIdMatriz()%>"
											style="height: 30px;" type="submit" name="button" value="<%=matriz.getIdMatriz()%>" > Visualizar
										</button>
										</form></td>
										<td><form method="post" action="EditarMatriz" id="formEdi<%=matriz.getIdMatriz()%>">
										<button  class="btn btn-primary btn-sm" form="formEdi<%=matriz.getIdMatriz()%>"
											style="height: 30px;" type="submit" name="button" value="<%=matriz.getIdMatriz()%>" > Editar
										</button>
										</form></td>
										<td><form method="post" action="ExcluirMatriz" id="formEx<%=matriz.getIdMatriz()%>">
										<button  class="btn btn-primary btn-sm" form="formEx<%=matriz.getIdMatriz()%>"
											style="height: 30px;" type="submit" name="button" value="<%=matriz.getIdMatriz()%>" > Excluir
										</button>
										</form></td>
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
		</div>
	</body>
</html>