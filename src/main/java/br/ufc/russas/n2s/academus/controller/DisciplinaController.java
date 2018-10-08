package br.ufc.russas.n2s.academus.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.model.Disciplina;

@WebServlet("/ServletCadastroDisciplina")
public class DisciplinaController extends HttpServlet {
	
	
		private static final long serialVersionUID = 1L;

		JDBCDisciplinaDAO daoCadastro = new JDBCDisciplinaDAO();
		
		public boolean aux;

		public DisciplinaController() {
			super();
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request, response);
		}
		

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			String id = request.getParameter("id_disciplina");
			String nome = request.getParameter("nome");
			int carga = Integer.parseInt(request.getParameter("carga"));
			int creditos = Integer.parseInt(request.getParameter("creditos"));

			Disciplina dis = new Disciplina();
			
			dis.setId(id);
			dis.setNome(nome);
			dis.setCarga(carga);
			dis.setCreditos(creditos);


			daoCadastro.insereDisciplina(dis);
			
			if (daoCadastro.buscarPorId2(dis.getId())){
				File diretorio = new File("C:\\n2s\\Academus\\Disciplinas\\"+dis.getId());
				diretorio.mkdir();
			}
			
			try {
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroDisciplina.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}