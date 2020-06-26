package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.TipoArquivo;

public interface ArquivoDAO {
	
	public Arquivo cadastrarArquivo(Arquivo arq, int dis);
	
	public Arquivo cadastrarArquivo(Arquivo arq);
	
	public int idUltimoArquivo();
		
	public Arquivo buscarPorDisciplinaCursada(DisciplinaCursada dis, TipoArquivo ta);
		
	public Arquivo editar(Arquivo arquivo);
	
	public void excluir(Arquivo arquivo);
	
}
