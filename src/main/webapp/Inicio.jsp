<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Início</title>
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
						<li class="breadcrumb-item active" aria-current="page">Histórico de Solicitações de Aproveitamento de Disciplinas</li>
					</ol>
					</nav>
					
					<h1>Histórico de Solicitações de Aproveitamento de Disciplinas</h1>
					
					<c:import url="jsp/elements/listagemSolicitacao.jsp" charEncoding="UTF-8"></c:import>
				</div>
			</div>
			<%		
				// Mensagens dadas aos usuarios do que aconteceu com o sistema
				// "mensagem" é repassado pelos controllers do sistema
				String mensagem = (String) request.getAttribute("mensagem");
					if(mensagem != null){
						
						if (mensagem.equals("ES")){
							
						// Mensagem de confirmação da pagina EditarSolicitação.jsp, controller AtualizarSolicitaçaoController
						// Solicitação atualizada com sucesso
					%>
						<script type="text/javascript">
        					alert("Solicitação atualizada com sucesso!");
    					</script>
    					
					<%	} else if(mensagem.equals("EN")){
						
						// Mensagem de Erro da pagina EditarSolicitacao.jsp, controller AtualizarSolicitacaoController
						// Erro, não pode editar a solicitação por nao ser dono da solicitacao 
						%>
						<script type="text/javascript">
        					alert("ERRO. Você não é dono desta solicitação");
    					</script>
    					
					<%	}else if(mensagem.equals("CS")){
						
						//Mensagem de confirmação da pagina CadastroSolicitacao.jsp, controller CadastrarSolicitacaoController
						// Cadastro  da solicitacao realizado com sucesso
						%>
						<script type="text/javascript">
        					alert("Cadastro da solicitação realizado com sucesso! \nEntregue as documentações necessárias para a secretaria do curso. \nQualquer dúvida, leia o edital do aproveitamento de disciplinas.");
    					</script>
    					
						<%
						} else if(mensagem.equals("AS")){
							%>
							<script type="text/javascript">
	        					alert("Avaliação da solicitação realizada com sucesso!");
	    					</script>
	    					
						<%
						} else if(mensagem.equals("AN")){
							%>
							<script type="text/javascript">
	        					alert("Avaliação inválida! Tente novamente mais tarde.");
	    					</script>
						<%
						
						} else if(mensagem.equals("Erro")){
							%>
							<script type="text/javascript">
	        					alert("Operação Inválida!");
	    					</script>
						<%
						}
					}%>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
</html>