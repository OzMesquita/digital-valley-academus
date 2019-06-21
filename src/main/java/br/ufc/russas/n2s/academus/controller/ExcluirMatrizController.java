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
					boolean deuCerto = daoMat.excluir(mat);
					if(deuCerto)
						request.setAttribute("success", "Matriz exclu�da com sucesso.");
					else
						request.setAttribute("erro", "N�o � possivel fazer a exclus�o, por ter pelo menos uma solicita��o ligada a matriz.");
				} else {
					request.setAttribute("erro", "A Matriz est� sendo utilizada, � necess�rio desativa-la para concluir essa a��o.");
				}
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaMatrizes.jsp");
				dispatcher.forward(request, response);
			
			} catch (Exception e) {
				request.setAttribute("erro", "Erro! N�o foi poss�vel excluir a Matriz.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaMatrizes.jsp");
				dispatcher.forward(request, response);
				
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("ListarMatrizes");
		}
	}

}
