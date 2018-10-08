package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;
import java.util.ArrayList;

public class MatrizCurricular implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private int idMatriz;
	private int idCurso;
	private int carga;
	private String periodoLetivo;
	private boolean vigente;
	private boolean ativo;
	private ArrayList<ComponenteCurricular> componentes;
	private int prazoMinimo;
	private int prazoMaximo;
	
	public MatrizCurricular(){
		this.setComponentes(new ArrayList<ComponenteCurricular>());
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdMatriz() {
		return idMatriz;
	}
	public void setIdMatriz(int idMatriz) {
		this.idMatriz = idMatriz;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public int getCarga() {
		return carga;
	}
	public void setCarga(int carga) {
		this.carga = carga;
	}
	public String getPeriodoLetivo() {
		return periodoLetivo;
	}
	public void setPeriodoLetivo(String periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}
	public boolean isVigente() {
		return vigente;
	}
	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public ArrayList<ComponenteCurricular> getComponentes() {
		return componentes;
	}
	public void setComponentes(ArrayList<ComponenteCurricular> componentes) {
		this.componentes = componentes;
	}
	public int getPrazoMinimo() {
		return prazoMinimo;
	}
	public void setPrazoMinimo(int prazoMinimo) {
		this.prazoMinimo = prazoMinimo;
	}
	public int getPrazoMaximo() {
		return prazoMaximo;
	}
	public void setPrazoMaximo(int prazoMaximo) {
		this.prazoMaximo = prazoMaximo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getInfoComponentes(){
		String comps = "";
		ArrayList<ComponenteCurricular> componentes = getComponentes();
		if(componentes.size() > 0){
			for(int i=0; i<componentes.size();i++){
				comps += componentes.get(i).getIdComponente()+"-"+componentes.get(i).getDisciplina().getNome()+";";
			}
		}
		return comps;
	}
}
