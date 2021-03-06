package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditarSolicitacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditarSolicitacaoController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String ans = request.getParameter("button");
			request.setAttribute("id", ans);
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("EditarSolicitacao.jsp");
			
			dispatcher.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
