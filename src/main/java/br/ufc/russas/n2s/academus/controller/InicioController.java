package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import model.Servidor;

public class InicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public InicioController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pegar o pessoa do guardiao e colocar em uma pagina de inicio
		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			//PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");
			PerfilAcademus per = new PerfilAcademus();
			/*
			Aluno alu = new Aluno();
			alu.setMatricula("1234");
			alu.setNome("Carlitos Eduardicas");
			per.setPessoa(alu);
			per.setNivel(NivelAcademus.ALUNO);
			/*
			Coordenador cor = new Coordenador();
			cor.setCargo("Coordenador");
			//cor.setCurso(new Curso());
			cor.setNome("Federico Lopes");
			cor.setId(1);
			per.setPessoa(cor);
			per.setNivel(NivelAcademus.COORDENADOR);
			*/
			Aluno alu2 = new DAOFactoryJDBC().criarAlunoDAO().buscarPorId(122);
			if(alu2 == null) {
				System.out.print("Nao dem tama");
			}
			System.out.println(alu2.getNome());
			per.setPessoa(alu2);
			per.setNivel(NivelAcademus.ALUNO);
			
			session.setAttribute("usuario", per);
			
			
			if( per.getNivel() == NivelAcademus.ALUNO) {
				System.out.print("aluno hahah");
				
				request.setAttribute("tipo", "Aluno");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioAluno.jsp");
				
				dispatcher.forward(request, response);
				
			} else if( per.getNivel() == NivelAcademus.SECRETARIO) {
				System.out.print("Secreatria,...,to correndo grande perigo de me parar no tribunal..");
				request.setAttribute("tipo", "Secretario");
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioSecretario.jsp");
				
				dispatcher.forward(request, response);
				
			} else if(per.getNivel() == NivelAcademus.COORDENADOR) {
				request.setAttribute("tipo", "Coordenador");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioCoordenador.jsp");
				
				dispatcher.forward(request, response);
			
			} else if(per.getNivel() == NivelAcademus.PROFESSOR) {
				request.setAttribute("tipo", "Professor");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioProfessor.jsp");
				
				dispatcher.forward(request, response);
				
			} else if (per.getNivel() == NivelAcademus.INDEFINIDO) {
				request.setAttribute("tipo", "indefinido");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("inicio.jsp");
				
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
