<%@ page import="br.ufc.russas.n2s.academus.model.NivelAcademus"%>
<%@ page import="br.ufc.russas.n2s.academus.model.PerfilAcademus"%>

<div class="col-md-2" style="margin-top: 5px;">
    <div class="btn-group-vertical d-flex flex-column border" role="group">
    <strong><p class="btn btn-sm" align="center">Opções</p></strong>
    <% 
		PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");
		if (per != null){
			if (per.getIsAdmin()){
				%>
				<a href="Inicio" class="btn btn-light btn-sm text-left">Início</a>
				<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Outra Coisa</a>
				<%
			} else if (per.getNivel() == NivelAcademus.ALUNO){
				%>
				<a href="Inicio" class="btn btn-light btn-sm text-left">Início</a>
        		<a href="CadastrarSolicitacao" class="btn btn-light btn-sm text-left">Cadastrar</a>
				<%
			} else if (per.getNivel() == NivelAcademus.COORDENADOR){
				%>
				<a href="Inicio" class="btn btn-light btn-sm text-left">Início</a> 
				<%
			} else if (per.getNivel() == NivelAcademus.SECRETARIO){
				%>
				<a href="Inicio" class="btn btn-light btn-sm text-left">Início</a>
				<%
			} else{
				%>
				<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Cadastrar</a>
				<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Editar</a>  
        		<a href="elements/aviso.jsp" class="btn btn-light btn-sm text-left">Remover</a>
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