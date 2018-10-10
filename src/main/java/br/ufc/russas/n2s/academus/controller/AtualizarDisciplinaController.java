package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.model.Disciplina;



/**
 * Servlet implementation class AtualizarDisciplinaController
 */
@WebServlet("/AtualizarDisciplina")

public class AtualizarDisciplinaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JDBCDisciplinaDAO daoCadastro = new JDBCDisciplinaDAO();
   
    public AtualizarDisciplinaController() {
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
		if(request.getParameter("button") == null && request.getParameter("id_disciplina") != null) { // se entrou aqui � porque est� editando
			String id = request.getParameter("id_disciplina");
			String nome = request.getParameter("nome");
			int carga = Integer.parseInt(request.getParameter("carga"));
			int creditos = Integer.parseInt(request.getParameter("creditos"));
	
			Disciplina dis = new Disciplina();
			
			dis.setId(id);
			dis.setNome(nome);
			dis.setCarga(carga);
			dis.setCreditos(creditos);
	
			daoCadastro.atualizarDisciplina(dis);
	
			try {
				//javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaDisciplinas.jsp");
				
				//dispatcher.forward(request, response);
				response.sendRedirect("VisualizarDisciplinas");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else { // se entrou aqui, � porque est� buscando a disciplina para visualizar
			try { 
				String ans = request.getParameter("button");
				request.setAttribute("id", ans);
				System.out.print(ans);
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("editarDisciplina.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
