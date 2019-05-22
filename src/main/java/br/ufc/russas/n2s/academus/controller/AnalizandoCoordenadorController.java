/* Controller da pagina Visualizar Solicitacao opcao "Avaliar" de coordenador.
 * Nesta pagina s�o salvo as informa��es passadas na avalia�ao do coordenador (Resultado e Justificativa) 
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
			int id = Integer.parseInt(request.getParameter("button"));
			
			String mensagem = "";
			//Verificando se todas as informa��es estao vazias
			if (resultado != null && (justificativa != null || justificativa == "") && id != 0) {
	
				if(resultado.equals("Sim")) {
					resultado = "DEFERIDO";
				} else if(resultado.equals("Nao")) {
					resultado = "INDEFERIDO";
				} else {
					resultado = "INDEFINIDO";
				}
				// Procurando no banco
				SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
				Solicitacao solicitacao = sodao.buscarPorId(id);
				
				// Verifica se a solicita��o esta no banco e seu resultado n�o foi invalido
				if(solicitacao == null || resultado.equals("INDEFINIDO")) {
					mensagem = "Erro";
					
				} else {
					// armezena as informa��es no banco
					solicitacao.setResultado(resultado);
					solicitacao.setJustificativa(justificativa);
					solicitacao.setStatus(Status.FINALIZADO);
					
					sodao.editar(solicitacao);
					mensagem = "AS";// Mensagem "Avaliza��o Sucesso"
				}
			} else {
				//As informa��es est�o vazias e n�o foram passadas corretamente
				mensagem = "AN";
				request.setAttribute("id", id);
				request.setAttribute("mensagem", mensagem);
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSocilitacao.jsp");
				// Mantem as informa��es e mantem na mesma pagina
				
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
