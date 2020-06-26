package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuscarMatrizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BuscarMatrizController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("buscaMatriz.jsp"); 
			dispatcher.forward(request, response);			
	
		}catch(Exception e){
			e.getMessage();
		}
	}

}
