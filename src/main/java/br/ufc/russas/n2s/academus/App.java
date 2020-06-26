package br.ufc.russas.n2s.academus;

import java.util.List;

import br.ufc.russas.n2s.academus.dao.JDBCMatrizCurricularDAO;
import br.ufc.russas.n2s.academus.dao.MatrizCurricularDAO;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Natureza n;
        //n = Natureza.getNatureza("OBRIGATÓRIA");
        //System.out.println(Natureza.getDescricao(n));
    	//System.out.println( "Hello World!" );
        MatrizCurricularDAO daoMC = new JDBCMatrizCurricularDAO();
    	List<MatrizCurricular> matrizes = daoMC.listar(0, 10);
    	for(MatrizCurricular mc : matrizes){
    		System.out.println(mc.getInfoComponentes());
    	}
    }
}
