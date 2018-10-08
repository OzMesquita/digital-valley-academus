package br.ufc.russas.n2s.academus.connection;

import br.ufc.russas.n2s.academus.dao.JDBCComponenteCurricularDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.modelo.ComponenteCurricular;

public class TesteMain {
	public static void main(String[] args){
		JDBCDisciplinaDAO dd = new JDBCDisciplinaDAO();
		//PerfilDao pd = new PerfilDao();
		//System.out.println(pd.existePerfil("4"));
		JDBCComponenteCurricularDAO ccd = new JDBCComponenteCurricularDAO();
		ComponenteCurricular cc =ccd.buscarPorId(15, 1);
		cc.getDisciplina().exibir();
		System.out.println(cc.getStringPreRequisitos());
		//ccd.deletarPreRequisitos(146, "TES0002");
		//Disciplina d = new Disciplina("TES0012", "TESTE", 64, 4);
		//MatrizCurricularDao mcd = new MatrizCurricularDao();
		//MatrizCurricular mc = mcd.buscarPorId(74);
		//mc.exibir();
		//mc.setNome("teste deu certo");
		//if(mcd.atualizarMatriz(mc)){
			//System.out.println("atualizou");
		//}else{
			//System.out.println("não atualizou");
		//}
		//mc = mcd.buscarPorId(21);
		//mc.exibir();
		//System.out.println(dd.buscarPorId("TES1239"));
		//String n = "TES0001-TESTE-OBRIGATORIA";
		//String natureza = n.split("-")[2];
		//System.out.println(natureza);
		//dd.insereDisciplina(d);
		//d.setNome("funfou");
		//dd.atualizarDisciplina(d);
		
		//d.exibir();
	}
}
