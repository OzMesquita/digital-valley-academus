package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;

public class AnalizandoCoordenadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AnalizandoCoordenadorController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String resultado = request.getParameter("resultado");
			String justificativa = request.getParameter("justificativaInput");
			int id = Integer.parseInt(request.getParameter("button"));
			
			if (resultado != null && justificativa != null && id != 0) {
	
				if(resultado.equals("Sim")) {
					resultado = "DEFERIDO";
				} else if(resultado.equals("Nao")) {
					resultado = "INDEFERIDO";
				} else {
					resultado = "INDEFINIDO";
				}
				
				SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
				Solicitacao solicitacao = sodao.buscarPorId(id);
				if(solicitacao == null || resultado.equals("INDEFINIDO")) {
					
				} else {
					
					
					solicitacao.setResultado(resultado);
					solicitacao.setJustificativa(justificativa);
					solicitacao.setStatus(Status.FINALIZADO);
					
					sodao.editar(solicitacao);
				}
			}
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
