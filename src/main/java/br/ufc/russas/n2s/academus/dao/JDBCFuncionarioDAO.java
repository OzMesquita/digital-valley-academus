package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Funcionario;
import br.ufc.russas.n2s.academus.model.NivelAcademus;

public class JDBCFuncionarioDAO implements FuncionarioDAO{

	@Override
	public Funcionario cadastrar(Funcionario funcionario) {
		
		String sql = "INSERT INTO academus.funcionario(id_perfil_academus, siape) VALUES (?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PerfilAcademusDAO perdao = new DAOFactoryJDBC().criarPerfilAcademusDAO();
			funcionario = (Funcionario) perdao.cadastrar(funcionario);
			funcionario.setId(perdao.buscarPorIdAcademus(funcionario.getIdGuardiao()));
			
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setInt(1, funcionario.getId());
			insert.setString(2, funcionario.getSiape());
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return funcionario;
	}

	@Override
	public Funcionario editar(Funcionario funcionario) {
		String sql = "UPDATE academus.funcionario SET siape = ? WHERE id_perfil_academus = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PerfilAcademusDAO perdao = new DAOFactoryJDBC().criarPerfilAcademusDAO();
			funcionario = (Funcionario) perdao.cadastrar(funcionario);
			
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setString(1, funcionario.getSiape());
			insert.setInt(2, funcionario.getId());
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return funcionario;
	}

	@Override
	public List<Funcionario> listar() {
		
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus ORDER BY f.id_perfil_academus;";
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
			
			while (rs.next()) {
				
				
				Funcionario funcionario = new Funcionario();
				
				funcionario.setId(rs.getInt("id_perfil_academus"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCPF(rs.getString("cpf"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				funcionario.setIsAdmin(rs.getBoolean("is_admin"));
				funcionario.setSiape(rs.getString("siape"));
				funcionario.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				
				funcionarios.add(funcionario);
				
			}

			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return funcionarios;
	}

	@Override
	public Funcionario buscarPorId(int id) {
		
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND p.id_perfil_academus = ?;";
		Funcionario funcionario = null;
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
				
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id_perfil_academus"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCPF(rs.getString("cpf"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				funcionario.setIsAdmin(rs.getBoolean("is_admin"));
				funcionario.setSiape(rs.getString("siape"));
				funcionario.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				

			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return funcionario;
	}
	
	@Override
	public Funcionario buscarPorCPF(String cpf) {
		
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND p.cpf = ?;";
		Funcionario funcionario = null;
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, cpf);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
				
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id_perfil_academus"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCPF(rs.getString("cpf"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				funcionario.setIsAdmin(rs.getBoolean("is_admin"));
				funcionario.setSiape(rs.getString("siape"));
				funcionario.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				

			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return funcionario;
	}

	@Override
	public Funcionario buscarPorSiape(String siape) {
		
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND f.siape =?;";
		Funcionario funcionario = new Funcionario();
		
		Connection conn = ConnectionPool.getConnection();
		try {

			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, siape);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
				
				funcionario.setId(rs.getInt("id_perfil_academus"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCPF(rs.getString("cpf"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				funcionario.setIsAdmin(rs.getBoolean("is_admin"));
				funcionario.setSiape(rs.getString("siape"));
				funcionario.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}

		return funcionario;
	}

}
