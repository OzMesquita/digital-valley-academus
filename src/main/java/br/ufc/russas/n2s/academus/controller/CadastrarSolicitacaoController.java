package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.JDBCComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.JDBCSolicitacaoDAO;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;

public class CadastrarSolicitacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static LocalDate data;
       
    
    public CadastrarSolicitacaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Verificando se n�o possui informa��es nulas
		
		if(	(request.getParameter("componenteInput") != null) &&
			request.getParameterValues("disc-nome") != null &&
			request.getParameterValues("disc-carga") != null &&
			request.getParameterValues("disc-semestre") != null &&
			request.getParameterValues("disc-nota") != null &&
			request.getParameterValues("disc-instituicao") != null &&
			request.getSession().getAttribute("userAcademus") != null){
			
			// Obtendo as irforma��es da pagina cadastroSolicitacao.jsp
			String componente = request.getParameter("componenteInput");
			String[] nomeDisciplinas = request.getParameterValues("disc-nome");
			String[] cargaDisciplinas = request.getParameterValues("disc-carga");
			String[] semestreDisciplinas = request.getParameterValues("disc-semestre");
			String[] notaDisciplinas = request.getParameterValues("disc-nota");
			String[] instituicaoDisciplinas = request.getParameterValues("disc-instituicao");
			PerfilAcademus usuario = (PerfilAcademus) request.getSession().getAttribute("userAcademus");
			ArrayList<DisciplinaCursada> disciplinasCursadas = new ArrayList<DisciplinaCursada>();
			
			ComponenteCurricularDAO ccd = new JDBCComponenteCurricularDAO();
			SolicitacaoDAO sd = new JDBCSolicitacaoDAO();
			Solicitacao solicitacao = new Solicitacao();
			
			// Preenchendo o Array disciplinas cursadas do solicitacao com as disciplinas cursadas do aluno 
			for(int i=0; i<nomeDisciplinas.length; i++){
				disciplinasCursadas.add(new DisciplinaCursada(semestreDisciplinas[i], Float.parseFloat(notaDisciplinas[i]), Integer.parseInt(cargaDisciplinas[i]), nomeDisciplinas[i], instituicaoDisciplinas[i]));
			}
			
			//Checando se a solicita��o com a mesma Disciplina pelo aluno
			Solicitacao solExistente = sd.buscarSolicitacaoExistente(Integer.parseInt(componente), usuario.getId());
			if(solExistente.getStatus() == Status.CANCELADO || solExistente.getStatus() == Status.FINALIZADO) {
			
				// Aranezanando as informacoes no banco de dados
				solicitacao.setSolicitante((Aluno) usuario);
				solicitacao.setDisciplinaAlvo(ccd.buscarPorId(Integer.parseInt(componente)));
				solicitacao.setStatus(Status.SOLICITADO);
				solicitacao.setDisciplinasCursadas(disciplinasCursadas);
				solicitacao.setCurso(((Aluno) usuario).getCurso());
				
				if(disciplinasCursadas.size() > 0){
					sd.cadastrar(solicitacao);
					request.setAttribute("success", "Solicita��o cadastrada com sucesso.");
				} else {
					request.setAttribute("erro", "Ocorreu um erro ao cadastrar a solicita��o.");
				}
			} else {
				request.setAttribute("erro", "Voc� j� possui uma solicita��o com essa Disciplina Alvo");
			}
			
			try {
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroSolicitacao.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else{
			
			try {
				if((request.getParameter("componenteInput") != null))
					request.setAttribute("erro", "Cadastre pelo menos uma Disciplina Cursada.");
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroSolicitacao.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}