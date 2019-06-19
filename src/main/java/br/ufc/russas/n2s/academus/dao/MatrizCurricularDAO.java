package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.MatrizCurricular;

public interface MatrizCurricularDAO {
	
	public MatrizCurricular cadastrar(MatrizCurricular mat);
	
	public List<MatrizCurricular> listar(int limiteInf, int limiteSup);
	
	public List<MatrizCurricular> listar();
	
	public MatrizCurricular buscarPorId(int idMatriz);
	
	public MatrizCurricular buscarPorSolicitacao(int idSolicitacao);
	
	public List<MatrizCurricular> buscarPorCurso(int idCurso);
	
	public List<MatrizCurricular> buscarAtivosPorCurso(int idCurso);
	
	public List<MatrizCurricular> buscarPorCurso(int idCurso, int limiteInf, int limiteSup);
	
	public List<MatrizCurricular> buscarPorNome(String nome, int limiteInf, int limiteSup);
	
	public MatrizCurricular editar(MatrizCurricular mat);
	
	public void editarVigente(int id_curso);
	
	public boolean verificarSeEstaSendoUtilizada(int idMat);
	
	public void excluir(MatrizCurricular mat);
		
}
