package br.ufc.russas.n2s.academus.model;

public class Funcionario extends PerfilAcademus{
	private static final long serialVersionUID = 1L;
	private String siape;
	
	
	public String getSiape() {
		return siape;
	}
	public void setSiape(String siape) {
		this.siape = siape;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
