package br.ufc.russas.n2s.academus.model;

public enum StatusRecorrecao {

	SOLICITADO(1, "SOLICITADO"),
	FINALIZADO(2, "FINALIZADO");
	
	private final int codigo;
	private final String descricao;
	
	private StatusRecorrecao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static int getCodigo(StatusRecorrecao status){
		return status.codigo;
	}
	
	public static String getDescricao(StatusRecorrecao status){
		return status.descricao;
	}
	
	public static StatusRecorrecao getStatus(int codigo) throws IllegalArgumentException{
		if(codigo == 1){
			return StatusRecorrecao.SOLICITADO;
		}
		if(codigo == 2){
			return StatusRecorrecao.FINALIZADO;
		}
		
		throw new IllegalArgumentException();
	}
}
