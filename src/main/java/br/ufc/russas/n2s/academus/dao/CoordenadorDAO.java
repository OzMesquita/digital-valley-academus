package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.model.Coordenador;
import model.Pessoa;

public interface CoordenadorDAO {
	
	public Coordenador cadastrarCoordedanador(Coordenador cord);
	
	public void cadastrarCoordedanador(int idCurso, int idProfessor);
	
	public Coordenador buscarPorId(int idCoordenador);
		
	public Coordenador buscarPorCurso(int idCurso);
	
	public Coordenador editar(Coordenador cord);
	
	public void excluir(Coordenador cord);
	
	public boolean isCoordenador(Pessoa pessoa);
	
	public void alterarCoordenador(int idCurso, int idProfessor);
}
