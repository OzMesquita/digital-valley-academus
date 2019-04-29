package br.ufc.russas.n2s.academus.dao;

import java.util.List;

import br.ufc.russas.n2s.academus.model.Funcionario;
import br.ufc.russas.n2s.academus.model.Professor;

public interface FuncionarioDAO {

	public Funcionario cadastrar(Funcionario funcionario);
	
	public Funcionario editar(Funcionario funcionario);
	
	public List<Funcionario> listar();
	
	public Funcionario buscarPorId(int id);
	
	public Funcionario buscarPorSiape(String siape);

	public Funcionario buscarPorCPF(String cpf);
}
