package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Curso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idCurso;
	private String nome;
	private List<MatrizCurricular> matrizes;
	
	public Curso(){
		this.setIdCurso(-1);
		this.setNome("INDEFINIDO");
		this.setMatrizes(new ArrayList<MatrizCurricular>());
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
	public List<MatrizCurricular> getMatrizes(){
		return this.matrizes;
	}
	public void setMatrizes(List<MatrizCurricular> matrizes){
		this.matrizes = matrizes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
