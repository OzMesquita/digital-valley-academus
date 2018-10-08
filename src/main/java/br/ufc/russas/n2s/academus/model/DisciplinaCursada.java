package br.ufc.russas.n2s.academus.modelo;

import java.io.Serializable;

public class DisciplinaCursada implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String semestre;
	private float nota;
	private int carga;
	private String nome;
	
	public DisciplinaCursada() {

	}
	
	public DisciplinaCursada(String semestre, float nota, int carga, String nome) {
		setSemestre(semestre);
		setNota(nota);
		setCarga(carga);
		setNome(nome);
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
