<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.ufc.russas.n2s.academus.model.Solicitacao" %>
<%@ page import="br.ufc.russas.n2s.academus.model.Aluno" %>
<%@ page import="br.ufc.russas.n2s.academus.model.DisciplinaCursada" %>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.SolicitacaoDAO" %>
<%@ page import="br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC" %>
<%@ page import="java.util.*"%>
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	
	Solicitacao solicitacao = new Solicitacao();
	SolicitacaoDAO dao = new DAOFactoryJDBC().criarSolicitacaoDAO();
	
	boolean deuCerto = true;
	try{
		System.out.print((String)(request.getAttribute("id")));
		solicitacao = dao.buscarPorId(Integer.parseInt((String)(request.getAttribute("id"))));
		//solicitacao = dao.buscarPorId(2);
	} catch(Exception e){
		deuCerto = false;
		System.out.print("Deu nao");
	}
	
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
		rel="stylesheet">
	<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/css/design.css" />
	<title>Visualizando Solicitação</title>
</head>
<body>
	<c:import url="jsp/elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
		<% 
			PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");
			if (per != null){
				if (per.getNivel() == NivelAcademus.ALUNO){ 	%>
					<c:import url="jsp/elements/menu-lateral-esquerdo-Aluno.jsp"
					charEncoding="UTF-8"></c:import>
			<%	} else if (per.getNivel() == NivelAcademus.COORDENADOR){ %>
					<c:import url="jsp/elements/menu-lateral-esquerdo-Coordenador.jsp"
					charEncoding="UTF-8"></c:import>
			<%	} else if (per.getNivel() == NivelAcademus.SECRETARIO){	%>
					<c:import url="jsp/elements/menu-lateral-esquerdo-Secretario.jsp"
					charEncoding="UTF-8"></c:import>
			<%	} else{ %>
					<c:import url="jsp/elements/menu-lateral-esquerdo.jsp"
					charEncoding="UTF-8"></c:import>
			<% }
			} else { %>
				<c:import url="jsp/elements/menu-lateral-esquerdo.jsp"
				charEncoding="UTF-8"></c:import>
			<% } %>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Você está em:</li>
					<li class="breadcrumb-item" aria-current="page"><a href="jsp/elements/aviso.jsp">Início</a></li> <!-- PREENCHER HREF -->					
					<li class="breadcrumb-item active" aria-current="page">Visualizar Solicitação</li>
				</ol>
				</nav>
				<h1>Visualizar Solicitação</h1>
				
				<br>
				<%
				if (solicitacao != null && deuCerto){
				%>
				<form action="SalvarAnexos" method="Post" enctype="multipart/form-data">
				<div class="form-group">						
					<!-- <form action="VisualizarSolicitacao" method="post"> -->
						<label for="idInput"><b> Código da Solicitação </b></label>
						<input type="text" name="id_solicitacao" id="id_solicitacao" value="<%= solicitacao.getIdSolicitacao() %>" class="form-control" id="idInput" aria-describedby="tituloHelp" readonly  required> 
						
						<div class="invalid-feedback">
                            
                        </div>
						<br> 
						
						<label for="nomeInput"><b> Status da Solicitação </b></label> 
						<input type="text" name="nome" value="<%= solicitacao.getStatus() %>" class="form-control" id="nomeInput" aria-describedby="tituloHelp" readonly required> 
						
						<div class="invalid-feedback">
                            
                        </div>
						<br> 
						
						<label for="cargaInput"><b> Nome do Solicitante </b></label> 
						<input type="text" name="componenteCurricular" value="<%=solicitacao.getSolicitante().getNome() %>" class="form-control" id="cargaInput" aria-describedby="tituloHelp" readonly required> 
						
						 <div class="invalid-feedback">
                            
                        </div>
						<br> 
						
						<label for="creditosInput"><b> Nome da Disciplina </b></label> 
						<input type="text" name="creditos" value="<%=solicitacao.getDisciplinaAlvo().getDisciplina().getNome() %>" class="form-control" id="creditosInput" aria-describedby="tituloHelp" readonly required> 
						 
						<div class="invalid-feedback">
                            
                        </div>
                        <br>
                        
                        <label for="justificativaInput"><b> Justificativa </b></label> 
						<textarea rows="8" id="justificativa" name="justificativa" class="form-control" aria-describedby="tituloHelp" readonly required> <%= solicitacao.getJustificativa() %></textarea>
						<div class="invalid-feedback">
                            
                        </div>
						<br>
												
						<label for="disciplinasCursadasInput"><b> Lista de Disciplinas Cursadas</b></label>
							<div class="form-group">
								<table class="table table-responsive">
									<thead>
										<tr>
											<th scope="col">Nome</th>
											<th scope="col">Instituição</th>
											<th scope="col">Carga</th>
											<th scope="col">Semestre</th>
											<th scope="col">Nota</th>
											<th scope="col">Anexo</th>
										</tr>
									</thead>
									<%
										for (DisciplinaCursada disCursada : solicitacao.getDisciplinasCursadas()) {
									%>
									<tr>
										<td><%=disCursada.getNome()%></td>
										<td><%=disCursada.getInstituicao()%></td>
										<td><%=disCursada.getCarga()%></td>
										<td><%=disCursada.getSemestre()%></td>
										<td><%=disCursada.getNota()%></td>
										<td>
											<%if(disCursada.getArquivo().getIdArquivo() == -1){%>
											<input type="file" name="anexo" accept=".pdf">
											<input type="submit" value="Anexar" onclick="atribuirValor(<%=disCursada.getId()%>)">
											<%}else{%>
												<iframe src="C:\n2s\Academus\anexo\371251\71\anexo-371251-71-78.pdf" width="600" height="780" style="border: none;">teste</iframe>
											<%}%>
										</td>
									</tr>
			
									<%
										}
									%>
								</table>
							</div>
						<br>
							
						<label for="resultadoInput"><b> Resultado </b></label>
						<input type="text" name="resultadoInput" value="<%= solicitacao.getResultado() %>" class="form-control" id="resultadoInput" aria-describedby="tituloHelp" readonly required>

						<div class="invalid-feedback">
                            
                        </div>
						<br>
						<br>
					     	
					     	<input type="hidden" name="id_disciplina_cursada" id="id_disciplina_cursada" value="0">
					     	<input type="hidden" name="matricula" id="matricula" value="<%=solicitacao.getSolicitante().getMatricula()%>">
					     	<input type="hidden" name="id_solicitacao" id="id_solicitacao" value="<%=solicitacao.getIdSolicitacao()%>">
			     
				</div>
				</form>
				<%
				} else {
					%>
					Solicitação não encontrada.
					<%
				}
				%>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function atribuirValor(i){
		document.getElementById("id_disciplina_cursada").value = i;
	}
</script>
</html>