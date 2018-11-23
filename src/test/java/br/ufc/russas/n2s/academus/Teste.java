package br.ufc.russas.n2s.academus;

import br.ufc.russas.n2s.academus.dao.JDBCCoordenadorDAO;
import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.util.Constantes;

public class Teste {

	public static void main(String[] args) {
		Coordenador coo = new JDBCCoordenadorDAO().buscarPorId(153);
		
		System.out.println(coo.getUsuario().getToken());
	}

}
