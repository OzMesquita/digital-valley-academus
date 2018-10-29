package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;

public class Disciplina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private int carga;
	private int creditos;
	
	public Disciplina() {
		this.setId("INDEFINIDO");
		this.setNome("INDEFINIDO");
		this.setCarga(-1);
		this.setCreditos(-1);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCarga() {
		return carga;
	}
	public void setCarga(int carga) {
		this.carga = carga;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
