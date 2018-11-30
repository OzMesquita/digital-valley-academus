package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.model.Disciplina;

public class EditarDisciplinaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JDBCDisciplinaDAO daoCadastro = new JDBCDisciplinaDAO();

    public EditarDisciplinaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("button") == null && request.getParameter("id_disciplina") != null) { // Se entrou aqui é porque está editando
			String id = request.getParameter("id_disciplina");
			String nome = request.getParameter("nome");
			int carga = Integer.parseInt(request.getParameter("carga"));
			int creditos = Integer.parseInt(request.getParameter("creditos"));
	
			Disciplina dis = new Disciplina();
			
			dis.setId(id);
			dis.setNome(nome);
			dis.setCarga(carga);
			dis.setCreditos(creditos);
	
			daoCadastro.editar(dis);
	
			try {

				response.sendRedirect("ListarDisciplinas");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else { // Se entrou aqui, é porque está buscando a disciplina para visualizar
			try { 
				String ans = request.getParameter("button");
				request.setAttribute("id", ans);
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("editarDisciplina.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
