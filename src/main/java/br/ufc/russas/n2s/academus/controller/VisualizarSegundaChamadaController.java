package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.dao.DAOFactory;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.SegundaChamadaDAO;
import br.ufc.russas.n2s.academus.model.SegundaChamada;

public class VisualizarSegundaChamadaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public VisualizarSegundaChamadaController() {
		super();
	}
	
	DAOFactory df = new DAOFactoryJDBC();
	SegundaChamadaDAO scdao = df.criarSegundaChamadaDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			SegundaChamada sc = scdao.buscarPorId(Integer.parseInt(request.getParameter("id")));
			
			HttpSession session = request.getSession();
			session.setAttribute("segundaChamada", sc);
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSegundaChamada.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
