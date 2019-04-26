package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.model.Professor;

public class AtribuirCoordenadoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AtribuirCoordenadoresController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("buttonCurso") != null && request.getParameter("cursoInput-"+request.getParameter("buttonCurso")) != null){
			int idCurso = Integer.parseInt(request.getParameter("buttonCurso"));
			int idProfessor = Integer.parseInt(request.getParameter("cursoInput-"+idCurso));
			
			addCoordenador(idCurso, idProfessor);
			try {
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("atribuirCoordenador.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("atribuirCoordenador.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void addCoordenador(int idCurso, int idProfessor){
		
		ProfessorDAO prodao = new DAOFactoryJDBC().criarProfessorDAO();
		Professor prof = prodao.buscarPorId(idProfessor);
		//alterarPerfil(idCurso, idProfessor);
		if(prodao.possuiCoordenador(idCurso)){
			alterarPerfil(idCurso, prof);
		}else{
			prodao.cadastrarCoordenador(idCurso, prof);
		}
	}
	
	private void alterarPerfil(int idCurso, Professor coorNovo){
		ProfessorDAO pd = new DAOFactoryJDBC().criarProfessorDAO();
		
		Professor coorAntigo = pd.isCoordenador(idCurso);
		
		pd.retirarCoordenador(idCurso, coorAntigo);
		pd.cadastrarCoordenador(idCurso, coorNovo);
			
	}

}
