package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.NivelAcademus;

@WebServlet("/VisualizarSolicitacoes")

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
			
			if (per.getNivel() == NivelAcademus.ALUNO) {
				
			} else if(per.getNivel() == NivelAcademus.SECRETARIO) {
				
			} else if(per.getNivel() == NivelAcademus.COORDENADOR) {
				
			} else if(per.getNivel() == NivelAcademus.INDEFINIDO) {
				
			}
			
			
			javax.servlet.RequestDispatcher dispacher = request.getRequestDispatcher("listagemSolicitacao.jsp");

			dispacher.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
