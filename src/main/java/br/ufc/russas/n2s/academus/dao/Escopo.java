package br.ufc.russas.n2s.academus.dao;

public enum Escopo {
	COMPLETO(1, "Todas informações contidas na clase"),
	PARCIAL(2, "Algumas infomações serão setadas");
	
	private final int codigo;
	private final String descricao;
	
	private Escopo(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static int getId(Escopo esc){
		return esc.codigo;
	}
	
	public static String getDescricao(Escopo esc){
		return esc.descricao;
	}
	
	public static Escopo getEcopo(int codigo) throws IllegalArgumentException{
		if(codigo == 1){
			return Escopo.COMPLETO;
		}
		
		if(codigo == 2){
			return Escopo.PARCIAL;
		}
		
		throw new IllegalArgumentException();
	}
}
