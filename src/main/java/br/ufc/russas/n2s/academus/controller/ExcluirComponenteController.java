package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;

public class ExcluirComponenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExcluirComponenteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_componente = Integer.parseInt(request.getParameter("id_componente"));
		String id_matriz = request.getParameter("id_matriz");
		if(request.getParameter("id_componente") != null && request.getParameter("id_matriz") != null){
			
			try {
				ComponenteCurricularDAO ccd = new DAOFactoryJDBC().criarComponenteCurricularDAO();
				System.out.println(id_matriz+ "<- id_matriz; id_componente -> "+id_componente); 
				ccd.excluirComponente(id_componente);
				
				request.setAttribute("button", id_matriz);
				request.setAttribute("sucess2", "Exclusão do componente realizada com sucesso.");
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("VisualizarMatriz");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				request.setAttribute("button", id_matriz);
				request.setAttribute("erro2", "Erro! O componente foi não removido.");
				e.printStackTrace();
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("VisualizarMatriz");
				dispatcher.forward(request, response);
			}
		}
		else{
			try {
				response.sendRedirect("ListarMatrizes");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
