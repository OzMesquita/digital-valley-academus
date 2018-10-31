package br.ufc.russas.n2s.academus.debug;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class No{
	public Operacao op;
	public int qtd;
};

class Info{
	public String classe;
	public No entrou;
}

public class IOConexao {
	
	private static Map<String, No> mapa = new HashMap<String, No>(); 
	
	public static void add(String classe, Operacao op){
		if(!mapa.containsKey(classe + " -> .:[" + Operacao.getString(op) + "]:.")){
			No no = new No();
			no.op = op;
			no.qtd = 1;
			mapa.put(classe + " -> .:[" + Operacao.getString(op) + "]:.", no);
		} else {
			No no = mapa.get(classe + " -> .:[" + Operacao.getString(op) + "]:.");
			no.qtd++;
			
			mapa.remove(classe + " -> .:[" + Operacao.getString(op) + "]:.");
			mapa.put(classe + " -> .:[" + Operacao.getString(op) + "]:.", no);
		}
	}
	
	public static void info(){
		Set<Entry<String, No> > set = mapa.entrySet();
	    Iterator<Entry<String, No>> it = set.iterator();
	     
	    System.out.println("Quantidade de vezes acessada\t\tClasse");
	     
	    while(it.hasNext()){
	      Entry<String, No> entry = (Entry<String, No>) it.next();
	      
	      System.out.println(entry.getValue().qtd + "\t\t\t\t\t" + entry.getKey());
	    }
	}
}
