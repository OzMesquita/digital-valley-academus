package br.ufc.russas.n2s.academus.model;

import java.sql.Date;


public class SegundaChamada {
	private int idSegundaChamada;
	private Aluno aluno;
	private Professor professor;
	private Disciplina disciplina;
	private Date dataProva;
	private String justificativa;
	private StatusSegundaChamada status;
	public SegundaChamada() {
		this.setStatus(StatusSegundaChamada.SOLICITADO);
	}
	
	
	
	public StatusSegundaChamada getStatus() {
		return status;
	}



	public void setStatus(StatusSegundaChamada status) {
		this.status = status;
	}
	public int getIdSegundaChamada() {
		return idSegundaChamada;
	}

	public void setIdSegundaChamada(int idSegundaChamada) {
		this.idSegundaChamada = idSegundaChamada;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Date getDataProva() {
		return dataProva;
	}




	public void setDataProva(Date dataProva) {
		this.dataProva = dataProva;
	}




	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	

}
