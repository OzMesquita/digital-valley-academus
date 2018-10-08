package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.modelo.Curso;

public interface CursoDAO {
	
	public Curso cardastrar(Curso curso);
	
	public Curso buscarPorId(int idCurso);
	
	public Curso editar(Curso curso);
	
	public void excluir(Curso curso);
	
}
