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
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Professor;
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
			PerfilAcademus per = (PerfilAcademus) session.getAttribute("userAcademus");

			SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
			List<Solicitacao> listaSol = new ArrayList<Solicitacao>();
			int numSolicitacoes = 0;
			
			String statusSolicitacao = request.getParameter("solicitacao");
			
			if (per.getIsAdmin()) {
				if (statusSolicitacao == null) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(0);
					} else {
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar(pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(pag);
					}
					
				} else if (statusSolicitacao.equals("submetido")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarSubmetida(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesSubmetidas(0);
					} else {
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarSubmetida(pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesSubmetidas(pag);
					}
					
				} else if(statusSolicitacao.equals("analizado")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarAnalizado(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesAnalizadas(0);
					} else {
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarAnalizado(pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesAnalizadas(pag);						
					}
					
				} else if(statusSolicitacao.equals("finalizado")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarFinalizado(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesFinalizadas(0);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarFinalizado(pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesFinalizadas(pag);
					}
					
				} else {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(0);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar(pag*qtdRegPorPag, pag*qtdRegPorPag + qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(pag);
					}
					
				}
			} else if( per.getNivel() == NivelAcademus.ALUNO) {
				
				if (statusSolicitacao == null) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar((Aluno)per, 0 , qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(0, (Aluno)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar((Aluno)per, qtdRegPorPag*pag , qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(pag, (Aluno)per);
					}
					
				} else if (statusSolicitacao.equals("submetido")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarSubmetida((Aluno)per, 0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesSubmetidas(0, (Aluno)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarSubmetida((Aluno)per, qtdRegPorPag*pag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesSubmetidas(pag, (Aluno)per);
					}
					
					
				} else if (statusSolicitacao.equals("andamento")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarAndamento((Aluno)per, 0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesAndamento(0, (Aluno)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarAndamento((Aluno)per, qtdRegPorPag*pag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesAndamento(pag, (Aluno)per);
					}
										
					
				} else if(statusSolicitacao.equals("finalizado")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarFinalizado((Aluno)per, 0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesFinalizadas(0, (Aluno)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarFinalizado((Aluno)per, qtdRegPorPag*pag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesFinalizadas(pag, (Aluno)per);
					}
					
				} else {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar((Aluno)per, 0 , qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(0, (Aluno)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar((Aluno)per, qtdRegPorPag*pag , qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(pag, (Aluno)per);
					}
				}
				
			} else if( per.getNivel() == NivelAcademus.SECRETARIO) {
				if (statusSolicitacao == null) {

					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(0);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar(pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(pag);
					}
					
				} else if (statusSolicitacao.equals("submetido")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarSubmetida(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesSubmetidas(0);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarSubmetida(pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesSubmetidas(pag);
					}
					
				} else if(statusSolicitacao.equals("finalizado")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarFinalizado(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesFinalizadas(0);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarFinalizado(pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesFinalizadas(pag);
					}

				} else {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar(0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(0);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar(pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(pag);
					}
					
				}
			} else if(per.getNivel() == NivelAcademus.COORDENADOR) {
				if (statusSolicitacao == null) {
					
					if(request.getParameter("pagina") == null){
						
						listaSol = sodao.listar((Professor)per, 0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(0, (Professor)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar((Professor)per, pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(pag, (Professor)per);
					}

				} else if (statusSolicitacao.equals("analizado")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarAnalizado((Professor)per, 0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesAnalizadas(0, (Professor)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarAnalizado((Professor)per, pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesAnalizadas(pag, (Professor)per);
					}

				} else if (statusSolicitacao.equals("andamento")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarAndamento((Professor)per, 0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesAndamento(0, (Professor)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarAndamento((Professor)per, pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesAndamento(pag, (Professor)per);
					}
					
				} else if(statusSolicitacao.equals("finalizado")) {
					
					if(request.getParameter("pagina") == null){
						listaSol = sodao.listarFinalizado((Professor)per, 0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesFinalizadas(0, (Professor)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listarFinalizado((Professor)per, pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoesFinalizadas(pag, (Professor)per);
					}
					
				} else {

					if(request.getParameter("pagina") == null){
						listaSol = sodao.listar((Professor)per, 0, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(0, (Professor)per);
					}
					else{
						int pag = Integer.parseInt(request.getParameter("pagina"));
						listaSol = sodao.listar((Professor)per, pag*qtdRegPorPag, qtdRegPorPag);
						numSolicitacoes = sodao.numSolicitacoes(pag, (Professor)per);
					}
					
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
			session.setAttribute("numSolicitacoes", Integer.valueOf(numSolicitacoes));
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
