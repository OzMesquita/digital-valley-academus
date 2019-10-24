package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.SegundaChamada;

public interface SegundaChamadaDAO {
	
	public SegundaChamada cadastro(SegundaChamada segundaChamada);
	
	public List<SegundaChamada> listar(int limitInf, int limitSup);
	
	public List<SegundaChamada> listar(Aluno aluno, int limitInf, int limitSup);
	
	public List<SegundaChamada> listar(Professor professor, int limitInf, int limitSup);
	
	public List<SegundaChamada> listarCoordenador(Professor professor, int limitInf, int limitSup);
	
	public SegundaChamada buscarPorId(int idSegundaChamada);
	
	public SegundaChamada editar(SegundaChamada segundaChamada);
	
	public void excluir(SegundaChamada segundaChamada);
	
	//Funções para a paginação
	public int numSolicitacoes(int pagina);
	public int numSolicitacoes(int pagina, Aluno aluno);
	public int numSolicitacoes(int pagina, Professor professor);
	public int numSolicitacoesCoordenador(int pagina, Professor professor);

}
