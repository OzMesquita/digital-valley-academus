package br.ufc.russas.n2s.academus.model;

public enum StatusSegundaChamada {

	SOLICITADO(1, "SOLICITADO"),
	FINALIZADO(2, "FINALIZADO");
	
	private final int codigo;
	private final String descricao;
	
	private StatusSegundaChamada(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static int getCodigo(StatusSegundaChamada status){
		return status.codigo;
	}
	
	public static String getDescricao(StatusSegundaChamada status){
		return status.descricao;
	}
	
	public static StatusSegundaChamada getStatus(int codigo) throws IllegalArgumentException{
		if(codigo == 1){
			return StatusSegundaChamada.SOLICITADO;
		}
		if(codigo == 2){
			return StatusSegundaChamada.FINALIZADO;
		}
		
		throw new IllegalArgumentException();
	}
}
