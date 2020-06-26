package br.ufc.russas.n2s.academus.model;

import java.util.ArrayList;

public class Professor extends Funcionario{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Disciplina> disciplinas;
	
	public Professor(){
		this.setDisciplinas(new ArrayList<Disciplina>());
	}
	
	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
