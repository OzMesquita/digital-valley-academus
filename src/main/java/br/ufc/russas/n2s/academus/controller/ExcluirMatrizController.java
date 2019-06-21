package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;
import br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;

public class ExcluirMatrizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExcluirMatrizController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("button") != null){
			try {
				int idMatriz = Integer.parseInt(request.getParameter("button"));
				
				MatrizCurricularDAO daoMat = new JDBCMatrizCurricularDAO();
				MatrizCurricular mat = daoMat.buscarPorId(idMatriz);
				
				if(daoMat.verificarSeEstaSendoUtilizada(idMatriz) == false){
					daoMat.excluir(mat);
					request.setAttribute("success", "A Matriz excluída com sucesso.");
				} else {
					request.setAttribute("erro", "A Matriz está sendo utilizada, desativi-a para poder remover.");
				}
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaMatrizes.jsp");
				dispatcher.forward(request, response);
			
			} catch (Exception e) {
				request.setAttribute("erro", "Erro! Não foi possível excluir a Matriz.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaMatrizes.jsp");
				dispatcher.forward(request, response);
				
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("ListarMatrizes");
		}
	}

}
