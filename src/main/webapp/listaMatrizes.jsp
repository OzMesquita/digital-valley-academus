<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="br.ufc.russas.n2s.academus.util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Listar Matrizes</title>
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
						<li class="breadcrumb-item"><a href="Inicio">Início</a></li>
						<li class="breadcrumb-item active" aria-current="page">Listar de Matrizes</li>
					</ol>
					</nav>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Código Matriz</th>
									<th scope="col">Nome</th>
									<!-- <form id="myform" method="post" action="VisualizarMatriz">
								  			<input type="hidden" name="link" value="< %=contato.getIdMatriz()% >" /> 
								  			<a onclick="document.getElementById('myform').submit();"><font color="blue"><  %=contato.getNome()% ></font></a></td>
										</form> -->
									<th scope="col">Periodo Letivo</th>
									<th scope="col">Carga</th>
									<th scope="col">Prazo Minimo</th>
									<th scope="col">Prazo Máximo</th>
									<th scope="col">Virgente</th>		
									<th scope="col">Ativo</th>
									<th scope="col">Código Curso</th>
									<th scope="col" colspan="3" style="text-align: center;">Opções</th>
								</tr>
							</thead>
							<%
								MatrizCurricularDAO dao = new JDBCMatrizCurricularDAO();
								List<MatrizCurricular> contatos;
							
								if(request.getParameter("pagina") == null){
									contatos = dao.listar(0, 10);
								}
								else{
									int pag = Integer.parseInt(request.getParameter("pagina"));
									contatos = dao.listar(pag*10, 10);
								}
								
								for (MatrizCurricular contato : contatos) {
							%>
							<tr>
								<td><%=contato.getIdMatriz()%></td>
								<td><%=contato.getNome()%></td>
								<td><%=contato.getPeriodoLetivo()%></td>
								<td><%=contato.getCarga()%></td>
								<td><%=contato.getPrazoMinimo()%></td>
								<td><%=contato.getPrazoMaximo()%></td>
								<td><%=(contato.isVigente())? "Sim":"Não"%></td>
								<td><%=(contato.isAtivo())? "Sim" : "Não"%></td>
								<td><%=contato.getIdCurso()%></td>
								<td><form method="post" action="VisualizarMatriz" id="formVis<%=contato.getIdMatriz()%>">
								<button  class="btn btn-primary btn-sm" form="formVis<%=contato.getIdMatriz()%>"
									style="height: 30px;" type="submit" name="button" value="<%=contato.getIdMatriz()%>" > Visualizar
								</button>
								</form></td>
								<td><form method="post" action="EditarMatriz" id="formEdi<%=contato.getIdMatriz()%>">
								<button  class="btn btn-primary btn-sm" form="formEdi<%=contato.getIdMatriz()%>"
									style="height: 30px;" type="submit" name="button" value="<%=contato.getIdMatriz()%>" > Editar
								</button>
								</form></td>
								<td><form method="post" action="ExcluirMatriz" id="formEx<%=contato.getIdMatriz()%>">
								<button  class="btn btn-primary btn-sm" form="formEx<%=contato.getIdMatriz()%>"
									style="height: 30px;" type="submit" name="button" value="<%=contato.getIdMatriz()%>" > Excluir
								</button>
								</form></td>
							</tr>
	
							<%
								}
							%>
	
						</table>
					</div>
					<nav aria-label="Page navigation example">
					  <ul class="pagination justify-content-center">
					    <li class="page-item <%if(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0){%>disabled<%}%>">
					      
					      <form method="post" action="ListarMatrizes" id="formPag">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
					      	Anterior
					      	</button>
					      </form>
					      
					    </li>
					    <li class="page-item <%if(contatos.size() < 10){%>disabled<%}%>">
					    
					      <form method="post" action="ListarMatrizes" id="formPag">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
					      	Proximo
					      	</button>
					      </form>
					      
					    </li>
					  </ul>
					</nav>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
		<% 
					String mensagem = (String) request.getAttribute("mensagem");
					if(mensagem != null){
						if (mensagem.equals("MS")){
					%>
						<script type="text/javascript">
        					alert("A Matriz foi alterada com sucesso!");
    					</script>
					<%	} else if(mensagem.equals("MN")){
						%>
						<script type="text/javascript">
        					alert("Não foi possível alterar a Matriz, por conter solicitações nas disciplinas associadas.");
    					</script>
					<%	}
					}%>
	</body>
</html>