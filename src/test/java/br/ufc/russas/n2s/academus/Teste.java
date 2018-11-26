package br.ufc.russas.n2s.academus;

import br.ufc.russas.n2s.academus.dao.JDBCCoordenadorDAO;
import dao.DAOFactory;
import dao.PessoaDAO;
import model.Pessoa;

public class Teste {

	public static void main(String[] args) {
		
		PessoaDAO daoPessoa = DAOFactory.criarPessoaDAO();
		Pessoa pessoaCore = daoPessoa.buscarPorId(154);
		
		System.out.println(pessoaCore.getNome());
		System.out.println(new JDBCCoordenadorDAO().isCoordenador(pessoaCore));
	}

}
