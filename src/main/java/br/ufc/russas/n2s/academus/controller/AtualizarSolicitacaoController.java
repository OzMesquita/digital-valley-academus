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
			int id = Integer.parseInt(request.getParameter("button"));
			String componente = request.getParameter("componenteInput");
			String[] nomeDisciplinas = request.getParameterValues("disc-nome");
			String[] cargaDisciplinas = request.getParameterValues("disc-carga");
			String[] semestreDisciplinas = request.getParameterValues("disc-semestre");
			String[] notaDisciplinas = request.getParameterValues("disc-nota");
			String[] instituicaoDisciplinas = request.getParameterValues("disc-instituicao");
			PerfilAcademus usuario = (PerfilAcademus) request.getSession().getAttribute("usuario");
			ArrayList<DisciplinaCursada> disciplinasCursadas = new ArrayList<DisciplinaCursada>();
			
			if(nomeDisciplinas != null) {
				for(int i=0; i<nomeDisciplinas.length; i++){
					
					disciplinasCursadas.add(new DisciplinaCursada(semestreDisciplinas[i], Float.parseFloat(notaDisciplinas[i]), Integer.parseInt(cargaDisciplinas[i]), nomeDisciplinas[i], instituicaoDisciplinas[i]));
					
				}
			
				ComponenteCurricularDAO ccd = new JDBCComponenteCurricularDAO();
				SolicitacaoDAO sd = new JDBCSolicitacaoDAO();
				DisciplinaCursadaDAO dcd = new JDBCDisciplinaCursadaDAO();
				
				Solicitacao solicitacao = sd.buscarPorId(id);
				
				if (solicitacao.getSolicitante().getId() == usuario.getPessoa().getId()) {
					if(componente != null) {
						solicitacao.setDisciplinaAlvo(ccd.buscarPorId(Integer.parseInt(componente)));
					}
					solicitacao.setStatus(Status.SUBMETIDO);
					solicitacao.setResultado("");
					solicitacao.setJustificativa("");
					solicitacao.setDisciplinasCursadas(disciplinasCursadas);
					
					sd.editar(solicitacao);
					dcd.excluir(solicitacao);
					dcd.cadastrar(disciplinasCursadas, solicitacao.getIdSolicitacao());
					
					
					System.out.println("possivel realizar a solicitacao");
				} else {
					System.out.println("Não foi possivel realizar a solicitacao");
				}
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
				
				dispatcher.forward(request, response);
				
			} else {
				request.setAttribute("id", id);
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("EditarSolicitacao");
				
				dispatcher.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
