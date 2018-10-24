package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Curso;

public interface CursoDAO {
	
	public Curso cadastrar(Curso curso);
	
	public List<Curso> listar();
	
	public Curso buscarPorId(int idCurso);
	
	public Curso editar(Curso curso);
	
	public void excluir(Curso curso);
	
}
