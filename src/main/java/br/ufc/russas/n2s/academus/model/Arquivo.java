package br.ufc.russas.n2s.academus.model;

import java.io.File;
import java.io.Serializable;

public class Arquivo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idArquivo;
	private String caminho;
	private File arquivo;
	
	public int getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(int idArquivo) {
		this.idArquivo = idArquivo;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public File getArquivo() {
		return arquivo;
	}
	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
	
	
}
