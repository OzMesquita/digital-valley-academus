package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.DisciplinaCursadaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaCursadaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCSolicitacaoDAO;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;


public class AtualizarSolicitacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AtualizarSolicitacaoController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// Pegando as informa��es da pagina EditarSolicita��o.jsp
			
			int id = Integer.parseInt(request.getParameter("button"));
			String componente = request.getParameter("componenteInput");
			String[] nomeDisciplinas = request.getParameterValues("disc-nome");
			String[] cargaDisciplinas = request.getParameterValues("disc-carga");
			String[] semestreDisciplinas = request.getParameterValues("disc-semestre");
			String[] notaDisciplinas = request.getParameterValues("disc-nota");
			String[] instituicaoDisciplinas = request.getParameterValues("disc-instituicao");
			PerfilAcademus usuario = (PerfilAcademus) request.getSession().getAttribute("userAcademus");
			ArrayList<DisciplinaCursada> disciplinasCursadas = new ArrayList<DisciplinaCursada>();
			
			// Verificando se possui pelo menos uma disciplina cursada na solicitacao 
			if(nomeDisciplinas != null) {
				for(int i=0; i<nomeDisciplinas.length; i++){
					
					disciplinasCursadas.add(new DisciplinaCursada(semestreDisciplinas[i], Float.parseFloat(notaDisciplinas[i]), Integer.parseInt(cargaDisciplinas[i]), nomeDisciplinas[i], instituicaoDisciplinas[i]));
					
				}
			
				ComponenteCurricularDAO ccd = new JDBCComponenteCurricularDAO();
				SolicitacaoDAO sd = new JDBCSolicitacaoDAO();
				DisciplinaCursadaDAO dcd = new JDBCDisciplinaCursadaDAO();
				
				Solicitacao solicitacao = sd.buscarPorId(id);
				
				// Verificando se a solicita��o � do usu�rio que est� logado
				if (solicitacao.getSolicitante().getId() == usuario.getId()) {
					if(componente != null) {
						solicitacao.setDisciplinaAlvo(ccd.buscarPorId(Integer.parseInt(componente)));
					}
					solicitacao.setStatus(Status.SOLICITADO);
					solicitacao.setDisciplinasCursadas(disciplinasCursadas);
					
					// colocando as informa��es no banco
					// solifita��o
					sd.editar(solicitacao);
					// as diciplinas cursadas de solicita�ao
					dcd.excluir(solicitacao);
					dcd.cadastrar(disciplinasCursadas, solicitacao.getIdSolicitacao());
					
					
					request.setAttribute("mensagem", "ES"); // Mensagem de concluss�o com sucesso
				} else {
					request.setAttribute("mensagem", "EN");// Mensagem de erro, usu�rio n�o � dono da solicita��o
				}
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("redirect.jsp");
				
				dispatcher.forward(request, response);
				
			} else {
				request.setAttribute("id", id);
				request.setAttribute("mensagem", "NS"); // Mensagem de opera��o por n�o ter disciplinas cursadas
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("EditarSolicitacao");
				
				dispatcher.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
