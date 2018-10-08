package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.NivelAcademus;

public class JDBCNivelAcademusDAO implements NivelAcademusDAO{
	private Connection connection;

	public JDBCNivelAcademusDAO() {
		connection = Conexao.getConexao();
	}

	@Override
	public NivelAcademus buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NivelAcademus editar(NivelAcademus nivel) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
