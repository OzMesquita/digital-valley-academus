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
import br.ufc.russas.n2s.academus.dao.SegundaChamadaDAO;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.SegundaChamada;

public class HistoricoSegundaChamadaController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public HistoricoSegundaChamadaController() {
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
			
			SegundaChamadaDAO scdao = new DAOFactoryJDBC().criarSegundaChamadaDAO();
			List<SegundaChamada> listaSC = new ArrayList<>();
			int numSolicitacoes = 0;
			
			if(perfilAcademus.getIsAdmin() || perfilAcademus.getNivel() == NivelAcademus.SECRETARIO) {
				
				if(request.getParameter("pagina") == null) {
					
					listaSC = scdao.listar(0, qtdRegPorPag);
					numSolicitacoes = scdao.numSolicitacoes(0);
					
				} else {
					
					int pag = Integer.parseInt(request.getParameter("pagina"));
					listaSC = scdao.listar(pag*qtdRegPorPag, qtdRegPorPag);
					numSolicitacoes = scdao.numSolicitacoes(pag);
				}
				
			} else if (perfilAcademus.getNivel() == NivelAcademus.ALUNO) {
				
				if(request.getParameter("pagina") == null) {
					
					listaSC = scdao.listar((Aluno) perfilAcademus, 0, qtdRegPorPag);
					numSolicitacoes = scdao.numSolicitacoes(0);
					
				} else {
					
					int pag = Integer.parseInt(request.getParameter("pagina"));
					listaSC = scdao.listar((Aluno) perfilAcademus, pag*qtdRegPorPag, qtdRegPorPag);
					numSolicitacoes = scdao.numSolicitacoes(pag);
					
				}
				
			} else if (perfilAcademus.getNivel() == NivelAcademus.PROFESSOR) {
				if(request.getParameter("pagina") == null) {
					
					listaSC = scdao.listar((Professor) perfilAcademus, 0, qtdRegPorPag);
					numSolicitacoes = scdao.numSolicitacoes(0);
					
				} else {
					
					int pag = Integer.parseInt(request.getParameter("pagina"));
					listaSC = scdao.listar((Professor) perfilAcademus, pag*qtdRegPorPag, qtdRegPorPag);
					numSolicitacoes = scdao.numSolicitacoes(pag);
					
				}
			} else if (perfilAcademus.getNivel() == NivelAcademus.COORDENADOR) {
				if(request.getParameter("pagina") == null) {
					
					listaSC = scdao.listarCoordenador((Professor) perfilAcademus, 0, qtdRegPorPag);
					numSolicitacoes = scdao.numSolicitacoes(0);
					
				} else {
					
					int pag = Integer.parseInt(request.getParameter("pagina"));
					listaSC = scdao.listarCoordenador((Professor) perfilAcademus, pag*qtdRegPorPag, qtdRegPorPag);
					numSolicitacoes = scdao.numSolicitacoes(pag);
					
				}
			}
			
			if(listaSC == null) {
				listaSC = new ArrayList<SegundaChamada>();
			}
			session.setAttribute("listaSC", listaSC);
			session.setAttribute("numSolicitacoes", Integer.valueOf(numSolicitacoes));
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("historicoSegundaChamada.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
