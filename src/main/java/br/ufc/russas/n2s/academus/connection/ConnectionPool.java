package br.ufc.russas.n2s.academus.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionPool {
	
	private static List<Connection> pool;
    private static List<Connection> usedConnections;
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 40;
    
    private static ConnectionPool INSTANCE = null;
		
	private ConnectionPool(){
		ConnectionPool.pool = Collections.synchronizedList(new ArrayList<>());
		for(int qtdConexoes = 0; qtdConexoes < INITIAL_POOL_SIZE; qtdConexoes++){
			ConnectionPool.pool.add(ConnectionPool.createConnection());
		}
		ConnectionPool.usedConnections = Collections.synchronizedList(new ArrayList<>());
	}
	
	public static synchronized Connection getConnection(){
		if(ConnectionPool.INSTANCE == null){
			ConnectionPool.INSTANCE = new ConnectionPool();
		}
		
		Connection conexao = ConnectionPool.pool.remove(ConnectionPool.pool.size()-1);
		ConnectionPool.usedConnections.add(conexao);
		
		return conexao;
	}
	
	public static synchronized boolean releaseConnection(Connection e){
		if(ConnectionPool.INSTANCE == null){
			ConnectionPool.INSTANCE = new ConnectionPool();
		}
		
		ConnectionPool.pool.add(e);
		return ConnectionPool.usedConnections.remove(e);
	}
	
	private static Connection createConnection(){
		String url = "jdbc:postgresql://192.169.1.2:5432/n2s-ufc";
		String usuario = "postgres";
		String senha = "postgres";
		
		Connection conn = null;
		
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(url+"?user="+usuario+"&password="+senha);
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConections(){
		for(int i = 0; i < ConnectionPool.pool.size(); i++){
			Connection conn = ConnectionPool.pool.remove(ConnectionPool.pool.size()-1);
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < ConnectionPool.usedConnections.size(); i++){
			Connection conn = ConnectionPool.usedConnections.remove(ConnectionPool.usedConnections.size()-1);
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}