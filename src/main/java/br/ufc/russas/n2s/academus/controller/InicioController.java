package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import model.Servidor;


@WebServlet("/Inicio")

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
			//HttpSession session = ((HttpServletRequest) request).getSession();
			//PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");
			PerfilAcademus per = new PerfilAcademus();
			
			Aluno alu = new Aluno();
			alu.setMatricula("1234");
			//alu.setCurso();
			alu.setNome("Carlitos Eduardicas");
			per.setPessoa(alu);
			System.out.print("ihadihsidhaihsdf");
			per.setNivel(NivelAcademus.ALUNO);
			
			
			if(per.getPessoa() instanceof Aluno || per.getNivel() == NivelAcademus.ALUNO) {
				System.out.print("aluno hahah");
				//ArrayList<Solicitacao> soli = new ArrayList(); nao sei se coloco as solicitacoes aqui ou em outro controller(ListarSolicitacoes(Melhor l�))
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioAluno.jsp");
				
				dispatcher.forward(request, response);
				
			} else if(per.getPessoa() instanceof Servidor || per.getNivel() == NivelAcademus.SECRETARIO) {
				System.out.print("Secreatria,...,to correndo grande perigo de me parar no tribunal..");
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioSecretario.jsp");
				
				dispatcher.forward(request, response);
				
			} else if(per.getPessoa() instanceof Coordenador || per.getNivel() == NivelAcademus.COORDENADOR) {
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioCoordenador.jsp");
				
				dispatcher.forward(request, response);
			
			} else if(per.getNivel() == NivelAcademus.PROFESSOR) {
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("InicioProfessor.jsp");
				
				dispatcher.forward(request, response);
				
			} else if (per.getNivel() == NivelAcademus.INDEFINIDO) {
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("inicio.jsp");
				
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
