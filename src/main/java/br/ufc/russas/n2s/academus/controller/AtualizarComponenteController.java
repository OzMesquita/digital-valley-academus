package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.ComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Disciplina;

public class AtualizarComponenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ComponenteCurricularDAO daoComp = new DAOFactoryJDBC().criarComponenteCurricularDAO();
	
    public AtualizarComponenteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_matriz = request.getParameter("id_matriz");
		String id_componente = request.getParameter("id_comp");
		try {
			
			String[] ids_disciplinas = request.getParameterValues("dis-id");
			
			List<Disciplina> preRequisitosAntigos = daoComp.buscarPreRequisitos(Integer.parseInt(id_componente));
			ArrayList<Disciplina> preReqExcluidos = new ArrayList<Disciplina>();
			
			for(Disciplina disciplina : preRequisitosAntigos) {
				System.out.println(disciplina.getId());
				int i = 0;
				boolean remVelho = true;
				if(ids_disciplinas != null) {
					for(i = 0; i < ids_disciplinas.length; i++){
						if(disciplina.getId().equals(ids_disciplinas[i])){
							remVelho = false;
							ids_disciplinas[i] = "";
						}
					}
				}
				if(remVelho)
					preReqExcluidos.add(disciplina);
			}
			
			// ADICIONANDO PRE-REQUISITO
			System.out.println("\nRequisitos Adicionados");
			if(ids_disciplinas != null) {
				List<Disciplina> addPreReq = new ArrayList<Disciplina>();
				for(int i = 0; i< ids_disciplinas.length; i++) {
					if(!ids_disciplinas[i].equals("")){
						Disciplina dis = new Disciplina();
						dis.setId(ids_disciplinas[i]);
						addPreReq.add(dis);
					}
				}
				if(!addPreReq.isEmpty()) {
					daoComp.inserirPreRequsitos(addPreReq, Integer.parseInt(id_componente));
				}
			}
			
			// EXCLUINDO PRE-REQUISITO
			System.out.println("\nRequisitos Excluidos");
			for(Disciplina dis : preReqExcluidos) {
				System.out.println(dis.getId());
				daoComp.excluirPreRequisito(Integer.parseInt(id_componente), dis);
			}
 			request.setAttribute("success", "Atualização de Pre-Requsitos cadastrados com sucesso.");
 			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Erro, infelizmente não foi possivel fazer a alteração.");
			// TODO: handle exception
		} finally {
			request.setAttribute("id_matriz", id_matriz);
			request.setAttribute("id_comp", id_componente);
			
			javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("EditarComponente");
			dispatcher.forward(request, response);
		}
	}

}
