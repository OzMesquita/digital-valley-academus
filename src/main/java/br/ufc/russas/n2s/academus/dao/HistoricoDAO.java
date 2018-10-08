package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.modelo.Historico;
import br.ufc.russas.n2s.academus.modelo.Solicitacao;

public interface HistoricoDAO {
	
	public Historico cadastrar(Historico his);
	
	public Historico buscarPorSolicitacao(Solicitacao sol);
		
	public Historico editar(Historico his);
	
	public void excluir(Historico his);
}
