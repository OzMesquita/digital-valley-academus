package br.ufc.russas.n2s.academus;

import java.util.ArrayList;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;

public class Teste {

	public static void main(String[] args) {
		AlunoDAO dao = new DAOFactoryJDBC().criarAlunoDAO();
		
		Aluno a = new Aluno();
		PerfilAcademus p = new PerfilAcademus();
		p.setPessoa(a);
		
		if(p.getPessoa() instanceof Aluno)
		{
			System.out.println("é aluno");
		}
	}

}
