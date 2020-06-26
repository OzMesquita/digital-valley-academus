package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.CursoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCCursoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCPerfilAcademusDAO;
import br.ufc.russas.n2s.academus.dao.PerfilAcademusDAO;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;

public class EditarPerfisController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PerfilAcademusDAO perfildao = new JDBCPerfilAcademusDAO();

    public EditarPerfisController() {
	    super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PerfilAcademus perfil = new PerfilAcademus();
		CursoDAO cursodao = new JDBCCursoDAO();
		
		perfil.setIdGuardiao(Integer.parseInt(request.getParameter("id_pessoa_usuario")));
		perfil.setId(Integer.parseInt(request.getParameter("id_perfil")));
		perfil.setNome(request.getParameter("nome"));
		perfil.setEmail(request.getParameter("email"));
		perfil.setCPF(request.getParameter("cpf"));
		perfil.setNivel(NivelAcademus.getNivel(Integer.parseInt(request.getParameter("nivel"))));
		perfil.setIsAdmin(Boolean.parseBoolean(request.getParameter("is_admin")));
		perfil.setCurso(cursodao.buscarPorId(Integer.parseInt(request.getParameter("curso"))));
		
		
		try {
			
			perfildao.editar(perfil);
			
			request.setAttribute("success", "Perfil alterado com sucesso.");
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("editarPerfil.jsp?id="+perfil.getId());
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
