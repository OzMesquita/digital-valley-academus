package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;

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
			HttpSession session = request.getSession();
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
			
			if (per.getNivel() == NivelAcademus.INDEFINIDO) {
				
				Aluno alu2 = new DAOFactoryJDBC().criarAlunoDAO().buscarPorId(122);
				if(alu2 == null) {
					System.out.print("Nao dem tama");
				}
				System.out.println(alu2.getNome());
				per.setPessoa(alu2);
				per.setNivel(NivelAcademus.ALUNO);
				
				session.setAttribute("usuario", per);
			}
			
			SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
			List<Solicitacao> listaSol = new ArrayList<Solicitacao>();
			
			String tipoSolicitacao = request.getParameter("solicitacao");
			if(tipoSolicitacao == null) {
				System.out.println("Solicitacao é igual a nullo");
			}
			
			if( per.getNivel() == NivelAcademus.ALUNO) {
				if (tipoSolicitacao == null || ( !tipoSolicitacao.equals("andamento") || !tipoSolicitacao.equals("finalizado") )) {
					listaSol = sodao.listar((Aluno)per.getPessoa());
				} else {
					if (tipoSolicitacao.equals("andamento")) {
						//listaSol = sodao.listar((Aluno)per.getPessoa()); precisa do andamento
					} else { //if (tipoSolicitacao.equals("finalizado")) {
						//listaSol = sodao.listar((Aluno)per.getPessoa()); precisa do finalizado
					}
				}
				
			} else if( per.getNivel() == NivelAcademus.SECRETARIO) {
				if (tipoSolicitacao == null || (!tipoSolicitacao.equals("andamento") || !tipoSolicitacao.equals("finalizado"))) {
					listaSol = sodao.listar();
				} else {
					if (tipoSolicitacao.equals("andamento")) {
						//listaSol = sodao.listar(); precisa do andamento
					} else {
						//listaSol = sodao.listar(); precisa do finalizado
					}
				}
			} else if(per.getNivel() == NivelAcademus.COORDENADOR) {
				if (tipoSolicitacao == null || (!tipoSolicitacao.equals("andamento") || !tipoSolicitacao.equals("finalizado"))) {
					listaSol = sodao.listar((Coordenador)per.getPessoa());
				} else {
					if (tipoSolicitacao.equals("andamento")) {
						//listaSol = sodao.listar((Coordenador)per.getPessoa()); precisa do andamento
					} else {
						//listaSol = sodao.listar((Coordenador)per.getPessoa()); precisa do finalizado
					}
				}
			} else if(per.getNivel() == NivelAcademus.PROFESSOR) {
				listaSol = sodao.listar();
				
			} else if (per.getNivel() == NivelAcademus.INDEFINIDO) {
				listaSol = sodao.listar();
			}
			
			if(listaSol == null || listaSol.isEmpty()) {
				System.out.println("Mais problemas");
				listaSol = sodao.listar();
				if(listaSol == null || listaSol.isEmpty()) {
					System.out.println("Não é possivel");
				}
			}
			session.setAttribute("listaSol", listaSol);
			
			if (session.getAttribute("listaSol") == null) {
				System.out.println("Deu erro no inicio");
			}
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
