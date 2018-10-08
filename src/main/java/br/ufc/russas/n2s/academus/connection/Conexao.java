package br.ufc.russas.n2s.academus.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	//192.169.1.2
	private static String url="jdbc:postgresql://192.169.1.2:5432/n2s-ufc";
	private static String usuario="postgres";
	private static String senha="postgres"; 	
	static String status ="";

	public static String getStatus() {
		return status;
	}
	
	public static Connection getConexao(){
		Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(url+"?user="+usuario+"&password="+senha);
			status = "Conexao Aberta";
		}catch(SQLException e){
			status = e.getMessage();
		}catch(ClassNotFoundException e){
			status = e.getMessage();
		}catch(Exception e){
			status = e.getMessage();
		}
		return conn;
	}
}