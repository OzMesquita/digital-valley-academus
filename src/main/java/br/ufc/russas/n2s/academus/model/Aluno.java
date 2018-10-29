package br.ufc.russas.n2s.academus.model;

import model.Pessoa;

public class Aluno extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String semestreIngresso;
	private Curso curso;
	
	public Aluno(){
		this.setMatricula("INDEFINIDO");
		this.setSemestreIngresso("INDEFINIDO");
		this.setCurso(new Curso());
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
