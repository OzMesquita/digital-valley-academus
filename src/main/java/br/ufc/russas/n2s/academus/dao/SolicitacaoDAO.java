package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public interface SolicitacaoDAO {
	
	public Solicitacao cadastrar(Solicitacao sol);
		
	public List<Solicitacao> listar(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAnalizado(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAndamento(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarFinalizado(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarSubmetida(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarValidada(int limiteInf, int limiteSup);
	
	public Solicitacao buscarPorId(int id);
	
	public Solicitacao editar(Solicitacao sol);
	
	public void excluir(Solicitacao sol);
	
	public List<Solicitacao> listar(Coordenador c, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listar(Aluno a, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAnalizado(Coordenador c, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAndamento(Aluno a, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAndemanto(Coordenador c, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarFinalizado(Aluno a, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarFinalizado(Coordenador c, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarSubmetida(Aluno a, int limiteInf, int limiteSup);
	
	public int idUltimaSolicitacao(String matricula);
}
