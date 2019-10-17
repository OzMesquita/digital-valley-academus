package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.Professor;

public interface ProfessorDAO {
		
	public Professor cadastrar(Professor professor);
	
	public Professor editar(Professor professor);
	
	public List<Professor> listar();
	
	public List<Disciplina> listarDisciplinas(int id);
	
	public void removeDisciplinas(int idProfessor);
	
	public Professor buscarPorId(int id);
	
	public Professor buscarPorSiape(String siape);
	
	public List<Professor> buscarPorNome(String nome);
	
	public boolean possuiCoordenador(int id_curso);
	
	public void retirarCoordenador(int id_curso, Professor professor);
	
	public void cadastrarCoordenador(int id_curso, Professor professor);

	public Professor isCoordenador(int idCurso);

	public Professor buscarPorCPF(String cpf);
	
	//public boolean isProfessor(Pessoa pessoa);
}
