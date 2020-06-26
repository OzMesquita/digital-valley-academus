package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;

public class Curso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idCurso;
	private String nome;
	
	public Curso(){
		this.setIdCurso(0);
		this.setNome("INDEFINIDO");
	}
	
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
