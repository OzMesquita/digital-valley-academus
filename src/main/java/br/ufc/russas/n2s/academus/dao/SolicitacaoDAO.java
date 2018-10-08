package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public interface SolicitacaoDAO {
	
	public Solicitacao cadastrar(Solicitacao sol);
	
	public Solicitacao buscarSolicitacaoId(int idSolicitacao);
	
	public List<Solicitacao> listar();
		
	public Solicitacao buscarPorId(int id);
	
	public Solicitacao editar(Solicitacao sol);
	
	public void excluir(Solicitacao sol);
	
	public List<Solicitacao> listar(Coordenador c);
	
	public List<Solicitacao> listar(Aluno a);
	
}
