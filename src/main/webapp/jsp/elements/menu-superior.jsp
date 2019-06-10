<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> 
<a class="navbar-brand" href="Inicio"> Logo do Academus </a> 
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
   	</button>
   
   <div class="collapse navbar-collapse" id="navbarNavAltMarkup">	
   		<%if(((PerfilAcademus) session.getAttribute("userAcademus")).getIsAdmin()){%>
       <ul class="navbar-nav">
           <li class="nav-item dropdown">
           		<a class="nav-link dropdown-toggle" href="#" id="disciplinaDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Disciplinas</a>
           		<div class="dropdown-menu" aria-labelledby="disciplinaDropdown">
		          	<a class="dropdown-item" href="ListarDisciplinas"> Listar Disciplinas</a>
		          	<a class="dropdown-item" href="BuscarDisciplina"> Buscar Disciplinas</a>
		          	<a class="dropdown-item" href="CadastrarDisciplina"> Cadastrar Disciplinas</a>
		        </div>
           </li>
           <li class="nav-item dropdown">
           		<a class="nav-link dropdown-toggle" href="#" id="matrizDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Matrizes Curriculares</a>
           		<div class="dropdown-menu" aria-labelledby="matrizDropdown">
		          	<a class="dropdown-item" href="ListarMatrizes"> Listar Matrizes</a>
		          	<a class="dropdown-item" href="BuscarMatriz"> Buscar Matrizes</a>
		          	<a class="dropdown-item" href="CadastrarMatriz"> Cadastrar Matrizes</a>
		          	<div class="dropdown-divider"></div>
		          	<a class="dropdown-item" href="AssociarComponentes"> Associar Componentes</a>
		        </div>
           </li>
           <li class="nav-item">
           		<a class="nav-link" href="GerenciarPerfis"> Gerenciar Perfis</a>
           	</li>
       </ul>
       <%}%>      
   </div>
   
	<a class="btn-sm btn-light text-right" href="Sair">Voltar ao Guardião</a>
</nav>