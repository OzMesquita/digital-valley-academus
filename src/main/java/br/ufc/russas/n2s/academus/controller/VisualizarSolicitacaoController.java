package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;

public class VisualizarSolicitacaoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public VisualizarSolicitacaoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			HttpSession session = request.getSession();
			PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");
			
			String ans = request.getParameter("button");
			request.setAttribute("id", ans);
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSolicitacao.jsp");
			
			if(per.getNivel() == NivelAcademus.SECRETARIO) {
				dispatcher = request.getRequestDispatcher("anexarDocumentos.jsp");
			}
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
