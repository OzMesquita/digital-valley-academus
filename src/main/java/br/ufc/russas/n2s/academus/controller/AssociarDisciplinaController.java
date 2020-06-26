package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.DisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.model.ComponenteCurricular;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;
import br.ufc.russas.n2s.academus.model.Natureza;
import br.ufc.russas.n2s.academus.model.Professor;

public class AssociarDisciplinaController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private DisciplinaDAO disciplina_dao = new DAOFactoryJDBC().criarDisciplinaDAO();
	private ProfessorDAO professor_dao = new DAOFactoryJDBC().criarProfessorDAO();
	

    public AssociarDisciplinaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("professor") != null && request.getParameter("observador") != null) { // Se entrou aqui é porque está associando um componente
			
			String curso = request.getParameter("curso");
			String idProfessor = request.getParameter("professor");
			String[] disciplinas = request.getParameterValues("disciplinas[]");
			Professor professor= professor_dao.buscarPorId(Integer.parseInt(idProfessor));
			professor_dao.removeDisciplinas(Integer.parseInt(idProfessor));
			
			if(disciplinas != null) {
				for (String id : disciplinas) {
					Disciplina d = disciplina_dao.buscarPorId(id.substring(0, 7));
					disciplina_dao.addProfessor(professor, d);
				}
			}
			
			try {				
				request.setAttribute("success", "Professor associado com sucesso!");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("associarDisciplina.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Ocorreu um erro ao associar professor. <br>Erro:<br>" + e.getMessage());
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("associarDisciplina.jsp");
				dispatcher.forward(request, response);
			}
		} else { // Se entrou aqui, é porque vai associar uma
			try { 
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("associarDisciplina.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Ocorreu um erro ao associar disciplina. <br>Erro:<br>" + e.getMessage());
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("associarDisciplina.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
