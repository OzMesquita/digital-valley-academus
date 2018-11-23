package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Solicitacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idSolicitacao;
	private Status status;
	private List<Historico> historicoOperacoes;
	private Aluno solicitante;
	private ComponenteCurricular disciplinaAlvo;
	private List<DisciplinaCursada> disciplinasCursadas;
	private String resultado;
	private String justificativa;
	private Curso curso;
	private Arquivo arquivo;
	
	public Solicitacao(){
		this.setIdSolicitacao(-1);
		this.setStatus(Status.CANCELADO);
		this.setHistoricoOperacoes(new ArrayList<Historico>());
		this.setSolicitante(new Aluno());
		this.setDisciplinaAlvo(new ComponenteCurricular());
		this.setDisciplinasCursadas(new ArrayList<DisciplinaCursada>());
		this.setResultado("INDEFINIDO");
		this.setJustificativa("INDEFINIDO");
		this.setArquivo(new Arquivo());
		this.setCurso(new Curso());
		this.justificativa = "";
		this.resultado = "";
	}
	
	public int getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<Historico> getHistoricoOperacoes() {
		return historicoOperacoes;
	}
	public void setHistoricoOperacoes(List<Historico> historicoOperacoes) {
		this.historicoOperacoes = historicoOperacoes;
	}
	public Aluno getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Aluno solicitante) {
		this.solicitante = solicitante;
	}
	public ComponenteCurricular getDisciplinaAlvo() {
		return disciplinaAlvo;
	}
	public void setDisciplinaAlvo(ComponenteCurricular disciplinaAlvo) {
		this.disciplinaAlvo = disciplinaAlvo;
	}
	public List<DisciplinaCursada> getDisciplinasCursadas() {
		return disciplinasCursadas;
	}
	public void setDisciplinasCursadas(List<DisciplinaCursada> disciplinasCursadas) {
		this.disciplinasCursadas = disciplinasCursadas;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
