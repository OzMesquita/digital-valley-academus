<%@ page import="util.Constantes"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Cadastro de Disciplina</title>
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
						<li class="breadcrumb-item"><a href="MenuInicial">Inicio</a></li>					
						<li class="breadcrumb-item active" aria-current="page">Cadastrar Disciplina</li>
					</ol>
					</nav>
					<h1>Cadastrar Disciplina</h1>
					<p>Atenção: Os campos abaixo (*) são de preenchimento
						obrigatório</p>
					<br>
					
					<!-- Caso o cadastro seja bem sucedido vem para essa tela com uma mensagem de sucesso -->
					<% if (request.getAttribute("success") != null){ %>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<%= request.getAttribute("success") %>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    	<span aria-hidden="true">&times;</span>
						  	</button>
						</div>
					<% } %>
					
					<!-- Caso o cadastro não seja bem sucedido vem para essa tela com uma mensagem de erro -->
					<% if (request.getAttribute("erro") != null){ %>
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
							<%= request.getAttribute("erro") %>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    	<span aria-hidden="true">&times;</span>
						  	</button>
						</div>
					<% } %>
					
					<div class="form-group">
						<form action="CadastrarDisciplina" method="post">
							<label for="idInput">Código da Disciplina*</label> <input type="text" pattern="[a-zA-Z\s]{3}[0-9]{4}$" name="id_disciplina" class="form-control" id="idInput" textTransform="uppercase" aria-describedby="tituloHelp" placeholder="Digite o código da Disciplina" required > 
							<small id="tituloHelp" class="form-text text-muted"> Exemplo: RUS1001</small> 
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="nomeInput"> Nome da Disciplina*</label> 
							<input type="text" name="nome" pattern="[a-zA-Z\sÇçÁáÀàÉéÍíÓóÚúÃãõÕêÊâÂôÔ]+$" class="form-control" id="nomeInput" aria-describedby="tituloHelp" placeholder="Digite o nome da Disciplina" required> 
							<small id="tituloHelp" class="form-text text-muted"> Exemplo: FUNDAMENTOS DE PROGRAMAÇÃO (Utilize apenas letras)</small> 
							<div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							<label for="cargaInput">  Carga Horária*</label> 
							<input type="number" step="16" min="16" name="carga" class="form-control" id="cargaInput" aria-describedby="tituloHelp" placeholder="Digite a carga horária" required> 
							<small id="tituloHelp" class="form-text text-muted"> Exemplo: 64</small> 
							 <div class="invalid-feedback">
	                            
	                        </div>
							<br> 
							
							
							<div class="modal-footer">
								<div id="botoes" class="controls">
									<button type="button" class="btn btn-primary" onclick="funcao()">Cancelar</button>
									<button type="submit" class="btn btn-primary">Confirmar</button>
								</div>
							</div>
							<!-- Modal -->
							<div class="modal fade" id="Voltar" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="modalLabel">Cancelar</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<h2>Deseja mesmo cancelar essa operação?<br>Você irá perder os dados informados!</h2>
										</div>
										<div class="modal-footer">
											<button type="button" id="modal-nao" autofocus class="btn btn-primary btn-sm active" data-dismiss="modal" >Não</button>
											<a href="Inicio"><button type="button" class="btn btn-primary btn-sm active">Sim</button></a>
										</div>
									</div>
								</div>
							</div>
							<!-- Fim de Modal -->
						</form>
					</div>
				</div>
			</div>
		</div>
		<c:import url="jsp/elements/footer.jsp" charEncoding="UTF-8"></c:import>
	</body>
	<script>
	var modificado = 0;
	$(document).ready(function(){
		$('input').change(function(){
			modificado = 1;
		});
	});
	
	function funcao(){
		if(modificado === 1){
			$(document).ready(function(){
	            $("#Voltar").modal();
	        });
		} else{
			window.location.href = "MenuInicial";
		}
	}
	</script>
</html>