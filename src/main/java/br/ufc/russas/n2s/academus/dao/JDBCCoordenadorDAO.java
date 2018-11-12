package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;
import br.ufc.russas.n2s.academus.model.Professor;

public class JDBCCoordenadorDAO implements CoordenadorDAO{

	@Override
	public Coordenador cadastrarCoordedanador(Coordenador cord) {
		String sql = "INSERT INTO academus.coordenador(id_pessoa, id_curso) VALUES (?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setInt(1, cord.getId());
			insert.setInt(2, cord.getCurso().getIdCurso());
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return cord;
	}

	@Override
	public Coordenador buscarPorId(int idCoordenador) {
		String sql = "SELECT id_pessoa, id_curso FROM academus.coordenador WHERE id_pessoa = ?;";
		Coordenador cord = new Coordenador();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement buscar = conn.prepareStatement(sql);
			buscar.setInt(1, idCoordenador);
			
			ResultSet rs = buscar.executeQuery();
			
			if(rs.next()){				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				Professor prof = new DAOFactoryJDBC().criarProfessorDAO().buscarPorId(rs.getInt("id_pessoa"));
				
				//Pessoa
				cord.setId(prof.getId());
				cord.setNome(prof.getNome());
				cord.setCpf(prof.getCpf());
				cord.setEmail(prof.getEmail());
				cord.setImagem(prof.getImagem());
				cord.setUsuario(prof.getUsuario());
				cord.setDataNascimento(prof.getDataNascimento());
				
				//Servidor
				cord.setCargo(prof.getCargo());
				cord.setSiape(prof.getSiape());
				
				//Professor
				cord.setDisciplinas(prof.getDisciplinas());
				
				//Coordenador
				cord.setCurso(curso);								
			}
			
			rs.close();
			buscar.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return cord;
	}
	
	@Override
	public Coordenador buscarPorCurso(Curso curso) {
		String sql = "SELECT id_pessoa FROM academus.coordenador WHERE id_curso = ?;";
		Coordenador cord = new Coordenador();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement buscar = conn.prepareStatement(sql);
			buscar.setInt(1, curso.getIdCurso());
			
			ResultSet rs = buscar.executeQuery();
			
			if(rs.next()){
				Professor prof = new DAOFactoryJDBC().criarProfessorDAO().buscarPorId(rs.getInt("id_pessoa"));
				
				//Pessoa
				cord.setId(prof.getId());
				cord.setNome(prof.getNome());
				cord.setCpf(prof.getCpf());
				cord.setEmail(prof.getEmail());
				cord.setImagem(prof.getImagem());
				cord.setUsuario(prof.getUsuario());
				cord.setDataNascimento(prof.getDataNascimento());
				
				//Servidor
				cord.setCargo(prof.getCargo());
				cord.setSiape(prof.getSiape());
				
				//Professor
				cord.setDisciplinas(prof.getDisciplinas());
				
				//Coordenador
				cord.setCurso(curso);
			}
			
			rs.close();
			buscar.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return cord;
	}

	@Override
	public Coordenador editar(Coordenador cord) {
		String sql = "UPDATE academus.coordenador SET id_curso=? WHERE id_pessoa=?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement editar = conn.prepareStatement(sql);
			editar.setInt(1, cord.getCurso().getIdCurso());
			editar.setInt(2, cord.getId());
			
			editar.execute();
			editar.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return cord;
	}

	@Override
	public void excluir(Coordenador cord) {
		String sql = "DELETE FROM academus.coordenador WHERE id_pessoa=?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement excluir = conn.prepareStatement(sql);
			excluir.setInt(1, cord.getId());
			
			excluir.execute();
			excluir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
	}

}
