package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Disciplina;

public class JDBCDisciplinaDAO implements DisciplinaDAO{
	
	@Override
	public Disciplina cadastrar(Disciplina dis) {
		String sql = "insert into academus.disciplina(id_disciplina, nome, carga, creditos) values (?, ?, ?, ?)";
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setString(1, dis.getId());
			insert.setString(2, dis.getNome());
			insert.setInt(3, dis.getCarga());
			insert.setInt(4, dis.getCreditos());
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return dis;
	}

	@Override
	public List<Disciplina> listar() {
		String sql = "select * from academus.disciplina";
		ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		//super.open();
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Disciplina aux = new Disciplina();
				aux.setId(rs.getString("id_disciplina"));
				aux.setNome(rs.getString("nome"));
				aux.setCarga(rs.getInt("carga"));
				aux.setCreditos(rs.getInt("creditos"));
				listaDisciplinas.add(aux);
			}
			
			ps.close();
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			//super.close();
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaDisciplinas;
	}

	@Override
	public Disciplina buscarPorId(String id) {
		String sql = "select * from academus.disciplina where id_disciplina = '"+id+"';";
		Disciplina aux = new Disciplina();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				aux.setId(rs.getString("id_disciplina"));
				aux.setNome(rs.getString("nome"));
				aux.setCarga(rs.getInt("carga"));
				aux.setCreditos(rs.getInt("creditos"));
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return aux;
	}

	@Override
	public List<Disciplina> buscarPorNome(String nome) {
		String sql = "select * from academus.disciplina where nome like '%?%';";
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Disciplina aux = new Disciplina();
				aux.setId(rs.getString("id_disciplina"));
				aux.setNome(rs.getString("nome"));
				aux.setCarga(rs.getInt("carga"));
				aux.setCreditos(rs.getInt("creditos"));
				
				listaDisciplinas.add(aux);
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaDisciplinas;
	}

	@Override
	public Disciplina editar(Disciplina dis) {
		String sql = "UPDATE academus.disciplina SET id_disciplina = ?, nome = ?, carga = ?, creditos = ? WHERE id_disciplina = ?";
		
		Connection conn = ConnectionPool.getConnection();
		try {			
			PreparedStatement update = conn.prepareStatement(sql);
			
			update.setString(1, dis.getId());
			update.setString(2, dis.getNome());			
			update.setInt(3, dis.getCarga());
			update.setInt(4, dis.getCreditos());
			update.setString(5, dis.getId());
			
			update.executeQuery();
			update.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return dis;
	}

	@Override
	public void excluir(Disciplina dis) {
		String sql = "delete from academus.disciplina where id_disciplina = "+dis.getId()+";";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
	}
	
}
