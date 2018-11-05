package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public class JDBCArquivoDAO implements ArquivoDAO{

	public Arquivo cadastrarArquivo(Arquivo arq, Solicitacao sol) {
		String sql = "INSERT INTO academus.arquivo(caminho, id_solicitacao) VALUES (?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, arq.getCaminho());
			insert.setInt(2, sol.getIdSolicitacao());
			
			insert.execute();
			insert.close();
			
		} catch(SQLException e) {	
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return arq;
	}

	@Override
	public Arquivo buscarPorSolicitacao(Solicitacao sol){
		String sql = "SELECT id_arquivo, caminho FROM academus.arquivo WHERE id_solicitacao = " + sol.getIdSolicitacao() + ";";
		Arquivo arq = new Arquivo();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				arq.setIdArquivo(rs.getInt("id_arquivo"));
				arq.setCaminho(rs.getString("caminho"));
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return arq;
	}

	@Override
	public Arquivo editar(Arquivo arquivo) {
		String sql = "UPDATE academus.arquivo SET caminho=? WHERE id_arquivo = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement editar = conn.prepareStatement(sql);
			editar.setString(1, arquivo.getCaminho());
			editar.setInt(2, arquivo.getIdArquivo());
		
			editar.execute();
			editar.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return arquivo;
	}

	@Override
	public void excluir(Arquivo arquivo) {
		String sql = "DELETE FROM academus.arquivo WHERE id_arquivo = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement excluir = super.getConnection().prepareStatement(sql);
			excluir.setInt(1, arquivo.getIdArquivo());
			
			excluir.execute();
			excluir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
	}
}
	
