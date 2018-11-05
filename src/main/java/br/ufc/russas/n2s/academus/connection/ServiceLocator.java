package br.ufc.russas.n2s.academus.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ServiceLocator {

	static private Connection conn;
	
	private ServiceLocator(){
		super();
	}
	
	private static Connection getConexao() {
		
		ServiceLocator.conn = null;
		try { 
			InitialContext contexto = new InitialContext();
			DataSource ds = (DataSource)contexto.lookup("java:comp/env/jdbc/academus");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.getMessage();
		}
		return conn;
	}
	
	public static Connection getInstance(){
		if(ServiceLocator.conn == null){
			getConexao();
		}
		return ServiceLocator.conn;
	}
}