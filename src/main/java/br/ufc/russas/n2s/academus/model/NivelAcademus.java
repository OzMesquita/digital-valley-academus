package br.ufc.russas.n2s.academus.model;

public enum NivelAcademus {
	
	INDEFINIDO(0, "INDEFINIDO"),
	SECRETARIO(1, "SECRETÁRIO"),
	COORDENADOR(2, "COORDENADOR"),
	ALUNO(3, "ALUNO"),
	PROFESSOR(4, "PROFESSOR");
	
	private final int id;
	private final String descricao;
	
	private NivelAcademus(int id, String s){
		this.id = id;
		this.descricao = s;
	}
	
	public static int getCodigo(NivelAcademus c){
		return c.id;
	}
	
	public static String getDescricao(NivelAcademus c){
		return c.descricao;
	}
	
	public static NivelAcademus getNivel(int id) throws IllegalArgumentException{
		if(id == 0){
			return NivelAcademus.INDEFINIDO;
		}
		if(id == 1){
			return NivelAcademus.SECRETARIO;
		}
		if(id == 2){
			return NivelAcademus.COORDENADOR;
		}
		if(id == 3){
			return NivelAcademus.ALUNO;
		}
		if(id == 4){
			return NivelAcademus.PROFESSOR;
		}
		
		throw new IllegalArgumentException();
	}
}
