package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.ComponenteCurricular;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;

public interface MatrizCurricularDAO {
	
	public MatrizCurricular cadastrar(MatrizCurricular mat);
	
	public List<MatrizCurricular> listar();
	
	public MatrizCurricular buscarPorId(int idMatriz);
	
	public List<MatrizCurricular> buscarPorNome(String nome);
	
	public MatrizCurricular editar(MatrizCurricular mat);
	
	public void excluir(MatrizCurricular mat);
	
	public void gerenciarComponentes(List<ComponenteCurricular> comps, int idMatriz);
	
}
