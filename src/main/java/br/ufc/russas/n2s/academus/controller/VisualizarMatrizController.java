package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisualizarMatrizController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public VisualizarMatrizController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			String ans = request.getParameter("button");
			if(ans != null)
				request.setAttribute("id_matriz", ans);
			else {
				String ans2 = (String)request.getAttribute("button");
				String success = (String) request.getAttribute("sucess2");
				String erro = (String) request.getAttribute("erro2");
				System.out.println("Passou aqui"+ ans2+ success+ erro);
				request.setAttribute("id_matriz", ans2);
				request.setAttribute("success", success);
				request.setAttribute("erro", erro);
			}
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarMatriz.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
