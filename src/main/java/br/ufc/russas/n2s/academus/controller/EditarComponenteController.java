package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditarComponenteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public EditarComponenteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String id_matriz = request.getParameter("id_matriz");
			if(id_matriz == null)
				id_matriz = (String)request.getAttribute("id_matriz");
			
			String ans = request.getParameter("button");
			if(ans == null)
				ans = (String)request.getAttribute("id_comp");
			
			request.setAttribute("id_componente", ans);
			request.setAttribute("id_matriz", id_matriz);
			request.setAttribute("success", (String)request.getAttribute("success"));
			request.setAttribute("error", (String)request.getAttribute("error"));
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("editarComponente.jsp");
			dispatcher.forward(request, response);
			return;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
