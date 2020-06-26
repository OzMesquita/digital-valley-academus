package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.DisciplinaDAO;
import br.ufc.russas.n2s.academus.model.Disciplina;

public class EditarDisciplinaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DisciplinaDAO disciplina_dao = new DAOFactoryJDBC().criarDisciplinaDAO();

    public EditarDisciplinaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("button") == null && request.getParameter("id_disciplina") != null) { // Se entrou aqui é porque está editando
			
			String id_antigo = request.getParameter("id_antigo");
			try {
				String id = request.getParameter("id_disciplina");
				String nome = request.getParameter("nome");
				int carga = Integer.parseInt(request.getParameter("carga"));
				int creditos = carga/16;
				
				
				Disciplina disciplina = disciplina_dao.buscarPorId(id);
				if(disciplina == null || id.equals(id_antigo)) {
					disciplina = disciplina_dao.buscarPorId(id_antigo);
					Disciplina nova_disciplina = new Disciplina();
					nova_disciplina.setId(id);
					nova_disciplina.setNome(nome);
					nova_disciplina.setCarga(carga);
					nova_disciplina.setCreditos(creditos);
					
					if(id == id_antigo) {
						disciplina_dao.editar(nova_disciplina);
					} else {
						disciplina_dao.excluir(disciplina);
						disciplina_dao.cadastrar(nova_disciplina);
					}
					request.setAttribute("success", "Disciplina alterada com sucesso.");
					request.setAttribute("id", id);
					
				} else {
					
					request.setAttribute("erro", "Erro! O código da disciplina já existe.");
					request.setAttribute("id", id_antigo);
					
				}
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("editarDisciplina.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("id", id_antigo);
				request.setAttribute("erro", "Ocorreu um erro ao cadastrar a disciplina. <br>Erro:<br>" + e.getMessage());
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("editarDisciplina.jsp");
				dispatcher.forward(request, response);
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
