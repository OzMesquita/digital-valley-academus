package br.ufc.russas.n2s.academus;

import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;

public class Teste {

	public static void main(String[] args) {
		
		boolean estaSendUlt = new JDBCMatrizCurricularDAO().verificarSeEstaSendoUtilizada(1);
		System.out.println(estaSendUlt);
	}

}
