package br.ufc.russas.n2s.academus.model;

import java.io.File;
import java.io.Serializable;

public class Arquivo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idArquivo;
	private String nome;
	private String caminho;
	private TipoArquivo tipo;
	
	
	public Arquivo(){
		this.setIdArquivo(-1);
		this.setNome("INDEFINIDO");
		this.setCaminho("INDEFINIDO");
		this.setTipo(TipoArquivo.INDEFINIDO);
	}
	
	public int getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(int idArquivo) {
		this.idArquivo = idArquivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public TipoArquivo getTipo() {
		return tipo;
	}
	public void setTipo(TipoArquivo tipo) {
		this.tipo = tipo;
	}
	public File getArquivo(){
		if(getCaminho() == "INDEFINIDO") return null;
		else return new File(getCaminho());
	}
		
}
