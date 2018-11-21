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
			if(request.getParameter("editarPtn") != null){
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("buscaMatriz.jsp"); //Encaminhando para errada
				dispatcher.forward(request, response);
				
				System.out.println("Editar btn");
			} else if(request.getParameter("vizualizarPtn") != null){
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("buscaMatriz.jsp"); //Encaminhando para errada
				dispatcher.forward(request, response);
				
				System.out.println("Vizualizar btn");
			} else if(request.getParameter("removerPtn") != null){
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("buscaMatriz.jsp"); //Encaminhando para errada
				dispatcher.forward(request, response);
				
				System.out.println("Remover btn");
			} else {
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("buscaMatriz.jsp"); 
				dispatcher.forward(request, response);
				System.out.println("nenhum btn");
			}
			
			
			
		}catch(Exception e){
			e.getMessage();
		}
	}

}
