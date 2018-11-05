package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Professor;
import model.EnumCargo;
import model.Usuario;

public class JDBCProfessorDAO implements ProfessorDAO{
	//.:Observações:. 
	// - As disciplinas ministradas pelos professores não estão sendo setadas na busca

	@Override
	public List<Professor> listar() {
		String SQL = "SELECT * FROM professor AS p, pessoa_usuario AS u, servidor AS s WHERE u.id_pessoa_usuario = p.id_pessoa_prof AND u.id_pessoa_usuario = s.id_pessoa_usuario";
		List<Professor> professores = new ArrayList<Professor>();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Professor professor = new Professor();
				Usuario usuario = new Usuario();
				
				usuario.setLogin(rs.getString("login"));
				usuario.setNivel(rs.getInt("nivel"));
				usuario.setPerfil(rs.getInt("perfil"));
				usuario.setSenha(rs.getString("senha"));
				
				professor.setId(rs.getInt("id_pessoa_prof"));
				professor.setNome(rs.getString("nome"));
				professor.setCpf(rs.getString("cpf"));
				professor.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento")));
				professor.setEmail(rs.getString("email"));
				professor.setSiape(rs.getString("siape"));
				professor.setCargo(EnumCargo.PROFESSOR);
				
				usuario.setPessoa(professor);
				professor.setUsuario(usuario);
				
				professores.add(professor);
				
			}

			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return professores;
	}

	@Override
	public Professor buscarPorId(int id) {
		String SQL = "SELECT * FROM professor AS p, pessoa_usuario AS u, servidor AS s WHERE p.id_pessoa_prof=? AND u.id_pessoa_usuario = p.id_pessoa_prof AND p.id_pessoa_prof = s.id_pessoa_usuario";
		Professor professor = new Professor();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				Usuario usuario = new Usuario();
				
				usuario.setLogin(rs.getString("login"));
				usuario.setNivel(rs.getInt("nivel"));
				usuario.setPerfil(rs.getInt("perfil"));
				usuario.setSenha(rs.getString("senha"));
				
				professor.setId(rs.getInt("id_pessoa_prof"));
				professor.setNome(rs.getString("nome"));
				professor.setCpf(rs.getString("cpf"));
				professor.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento")));
				professor.setEmail(rs.getString("email"));
				professor.setSiape(rs.getString("siape"));
				professor.setCargo(EnumCargo.PROFESSOR);
				
				usuario.setPessoa(professor);
				professor.setUsuario(usuario);;

			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return professor;
	}

	@Override
	public Professor buscarPorSiape(String siape) {
		String SQL = "SELECT * FROM servidor AS s, professor AS prof, pessoa_usuario AS u WHERE s.siape = ? AND s.id_pessoa_usuario = u.id_pessoa_usuario AND u.id_pessoa_usuario =  prof.id_pessoa_prof";
		Professor professor = new Professor();
		
		Connection conn = ConnectionPool.getConnection();
		try {

			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, siape);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				Usuario usuario = new Usuario();
				
				usuario.setLogin(rs.getString("login"));
				usuario.setNivel(rs.getInt("nivel"));
				usuario.setPerfil(rs.getInt("perfil"));
				usuario.setSenha(rs.getString("senha"));
				
				professor.setId(rs.getInt("id_pessoa_prof"));
				professor.setNome(rs.getString("nome"));
				professor.setCpf(rs.getString("cpf"));
				professor.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento")));
				professor.setEmail(rs.getString("email"));
				professor.setSiape(rs.getString("siape"));
				professor.setCargo(EnumCargo.PROFESSOR);
				
				usuario.setPessoa(professor);
				professor.setUsuario(usuario);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}

		return professor;
	}
	
}
