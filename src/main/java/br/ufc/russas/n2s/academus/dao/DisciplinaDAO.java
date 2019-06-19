package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Disciplina;

public interface DisciplinaDAO {
	
	public Disciplina cadastrar(Disciplina dis);
	
	public List<Disciplina> listar();
	
	public List<Disciplina> listar(int limiteInf, int limiteSup);
	
	public Disciplina buscarPorId(String id);
	
	public List<Disciplina> buscarPorNome(String nome);
	
	public Disciplina editar(Disciplina dis);
	
	public void excluir(Disciplina dis);

	public List<Disciplina> buscarPorNome(String nome, int limiteInf, int limiteSup);
	
	public int countDisciplina(int pagina);
	
	public int countDisciplina(int pagina, String nome);
	
}
