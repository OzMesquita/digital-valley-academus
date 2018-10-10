package br.ufc.russas.n2s.academus;

import java.util.ArrayList;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Aluno;

public class Teste {

	public static void main(String[] args) {
		AlunoDAO dao = new DAOFactoryJDBC().criarAlunoDAO();
		
		//Aluno alu = dao.buscarPorId(1);
		//Aluno alu = dao.buscarPorMatricula("113456");
		//System.out.println(alu.getNome());
		
		ArrayList<Aluno> alunos = (ArrayList<Aluno>) dao.listar();
		for(int i = 0; i < alunos.size(); i++){
			System.out.println(alunos.get(i).getNome());
		}
		
	}

}
