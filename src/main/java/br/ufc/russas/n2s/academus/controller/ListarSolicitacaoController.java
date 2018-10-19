package br.ufc.russas.n2s.academus.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
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
			ArrayList<Solicitacao> listaSol = new ArrayList<>();// (ArrayList<Solicitacao>) sodao.listar();
			
			Aluno a = new Aluno();
			a.setNome("Eduardo Costa");
			Aluno b = new Aluno();
			b.setNome(per.getPessoa().getNome());
			
			Solicitacao s = new Solicitacao();
			s.setIdSolicitacao(1);
			s.setStatus(Status.FINALIZADO);
			s.setResultado("Deferido");
			s.setSolicitante(a);
			
			Solicitacao s2 = new Solicitacao();
			s2.setIdSolicitacao(2);
			s2.setStatus(Status.FINALIZADO);
			s2.setResultado("Indeferido");
			s2.setSolicitante(a);
			
			Solicitacao s3 = new Solicitacao();
			s3.setIdSolicitacao(3);
			s3.setStatus(Status.SUBMETIDO);
			s3.setResultado("Analisando");
			s3.setSolicitante(b);
			
			
			listaSol.add(s);
			listaSol.add(s2);
			listaSol.add(s3);
			
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
