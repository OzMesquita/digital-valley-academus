package br.ufc.russas.n2s.academus;

import java.util.ArrayList;


import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Aluno;

public class Teste {

	public static void main(String[] args) {
		//System.out.println(new DAOFactoryJDBC().criarSolicitacaoDAO().listar().size());
		//System.out.println(new DAOFactoryJDBC().criarCoordenadorDAO().buscarPorId(1).getNome());
		//System.out.println(new DAOFactoryJDBC().criarAlunoDAO().buscarPorId(122).getNome());
		//System.out.println(new DAOFactoryJDBC().criarCursoDAO().buscarPorId(1).getNome());
		//System.out.println(new DAOFactoryJDBC().criarDisciplinaDAO().listar().size());
		//System.out.println(new DAOFactoryJDBC().criarSolicitacaoDAO().listar().size());
		
		Aluno n = new Aluno();
		System.out.println(n.getCurso().getNome());
	}

}
