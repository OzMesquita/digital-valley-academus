
package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.PerfilAcademusDAO;

public class PerfilController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	PerfilAcademusDAO daoCadastro = new PerfilAcademusDAO();

	public PerfilController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idPessoaUsuario = Integer.parseInt(request.getParameter("usuario"));
		int nivelAcesso = Integer.parseInt(request.getParameter(request.getParameter("usuario")));

		Perfil perfil = new Servidor();
		System.out.println(nivelAcesso);
		perfil.setIdPessoaUsuario(idPessoaUsuario);
		//perfil.setNivelAcesso(NivelAcesso.getNivel(nivelAcesso));

		//Perfil aux = daoCadastro.buscarPorId(idPessoaUsuario);
		boolean aux = daoCadastro.existePerfil(perfil.getIdPessoaUsuario());
		
		if(aux){
			daoCadastro.atualizarPerfil(perfil);
			
		}else{
			daoCadastro.atribuirPerfil(perfil);
		}
		
		try {
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("listaPerfis.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}