package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;


public class BotoesVisualizarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BotoesVisualizarController() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			PerfilAcademus per = (PerfilAcademus) session.getAttribute("usuario");
			
			int id = Integer.parseInt((String)request.getAttribute("id"));
			SolicitacaoDAO sd = new DAOFactoryJDBC().criarSolicitacaoDAO();
			Solicitacao sol = sd.buscarPorId(id);
			
			if( per.getNivel() == NivelAcademus.ALUNO) {
				if (Status.getCodigo(Status.SUBMETIDO) == Status.getCodigo(sol.getStatus())) {
					request.setAttribute("Editar", true);
				} else {
					request.setAttribute("Editar", false);
				}
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/elements/botoesVisualizarAluno.jsp");
				dispatcher.forward(request, response);
				return;
				
			} else if( per.getNivel() == NivelAcademus.SECRETARIO) {
				if (Status.getCodigo(Status.VALIDANDO) == Status.getCodigo(sol.getStatus())) {
					request.setAttribute("Avaliar", true);
				} else {
					request.setAttribute("Avaliar", false);
				}
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/elements/botoesVisualizarSecretario.jsp");
				dispatcher.forward(request, response);
				return;
				
			} else if(per.getNivel() == NivelAcademus.COORDENADOR) {
				if (Status.getCodigo(Status.ANALIZANDO) == Status.getCodigo(sol.getStatus())) {
					request.setAttribute("Avaliar", true);
				} else {
					request.setAttribute("Avaliar", false);
				}
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/elements/botoesVisualizarCoordenador.jsp");
				dispatcher.forward(request, response);
				return;
				
			} else if(per.getNivel() == NivelAcademus.PROFESSOR) {
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
				
			} else if (per.getNivel() == NivelAcademus.INDEFINIDO) {
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
				return;
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
