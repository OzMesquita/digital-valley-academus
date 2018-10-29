package br.ufc.russas.n2s.academus.model;


public class Coordenador extends Professor{
	
	private final static long serialVersionUID = 1L;
	private Curso curso;
	
	public Coordenador(){
		this.setCurso(new Curso());
	}

	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
