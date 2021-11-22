package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.SegundaChamadaDAO;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.SegundaChamada;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;
import br.ufc.russas.n2s.academus.model.StatusSegundaChamada;

public class MudarStatusController  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public MudarStatusController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String status = request.getParameter("status");
			String idSolicitacao = request.getParameter("button");
			String justificativa =request.getParameter("justificativaInput");
			int idSol = Integer.parseInt(idSolicitacao);
			String resultado = request.getParameter("resultado");
			
			
			String mensagem = "";
			//Verificando se todas as informações estao vazias
			if (resultado != null && idSol != 0) {
				if(justificativa == null)
					justificativa = "";
				// Procurando no banco
				SegundaChamadaDAO scdao = new DAOFactoryJDBC().criarSegundaChamadaDAO();
				SegundaChamada sc = scdao.buscarPorId(idSol);
				
				// Verifica se a solicitação esta no banco e seu resultado não foi invalido
				if(sc== null) {
					mensagem = "Erro! Não foi possivel avaliar a solicitação";
					request.setAttribute("erro", mensagem);
				} else {
					// armezena as informações no banco para cada tipo de resultado
					
					if(resultado.equals("Deferido")) {
						sc.setStatus(StatusSegundaChamada.DEFERIDO);
						mensagem = "A avaliação da Validez do documento da solicitação foi registrada com sucesso";// Mensagem "Avaliação Secretario Sucesso"
					
					} else if(resultado.equals("Indeferido")) {
						sc.setStatus(StatusSegundaChamada.INDEFERIDO);
						mensagem = "A avaliação de Invalidez do ducumento da solicitação foi cadastrada com sucesso";// Mensagem "Avaliação de Invalida do Secretario Sucesso"
					} else {
						resultado = "INDEFINIDO";
					}
					scdao.editar(sc);
					request.setAttribute("success", mensagem);
					request.setAttribute("id", idSolicitacao);
					javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarSegundaChamada.jsp");
					dispatcher.forward(request, response);
					return;
					
				
			
			
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("id", request.getParameter("button"));
			
		}
	}
}

