package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.ComponenteCurricular;

@WebServlet("/ServletCadastroComponente")
public class ComponenteController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	JDBCDisciplinaDAO daoD = new JDBCDisciplinaDAO();
	JDBCComponenteCurricularDAO daoCadastroM = new JDBCComponenteCurricularDAO();

	public ComponenteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("disciplina");
		String natureza = request.getParameter("natureza");
		int matriz = Integer.parseInt(request.getParameter("matriz"));

		Disciplina dis = new Disciplina();
		ComponenteCurricular dm = new ComponenteCurricular();
		
		dis.setId(id);
		dm.setIdMatriz(matriz);
		dm.setNatureza(natureza);
		dm.setDisciplina(daoD.buscarPorId(id));

		daoCadastroM.insereDisciplinaMatriz(dm);

		try {
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("adicionarComponentes.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
