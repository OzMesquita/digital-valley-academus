package br.ufc.russas.n2s.academus.model;

public enum Natureza {
	
	OPTATIVA(0, "OPTATIVA"),
	OBRIGATORIA(1, "OBRIGATÓRIA");
	
	private final int id;
	private final String descricao;
	
	private Natureza(int id, String descricao){
		this.id= id;
		this.descricao = descricao;
	}
	
	public static int getId(Natureza nat){
		return nat.id;
	}
	
	public static String getDescricao(Natureza nat){
		return nat.descricao;
	}
	
	public static Natureza getNatureza(String descricao) throws IllegalArgumentException{
		if(descricao.equalsIgnoreCase("optativa")){
			return Natureza.OPTATIVA;
		}
		
		if(descricao.equalsIgnoreCase("obrigatória")){
			return Natureza.OBRIGATORIA;
		}
		
		throw new IllegalArgumentException();
	}
	
}
