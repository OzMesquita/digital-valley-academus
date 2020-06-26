package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;

public interface AlunoDAO {
	
	public Aluno cadastrar(Aluno aluno);
	
	public Aluno editar(Aluno aluno);
	
	public Aluno buscarPorId(int id);
	
	public Aluno buscarPorMatricula(String matricula);
	
	public List<Aluno> buscarPorNome(String nome);
	
	public List<Aluno> listar();

	public Aluno buscarPorCPF(String cpf);
		
}
