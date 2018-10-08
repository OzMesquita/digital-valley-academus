package br.ufc.russas.n2s.academus.modelo;
import java.io.Serializable;

import model.Pessoa;

public class PerfilAcademus implements Serializable{
	
	private final static long serialVersionUID = 1L;
	private Pessoa pessoa;
	private NivelAcademus nivel;
	
	public PerfilAcademus() {
		this.setPessoa(new Pessoa());
		this.setNivel(NivelAcademus.INDEFINIDO);
	}
	
	public PerfilAcademus(Pessoa pessoa){
		this.pessoa = pessoa;
		this.setNivel(NivelAcademus.INDEFINIDO);
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
