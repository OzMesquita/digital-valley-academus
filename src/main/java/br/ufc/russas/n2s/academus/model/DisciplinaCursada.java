package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;

public class DisciplinaCursada implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String semestre;
	private float nota;
	private int carga;
	private String nome;
	
	public DisciplinaCursada() {
		this.setSemestre("INDEFINIDO");
		this.setNota(-1.0f);
		this.setCarga(-1);
		this.setNome("INDEFINIDO");
	}

	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public int getCarga() {
		return carga;
	}
	public void setCarga(int carga) {
		this.carga = carga;
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
