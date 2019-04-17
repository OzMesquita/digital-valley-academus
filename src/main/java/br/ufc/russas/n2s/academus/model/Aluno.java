package br.ufc.russas.n2s.academus.model;

public class Aluno extends PerfilAcademus{
	
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String semestreIngresso;
	
	public Aluno(){
		this.setMatricula("INDEFINIDO");
		this.setSemestreIngresso("INDEFINIDO");
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSemestreIngresso() {
		return semestreIngresso;
	}
	public void setSemestreIngresso(String semestreIngresso) {
		this.semestreIngresso = semestreIngresso;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
