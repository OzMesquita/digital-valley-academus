package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.Conexao;

public abstract class JDBCDAO {
	
	private Connection connection;
	
	protected Connection getConnection() {
		return connection;
	}

	protected void setConnection(Connection connection) {
		this.connection = connection;
	}

	protected void open() {
		setConnection(Conexao.getConexao());
	}
	
	protected void close() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
