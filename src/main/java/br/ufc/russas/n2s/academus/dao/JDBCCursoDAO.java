package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Curso;

public class JDBCCursoDAO implements CursoDAO{

	@Override
	public Curso cadastrar(Curso curso) {
		String sql = "INSERT INTO academus.curso(nome) VALUES (?);";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement inserir = conn.prepareStatement(sql);
			inserir.setString(1, curso.getNome());
			
			inserir.execute();
			inserir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return curso;
	}

	@Override
	public List<Curso> listar() {
		String sql = "SELECT academus.curso.id_curso, academus.curso.nome "
				+ "FROM academus.curso LEFT JOIN academus.coordenador "
				+ "ON academus.curso.id_curso = academus.coordenador.id_curso ORDER BY id_curso";
		List<Curso> cursos = new ArrayList<Curso>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement listar = conn.prepareStatement(sql);
			ResultSet rs = listar.executeQuery();
			
			while(rs.next()){				
				Curso curso = new Curso();
				curso.setIdCurso(rs.getInt("id_curso"));
				curso.setNome(rs.getString("nome"));
				
				cursos.add(curso);
			}
			
			listar.close();
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return cursos;
	}
	
	@Override
	public Curso buscarPorId(int idCurso) {
		String sql = "SELECT academus.curso.id_curso, academus.curso.nome "
				+ "FROM academus.curso LEFT JOIN academus.coordenador "
				+ "ON academus.curso.id_curso = academus.coordenador.id_curso "
				+ "WHERE academus.curso.id_curso=?;";
		
		Curso curso = new Curso();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement listar = conn.prepareStatement(sql);
			listar.setInt(1, idCurso);
			
			ResultSet rs = listar.executeQuery();
			
			if(rs.next()){
				
				curso.setIdCurso(rs.getInt("id_curso"));
				curso.setNome(rs.getString("nome"));
			}
			
			listar.close();
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return curso;
	}

	@Override
	public Curso editar(Curso curso) {
		String sql = "UPDATE academus.curso SET nome=? WHERE id_curso=?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement editar = conn.prepareStatement(sql);
			editar.setString(1, curso.getNome());
			editar.setInt(2, curso.getIdCurso());
			
			editar.execute();
			editar.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return curso;
	}

	@Override
	public void excluir(Curso curso) {
		String sql = "DELETE FROM academus.curso WHERE id_curso=?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement excluir = conn.prepareStatement(sql);
			excluir.setInt(1, curso.getIdCurso());
			
			excluir.execute();
			excluir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
	}
	
	public boolean possuiCoordenador(int idCurso){
		String sql = "SELECT id_pessoa FROM academus.coordenador where id_curso = ?";
		Connection conn = ConnectionPool.getConnection();
		boolean resultado = false;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idCurso);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("id_pessoa") > 0) resultado = true;
			}
			rs.close();
			ps.close();
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		return resultado;
	}

}
