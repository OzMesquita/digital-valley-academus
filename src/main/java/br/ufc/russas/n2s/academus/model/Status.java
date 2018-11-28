package br.ufc.russas.n2s.academus.model;

public enum Status {
	
	CANCELADO(1, "CANCELADO"),
	SUBMETIDO(2, "SUBMETIDO"),
	VALIDANDO(3, "VALIDANDO"),
	ANALIZANDO(4, "ANALIZANDO"),
	FINALIZADO(5, "FINALIZADO");
	
	private final int codigo;
	private final String descricao;
	
	private Status(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static int getCodigo(Status status){
		return status.codigo;
	}
	
	public static String getDescricao(Status status){
		return status.descricao;
	}
	
	public static Status getStatus(int codigo) throws IllegalArgumentException{
		if(codigo == 1){
			return Status.CANCELADO;
		}
		if(codigo == 2){
			return Status.SUBMETIDO;
		}
		if(codigo == 3){
			return Status.VALIDANDO;
		}
		if(codigo == 4){
			return Status.ANALIZANDO;
		}
		if(codigo == 5){
			return Status.FINALIZADO;
		}
		
		throw new IllegalArgumentException();
	}
}
