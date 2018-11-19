package br.ufc.russas.n2s.academus;

import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;
import br.ufc.russas.n2s.academus.util.Constantes;

public class Teste {

	public static void main(String[] args) {
		System.out.println(Constantes.getDatabaseConfDir());
		System.out.println(Constantes.getAppUrl());
		
		System.out.println(new JDBCMatrizCurricularDAO().buscarPorSolicitacao(2).getNome());
	}

}
