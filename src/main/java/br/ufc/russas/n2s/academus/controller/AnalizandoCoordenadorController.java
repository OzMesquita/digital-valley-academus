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
	
				System.out.println(resultado+"   "+justificativa+"  "+id);
				
				SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
				Solicitacao solicitacao = sodao.buscarPorId(id);
				if(solicitacao == null) {
					System.out.println("solicitacao is null");
				} else {
					System.out.println("Entrou aqui no banco");
					solicitacao.setResultado(resultado);
					solicitacao.setJustificativa(justificativa);
					solicitacao.setStatus(Status.FINALIZADO);
					
					sodao.editar(solicitacao);
				}
			} else {
				System.out.println("alguem nulo");
			}
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
