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
		int qtdRegPorPag = 10;
		
		try {
			HttpSession session = request.getSession();
			PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");

						
			SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
			List<Solicitacao> listaSol = new ArrayList<Solicitacao>();
			
			String tipoSolicitacao = request.getParameter("solicitacao");
			
			if (per.getIsAdmin()) {
				if (tipoSolicitacao == null) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar(0, qtdRegPorPag);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar(pag*qtdRegPorPag, qtdRegPorPag);
					}
					
				} else if (tipoSolicitacao.equals("submetido")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarSubmetida(0, qtdRegPorPag);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarSubmetida(pag*qtdRegPorPag, qtdRegPorPag);
					}
					
				} else if (tipoSolicitacao.equals("validado")) {
					listaSol = sodao.listarValidada();
				} else if(tipoSolicitacao.equals("analizado")) {
					listaSol = sodao.listarAnalizado();
				} else if(tipoSolicitacao.equals("finalizado")) {
					listaSol = sodao.listarFinalizado();
				} else {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar(0, qtdRegPorPag);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar(pag*qtdRegPorPag, pag*qtdRegPorPag + qtdRegPorPag);
					}
					
				}
			} else if( per.getNivel() == NivelAcademus.ALUNO) {
				
				if (tipoSolicitacao == null) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar((Aluno)per.getPessoa(), 0 , qtdRegPorPag);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar((Aluno)per.getPessoa(), qtdRegPorPag*pag , qtdRegPorPag);
					}
					
				} else if (tipoSolicitacao.equals("submetido")) {
					listaSol = sodao.listarSubmetida((Aluno)per.getPessoa());
				} else if (tipoSolicitacao.equals("andamento")) {
					listaSol = sodao.listarAndamento((Aluno)per.getPessoa());
				} else if(tipoSolicitacao.equals("finalizado")) {
					listaSol = sodao.listarFinalizado((Aluno)per.getPessoa());
				} else {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar((Aluno)per.getPessoa(), 0 , qtdRegPorPag);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar((Aluno)per.getPessoa(), qtdRegPorPag*pag , qtdRegPorPag);
					}
				}
				
			} else if( per.getNivel() == NivelAcademus.SECRETARIO) {
				if (tipoSolicitacao == null) {

					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar(0, qtdRegPorPag);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar(pag*qtdRegPorPag, qtdRegPorPag);
					}
					
				} else if (tipoSolicitacao.equals("submetido")) {
					listaSol = sodao.listarSubmetida();
				} else if (tipoSolicitacao.equals("validado")) {
					listaSol = sodao.listarValidada();
				} else if(tipoSolicitacao.equals("finalizado")) {
					listaSol = sodao.listarFinalizado();
				} else {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar(0, qtdRegPorPag);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar(pag*qtdRegPorPag, qtdRegPorPag);
					}
					
				}
			} else if(per.getNivel() == NivelAcademus.COORDENADOR) {
				if (tipoSolicitacao == null) {
					listaSol = sodao.listar((Coordenador)per.getPessoa());
				} else if (tipoSolicitacao.equals("analizado")) {
					listaSol = sodao.listarAnalizado((Coordenador)per.getPessoa());
				} else if (tipoSolicitacao.equals("andamento")) {
					listaSol = sodao.listarAndemanto((Coordenador)per.getPessoa());
				} else if(tipoSolicitacao.equals("finalizado")) {
					listaSol = sodao.listarFinalizado((Coordenador)per.getPessoa());
				} else {
					listaSol = sodao.listar((Coordenador)per.getPessoa());
				}
			} else if(per.getNivel() == NivelAcademus.PROFESSOR) {
				//listaSol = sodao.listar();
				
			} else if (per.getNivel() == NivelAcademus.INDEFINIDO) {
				//listaSol = sodao.listar();
			}
			
			if(listaSol == null) {
				listaSol = new ArrayList<Solicitacao>();
			}
			session.setAttribute("listaSol", listaSol);
			
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
