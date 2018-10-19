package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.HistoricoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.JDBCHistoricoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCSolicitacaoDAO;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.Historico;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;
import model.Pessoa;

@WebServlet("/ServletCadastroSolicitacao")
public class CadastrarSolicitacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static LocalDate data;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarSolicitacaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String componente = request.getParameter("componenteInput");
		String instituicao = request.getParameter("instituicaoInput");
		String[] nomeDisciplinas = request.getParameterValues("disc-nome");
		String[] cargaDisciplinas = request.getParameterValues("disc-carga");
		String[] semestreDisciplinas = request.getParameterValues("disc-semestre");
		String[] notaDisciplinas = request.getParameterValues("disc-nota");
		Aluno aluno = (Aluno) request.getSession().getAttribute("Usuario");
		System.out.println("teste================================================================");
		ArrayList<DisciplinaCursada> disciplinasCursadas = new ArrayList<DisciplinaCursada>();
		ComponenteCurricularDAO ccd = new JDBCComponenteCurricularDAO();
		JDBCHistoricoDAO hd = new JDBCHistoricoDAO();
		SolicitacaoDAO sd = new JDBCSolicitacaoDAO();
		Solicitacao solicitacao = new Solicitacao();
		
		for(int i=0; i<nomeDisciplinas.length; i++){
			disciplinasCursadas.add(new DisciplinaCursada(semestreDisciplinas[i], Float.parseFloat(notaDisciplinas[i]), Integer.parseInt(cargaDisciplinas[i]), nomeDisciplinas[i]));
		}
		
		solicitacao.setSolicitante(aluno);
		solicitacao.setDisciplinaAlvo(ccd.buscarPorId(Integer.parseInt(componente)));
		solicitacao.setInstituicao(instituicao);
		solicitacao.setStatus(Status.SUBMETIDO);
		solicitacao.setDisciplinasCursadas(disciplinasCursadas);
		
		sd.cadastrar(solicitacao);
		
		if(disciplinasCursadas.size() > 0){
			Historico historico = new Historico();
			historico.setData(LocalDate.now());
			historico.setHorario(LocalTime.now());
			//historico.setResponsavel(aluno);
			//historico.gerarDescricao(1);
			
			//int id = hd.ultimaSolicitacao();
			//hd.cadastrar(historico, id);
		}
		
		try {
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroSolicitacao.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean verificarSemestre(String semestre){
		String[] valores = semestre.split(".");
		if((1900 >= Integer.parseInt(valores[0]) && data.getYear() <= Integer.parseInt(valores[0])) && 
				(0 > Integer.parseInt(valores[1]) && 2 <= Integer.parseInt(valores[1]))){
			return true;
		}
		return false;
	}
}