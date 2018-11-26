package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public interface ArquivoDAO {
	
	public Arquivo cadastrarArquivo(Arquivo arq, int dis);
	
	public List<Arquivo> buscarPorSolicitacao(Solicitacao sol);
	
	public Arquivo buscarPorDisciplinaCursada(DisciplinaCursada dis);
		
	public Arquivo editar(Arquivo arquivo);
	
	public void excluir(Arquivo arquivo);
	
}
