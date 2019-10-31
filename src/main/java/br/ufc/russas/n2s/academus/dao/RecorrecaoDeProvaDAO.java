package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.RecorrecaoDeProva;

public interface RecorrecaoDeProvaDAO {

		
		public RecorrecaoDeProva cadastro(RecorrecaoDeProva RecorrecaoDeProva);
		
		public List<RecorrecaoDeProva> listar(int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listar(Aluno aluno, int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listar(Professor professor, int limitInf, int limitSup);
		
		public List<RecorrecaoDeProva> listarCoordenador(Professor professor, int limitInf, int limitSup);
		
		public RecorrecaoDeProva buscarPorId(int idRecorrecaoDeProva);
		
		public RecorrecaoDeProva editar(RecorrecaoDeProva RecorrecaoDeProva);
		
		public void excluir(RecorrecaoDeProva RecorrecaoDeProva);
		
		//Funções para a paginação
		public int numSolicitacoes(int pagina);
		public int numSolicitacoes(int pagina, Aluno aluno);
		public int numSolicitacoes(int pagina, Professor professor);
		public int numSolicitacoesCoordenador(int pagina, Professor professor);

	


}
