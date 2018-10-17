package br.ufc.russas.n2s.academus.controller;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.NivelAcademus;

public class ListarSolicitacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ListarSolicitacaoController() {
		super();
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");
			
			SolicitacaoDAO sodao = new DAOFactoryJDBC().criarSolicitacaoDAO();
			List<Solicitacao> listaSol = sodao.listar();
			
			if (per.getNivel() == NivelAcademus.ALUNO) {
				
			} else if(per.getNivel() == NivelAcademus.SECRETARIO) {
				
			} else if(per.getNivel() == NivelAcademus.COORDENADOR) {
				
			} else if(per.getNivel() == NivelAcademus.INDEFINIDO) {
				
			}
			
			request.setAttribute("listaSol", listaSol);
			javax.servlet.RequestDispatcher dispacher = request.getRequestDispatcher("jsp/elements/listagemSolicitacao.jsp");

			dispacher.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
