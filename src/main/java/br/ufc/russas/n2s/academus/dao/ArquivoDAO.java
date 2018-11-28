package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;

public interface ArquivoDAO {
	
	public Arquivo cadastrarArquivo(Arquivo arq, int dis);
		
	public Arquivo buscarPorDisciplinaCursada(DisciplinaCursada dis);
		
	public Arquivo editar(Arquivo arquivo);
	
	public void excluir(Arquivo arquivo);
	
}
