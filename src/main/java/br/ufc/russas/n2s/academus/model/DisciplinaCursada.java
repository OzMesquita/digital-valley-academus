package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;

public class DisciplinaCursada implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String semestre;
	private float nota;
	private int carga;
	private String nome;
	private String instituicao;
	
	public DisciplinaCursada() {
		this.setSemestre("INDEFINIDO");
		this.setNota(-1.0f);
		this.setCarga(-1);
		this.setNome("INDEFINIDO");
		this.setInstituicao("INDEFINIDO");
	}
	
	public DisciplinaCursada(String semestre, float nota, int carga, String nome, String instituicao) {
		super();
		this.semestre = semestre;
		this.nota = nota;
		this.carga = carga;
		this.nome = nome;
		this.instituicao = instituicao;
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
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
