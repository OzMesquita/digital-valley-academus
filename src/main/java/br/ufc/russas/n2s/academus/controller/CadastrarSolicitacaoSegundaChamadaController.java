package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CadastrarSolicitacaoSegundaChamadaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public CadastrarSolicitacaoSegundaChamadaController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//if((request.getParameter("componenteInput") != null))
				//request.setAttribute("erro", "Cadastre pelo menos uma Disciplina Cursada.");
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroSegundaChamada.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
