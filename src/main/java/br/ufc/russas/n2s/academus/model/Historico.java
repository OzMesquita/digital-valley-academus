package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Historico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private PerfilAcademus responsavel;
	private LocalDate data;
	private LocalTime horario;
	private String descricao;
	
	public Historico() {
		this.setResponsavel(new PerfilAcademus());
		this.setData(LocalDate.now());
		this.setHorario(LocalTime.now());
		this.setDescricao("INDEFINIDO");
	}
	
	public Historico(PerfilAcademus responsavel, int tipoMensagem) {
		this.setData(LocalDate.now());
		this.setHorario(LocalTime.now());
		this.setResponsavel(responsavel);
		this.gerarDescricao(tipoMensagem);
	}
	
	public PerfilAcademus getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(PerfilAcademus responsavel) {
		this.responsavel = responsavel;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void gerarDescricao(int i){
		String string = "";
		if(i == 1) string = "O(A) aluno(a) "+getResponsavel().getNome()+" cadastrou esta solicitação";
		if(i == 2) string = "O(A) secretário(a) "+getResponsavel().getNome()+" validou esta solicitação";
		if(i == 3) string = "O(A) coordenador(a) "+getResponsavel().getNome()+" avaliou esta solicitação";
		setDescricao(string);
	}
	
}
