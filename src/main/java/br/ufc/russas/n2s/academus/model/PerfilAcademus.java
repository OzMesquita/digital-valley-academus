package br.ufc.russas.n2s.academus.model;
import java.io.Serializable;

import model.Pessoa;

public class PerfilAcademus implements Serializable{
	
	private final static long serialVersionUID = 1L;
	private Pessoa pessoa;
	private NivelAcademus nivel;
	private boolean isAdmin;
	
	public PerfilAcademus() {
		this.setPessoa(new Pessoa());
		this.setNivel(NivelAcademus.INDEFINIDO);
		this.isAdmin = false;
	}
	
	public PerfilAcademus(Pessoa pessoa){
		this.pessoa = pessoa;
		this.setNivel(verificarNivel(pessoa));
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public NivelAcademus getNivel() {
		return nivel;
	}
	public void setNivel(NivelAcademus nivel) {
		this.nivel = nivel;
	}
	public boolean getIsAdmin(){
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private static NivelAcademus verificarNivel(Pessoa p){
		if(p instanceof Aluno){
			return NivelAcademus.ALUNO;
		}else if(p instanceof Professor){
			return NivelAcademus.PROFESSOR;
		}else if(p instanceof Coordenador){
			return NivelAcademus.COORDENADOR;
		}else{
			return NivelAcademus.INDEFINIDO;
		}
	}
	
}
