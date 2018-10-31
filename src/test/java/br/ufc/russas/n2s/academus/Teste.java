package br.ufc.russas.n2s.academus;

import java.util.ArrayList;


import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.JDBCHistoricoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCPerfilAcademusDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Historico;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public class Teste {

	public static void main(String[] args) {
		
		/*
		JDBCHistoricoDAO dao = new JDBCHistoricoDAO();
		
		Solicitacao s = new Solicitacao();
		s.setIdSolicitacao(28);
		
		ArrayList<Historico> h=  (ArrayList<Historico>) dao.buscarPorSolicitacao(s);
		*/
		
		JDBCPerfilAcademusDAO dao = new JDBCPerfilAcademusDAO();
		PerfilAcademus p = dao.buscarPorId(122);
		
	}

}
