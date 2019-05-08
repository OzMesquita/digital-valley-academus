package br.ufc.russas.n2s.academus.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Funcionario;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Professor;
import util.Constantes;



public class Facade {

	private Facade() {
		//
	}
	
	public static PerfilAcademus buscarPerfilPorCPF(String cpf) {
		Aluno aluno = new DAOFactoryJDBC().criarAlunoDAO().buscarPorCPF(cpf);
		if(aluno != null) {
			return aluno;
		}
		Professor pro = new DAOFactoryJDBC().criarProfessorDAO().buscarPorCPF(cpf);
		if(pro != null) {
			return pro;
		}
		Funcionario func = new DAOFactoryJDBC().criarFuncionarioDAO().buscarPorCPF(cpf);
		if(func != null) {
			return func;
		}
		PerfilAcademus per = new DAOFactoryJDBC().criarPerfilAcademusDAO().buscarPorCPF(cpf);
		return per;
	}
	
	public static String[] lerArquivoBancoDeDados() {
		String[] bd = new String[3];
		try {			
			FileReader arquivo = new FileReader(Constantes.getDatabaseConfDir());
			BufferedReader reader = new BufferedReader(arquivo);
			try {
				bd[0] = reader.readLine();
				bd[1] = reader.readLine();
				bd[2] = reader.readLine();
			} catch (IOException e) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return bd;
	}

	public static String[] lerArquivoEmail() {
		String[] email = new String[2];
		try {
			FileReader arquivo = new FileReader(Constantes.getEmailConfDir());
			BufferedReader reader = new BufferedReader(arquivo);
			try {
				email[0] = reader.readLine();
				email[1] = reader.readLine();
			} catch (IOException e) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return email;

	}

}
