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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStringPreRequisitos(){
		String stringPr = "";
		if(!preRequisitos.isEmpty()){
			for(int j=0;j<preRequisitos.size();j++){
				if(preRequisitos.get(j).getId() != ""){
					if(j>0){stringPr += "; ";}
					stringPr += preRequisitos.get(j).getId();
				}
			}
		}else{
			stringPr = "-";
		}
		return stringPr;
	}
}
