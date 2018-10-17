package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;
import br.ufc.russas.n2s.academus.model.ComponenteCurricular;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;


public class AtualizarMatrizController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JDBCMatrizCurricularDAO daoMatriz = new JDBCMatrizCurricularDAO();
	
    public AtualizarMatrizController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id =  Integer.parseInt(request.getParameter("id_matriz"));
		String nome = request.getParameter("nome");
		String periodoLetivo = request.getParameter("periodo_letivo");
		int cargaHoraria = Integer.parseInt(request.getParameter("carga_horaria"));
		int prazoMinimo = Integer.parseInt(request.getParameter("prazo_minimo"));
		int prazoMaximo = Integer.parseInt(request.getParameter("prazo_maximo"));
		boolean vigente = Boolean.parseBoolean((request.getParameter("vigente"))); 
		boolean ativo = Boolean.parseBoolean((request.getParameter("ativo"))); 
		int curso = Integer.parseInt(request.getParameter("id_curso"));
		
		String[] discIds = request.getParameterValues("listaComponentes");
		ArrayList<ComponenteCurricular> compObjs = new ArrayList<ComponenteCurricular>();
		
		if(discIds != null) {
			//Disciplina[] discObjs = new Disciplina[discIds.length];
			JDBCDisciplinaDAO ddao = new JDBCDisciplinaDAO();
			for(int i=0;i<discIds.length;i++) {
				String cod = discIds[i].substring(0, 7);
				String natureza = discIds[i].split("-")[2];
				Disciplina discObj = ddao.buscarPorId(cod);
				ComponenteCurricular cc = new ComponenteCurricular(id, discObj, natureza);
				compObjs.add(cc);
			}
		}
		
		MatrizCurricular ma = new MatrizCurricular();
		
		ma.setIdMatriz(id);
		ma.setNome(nome);
		ma.setPeriodoLetivo(periodoLetivo);
		ma.setCarga(cargaHoraria);
		ma.setPrazoMinimo(prazoMinimo);
		ma.setPrazoMaximo(prazoMaximo);
		ma.setVigente(vigente);
		ma.setAtivo(ativo);
		ma.setIdCurso(curso);
		
		daoMatriz.atualizarMatriz(ma);
		daoMatriz.gerenciarComponentes(compObjs, id);
		
		try {
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaMatrizes.jsp");			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}