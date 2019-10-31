<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<div class="col-md-2" style="margin-top: 5px;">
    <div class="btn-group-vertical d-flex flex-column border" role="group">
    <strong><p class="btn btn-sm" align="center">Opções</p></strong>
    <% 
		PerfilAcademus per = (PerfilAcademus) session.getAttribute("userAcademus");
		if (per != null){
			if (per.getIsAdmin()){
				%>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="aproveitamento" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Proveito de disciplina
				    </a>
				    <div class="dropdown-menu" aria-labelledby="aproveitamento">
				      <a class="dropdown-item" href="CadastrarSolicitacao">Cadastrar</a>
				      <a class="dropdown-item" href="Inicio">Ver Histórico</a>
				    </div>
				</div>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="segundaChamada" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Segunda chamada
				    </a>
				    <div class="dropdown-menu" aria-labelledby="segundaChamada">
				      <a class="dropdown-item" href="CadastrarSegundaChamada">Cadastrar</a>
				      <a class="dropdown-item" href="HistoricoSegundaChamada">Ver Histórico</a>
				    </div>
				</div>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="recorrecao" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Recorreção de prova
				    </a>
				    <div class="dropdown-menu" aria-labelledby="recorrecao">
				      <a class="dropdown-item" href="CadastroRecorrecaoDeProva">Cadastrar</a>
				      <a class="dropdown-item" href="#">Ver Histórico</a>
				    </div>
				</div>
				<%
			} else if (per.getNivel() == NivelAcademus.ALUNO){
				%>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="aproveitamento" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Proveito de disciplina
				    </a>
				    <div class="dropdown-menu" aria-labelledby="aproveitamento">
				      <a class="dropdown-item" href="CadastrarSolicitacao">Cadastrar</a>
				      <a class="dropdown-item" href="Inicio">Ver Histórico</a>
				    </div>
				</div>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="segundaChamada" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Segunda chamada
				    </a>
				    <div class="dropdown-menu" aria-labelledby="segundaChamada">
				      <a class="dropdown-item" href="CadastrarSegundaChamada">Cadastrar</a>
				      <a class="dropdown-item" href="HistoricoSegundaChamada">Ver Histórico</a>
				    </div>
				</div>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="recorrecao" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Recorreção de prova
				    </a>
				    <div class="dropdown-menu" aria-labelledby="recorrecao">
				      <a class="dropdown-item" href="CadastroRecorrecaoDeProva">Cadastrar</a>
				      <a class="dropdown-item" href="#">Ver Histórico</a>
				    </div>
				</div>
				
				<%
			} else if (per.getNivel() == NivelAcademus.COORDENADOR){
				%>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="aproveitamento" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Proveito de disciplina
				    </a>
				    <div class="dropdown-menu" aria-labelledby="aproveitamento">
				      <a class="dropdown-item" href="CadastrarSolicitacao">Cadastrar</a>
				      <a class="dropdown-item" href="Inicio">Ver Histórico</a>
				    </div>
				</div>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="segundaChamada" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Segunda chamada
				    </a>
				    <div class="dropdown-menu" aria-labelledby="segundaChamada">
				      <a class="dropdown-item" href="CadastrarSegundaChamada">Cadastrar</a>
				      <a class="dropdown-item" href="HistoricoSegundaChamada">Ver Histórico</a>
				    </div>
				</div>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="recorrecao" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Recorreção de prova
				    </a>
				    <div class="dropdown-menu" aria-labelledby="recorrecao">
				      <a class="dropdown-item" href="CadastroRecorrecaoDeProva">Cadastrar</a>
				      <a class="dropdown-item" href="#">Ver Histórico</a>
				    </div>
				</div> 
				<%
			} else if (per.getNivel() == NivelAcademus.SECRETARIO){
				%>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="aproveitamento" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Proveito de disciplina
				    </a>
				    <div class="dropdown-menu" aria-labelledby="aproveitamento">
				      <a class="dropdown-item" href="CadastrarSolicitacao">Cadastrar</a>
				      <a class="dropdown-item" href="Inicio">Ver Histórico</a>
				    </div>
				</div>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="segundaChamada" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Segunda chamada
				    </a>
				    <div class="dropdown-menu" aria-labelledby="segundaChamada">
				      <a class="dropdown-item" href="CadastrarSegundaChamada">Cadastrar</a>
				      <a class="dropdown-item" href="HistoricoSegundaChamada">Ver Histórico</a>
				    </div>
				</div>
				<div class="btn-group-vertical btn-group-sm" role="group">
					<a id="recorrecao" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      Recorreção de prova
				    </a>
				    <div class="dropdown-menu" aria-labelledby="recorrecao">
				      <a class="dropdown-item" href="CadastroRecorrecaoDeProva">Cadastrar</a>
				      <a class="dropdown-item" href="#">Ver Histórico</a>
				    </div>
				</div>
				<%
			} else{
				%>
				<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Cadastrar</a>
				<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Editar</a>
        		<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Listar</a>
				<%				
				}
		} else {
			%>
			<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Cadastrar</a>
    		<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Editar</a>  
       		<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Remover</a>
 			<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Listar</a>
			<%
		}
	%>   
    </div>
    <br>
</div>