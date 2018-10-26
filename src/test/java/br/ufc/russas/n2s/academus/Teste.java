package br.ufc.russas.n2s.academus;

import java.util.ArrayList;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public class Teste {

	public static void main(String[] args) {
		System.out.println(new DAOFactoryJDBC().criarSolicitacaoDAO().listar().size());
	}

}
