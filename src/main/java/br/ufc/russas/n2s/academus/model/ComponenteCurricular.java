package br.ufc.russas.n2s.academus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComponenteCurricular implements Serializable{
	
	private static final long serialVersionUID  = 1L;
	private int idComponente;
	private Disciplina disciplina;
	private Natureza natureza;
	private List<Disciplina> preRequisitos;
	
	public ComponenteCurricular(){
		this.setIdComponente(-1);
		this.setDisciplina(new Disciplina());
		this.setNatureza(Natureza.OPTATIVA);
		this.setPreRequisitos(new ArrayList<Disciplina>());
	}

	public int getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Natureza getNatureza() {
		return natureza;
	}
	public void setNatureza(Natureza natureza) {
		this.natureza = natureza;
	}
	public List<Disciplina> getPreRequisitos() {
		return preRequisitos;
	}
	public void setPreRequisitos(List<Disciplina> list) {
		this.preRequisitos = list;
	}
	
	public String getStringPreRequisitos(){
		String s = "";
		if(!getPreRequisitos().isEmpty()){
			for(Disciplina d : getPreRequisitos()){
				s += d.getId()+";";
			}
			s = s.substring(0, s.length()-1);
		}else{
			return "-";
		}
		
		return s;
		
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
