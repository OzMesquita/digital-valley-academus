package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;


@WebServlet("/AnalizarSolicitacao")
public class AnalizandoCoordenadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalizandoCoordenadorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultado = request.getParameter("resultado");
		String justificativa = request.getParameter("justificativaInput");
		int id_sol = Integer.parseInt((String)request.getParameter("id"));
		System.out.println(resultado+"   "+justificativa+"  "+id_sol);
		
		Solicitacao solicitacao = new Solicitacao();
		//pegar a solicitacao do dao
		
		solicitacao.setResultado(resultado);
		solicitacao.setJustificativa(justificativa);
		solicitacao.setStatus(Status.FINALIZADO);
		
		
		try {
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
