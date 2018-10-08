package br.ufc.russas.n2s.academus.modelo;

import java.util.ArrayList;

import model.Servidor;

public class Professor extends Servidor{
	
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
