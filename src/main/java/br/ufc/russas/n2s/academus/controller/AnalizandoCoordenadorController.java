/* Controller da pagina Visualizar Solicitacao opcao "Avaliar" de coordenador.
 * Nesta pagina são salvo as informações passadas na avaliaçao do coordenador (Resultado e Justificativa) 
 */
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
			String id = request.getParameter("button");
			int id_sol = Integer.parseInt(id);
			
			String mensagem = "";
			//Verificando se todas as informações estao vazias
			if (resultado != null && (justificativa != null || justificativa == "") && id_sol != 0) {
	
				if(resultado.equals("Sim")) {
					resultado = "DEFERIDO";
				} else if(resultado.equals("Nao")) {
					resultado = "INDEFERIDO";
				} else {
					resultado = "INDEFINIDO";
				}
				// Procurando no banco
				SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
				Solicitacao solicitacao = sodao.buscarPorId(id_sol);
				
				// Verifica se a solicitação esta no banco e seu resultado não foi invalido
				if(solicitacao == null || resultado.equals("INDEFINIDO")) {
					mensagem = "Erro! Não foi possível avaliar a solicitação.";
					request.setAttribute("erro", mensagem);
					
				} else {
					// armezena as informações no banco
					solicitacao.setResultado(resultado);
					solicitacao.setJustificativa(justificativa);
					solicitacao.setStatus(Status.FINALIZADO);
					
					sodao.editar(solicitacao);
					mensagem = "A avaliação de resultado "+resultado+" foi cadastrada com sucesso.";// Mensagem "Avalização Sucesso"
					request.setAttribute("success", mensagem);
				}
			} else {
				//As informações estão vazias e não foram passadas corretamente
				mensagem = "Erro! Não foi passado informações necessária para a ação.";
				request.setAttribute("id", id);
				request.setAttribute("erro", mensagem);
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSolicitacao.jsp");
				dispatcher.forward(request, response);
				// Mantem as informações e mantem na mesma pagina
				
				return;
			}
			
			request.setAttribute("id", id);
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSolicitacao.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
