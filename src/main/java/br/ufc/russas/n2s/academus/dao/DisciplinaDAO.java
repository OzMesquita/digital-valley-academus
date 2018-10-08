package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.modelo.Disciplina;

public interface DisciplinaDAO {
	
	public Disciplina cadastrar(Disciplina dis);
	
	public List<Disciplina> listar();
	
	public Disciplina buscarPorId(int id);
	
	public List<Disciplina> buscarPorNome(String nome);
	
	public Disciplina editar(Disciplina dis);
	
	public void excluir(Disciplina dis);
	
}
