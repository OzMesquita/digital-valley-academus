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
import br.ufc.russas.n2s.academus.model.Natureza;

public class AtualizarMatrizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JDBCMatrizCurricularDAO daoMatriz = new JDBCMatrizCurricularDAO();
	
    public AtualizarMatrizController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id =  Integer.parseInt(request.getParameter("id_matriz"));
			String nome = request.getParameter("nome");
			String periodoLetivo = request.getParameter("periodo_letivo");
			int cargaHoraria = Integer.parseInt(request.getParameter("carga_horaria"));
			int prazoMinimo = Integer.parseInt(request.getParameter("prazo_minimo"));
			int prazoMaximo = Integer.parseInt(request.getParameter("prazo_maximo"));
			boolean vigente = Boolean.parseBoolean((request.getParameter("vigente"))); 
			boolean ativo = Boolean.parseBoolean((request.getParameter("ativo"))); 
			int curso = Integer.parseInt(request.getParameter("id_curso"));
				
			
			int i = 0;
			JDBCDisciplinaDAO ddao = new JDBCDisciplinaDAO();
			ArrayList<ComponenteCurricular> compObjs = new ArrayList<ComponenteCurricular>();
			String[] compIds = request.getParameterValues("comp-id");
			String[] compNaturezas = request.getParameterValues("comp-natureza");
			String[] compPreRequisitos = request.getParameterValues("comp-preRequisitos");
				
			for(i = 0; i < compIds.length; i++) {
					
				String cod = compIds[i];
				Disciplina dis = ddao.buscarPorId(cod);
				Natureza natureza = Natureza.getNatureza(compNaturezas[i]);
				String preReqs = compPreRequisitos[i];
				ArrayList<Disciplina> DisciplinaPreReq = new ArrayList<Disciplina>();
				if(!preReqs.equals("-")) {
					String[] preRequisito = preReqs.split(";");
					for(int j = 0; j < preRequisito.length;j++) {
						System.out.println(preRequisito[j]);
						DisciplinaPreReq.add(ddao.buscarPorId(preRequisito[j]));
					}
				}
				ComponenteCurricular cc = new ComponenteCurricular();
				cc.setIdComponente(id);
				cc.setDisciplina(dis);
				cc.setNatureza(natureza);
				cc.setPreRequisitos(DisciplinaPreReq);
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
			ma.setComponentes(compObjs);
				
			daoMatriz.editar(ma);
				
			/*
			while(request.getParameter("comp-id-"+i) != null) {
				 
				String compId = request.getParameter("comp-id-"+i);//recuperando id da disciplina add como componente
				Natureza compNatureza = Natureza.getNatureza(request.getParameter("comp-natureza-"+i));// recuperando natureza da disciplina add como componente
				String[] compPreRequisitos = (request.getParameter("comp-preRequisitos-"+i) != "-") ? request.getParameter("comp-preRequisitos-"+i).split(";"): new String[0];
				ArrayList<Disciplina> prObjs = new ArrayList<Disciplina>();
				Disciplina disciplina = ddao.buscarPorId(compId);
				for(String pr : compPreRequisitos){
					prObjs.add(ddao.buscarPorId(pr));
				}
				ComponenteCurricular cc = new ComponenteCurricular();
				cc.setIdComponente(id);
				cc.setDisciplina(disciplina);
				cc.setNatureza(compNatureza);
				cc.setPreRequisitos(prObjs);
				compObjs.add(cc);
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
			ma.setComponentes(compObjs);
			
			
			//daoMatriz.editar(ma);
			System.out.println("Deu certo!");
			*/
			request.setAttribute("mensagem","MS");
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("ListarMatrizes");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("mensagem","MN");
			response.sendRedirect("ListarMatriz");
		}
	}
}