package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.Professor;

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
	public List<Disciplina> listar(){
		String sql = "select * from academus.disciplina order by id_disciplina;";
		ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
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
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaDisciplinas;
	}

	@Override
	public List<Disciplina> listar(int limiteInf, int limiteSup) {
		String sql = "select * from academus.disciplina order by id_disciplina offset ? limit ?;";
		ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, limiteInf);
			ps.setInt(2, limiteSup);
			
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
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaDisciplinas;
	}

	@Override
	public Disciplina buscarPorId(String id) {
		String sql = "select * from academus.disciplina where id_disciplina = '"+id+"';";
		Disciplina aux = null;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				aux = new Disciplina();
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
		String sql = "select * from academus.disciplina where nome ilike '%"+nome+"%' order by id_disciplina";
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
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
	public List<Disciplina> buscarPorNome(String nome, int limiteInf, int limiteSup) {
		String sql = "select * from academus.disciplina where nome ilike '%"+nome+"%' order by id_disciplina offset ? limit ?";
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, limiteInf);
			ps.setInt(2, limiteSup);
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
	
	/**
	 * Função que retorna disciplinas de um determinado curso, filtradas pelo id ou pelo nome.
	 */
	@Override
	public List<Disciplina> buscarPorCurso(int idCurso, String query) {
		String sql = "SELECT * FROM academus.disciplina AS d, academus.componente_curricular AS c, academus.matriz_curricular AS m WHERE d.id_disciplina = c.id_disciplina AND m.id_matriz = c.id_matriz AND m.id_curso = ? AND (d.nome ilike '%"+query+"%' OR d.id_disciplina ilike '%"+query+"%') order by c.id_disciplina";
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idCurso);
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
			
			update.executeUpdate();
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
		String sql = "DELETE FROM academus.disciplina where id_disciplina = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dis.getId());
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
	}
	
	//Métodos para paginação
	
	@Override
	public int countDisciplina(int pagina) {
		String sql = "WITH cte AS (SELECT * FROM academus.disciplina) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pagina*10);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				resultSql = rs.getInt(1);
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return resultSql;
	}
	
	@Override
	public int countDisciplina(int pagina, String nome) {
		String sql = "WITH cte AS (SELECT * FROM academus.disciplina where nome ilike '%"+nome+"%') " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pagina*10);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				resultSql = rs.getInt(1);
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return resultSql;
	}
	
	public void addProfessor(Professor professor, Disciplina disciplina) {
		String sql = "INSERT INTO academus.disciplina_professor(id_disciplina, id_perfil_academus) values (?, ?)";
		Connection con = ConnectionPool.getConnection();
		try {
			PreparedStatement insert = con.prepareStatement(sql);
			insert.setString(1, disciplina.getId());
			insert.setInt(2, professor.getId());
			
			insert.execute();
			insert.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
}
