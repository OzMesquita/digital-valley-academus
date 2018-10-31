package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.debug.IOConexao;
import br.ufc.russas.n2s.academus.debug.Operacao;

public abstract class JDBCDAO {
	
	private Connection connection;
	
	protected Connection getConnection() {
		return connection;
	}

	protected void setConnection(Connection connection) {
		this.connection = connection;
	}

	protected void open() {
		IOConexao.add(this.getClass().getName(), Operacao.ENTROU);
		setConnection(Conexao.getConexao());
	}
	
	protected void close() {
		IOConexao.add(this.getClass().getName(), Operacao.SAIO);
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
