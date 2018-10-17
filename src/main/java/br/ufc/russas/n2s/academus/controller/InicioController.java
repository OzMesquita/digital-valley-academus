package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;

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
			
			Aluno alu = new Aluno();
			alu.setMatricula("1234");
			//alu.setCurso();
			alu.setNome("Carlitos Eduardicas");
			per.setPessoa(alu);
			System.out.print("ihadihsidhaihsdf");
			per.setNivel(NivelAcademus.ALUNO);
			
			request.setAttribute("tipo", "aluno");
			
			session.setAttribute("usuario", per);
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioAluno.jsp");
			
			dispatcher.forward(request, response);
			/*
			if(per.getPessoa() instanceof Aluno || per.getNivel() == NivelAcademus.ALUNO) {
				System.out.print("aluno hahah");
				
				request.setAttribute("tipo", "aluno");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioAluno.jsp");
				
				dispatcher.forward(request, response);
				
			} else if(per.getPessoa() instanceof Servidor || per.getNivel() == NivelAcademus.SECRETARIO) {
				System.out.print("Secreatria,...,to correndo grande perigo de me parar no tribunal..");
				request.setAttribute("tipo", "secretario");
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioSecretario.jsp");
				
				dispatcher.forward(request, response);
				
			} else if(per.getPessoa() instanceof Coordenador || per.getNivel() == NivelAcademus.COORDENADOR) {
				request.setAttribute("tipo", "coordenador");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioCoordenador.jsp");
				
				dispatcher.forward(request, response);
			
			} else if(per.getNivel() == NivelAcademus.PROFESSOR) {
				request.setAttribute("tipo", "professor");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioProfessor.jsp");
				
				dispatcher.forward(request, response);
				
			} else if (per.getNivel() == NivelAcademus.INDEFINIDO) {
				request.setAttribute("tipo", "indefinido");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("inicio.jsp");
				
				dispatcher.forward(request, response);
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
