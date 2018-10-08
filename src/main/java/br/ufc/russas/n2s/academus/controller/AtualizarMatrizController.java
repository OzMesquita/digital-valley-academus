package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;
import br.ufc.russas.n2s.academus.modelo.ComponenteCurricular;
import br.ufc.russas.n2s.academus.modelo.Disciplina;
import br.ufc.russas.n2s.academus.modelo.MatrizCurricular;

/**
 * Servlet implementation class AtualizarMatrizController
 */
@WebServlet("/AtualizarMatrizController")
public class AtualizarMatrizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JDBCMatrizCurricularDAO daoMatriz = new JDBCMatrizCurricularDAO();
	
    public AtualizarMatrizController() {
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
		if (request.getParameter("button") == null && request.getParameter("id_matriz") != null) {
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
				while(request.getParameter("comp-id-"+i) != null) {
					String compId = request.getParameter("comp-id-"+i);//recuperando id da disciplina add como componente
					String compNatureza = request.getParameter("comp-natureza-"+i);// recuperando natureza da disciplina add como componente
					String[] compPreRequisitos = (request.getParameter("comp-preRequisitos-"+i) != "-") ? request.getParameter("comp-preRequisitos-"+i).split(";"): new String[0];
					ArrayList<Disciplina> prObjs = new ArrayList<Disciplina>();
					Disciplina disciplina = ddao.buscarPorId(compId);
					for(String pr : compPreRequisitos){
						prObjs.add(ddao.buscarPorId(pr));
					}
					compObjs.add(new ComponenteCurricular(id, disciplina, compNatureza, prObjs));
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
				
			
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaMatrizes.jsp");			
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				response.sendRedirect("VisualizarMatrizes");
			}
			
			
			
		} else {
			try {
				String ans = request.getParameter("button");
				request.setAttribute("id", ans);
				//System.out.println(ans);
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("EditarMatrizTeste.jsp");
				
				dispatcher.forward(request, response);
				
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}

}