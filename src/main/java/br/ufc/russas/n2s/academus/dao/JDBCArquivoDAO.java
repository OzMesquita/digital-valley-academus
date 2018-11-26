package br.ufc.russas.n2s.academus.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public class JDBCArquivoDAO implements ArquivoDAO{

	public Arquivo cadastrarArquivo(Arquivo arq, int dis) {
		String sql = "INSERT INTO academus.arquivo(caminho, id_disciplina_cursada) VALUES (?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, arq.getCaminho());
			insert.setInt(2, dis);
			
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
	public List<Arquivo> buscarPorSolicitacao(Solicitacao sol){
		String sql = "SELECT id_arquivo, caminho FROM academus.arquivo WHERE id_solicitacao = " + sol.getIdSolicitacao() + ";";
		List<Arquivo> listaArq = new ArrayList<Arquivo>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Arquivo arq = new Arquivo();
				arq.setIdArquivo(rs.getInt("id_arquivo"));
				arq.setCaminho(rs.getString("caminho"));
				arq.setArquivo(new File(rs.getString("caminho")));
				listaArq.add(arq);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaArq;
	}
	
	public Arquivo buscarPorDisciplinaCursada(DisciplinaCursada dis){
		String sql = "SELECT id_arquivo, caminho FROM academus.arquivo WHERE id_disciplina_cursada = " + dis.getId() + ";";
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
			PreparedStatement excluir = conn.prepareStatement(sql);
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
	
