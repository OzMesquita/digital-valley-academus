package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;

@WebServlet("/CadastrarMatriz") 
public class CadastrarMatrizController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	JDBCMatrizCurricularDAO daoCadastroMatriz = new JDBCMatrizCurricularDAO();

	public CadastrarMatrizController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String periodoLetivo = request.getParameter("periodo_letivo");
		int cargaHoraria = Integer.parseInt(request.getParameter("carga_horaria"));
		int prazoMinimo = Integer.parseInt(request.getParameter("prazo_minimo"));
		int prazoMaximo = Integer.parseInt(request.getParameter("prazo_maximo"));
		boolean vigente = Boolean.parseBoolean((request.getParameter("vigente"))); 
		boolean ativo = Boolean.parseBoolean((request.getParameter("ativo"))); 
		int curso = Integer.parseInt(request.getParameter("id_curso"));
		

		MatrizCurricular ma = new MatrizCurricular();
		
		ma.setNome(nome);
		ma.setPeriodoLetivo(periodoLetivo);
		ma.setCarga(cargaHoraria);
		ma.setPrazoMinimo(prazoMinimo);
		ma.setPrazoMaximo(prazoMaximo);
		ma.setVigente(vigente);
		ma.setAtivo(ativo);
		ma.setIdCurso(curso);
		
		daoCadastroMatriz.insereMatriz(ma);

		try {
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroMatriz.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
