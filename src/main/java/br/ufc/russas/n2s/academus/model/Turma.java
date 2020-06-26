package br.ufc.russas.n2s.academus.model;

import java.time.LocalTime;

public class Turma extends Disciplina{
	
	private static final long serialVersionUID = 1L;
	private LocalTime horario;
	private Professor professor;
	//PlanoDisciplina
	
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
