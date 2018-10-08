package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.modelo.DisciplinaCursada;
import br.ufc.russas.n2s.academus.modelo.Solicitacao;

public interface DisciplinaCursadaDAO {
	
	public void cadasatrar(List<DisciplinaCursada> disciplinas, Solicitacao sol);
	
	public List<DisciplinaCursada> buscar(Solicitacao sol);
	
	public void editar(List<DisciplinaCursada> disciplinaCursadas, Solicitacao sol);
	
	public void excluir(Solicitacao sol);
		
}
