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
import br.ufc.russas.n2s.academus.dao.RecorrecaoDeProvaDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.RecorrecaoDeProva;
import br.ufc.russas.n2s.academus.model.StatusRecorrecao;

public class HistoricoRecorrecaoDeProvaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public HistoricoRecorrecaoDeProvaController() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qtdRegPorPag = 10;
		
		try {
			
			HttpSession session = request.getSession();
			PerfilAcademus perfilAcademus = (PerfilAcademus) session.getAttribute("userAcademus");
			
			RecorrecaoDeProvaDAO rpdao = new DAOFactoryJDBC().criarRecorrecaoDeProvaDAO();
			
			List<RecorrecaoDeProva> listaRP = new ArrayList<RecorrecaoDeProva>();
			int numSolicitacoes = 0;
			String tipoSolicitacao = request.getParameter("tipoSolicitacao");
			
			if(perfilAcademus.getIsAdmin() || perfilAcademus.getNivel() == NivelAcademus.SECRETARIO) {
				
				if(tipoSolicitacao == null) {
					
					try {
						
						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listar(0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(0);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listar(pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(pag);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "solicitado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus(StatusRecorrecao.SOLICITADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, StatusRecorrecao.SOLICITADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus(StatusRecorrecao.SOLICITADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, StatusRecorrecao.SOLICITADO);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "finalizado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus(StatusRecorrecao.FINALIZADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, StatusRecorrecao.FINALIZADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus(StatusRecorrecao.FINALIZADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, StatusRecorrecao.FINALIZADO);
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				}
				
			} else if (perfilAcademus.getNivel() == NivelAcademus.ALUNO) {
				
				if(tipoSolicitacao == null) {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listar((Aluno) perfilAcademus, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(0, (Aluno) perfilAcademus);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listar((Aluno) perfilAcademus, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(pag, (Aluno) perfilAcademus);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "solicitado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus((Aluno) perfilAcademus, StatusRecorrecao.SOLICITADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, (Aluno) perfilAcademus, StatusRecorrecao.SOLICITADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus((Aluno) perfilAcademus, StatusRecorrecao.SOLICITADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, (Aluno) perfilAcademus, StatusRecorrecao.SOLICITADO);
						}
						
					} catch (NullPointerException e) {
						
					}
					
				} else if(tipoSolicitacao == "finalizado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus((Aluno) perfilAcademus, StatusRecorrecao.FINALIZADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, (Aluno) perfilAcademus, StatusRecorrecao.FINALIZADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus((Aluno) perfilAcademus, StatusRecorrecao.FINALIZADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, (Aluno) perfilAcademus, StatusRecorrecao.FINALIZADO);
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				}

			} else if (perfilAcademus.getNivel() == NivelAcademus.PROFESSOR) {
				
				if(tipoSolicitacao == null) {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listar((Professor) perfilAcademus, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(0, (Professor) perfilAcademus);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listar((Professor) perfilAcademus, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(pag, (Professor) perfilAcademus);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "solicitado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus((Professor) perfilAcademus, StatusRecorrecao.SOLICITADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, (Professor) perfilAcademus, StatusRecorrecao.SOLICITADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus((Professor) perfilAcademus, StatusRecorrecao.SOLICITADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, (Professor) perfilAcademus, StatusRecorrecao.SOLICITADO);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "finalizado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus((Professor) perfilAcademus, StatusRecorrecao.FINALIZADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, (Professor) perfilAcademus, StatusRecorrecao.FINALIZADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus((Professor) perfilAcademus, StatusRecorrecao.FINALIZADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, (Professor) perfilAcademus, StatusRecorrecao.FINALIZADO);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				}

			} else if (perfilAcademus.getNivel() == NivelAcademus.COORDENADOR) {
				
				if(tipoSolicitacao == null) {
					System.out.println("listarHCoor");
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listar((Professor) perfilAcademus, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(0, (Professor) perfilAcademus);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listar((Professor) perfilAcademus, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(pag, (Professor) perfilAcademus);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "solicitado") {
					System.out.println("lista");
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus((Professor) perfilAcademus, StatusRecorrecao.SOLICITADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, (Professor) perfilAcademus, StatusRecorrecao.SOLICITADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus((Professor) perfilAcademus, StatusRecorrecao.SOLICITADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, (Professor) perfilAcademus, StatusRecorrecao.SOLICITADO);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "finalizado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus((Professor) perfilAcademus, StatusRecorrecao.FINALIZADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, (Professor) perfilAcademus, StatusRecorrecao.FINALIZADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus((Professor) perfilAcademus, StatusRecorrecao.FINALIZADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, (Professor) perfilAcademus, StatusRecorrecao.FINALIZADO);
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				}

			} else {
				if(tipoSolicitacao == null) {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listar(0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(0);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listar(pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoes(pag);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "solicitado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus(StatusRecorrecao.SOLICITADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, StatusRecorrecao.SOLICITADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus(StatusRecorrecao.SOLICITADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, StatusRecorrecao.SOLICITADO);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				} else if(tipoSolicitacao == "finalizado") {
					
					try {

						if(request.getParameter("pagina") == null) {
							listaRP = rpdao.listarPorStatus(StatusRecorrecao.FINALIZADO, 0, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(0, StatusRecorrecao.FINALIZADO);
						} else {
							int pag = Integer.parseInt(request.getParameter("pagina"));
							listaRP = rpdao.listarPorStatus(StatusRecorrecao.FINALIZADO, pag*qtdRegPorPag, qtdRegPorPag);
							numSolicitacoes = rpdao.numSolicitacoesPorStatus(pag, StatusRecorrecao.FINALIZADO);
						}
						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
				}
			}
			
			
			session.setAttribute("listaRP", listaRP);
			session.setAttribute("numSolicitacoes", Integer.valueOf(numSolicitacoes));
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("historicoRecorrecaoDeProva.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}