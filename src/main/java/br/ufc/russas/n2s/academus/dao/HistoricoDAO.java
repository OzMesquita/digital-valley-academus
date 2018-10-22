package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Historico;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public interface HistoricoDAO {
	
	public Historico cadastrar(Historico his, int idSolicitacao) throws Exception;
	
	public List<Historico> buscarPorSolicitacao(Solicitacao sol);
		
	public Historico editar(Historico his);
	
	public void excluir(Historico his);
}
