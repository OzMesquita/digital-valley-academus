package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.DisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.model.Disciplina;

public class ExcluirDisciplinaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExcluirDisciplinaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("buttonEx") != null){
			try {
				String idDisc = request.getParameter("buttonEx");
				Disciplina d = new Disciplina();
				
				d.setId(idDisc);
				DisciplinaDAO daoDisc = new JDBCDisciplinaDAO();
				
				daoDisc.excluir(d);
			
				request.setAttribute("success", "Disciplina excluída com sucesso.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaDisciplinas.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				request.setAttribute("erro", "Erro! Não foi possível excluir Disciplina.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaDisciplinas.jsp");
				dispatcher.forward(request, response);
				
				e.printStackTrace();
			}
		}
		else{
			try {
				response.sendRedirect("ListarDisciplinas");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
