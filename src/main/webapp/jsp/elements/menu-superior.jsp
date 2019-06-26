<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> 
<a class="navbar-brand" href="Inicio"><img alt="Logo do Academus" src="resources/images/logoacademus.png" width="40px" height="30px"> Academus</a> 
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
   	</button>
   
   <div class="collapse navbar-collapse" id="navbarNavAltMarkup">	
   		<%if(((PerfilAcademus) session.getAttribute("userAcademus")).getIsAdmin()){%>
       <ul class="navbar-nav w-100">
           <li class="nav-item dropdown">
           		<a class="nav-link dropdown-toggle" href="#" id="disciplinaDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Disciplinas</a>
           		<div class="dropdown-menu" aria-labelledby="disciplinaDropdown">
		          	<a class="dropdown-item" href="ListarDisciplinas">Listar Disciplinas</a>
		          	<a class="dropdown-item" href="CadastrarDisciplina">Cadastrar Disciplinas</a>
		        </div>
           </li>
           <li class="nav-item dropdown">
           		<a class="nav-link dropdown-toggle" href="#" id="matrizDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Matrizes Curriculares</a>
           		<div class="dropdown-menu" aria-labelledby="matrizDropdown">
		          	<a class="dropdown-item" href="ListarMatrizes"> Listar Matrizes</a>
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
       <div class="d-flex justify-content-end w-100">
       		<a class="btn-sm btn-light" href="Sair">Voltar ao Guardião</a>
       </div>
             
   </div>
   
	
</nav>