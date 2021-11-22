package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.DisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCAlunoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCProfessorDAO;
import br.ufc.russas.n2s.academus.dao.*;
import br.ufc.russas.n2s.academus.dao.JDBCSegundaChamada;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.dao.SegundaChamadaDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.SegundaChamada;
import br.ufc.russas.n2s.academus.model.Email;



public class CadastrarSolicitacaoSegundaChamadaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public CadastrarSolicitacaoSegundaChamadaController() {
		super();
	}
	
	SegundaChamadaDAO scdao = new JDBCSegundaChamada();
	AlunoDAO alunodao = new JDBCAlunoDAO();
	ProfessorDAO professordao = new JDBCProfessorDAO();
	//CoordenadorDAO coordao=nem JDBCCoordenadorDAO();
	DisciplinaDAO discdao = new JDBCDisciplinaDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("professor")!= null &&
		   request.getParameter("nomeAluno") != null &&
		   request.getParameter("disciplina") != null &&
		   request.getParameter("dataDaProva") != null &&
		   request.getParameter("justificativa") != null) {
			
			int idProfessor = Integer.parseInt(request.getParameter("professor"));
			String matricula = request.getParameter("matricula");
			String idDisciplina= request.getParameter("disciplina");
			String dataDaProva= request.getParameter("dataDaProva");
			System.out.println(idProfessor);
			//System.out.println(Date.valueOf(dataDaProva));
			String justificativa= request.getParameter("justificativa");
			
			SegundaChamada segundaChamada =	new SegundaChamada();
			
			Aluno aluno = alunodao.buscarPorMatricula(matricula);
			Professor professor = professordao.buscarPorId(idProfessor);
			//Coordenador prof =coordao.buscarPorId(idProfessor);
			System.out.println(professor.getNome());
			Disciplina disc = discdao.buscarPorId(idDisciplina);
			
			segundaChamada.setAluno(aluno);
			segundaChamada.setDisciplina(disc);
			segundaChamada.setProfessor(professor);
			segundaChamada.setDataProva(Date.valueOf(dataDaProva));
			segundaChamada.setJustificativa(justificativa);
			
			try {
				scdao.cadastro(segundaChamada);
				Thread sendEmail = new Thread(new Email(professor, "Solicitação de Segunda Chamada", "Segunda Chamada",
						"O aluno " + aluno.getNome() + "solicitou a segunda chamada da disciplina"+disc.getNome()));
				sendEmail.start();
				
				request.setAttribute("success", "Cadastro realizado com sucesso.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroSegundaChamada.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "O servidor não conseguiu cadastrar a solicitação.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroSegundaChamada.jsp");
				dispatcher.forward(request, response);
			}
			
		} else {
		
			try {
				//if((request.getParameter("componenteInput") != null))
					//request.setAttribute("erro", "Cadastre pelo menos uma Disciplina Cursada.");
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroSegundaChamada.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
