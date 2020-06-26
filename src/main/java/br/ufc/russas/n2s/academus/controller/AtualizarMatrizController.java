package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;

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
				String id = request.getParameter("id_matriz");
				request.setAttribute("id", id);
				
				String nome = request.getParameter("nome");
				String periodoLetivo = request.getParameter("periodo_letivo");
				int cargaHoraria = Integer.parseInt(request.getParameter("carga_horaria"));
				int prazoMinimo = Integer.parseInt(request.getParameter("prazo_minimo"));
				int prazoMaximo = Integer.parseInt(request.getParameter("prazo_maximo"));
				boolean vigente = Boolean.parseBoolean((request.getParameter("vigente"))); 
				boolean ativo = Boolean.parseBoolean((request.getParameter("ativo"))); 
				int curso = Integer.parseInt(request.getParameter("id_curso"));
				
				if(vigente) {
					daoMatriz.editarVigente(curso);
					ativo = true;
				}
				
				MatrizCurricular matriz = new MatrizCurricular();
				matriz.setIdMatriz(Integer.parseInt(id));
				matriz.setNome(nome);
				matriz.setPeriodoLetivo(periodoLetivo);
				matriz.setCarga(cargaHoraria);
				matriz.setPrazoMaximo(prazoMaximo);
				matriz.setPrazoMinimo(prazoMinimo);
				matriz.setVigente(vigente);
				matriz.setAtivo(ativo);
				matriz.setIdCurso(curso);
				
				daoMatriz.editar(matriz);
				
				request.setAttribute("success", "Matriz alterada com sucesso.");
				
			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Infelizmente não foi possivel alterar a Matriz.");
			}finally {
			
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("EditarMatrizTeste.jsp");
				dispatcher.forward(request, response);
				
			}
		
	}
}