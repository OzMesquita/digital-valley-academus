package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrarEntregaController  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public RegistrarEntregaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			
			String idSolicitacao = request.getParameter("registro");
			request.setAttribute("id", idSolicitacao);
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("anexarDocumentos.jsp");
			dispatcher.forward(request, response);
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("redirect.jsp");
		}
	}
}
