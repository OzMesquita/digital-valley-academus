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
							
								List<PerfilAcademus> lista_perfil;
								PerfilAcademusDAO perfilDAO = new JDBCPerfilAcademusDAO();
								
								int pagina = 0;
								String busca = "";
								int numSolicitacoes = 0;
								
								if(request.getParameter("pagina") != null){
									pagina = Integer.parseInt(request.getParameter("pagina"));
								}
								
								if(request.getParameter("nome_perfil") != null){
									
									busca = request.getParameter("nome_perfil");
									
								}
								
								if(busca != ""){
									lista_perfil = perfilDAO.buscarPorNome(busca, pagina*10, 10);
									numSolicitacoes = perfilDAO.countPerfis(pagina, busca);
								} else {
									lista_perfil = perfilDAO.listar(pagina*10, 10);
									numSolicitacoes = perfilDAO.countPerfis(pagina);
								}
								
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
							%>	
						</table>
					</div>
					<nav aria-label="Page navigation example">
					  <ul class="pagination justify-content-center">
					  
					  	
					    <li class="page-item <%if((request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0)){%>disabled<% } %>">
					      
					      <form method="post" action="GerenciarPerfis" id="formPag">
					      	<input type="hidden" name="nome_perfil" value="<%=busca%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
					      	Anterior
					      	</button>
					      </form>
					      
					    </li>
					    
					  
					    <%if(!(request.getParameter("pagina") == null || pagina <= 2)){%>
					    <li class="page-item">
					      
					      <form method="post" action="GerenciarPerfis" id="formPag">
					      	<input type="hidden" name="nome_perfil" value="<%=busca%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -3);}%>">
					      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(pagina-2);}%>
					      	</button>
					      </form>
					      
					    </li>
					    <% } %>
					  	
					  	
					  	<%if(!(request.getParameter("pagina") == null || pagina <= 1)){%>
					    <li class="page-item">
					      
					      <form method="post" action="GerenciarPerfis" id="formPag">
					      	<input type="hidden" name="nome_perfil" value="<%=busca%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -2);}%>">
					      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(pagina-1);}%>
					      	</button>
					      </form>
					      
					    </li>
					    <% } %>
					  	
					  	<%if(!(request.getParameter("pagina") == null || Integer.parseInt(request.getParameter("pagina")) <= 0)){%>
					    <li class="page-item">
					      
					      <form method="post" action="GerenciarPerfis" id="formPag">
					      	<input type="hidden" name="nome_perfil" value="<%=busca%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) -1);}%>">
					      	<%if(request.getParameter("pagina") == null){%>0<%}else{out.print(pagina);}%>
					      	</button>
					      </form>
					      
					    </li>
					    <% } %>
					    
					    <li class="page-item disabled">
					    	<span class="page-link"><%if(request.getParameter("pagina") == null){%>1<%}else{out.print(pagina+1);}%></span>
					    </li>
					    
					    <% if(numSolicitacoes >= 10){ %>
					    <li class="page-item">
					    
					      <form method="post" action="GerenciarPerfis" id="formPag">
					      	<input type="hidden" name="nome_perfil" value="<%=busca%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
					      	<%if(pagina == 0){%>2<%}else{out.print(pagina +2);}%>
					      	</button>
					      </form>
					    </li>
					    <% } %>
					    
					    <% if(numSolicitacoes >= 20){ %>
					    <li class="page-item">
					    
					      <form method="post" action="GerenciarPerfis" id="formPag">
					      	<input type="hidden" name="nome_perfil" value="<%=busca%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>2<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +2);}%>">
					      	<%if(pagina == 0){%>3<%}else{out.print(pagina +3);}%>
					      	</button>
					      </form>
					    </li>
					    <% } %>
					    
					    <% if(numSolicitacoes >= 30){ %>
					    <li class="page-item">
					    
					      <form method="post" action="GerenciarPerfis" id="formPag">
					      	<input type="hidden" name="nome_perfil" value="<%=busca%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>3<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +3);}%>">
					      	<%if(pagina == 0){%>4<%}else{out.print(pagina +4);}%>
					      	</button>
					      </form>
					    </li>
					    <% } %>
					    
					   
					    <li class="page-item <% if(numSolicitacoes <= 10){ %>disabled<% } %>">
					    
					      <form method="post" action="GerenciarPerfis" id="formPag">
					      	<input type="hidden" name="nome_perfil" value="<%=busca%>">
					      	<button class="page-link" type="submit" name="pagina" value="<%if(request.getParameter("pagina") == null){%>1<%}else{out.print(Integer.parseInt(request.getParameter("pagina")) +1);}%>">
					      	Próximo
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