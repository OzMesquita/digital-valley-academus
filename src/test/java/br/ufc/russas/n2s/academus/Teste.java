package br.ufc.russas.n2s.academus;

import java.util.ArrayList;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Professor;

public class Teste {

	public static void main(String[] args) {
		Professor p = new DAOFactoryJDBC().criarProfessorDAO().buscarPorId(6);
		
		System.out.println(p.getNome());
	}

}
