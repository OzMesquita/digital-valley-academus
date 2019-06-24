package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.PerfilAcademus;

public interface PerfilAcademusDAO {
	
	public PerfilAcademus cadastrar(PerfilAcademus perfil);
	
	public List<PerfilAcademus> listar();
	
	public List<PerfilAcademus> listar(int limiteInf, int limiteSup);
	
	public PerfilAcademus buscarPorId(int id);
	
	public PerfilAcademus buscarPorCPF(String cpf);
	
	public List<PerfilAcademus> buscarPorNome(String nome);
	
	public List<PerfilAcademus> buscarPorNome(String nome, int limiteInf, int limiteSup);
	
	public PerfilAcademus editar(PerfilAcademus perfil);
	
	public void excluir(PerfilAcademus perfil);

	public int buscarPorIdAcademus(int idGuardiao);
	
	public int countPerfis(int pagina);
	
	public int countPerfis(int pagina, String nome);
}
