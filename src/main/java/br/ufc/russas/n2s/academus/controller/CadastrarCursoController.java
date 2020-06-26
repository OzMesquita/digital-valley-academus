package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.CursoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCCursoDAO;
import br.ufc.russas.n2s.academus.model.Curso;

public class CadastrarCursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CadastrarCursoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getParameter("nome") != null){
			String nome = request.getParameter("nome");
			Curso curso = new Curso();
			CursoDAO cd = new JDBCCursoDAO();
			if(nome != null) {
				curso.setNome(nome);
				cd.cadastrar(curso);
			}
			
			try{
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroCurso.jsp");
				
				dispatcher.forward(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else{
			try{
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroCurso.jsp");
				
				dispatcher.forward(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
