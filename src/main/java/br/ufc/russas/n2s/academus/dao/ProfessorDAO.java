package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Professor;

public interface ProfessorDAO {
		
	public Professor cadastrar(Professor professor);
	
	public Professor editar(Professor professor);
	
	public List<Professor> listar();
	
	public Professor buscarPorId(int id);
	
	public Professor buscarPorSiape(String siape);
	
	//public boolean isProfessor(Pessoa pessoa);
}
