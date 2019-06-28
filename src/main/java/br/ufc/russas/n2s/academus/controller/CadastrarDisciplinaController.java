package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.model.Disciplina;

public class CadastrarDisciplinaController extends HttpServlet {
	
	
		private static final long serialVersionUID = 1L;

		JDBCDisciplinaDAO daoCadastro = new JDBCDisciplinaDAO();
		
		public boolean aux;

		public CadastrarDisciplinaController() {
			super();
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request, response);
		}
		

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			if(request.getParameter("id_disciplina") != null &&
					request.getParameter("nome") != null &&
					request.getParameter("carga") != null){
				String id = request.getParameter("id_disciplina").toUpperCase();
				String nome = request.getParameter("nome").toUpperCase();
				int carga = Integer.parseInt(request.getParameter("carga"));
				int creditos = carga/16;

				Disciplina dis = new Disciplina();
				
				dis.setId(id);
				dis.setNome(nome);
				dis.setCarga(carga);
				dis.setCreditos(creditos);
				
				
				try {
					if(daoCadastro.buscarPorId(id) == null) {
							
						daoCadastro.cadastrar(dis);
						request.setAttribute("success", "Disciplina cadastrada com sucesso.");
					
					} else {
						request.setAttribute("erro", "Erro! Esse código já foi cadastrado no sistema. Por favor, altere-o.");
					}
					javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroDisciplina.jsp");
					
					dispatcher.forward(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("erro", "Ocorreu um erro ao cadastrar a disciplina. <br>Erro:<br>" + e.getMessage());
					javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroDisciplina.jsp");
					
					dispatcher.forward(request, response);
				}
			}
			else{
				try {
					javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroDisciplina.jsp");
					
					dispatcher.forward(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
}