package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;

public interface CoordenadorDAO {
	
	public Coordenador cadastrarCoordedanador(Coordenador cord);
	
	public Coordenador buscarPorId(int idCoordenador);
	
	public Coordenador buscarPorSiape(String siape);
	
	public Coordenador buscarPorCurso(Curso curso);
	
	public Coordenador editar(Coordenador cord);
	
	public void excluir(Coordenador cord);
	
}
