package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.modelo.NivelAcademus;

public interface NivelAcademusDAO {
		
	public NivelAcademus buscar(int id);
	
	public NivelAcademus editar(NivelAcademus nivel);
	
	
}
