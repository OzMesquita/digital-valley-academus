package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VisualizarMatriz")

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
			request.setAttribute("id_matriz", ans);
			//System.out.println(" 1 p "+ans+"     ");
			
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarMatriz.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}