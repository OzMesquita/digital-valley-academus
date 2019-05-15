package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;


public class JDBCPerfilAcademusDAO implements PerfilAcademusDAO{

	@Override
	public PerfilAcademus cadastrar(PerfilAcademus perfil) {
		//String sql = "INSERT INTO academus.perfil_academus(id_pessoa_usuario, id_nivel, is_admin) VALUES (?, ?, ?);";
		String sql = "INSERT INTO academus.perfil_academus( id_pessoa_usuario, id_nivel, is_admin, nome, email, cpf, id_curso) VALUES (?, ?, ?, ?, ?, ?, ?);";
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setInt(1, perfil.getIdGuardiao());
			insert.setInt(2, NivelAcademus.getCodigo(perfil.getNivel()));
			insert.setBoolean(3, perfil.getIsAdmin());
			insert.setString(4, perfil.getNome());
			insert.setString(5, perfil.getEmail());
			insert.setString(6, perfil.getCPF());
			insert.setInt(7, perfil.getCurso().getIdCurso());
			
			insert.execute();
			insert.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfil;
	}

	@Override
	public List<PerfilAcademus> listar() {
		//String sql = "SELECT id_pessoa_usuario, id_nivel, is_admin FROM academus.perfil_academus order by id_pessoa_usuario;";
		String sql = "SELECT * FROM academus.perfil_academus Order by id_perfil_academus;";
		ArrayList<PerfilAcademus> perfis = new ArrayList<PerfilAcademus>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			CursoDAO curdao = new DAOFactoryJDBC().criarCursoDAO();
			
			while (rs.next()) {
				PerfilAcademus perfil = new PerfilAcademus();
				
				perfil.setIdGuardiao(rs.getInt("id_pessoa_usuario"));
				perfil.setId(rs.getInt("id_perfil_academus"));
				perfil.setIsAdmin(rs.getBoolean("is_admin"));
				perfil.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				perfil.setNome(rs.getString("nome"));
				perfil.setEmail(rs.getString("email"));
				perfil.setCPF(rs.getString("cpf"));
				perfil.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				perfis.add(perfil);
			}
			
			ps.close();
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfis;
	}

	@Override
	public PerfilAcademus buscarPorId(int id) {
		//String sql = "SELECT id_pessoa_usuario, id_nivel, is_admin FROM academus.perfil_academus WHERE id_pessoa_usuario = "+ id +";";
		String sql = "SELECT * FROM academus.perfil_academus WHERE id_perfil_academus = "+ id +";";
		PerfilAcademus perfil = new PerfilAcademus();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			CursoDAO curdao = new DAOFactoryJDBC().criarCursoDAO();
			
			if(rs.next()){
				
				perfil.setIdGuardiao(rs.getInt("id_pessoa_usuario"));
				perfil.setId(rs.getInt("id_perfil_academus"));
				perfil.setIsAdmin(rs.getBoolean("is_admin"));
				perfil.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				perfil.setNome(rs.getString("nome"));
				perfil.setEmail(rs.getString("email"));
				perfil.setCPF(rs.getString("cpf"));
				perfil.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
			}
			
			ps.close();
			rs.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfil;
	}
	
	@Override
	public int buscarPorIdAcademus(int idGuardiao) {
		//String sql = "SELECT id_pessoa_usuario, id_nivel, is_admin FROM academus.perfil_academus WHERE id_pessoa_usuario = "+ id +";";
		String sql = "SELECT id_perfil_academus FROM academus.perfil_academus WHERE id_pessoa_usuario = "+ idGuardiao +";";
		
		int idAcademus = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			CursoDAO curdao = new DAOFactoryJDBC().criarCursoDAO();
			
			if(rs.next()){
				
				idAcademus = rs.getInt("id_perfil_academus");
				
			}
			
			ps.close();
			rs.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return idAcademus;
	}
	
	@Override
	public PerfilAcademus buscarPorCPF(String cpf) {
		String sql = "SELECT * FROM academus.perfil_academus WHERE cpf = ?;";
		PerfilAcademus perfil = new PerfilAcademus();
		
		Connection conn = ConnectionPool.getConnection();
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			CursoDAO curdao = new DAOFactoryJDBC().criarCursoDAO();
			
			if(rs.next()){
				
				perfil.setIdGuardiao(rs.getInt("id_pessoa_usuario"));
				perfil.setId(rs.getInt("id_perfil_academus"));
				perfil.setIsAdmin(rs.getBoolean("is_admin"));
				perfil.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				perfil.setNome(rs.getString("nome"));
				perfil.setEmail(rs.getString("email"));
				perfil.setCPF(rs.getString("cpf"));
				//perfil.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
			}
			
			ps.close();
			rs.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfil;
	}
	
	@Override
	public List<PerfilAcademus> buscarPorNome(String nome) {
		String sql = "SELECT * FROM academus.perfil_academus WHERE nome LIKE ?;";
		
		ArrayList<PerfilAcademus> perfis = new ArrayList<PerfilAcademus>();
		Connection conn = ConnectionPool.getConnection();
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();
			
			CursoDAO curdao = new DAOFactoryJDBC().criarCursoDAO();
			
			while(rs.next()){
				PerfilAcademus perfil = new PerfilAcademus();
				
				perfil.setIdGuardiao(rs.getInt("id_pessoa_usuario"));
				perfil.setId(rs.getInt("id_perfil_academus"));
				perfil.setIsAdmin(rs.getBoolean("is_admin"));
				perfil.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				perfil.setNome(rs.getString("nome"));
				perfil.setEmail(rs.getString("email"));
				perfil.setCPF(rs.getString("cpf"));
				perfil.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				perfis.add(perfil);
				
			}
			
			ps.close();
			rs.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfis;
	}

	@Override
	public PerfilAcademus editar(PerfilAcademus perfil) {
		String sql = "UPDATE academus.perfil_academus SET id_nivel=?, is_admin=?, nome=?, email=?, cpf=?, id_curso=? WHERE id_perfil_academus = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement editar = conn.prepareStatement(sql);
			editar.setInt(1, NivelAcademus.getCodigo(perfil.getNivel()));
			editar.setBoolean(2, perfil.getIsAdmin());
			editar.setString(3, perfil.getNome());
			editar.setString(4, perfil.getEmail());
			editar.setString(5, perfil.getCPF());
			editar.setInt(6, perfil.getCurso().getIdCurso());
			
			editar.setInt(7, perfil.getId());
			
			editar.executeUpdate();
			editar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfil;
	}

	@Override
	public void excluir(PerfilAcademus perfil) {
		String sql = "DELETE FROM academus.perfil_academus WHERE id_perfil_academus = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement excluir = conn.prepareStatement(sql);
			excluir.setInt(1, perfil.getId());
			
			excluir.executeUpdate();
			excluir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
	}

}
