package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.modelo.Solicitacao;

public interface SolicitacaoDAO {
	
	public Solicitacao cadastrar(Solicitacao sol);
	
	public List<Solicitacao> listar();
		
	public Solicitacao buscarPorId(int id);
	
	public Solicitacao editar(Solicitacao sol);
	
	public void excluir(Solicitacao sol);
	
}
