package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.modelo.Aluno;

public interface AlunoDAO {
	
	public Aluno buscarPorId(int id);
	
	public Aluno buscarPorMatricula(String matricula);
	
	public List<Aluno> buscarPorNome(String nome);
	
	public List<Aluno> listar();
	
	public Aluno editar(Aluno aluno);
	
}
