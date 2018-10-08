package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.modelo.Arquivo;
import br.ufc.russas.n2s.academus.modelo.Solicitacao;

public interface ArquivoDAO {
	
	public Arquivo cadastrarArquivo(Arquivo arq, Solicitacao sol);
	
	public Arquivo buscarPorSolicitacao(Solicitacao sol);
		
	public Arquivo editar(Arquivo arquivo);
	
	public void excluir(Arquivo arquivo);
	
}
