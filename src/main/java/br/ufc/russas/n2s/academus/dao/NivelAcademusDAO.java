package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.model.NivelAcademus;

public interface NivelAcademusDAO {
	
	public NivelAcademus cadastrar(NivelAcademus nivel);
		
	public NivelAcademus buscar(int id);
	
	public NivelAcademus editar(NivelAcademus nivel);
	
	public void excluir(NivelAcademus nivel);
	
}
