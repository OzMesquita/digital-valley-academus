package br.ufc.russas.n2s.academus.debug;

public enum Operacao {
	ENTROU(1, "Entrou"),
	SAIO(2, "Saio"),
	PEGOU(3, "Pegou"),
	DEVOLVEU(4, "Devolveu");
	
	private final int id;
	private final String desc;
	
	private Operacao(int id, String desc){
		this.id = id;
		this.desc = desc;
	}
	
	public static int getId(Operacao op){
		return op.id;
	}
	
	public static String getString(Operacao op){
		return op.desc;
	}
}
