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
	
	//Singleton
	private static Connection conn = null;
	private static Conexao conexao = null;
	
	private Conexao(){
		//Abre a conexão com o banco
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
		
	}

	public static String getStatus() {
		return status;
	}
	
	public static Connection getConexao(){
		if(Conexao.conn == null){
			Conexao.conexao = new Conexao();
		}
		return Conexao.conn;
	}
	
}
