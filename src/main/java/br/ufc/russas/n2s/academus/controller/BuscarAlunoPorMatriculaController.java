package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Aluno;

public class BuscarAlunoPorMatriculaController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	AlunoDAO alunodao = new DAOFactoryJDBC().criarAlunoDAO();
	Aluno aluno = new Aluno();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aluno = alunodao.buscarPorMatricula(request.getParameter("matricula"));
		String result = "";
		if(aluno != null){
			result = "{"
					+ "\"nome\": \""+aluno.getNome()+"\","
					+ "\"curso\": \""+aluno.getCurso().getNome()+"\""
					+ "}";
		}
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
