package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;

public class JDBCCoordenadorDAO implements CoordenadorDAO{
	private Connection connection;
	
	public JDBCCoordenadorDAO() {
		this.connection = Conexao.getConexao();
	}

	@Override
	public Coordenador cadastrarCoordedanador(Coordenador cord) {
		//Criar tabela para o coordenador
		String sql = "";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public Coordenador buscarPorId(int idCoordenador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coordenador buscarPorSiape(String siape) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coordenador buscarPorCurso(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coordenador editar(Coordenador cord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Coordenador cord) {
		// TODO Auto-generated method stub
		
	}

}
