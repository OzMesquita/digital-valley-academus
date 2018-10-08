package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.PerfilAcademus;

public interface PerfilAcademusDAO {
	
	public PerfilAcademus cadastrar(PerfilAcademus perfil);
	
	public List<PerfilAcademus> listar();
	
	public PerfilAcademus buscarPorId(int id);
	
	public PerfilAcademus editar(PerfilAcademus perfil);
	
	public void excluir(PerfilAcademus perfil);
	
}
