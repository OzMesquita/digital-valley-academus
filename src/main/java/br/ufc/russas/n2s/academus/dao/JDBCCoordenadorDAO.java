package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;
import br.ufc.russas.n2s.academus.model.Professor;
import model.Pessoa;

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
	
	public void cadastrarCoordedanador(int idCurso, int idProfessor) {
		String sql = "INSERT INTO academus.coordenador(id_pessoa, id_curso) VALUES (?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setInt(1, idProfessor);
			insert.setInt(2, idCurso);
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
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
	public Coordenador buscarPorCurso(int idCurso) {
		String sql = "SELECT * FROM academus.coordenador WHERE id_curso = ?;";
		Coordenador cord = new Coordenador();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement buscar = conn.prepareStatement(sql);
			buscar.setInt(1, idCurso);
			
			ResultSet rs = buscar.executeQuery();
			
			if(rs.next()){
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_Curso"));
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
	public void excluir(Coordenador cord) {
		String sql = "DELETE FROM academus.coordenador WHERE id_pessoa=?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement excluir = conn.prepareStatement(sql);
			excluir.setInt(1, cord.getId());
			
			excluir.executeUpdate();
			excluir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
	}

	@Override
	public boolean isCoordenador(Pessoa pessoa) {
		String sql = "SELECT academus.coordenador.id_pessoa, public.pessoa_usuario.nome "
				+ "FROM academus.coordenador INNER JOIN public.pessoa_usuario "
				+ "ON academus.coordenador.id_pessoa = public.pessoa_usuario.id_pessoa_usuario AND academus.coordenador.id_pessoa = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pessoa.getId());
			
			ResultSet rs = ps.executeQuery();
			
			boolean resultado = false;
			if(rs.next()){
				resultado = true;
			}
			ps.close();
			
			return resultado;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.closeConections();
		}
		return false;
	}
	
	public void alterarCoordenador(int idCurso, int idProfessor){
		String sql = "UPDATE academus.coordenador SET id_pessoa = ? WHERE id_curso = ?";
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idProfessor);
			ps.setInt(2, idCurso);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.closeConections();
		}
	}

}
