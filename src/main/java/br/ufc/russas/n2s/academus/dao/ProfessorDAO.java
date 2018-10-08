package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.modelo.Professor;

public interface ProfessorDAO {
	
	public Professor cadastrar(Professor prof);
	
	public List<Professor> listar();
	
	public Professor buscarPorId(int id);
	
	public Professor editar(Professor prof);
	
	public void excluir(Professor prof);
	
}
