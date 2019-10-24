<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> 
<a class="navbar-brand" href="MenuInicial"><img alt="Logo do Academus" src="resources/images/logoacademus.png" width="40px" height="30px"> Academus</a> 
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
   	</button>
   
   <div class="collapse navbar-collapse" id="navbarNavAltMarkup">	
   		
       	<ul class="navbar-nav w-100">
       		<li class="nav-item">
           		<a href="MenuInicial" class="nav-link">Início</a>
           	</li>
           	
           	<li class="nav-item">
           		<a class="nav-link" href="Sair">Guardião</a>
           	</li>     	
           	
           	<%if(((PerfilAcademus) session.getAttribute("userAcademus")).getIsAdmin()){%>
           	
           	<li class="nav-item dropdown">
           		<a class="nav-link dropdown-toggle" href="#" id="disciplinaDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Disciplinas</a>
           		<div class="dropdown-menu" aria-labelledby="disciplinaDropdown">
		          	<a class="dropdown-item" href="ListarDisciplinas">Listar Disciplinas</a>
		          	<a class="dropdown-item" href="CadastrarDisciplina">Cadastrar Disciplinas</a>
		          	<a class="dropdown-item" href="AssociarDisciplina">Associar Disciplinas</a>
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
           	<%}%>
           	<li class="nav-item dropdown ml-auto">
           		<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=((PerfilAcademus) session.getAttribute("userAcademus")).getNome()%></a>
		        <div class="dropdown-menu" aria-labelledby="userDropdown">
		         	<a class="dropdown-item" href="Sair">Sair</a>
		       	</div>
           	</li>
       	</ul>
       
       	
       	
   </div>
   
	
</nav>