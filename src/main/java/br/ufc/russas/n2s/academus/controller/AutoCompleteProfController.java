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
import br.ufc.russas.n2s.academus.model.Professor;

public class AutoCompleteProfController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	ProfessorDAO professordao = new DAOFactoryJDBC().criarProfessorDAO();
	List<Professor> professores = new ArrayList<Professor>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		professores = professordao.buscarPorNome(request.getParameter("nome"));
		String result = "";
		if(professores.size()>0){
			result = "{\"query\": \"Unit\", \"suggestions\": [";
			for(int i=0;i<professores.size()-1;i++){
				result+="{\"value\": \""+professores.get(i).getNome()+"\", \"data\": \""+professores.get(i).getId()+"\"},";
			}
			result+="{\"value\": \""+professores.get(professores.size()-1).getNome()+"\", \"data\": \""+professores.get(professores.size()-1).getId()+"\"}";
			result+= "]}";
		}
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
