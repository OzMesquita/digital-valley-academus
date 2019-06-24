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
			String idSolicitacao = request.getParameter("button");
			int idSol = Integer.parseInt(idSolicitacao);
			
			String mensagem = "";
			//Verificando se todas as informações estao vazias
			if (resultado != null && idSol != 0) {
				if(justificativa == null)
					justificativa = "";
				// Procurando no banco
				SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
				Solicitacao solicitacao = sodao.buscarPorId(idSol);
				
				// Verifica se a solicitação esta no banco e seu resultado não foi invalido
				if(solicitacao == null || resultado.equals("INDEFINIDO")) {
					mensagem = "Erro! Não foi possivel avaliar a solicitação";
					request.setAttribute("erro", mensagem);
				} else {
					// armezena as informações no banco para cada tipo de resultado
					
					if(resultado.equals("Valido")) {
						solicitacao.setStatus(Status.ANALISANDO);
						mensagem = "A avaliação da Validez do documento da solicitação foi registrada com sucesso";// Mensagem "Avalização Secretario Sucesso"
					} else if(resultado.equals("Invalido")) {
						solicitacao.setResultado("INDEFERIDO");
						solicitacao.setJustificativa(justificativa);
						solicitacao.setStatus(Status.FINALIZADO);
						mensagem = "A avaliação de Invalidez do ducumento da solicitação foi cadastrada com sucesso";// Mensagem "Avalização de Invalida do Secretario Sucesso"
					} else {
						resultado = "INDEFINIDO";
					}
					
					sodao.editar(solicitacao);
					request.setAttribute("success", mensagem);
					
					request.setAttribute("id", idSolicitacao);
					javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSolicitacao.jsp");
					dispatcher.forward(request, response);
					return;
				}
				System.out.println(mensagem);
				
			} else {
				//As informações estão vazias e não foram passadas corretamente
				mensagem = "As informações estão vazias e não foram passadas corretamente";
				request.setAttribute("erro", mensagem);
			}
			
			request.setAttribute("id", idSolicitacao);								// anexarDocumentos.jsp
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("anexarDocumentos.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("id", request.getParameter("button"));
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("anexarDocumentos.jsp");
			dispatcher.forward(request, response);
		}
	}
}
