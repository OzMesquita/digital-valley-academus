package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.Funcionario;
import br.ufc.russas.n2s.academus.model.RecorrecaoDeProva;
import br.ufc.russas.n2s.academus.model.StatusRecorrecao;

public interface RecorrecaoDeProvaDAO {

		
		public RecorrecaoDeProva cadastro(RecorrecaoDeProva RecorrecaoDeProva);
		
		public List<RecorrecaoDeProva> listar(int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listar(Aluno aluno, int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listar(Professor professor, int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listarCoordenador(Professor professor, int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listarPorStatus(StatusRecorrecao status, int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listarPorStatus(Aluno aluno, StatusRecorrecao status, int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listarPorStatus(Professor professor, StatusRecorrecao status, int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listarPorStatusCoordenador(Professor coordenador, StatusRecorrecao status, int limitInf, int limitSup);
		
		public RecorrecaoDeProva buscarPorId(int idRecorrecaoDeProva);
		
		public RecorrecaoDeProva editar(RecorrecaoDeProva RecorrecaoDeProva);
		
		public void excluir(RecorrecaoDeProva RecorrecaoDeProva);
		
		//Funções para a paginação
		public int numSolicitacoes(int pagina);
		public int numSolicitacoes(int pagina, Aluno aluno);
		public int numSolicitacoes(int pagina, Professor professor);
		public int numSolicitacoesCoordenador(int pagina, Professor professor);
		public int numSolicitacoesPorStatus(int pagina, StatusRecorrecao status);
		public int numSolicitacoesPorStatus(int pagina, Aluno aluno, StatusRecorrecao status);
		public int numSolicitacoesPorStatus(int pagina, Professor professor, StatusRecorrecao status);
		public int numSolicitacoesCoordenadorPorStatus(int pagina, Professor coordenador, StatusRecorrecao status);

		

	


}
