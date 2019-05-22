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

public class AnaliseSecretarioController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    public AnaliseSecretarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String resultado = request.getParameter("resultado");
			String justificativa = request.getParameter("justificativaInput");
			int idSolicitacao = Integer.parseInt(request.getParameter("button"));
			
			String mensagem = "";
			//Verificando se todas as informações estao vazias
			if (resultado != null && idSolicitacao != 0) {
				if(justificativa == null)
					justificativa = "";
				// Procurando no banco
				SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
				Solicitacao solicitacao = sodao.buscarPorId(idSolicitacao);
				
				// Verifica se a solicitação esta no banco e seu resultado não foi invalido
				if(solicitacao == null || resultado.equals("INDEFINIDO")) {
					mensagem = "Erro";
					
				} else {
					// armezena as informações no banco para cada tipo de resultado
					
					if(resultado.equals("Valido")) {
						solicitacao.setStatus(Status.ANALISANDO);
						mensagem = "ASS";// Mensagem "Avalização Secretario Sucesso"
					} else if(resultado.equals("Invalido")) {
						solicitacao.setResultado("INDEFERIDO");
						solicitacao.setJustificativa(justificativa);
						solicitacao.setStatus(Status.FINALIZADO);
						mensagem = "AIS";// Mensagem "Avalização de Invalida do Secretario Sucesso"
					} else {
						resultado = "INDEFINIDO";
					}
					
					sodao.editar(solicitacao);
					
				}
			} else {
				//As informações estão vazias e não foram passadas corretamente
				mensagem = "AN";
				request.setAttribute("id", idSolicitacao);
				request.setAttribute("mensagem", mensagem);
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSocilitacao.jsp");
				// Mantem as informações e mantem na mesma pagina
				
				dispatcher.forward(request, response);
				return;
			}
			
			request.setAttribute("mensagem", mensagem);
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("redirect.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
