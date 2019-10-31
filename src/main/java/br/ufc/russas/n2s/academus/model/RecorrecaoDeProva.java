package br.ufc.russas.n2s.academus.model;

import java.sql.Date;
import java.sql.Time;

public class RecorrecaoDeProva {
	private Aluno aluno;
	private Professor professor;
	private Disciplina disciplina;
	private Date dataProva;
	private Date dataRecebimento;
	private Time horarioDaProva;
	private Time horarioRecebimento;
	private String justificativa;
	private int idRecorrecao;
	
	
	public RecorrecaoDeProva() {
		
	}
	
	
	
	public Date getDataRecebimento() {
		return dataRecebimento;
	}



	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}



	public Time getHorarioRecebimento() {
		return horarioRecebimento;
	}



	public void setHorarioRecebimento(Time horarioRecebimento) {
		this.horarioRecebimento = horarioRecebimento;
	}
	
	


	public int getIdRecorrecao() {
		return idRecorrecao;
	}



	public void setIdRecorrecao(int idRecorrecao) {
		this.idRecorrecao = idRecorrecao;
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

	public Time getHorarioDaProva() {
		return horarioDaProva;
	}

	public void setHorarioDaProva(Time horarioDaProva) {
		this.horarioDaProva = horarioDaProva;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
}
