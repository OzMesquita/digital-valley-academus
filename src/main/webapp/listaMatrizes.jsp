<%@ page import="br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO"%>
<%@ page import="br.ufc.russas.n2s.academus.model.MatrizCurricular"%>
<%@ page import="util.Constantes"%>
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
					<form method="post" action="">
						<label for="buscainput"><h2><b> Escolha forma de pesquisa </b></h2></label> 
						<h2><INPUT TYPE="radio" name="opcao" VALUE="1"> código
						<INPUT TYPE="radio" name="opcao" VALUE="2"> nome</h2>
						<a class="form-inline">
						<input class="form-control" style="width: 250px;" style='text-transform:uppercase'
							name="id_matriz" placeholder="Código ou nome da Matriz" <%if(request.getParameter("id_matriz")!= null){%>value="<%=request.getParameter("id_matriz")%>"<%}%>>&nbsp;
						<button class="btn btn-sm btn-primary" type="submit">Procurar</button></a>
					</form><br>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Código Matriz</th>
									<th scope="col">Nome</th>
									<th scope="col">Período Letivo</th>
									<th scope="col">Carga</th>
									<th scope="col">Prazo Mínimo</th>
									<th scope="col">Prazo Máximo</th>
									<th scope="col">Virgente</th>		
									<th scope="col">Ativo</th>
									<th scope="col">Código Curso</th>
									<th scope="col" colspan="3" style="text-align: center;">Opções</th>
								</tr>
							</thead>
							<%
								MatrizCurricularDAO dao = new JDBCMatrizCurricularDAO();
								List<MatrizCurricular> contatos = new ArrayList<>();
								int tipobusca = 0;
								int pagina = 0;
								String id_matriz = "";
								if(request.getParameter("opcao") != null){
									tipobusca = Integer.parseInt(request.getParameter("opcao"));
								}
								if(request.getParameter("pagina") != null){
									pagina = Integer.parseInt(request.getParameter("pagina"));
								}
								if(request.getParameter("id_matriz") != null){
									id_matriz = request.getParameter("id_matriz");
								}
								
								if(tipobusca == 1){
									try{
										MatrizCurricular matriz = dao.buscarPorId(Integer.parseInt(id_matriz));
										if(matriz != null){
											contatos.add(matriz);
										}	
									}catch(Exception e){
										
									}
								}else if (tipobusca == 2 || !id_matriz.equals("")){
									contatos = dao.buscarPorNome( id_matriz, pagina*10, 10);
									
								} else{
									contatos = dao.listar(pagina*10, 10);
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
								<td><form method="post" action="ExcluirMatriz" id="formEx<%=contato.getIdMatriz()%>">
								<button  class="btn btn-primary btn-sm" form="formEx<%=contato.getIdMatriz()%>"
									style="height: 30px;" type="submit" name="button" value="<%=contato.getIdMatriz()%>" > Excluir
								</button>
								</form></td>
							</tr>
	
							<%
								}
								if(contatos.isEmpty()){
									%>
									<tr><td><p>Nenhuma matriz foi encontrada! </p></td></tr>
								<% 
								}%>
	
						</table>
					</div>
					<nav aria-label="Page navigation example">
					  <ul class="pagination justify-content-center">
					    <li class="page-item <%if(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0){%>disabled<%}%>">
					      
					      <form method="post" action="ListarMatrizes" id="formPag">
					      	<input type="hidden" name="id_disciplina" value="<%=request.getParameter("id_matriz")%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
					      	Anterior
					      	</button>
					      </form>
					      
					    </li>
					    <li class="page-item <%if(contatos.size() < 10){%>disabled<%}%>">
					    
					      <form method="post" action="ListarMatrizes" id="formPag">
					      	<input type="hidden" name="id_disciplina" value="<%=request.getParameter("id_matriz")%>">
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
	</body>
</html>