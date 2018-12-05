package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.TipoArquivo;

public class JDBCArquivoDAO implements ArquivoDAO{

	public Arquivo cadastrarArquivo(Arquivo arq, int dis) {
		String sql = "INSERT INTO academus.arquivo(caminho, id_disciplina_cursada, nome, tipo) VALUES (?, ?, ?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, arq.getCaminho());
			insert.setInt(2, dis);
			insert.setString(3, arq.getNome());
			insert.setInt(4, TipoArquivo.getId(arq.getTipo()));
			
			insert.execute();
			insert.close();
			
		} catch(SQLException e) {	
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return arq;
	}
	
	public Arquivo buscarPorDisciplinaCursada(DisciplinaCursada dis, TipoArquivo ta){
		String sql = "SELECT id_arquivo, caminho, nome, tipo FROM academus.arquivo WHERE id_disciplina_cursada = " + dis.getId() + " and tipo = " + TipoArquivo.getId(ta) + ";";
		Arquivo arq = new Arquivo();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				arq.setIdArquivo(rs.getInt("id_arquivo"));
				arq.setCaminho(rs.getString("caminho"));
				arq.setNome(rs.getString("nome"));
				arq.setTipo(TipoArquivo.getTipoArquivo(rs.getInt("tipo")));
				
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
		String sql = "UPDATE academus.arquivo SET caminho=?, nome=?, tipo=? WHERE id_arquivo = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement editar = conn.prepareStatement(sql);
			editar.setString(1, arquivo.getCaminho());
			editar.setString(2, arquivo.getNome());
			editar.setInt(3, TipoArquivo.getId(arquivo.getTipo()));
			editar.setInt(4, arquivo.getIdArquivo());
		
			editar.executeUpdate();
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
			
			excluir.executeUpdate();
			excluir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
	}
}
	
