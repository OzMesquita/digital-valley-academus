package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.CoordenadorDAO;
import br.ufc.russas.n2s.academus.dao.CursoDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactory;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.JDBCCoordenadorDAO;
import br.ufc.russas.n2s.academus.dao.JDBCCursoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCPerfilAcademusDAO;
import br.ufc.russas.n2s.academus.dao.JDBCProfessorDAO;
import br.ufc.russas.n2s.academus.dao.PerfilAcademusDAO;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Professor;

/**
 * Servlet implementation class AtribuirCoordenadoresController
 */
public class AtribuirCoordenadoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtribuirCoordenadoresController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCurso = Integer.parseInt(request.getParameter("buttonCurso"));
		int idProfessor = Integer.parseInt(request.getParameter("cursoInput-"+idCurso));
		System.out.println(idCurso);
		System.out.println(idProfessor);
		addCoordenador(idCurso, idProfessor);
		try {
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("atribuirCoordenador.jsp");
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addCoordenador(int idCurso, int idProfessor){
		CursoDAO cd = new JDBCCursoDAO();
		CoordenadorDAO cod = new JDBCCoordenadorDAO();
		alterarPerfil(idCurso, idProfessor);
		if(cd.possuiCoordenador(idCurso)){
			cod.alterarCoordenador(idCurso, idProfessor);
		}else{
			cod.cadastrarCoordedanador(idCurso, idProfessor);
		}
			
	}
	
	private void alterarPerfil(int idCurso, int idProfessor){
		ProfessorDAO pd = new JDBCProfessorDAO();
		CoordenadorDAO cod = new JDBCCoordenadorDAO();
		PerfilAcademusDAO pad = new JDBCPerfilAcademusDAO();
		
		PerfilAcademus novoCoordenador = pad.buscarPorId(idProfessor);
		PerfilAcademus antigoCoordenador = new PerfilAcademus(cod.buscarPorCurso(idCurso));
		
		System.out.println("id antigo coordenador: "+antigoCoordenador.getPessoa().getId());
		if(antigoCoordenador.getPessoa().getId() > 0){
			antigoCoordenador.setNivel(NivelAcademus.PROFESSOR);
			pad.editar(antigoCoordenador);
		}
		
		//System.out.println("id novo coordenador: "+novoCoordenador.getPessoa().getId());
		if(novoCoordenador != null){
			System.out.println("Entrou");
			novoCoordenador.setNivel(NivelAcademus.COORDENADOR);
			pad.editar(novoCoordenador);
		}else{
			Professor professor = pd.buscarPorId(idProfessor);
			PerfilAcademus coordenador = new PerfilAcademus(professor);
			coordenador.setNivel(NivelAcademus.COORDENADOR);
			pad.cadastrar(coordenador);
			
		}
			
	}

}
