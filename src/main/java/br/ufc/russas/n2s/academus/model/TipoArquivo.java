package br.ufc.russas.n2s.academus.model;

public enum TipoArquivo {
	INDEFINIDO(0, "INDEFINIDO"),
	EMENTA(1, "EMENTA"),
	HISTORICO(2, "HISTÓRICO");
	
	private final int id;
	private final String descricao;
	
	private TipoArquivo(int id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public static int getId(TipoArquivo tipo){
		return tipo.id;
	}
	
	public static String getDescricao(TipoArquivo tipo){
		return tipo.descricao;
	}
	
	public static TipoArquivo getTipoArquivo(int id) throws IllegalArgumentException{
		if(id == 0) {
			return TipoArquivo.INDEFINIDO;
		}
		
		if(id == 1){
			return TipoArquivo.EMENTA;
		}
		
		if(id == 2){
			return TipoArquivo.HISTORICO;
		}
		
		throw new IllegalArgumentException();
	}
}
