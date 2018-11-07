package br.ufc.russas.n2s.academus;

import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.Escopo;
import br.ufc.russas.n2s.academus.dao.JDBCAlunoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCHistoricoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCPerfilAcademusDAO;
import br.ufc.russas.n2s.academus.dao.JDBCSolicitacaoDAO;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Historico;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public class Teste {

	public static void main(String[] args) {
		
		
		List<Aluno> lis = new JDBCAlunoDAO().listar();
		System.out.println(lis.size());
		
	}

}
