package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;
import model.Pessoa;

public interface CoordenadorDAO {
	
	public Coordenador cadastrarCoordedanador(Coordenador cord);
	
	public Coordenador buscarPorId(int idCoordenador);
		
	public Coordenador buscarPorCurso(Curso curso);
	
	public Coordenador editar(Coordenador cord);
	
	public void excluir(Coordenador cord);
	
	public boolean isCoordenador(Pessoa pessoa);
}
