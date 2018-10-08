package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.modelo.ComponenteCurricular;
import br.ufc.russas.n2s.academus.modelo.Disciplina;
import br.ufc.russas.n2s.academus.modelo.MatrizCurricular;

public interface ComponenteCurricularDAO {
	
	public ComponenteCurricular cadastrar(ComponenteCurricular comp);
	
	public List<ComponenteCurricular> listar(MatrizCurricular matriz);
	
	public ComponenteCurricular buscarPorId(int idComponente, MatrizCurricular matriz);
	
	public ComponenteCurricular buscarPorId(int idComponente);
		
	public void inserirPreRequsitos(List<Disciplina> d, int idComponente);
	
	public List<Disciplina> buscarPreRequisitos(int idComponente);
	
	public void excluirPreRequisitos(int idComponente, Disciplina disciplina);
	
	public void excluirComponente(ComponenteCurricular comp);
	
}
