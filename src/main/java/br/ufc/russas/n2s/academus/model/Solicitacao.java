package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Solicitacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idSolicitacao;
	private Status status;
	private ArrayList<Historico> historicoOperacoes;
	private Aluno solicitante;
	private ComponenteCurricular disciplinaAlvo;
	private List<DisciplinaCursada> disciplinasCursadas;
	private String resultado;
	private String justificativa;
	private String instituicao;
	private Arquivo arquivo;
	
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
	public ArrayList<Historico> getHistoricoOperacoes() {
		return historicoOperacoes;
	}
	public void setHistoricoOperacoes(ArrayList<Historico> historicoOperacoes) {
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
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
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
