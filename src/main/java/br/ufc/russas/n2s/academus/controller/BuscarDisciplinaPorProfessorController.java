package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.model.Disciplina;

public class BuscarDisciplinaPorProfessorController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	ProfessorDAO professordao = new DAOFactoryJDBC().criarProfessorDAO();
	List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disciplinas = professordao.listarDisciplinas(Integer.parseInt(request.getParameter("id")));
		String result = "";
		if(disciplinas.size()>0){
			result = "[";
			for(int i=0;i<disciplinas.size()-1;i++){
				result+="{\"id\": \""+disciplinas.get(i).getId()+"\", \"text\": \""+disciplinas.get(i).getId()+" - "+disciplinas.get(i).getNome()+"\"},";
			}
			result+="{\"id\": \""+disciplinas.get(disciplinas.size()-1).getId()+"\", \"text\": \""+disciplinas.get(disciplinas.size()-1).getId()+" - "+disciplinas.get(disciplinas.size()-1).getNome()+"\"}";
			result+= "]";
		}
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
