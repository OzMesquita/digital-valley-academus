<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> 
<a class="navbar-brand" href=""> Logo do Academus </a> 
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
   	</button>
   
   <div class="collapse navbar-collapse" id="navbarNavAltMarkup">	
   		<%if(((PerfilAcademus) session.getAttribute("usuario")).getIsAdmin()){%>
       <div class="navbar-nav">
           <a class="nav-item nav-link" href="ListarDisciplinas"> Listar Disciplinas</a>
           <a class="nav-item nav-link" href="BuscarDisciplina"> Buscar Disciplinas</a>
           <a class="nav-item nav-link" href="CadastrarDisciplina"> Cadastrar Disciplinas</a>
           <a class="nav-item nav-link" href="ListarMatrizes"> Listar Matrizes</a>
           <a class="nav-item nav-link" href="BuscarMatriz"> Buscar Matrizes</a>
           <a class="nav-item nav-link" href="CadastrarMatriz"> Cadastrar Matrizes</a>
           
       </div>
       <%}%>      
   </div>
   
	<a class="btn-sm btn-light text-right" href="Sair">Voltar ao Guardião</a>
</nav>