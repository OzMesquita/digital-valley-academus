package br.ufc.russas.n2s.academus.model;
import java.io.Serializable;

public class PerfilAcademus implements Serializable{
	
	private final static long serialVersionUID = 1L;
	private int id_guardiao;
	private int id;
	private String nome;
	private String email;
	private String cpf;
	private NivelAcademus nivel;
	private Curso curso;
	private boolean isAdmin;
	
	public PerfilAcademus() {
		this.nome = "DESCONHECIDO";
		this.email = "DESCONHECIDO";
		this.cpf = "DESCONHECIDO";
		this.setNivel(NivelAcademus.INDEFINIDO);
		this.curso = new Curso();
		this.isAdmin = false;
	}
	public PerfilAcademus(String nome, String email, String cpf, Curso curso, NivelAcademus nivel){
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.curso = curso;
		this.nivel = nivel;
	}
	public int getIdGuardiao() {
		return id_guardiao;
	}
	public void setIdGuardiao(int id_guardiao) {
		this.id_guardiao = id_guardiao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCPF() {
		return cpf;
	}
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	public NivelAcademus getNivel() {
		return nivel;
	}
	public void setNivel(NivelAcademus nivel) {
		this.nivel = nivel;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso( Curso curso) {
		this.curso = curso;
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
	
	
}
