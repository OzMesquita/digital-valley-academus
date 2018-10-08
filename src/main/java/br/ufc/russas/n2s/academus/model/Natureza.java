package br.ufc.russas.n2s.academus.modelo;

public enum Natureza {
	
	OPTATIVO(0, "Optativo"),
	OBRIGATORIO(1, "Obrigatório");
	
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
		if(descricao.equals("Optativo")){
			return Natureza.OPTATIVO;
		}
		
		if(descricao.equals("Obrigatório")){
			return Natureza.OBRIGATORIO;
		}
		
		throw new IllegalArgumentException();
	}
	
}
