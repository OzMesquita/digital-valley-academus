package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.DisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCAlunoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCProfessorDAO;
import br.ufc.russas.n2s.academus.dao.JDBCRecorrecaoDeProvaDAO;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.dao.RecorrecaoDeProvaDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.RecorrecaoDeProva;




public class CadastroRecorrecaoDeProvaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public CadastroRecorrecaoDeProvaController() {
		super();
	}
	
	RecorrecaoDeProvaDAO rpdao = new JDBCRecorrecaoDeProvaDAO();
	AlunoDAO alunodao = new JDBCAlunoDAO();
	ProfessorDAO professordao = new JDBCProfessorDAO();
	DisciplinaDAO discdao = new JDBCDisciplinaDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("professor")!= null &&
		   request.getParameter("nomeAluno") != null &&
		   request.getParameter("disciplina") != null &&
		   request.getParameter("dataDaProva") != null &&
		   request.getParameter("justificativa") != null &&
		   request.getParameter("horarioDaProva")!= null &&
		   request.getParameter("dataRecebimento")!= null &&
		   request.getParameter("horarioRecebimento")!= null ) {
			
			int idProfessor = Integer.parseInt(request.getParameter("professor"));
			String matricula = request.getParameter("matricula");
			String idDisciplina= request.getParameter("disciplina");
			String dataDaProva= request.getParameter("dataDaProva");
			String horarioDaProva = request.getParameter("horarioDaProva");
			String justificativa= request.getParameter("justificativa");
			String dataRecebimento= request.getParameter("dataRecebimento");
			String horarioRecebimento = request.getParameter("horarioRecebimento");
			
			RecorrecaoDeProva recorrecao =	new RecorrecaoDeProva();
			
			Aluno aluno = alunodao.buscarPorMatricula(matricula);
			Professor professor = professordao.buscarPorId(idProfessor);
			Disciplina disc = discdao.buscarPorId(idDisciplina);
			
			recorrecao.setAluno(aluno);
			recorrecao.setDisciplina(disc);
			recorrecao.setProfessor(professor);
			recorrecao.setDataProva(Date.valueOf(dataDaProva));
			recorrecao.setHorarioDaProva(Time.valueOf(horarioDaProva));
			recorrecao.setJustificativa(justificativa);
			recorrecao.setDataRecebimento(Date.valueOf(dataRecebimento));
			recorrecao.setHorarioRecebimento(Time.valueOf(horarioRecebimento));
			
			try {
				rpdao.cadastro(recorrecao);
				
				request.setAttribute("success", "Cadastro realizado com sucesso.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroRecorrecaoDeProva.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "O servidor não conseguiu cadastrar a solicitação.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroRecorrecaoDeProva.jsp");
				dispatcher.forward(request, response);
			}
			
		} else {
		
			try {
				//if((request.getParameter("componenteInput") != null))
					//request.setAttribute("erro", "Cadastre pelo menos uma Disciplina Cursada.");
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroRecorrecaoDeProva.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
