package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.CursoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCCursoDAO;
import br.ufc.russas.n2s.academus.model.Curso;

/**
 * Servlet implementation class CadastrarCursoController
 */
@WebServlet("/CadastrarCursoController")
public class CadastrarCursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarCursoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		Curso curso = new Curso();
		CursoDAO cd = new JDBCCursoDAO();
		if(nome != null) {
			curso.setNome(nome);
			cd.cadastrar(curso);
		}
	}

}
