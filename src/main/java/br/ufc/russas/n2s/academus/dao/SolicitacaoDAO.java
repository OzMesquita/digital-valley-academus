package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public interface SolicitacaoDAO {
	
	public Solicitacao cadastrar(Solicitacao sol);
		
	public List<Solicitacao> listar(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAnalizado(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAndamento(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarFinalizado(int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarSubmetida(int limiteInf, int limiteSup);
	
	public Solicitacao buscarPorId(int id);
	
	public Solicitacao buscarSolicitacaoExistente(int idDisciplina, int idSolicitante);
	
	public Solicitacao editar(Solicitacao sol);
	
	public void excluir(Solicitacao sol);
	
	public List<Solicitacao> listar(Professor c, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listar(Aluno a, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAnalizado(Professor c, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAndamento(Aluno a, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarAndamento(Professor c, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarFinalizado(Aluno a, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarFinalizado(Professor c, int limiteInf, int limiteSup);
	
	public List<Solicitacao> listarSubmetida(Aluno a, int limiteInf, int limiteSup);
	
	public int idUltimaSolicitacao(String matricula);
	
	public int numSolicitacoes(int pagina);
	public int numSolicitacoes(int pagina, Aluno aluno);
	public int numSolicitacoes(int pagina, Professor coordenador);
	
	public int numSolicitacoesAnalizadas(int pagina);
	public int numSolicitacoesAnalizadas(int pagina, Professor coordenador);
	
	public int numSolicitacoesFinalizadas(int pagina);
	public int numSolicitacoesFinalizadas(int pagina, Aluno aluno);
	public int numSolicitacoesFinalizadas(int pagina, Professor coordenador);
	
	public int numSolicitacoesSubmetidas(int pagina);
	public int numSolicitacoesSubmetidas(int pagina, Aluno aluno);
	
	public int numSolicitacoesAndamento(int pagina);
	public int numSolicitacoesAndamento(int pagina, Aluno aluno);
	public int numSolicitacoesAndamento(int pagina, Professor coordenador);

}
