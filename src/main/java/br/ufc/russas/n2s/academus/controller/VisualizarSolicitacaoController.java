package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VisualizarSolicitacao")

public class VisualizarSolicitacaoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//MatrizCurricularDao daoCadastroMatriz = new MatrizCurricularDao();

	public VisualizarSolicitacaoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String ans = request.getParameter("button");
			request.setAttribute("id", ans);
			//System.out.println(ans);
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSolicitacao.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
