package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcluirMatrizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExcluirMatrizController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("button") != null){
			int idMatriz = Integer.parseInt(request.getParameter("button"));
		}
		try {
			response.sendRedirect("ListarMatrizes");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
