package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnalizandoCoordenadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AnalizandoCoordenadorController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultado = request.getParameter("resultado");
		String justificativa = request.getParameter("justificativaInput");
		//int id_sol = Integer.parseInt((String)request.getParameter("id"));
		if (resultado != null && justificativa != null) {

			System.out.println(resultado+"   "+justificativa+"  ");
			
		} else {
			if (resultado == null) 
				System.out.print("resultado == null ");
			
			if (justificativa == null)
				System.out.print(" justificativa == null ");
		} 
		
		
		//Solicitacao solicitacao = new Solicitacao();
		//pegar a solicitacao do dao
		
		//solicitacao.setResultado(resultado);
		//solicitacao.setJustificativa(justificativa);
		//solicitacao.setStatus(Status.FINALIZADO);
		
		
		try {
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
