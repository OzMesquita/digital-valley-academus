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
import br.ufc.russas.n2s.academus.model.ComponenteCurricular;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;
import br.ufc.russas.n2s.academus.model.Natureza;

public class AssociarComponentesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DisciplinaDAO disciplina_dao = new DAOFactoryJDBC().criarDisciplinaDAO();
	private MatrizCurricularDAO matrizdao = new DAOFactoryJDBC().criarMatrizCurricularDAO();
	private ComponenteCurricularDAO componentedao = new DAOFactoryJDBC().criarComponenteCurricularDAO();

    public AssociarComponentesController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("matriz") != null && request.getParameter("componente") != null) { // Se entrou aqui é porque está associando um componente
			
			int idMatriz = Integer.parseInt(request.getParameter("matriz"));
			String idDisciplina = request.getParameter("componente");
			Natureza natureza = Natureza.getNatureza(request.getParameter("natureza"));
			String[] idPreRequisitos = request.getParameterValues("preRequisitos[]");
			
			MatrizCurricular matriz = matrizdao.buscarPorId(idMatriz);
			
			Disciplina disciplina = disciplina_dao.buscarPorId(idDisciplina);
			
			ComponenteCurricular componente = new ComponenteCurricular();
			componente.setDisciplina(disciplina);
			componente.setNatureza(natureza);
			
			ArrayList<Disciplina> preRequisitos = new ArrayList<Disciplina>();
			
			if(idPreRequisitos != null) {
				for (String id : idPreRequisitos) {
					System.out.println(id);
					System.out.println(id.substring(0, 7));
					Disciplina d = disciplina_dao.buscarPorId(id.substring(0, 7));
					System.out.println(d);
					preRequisitos.add(d);
				}
				System.out.println(idPreRequisitos);
				System.out.println(preRequisitos);
			}
			
			try {
				
				componente = componentedao.cadastrar(componente, matriz);
				
				componentedao.inserirPreRequsitos(preRequisitos, componente.getIdComponente());
				
				request.setAttribute("success", "Componente associado com sucesso.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("associarComponentes.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Ocorreu um erro ao associar componente. <br>Erro:<br>" + e.getMessage());
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("associarComponentes.jsp");
				dispatcher.forward(request, response);
			}
		} else { // Se entrou aqui, é porque vai associar uma
			try { 
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("associarComponentes.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Ocorreu um erro ao associar componente. <br>Erro:<br>" + e.getMessage());
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("associarComponentes.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
