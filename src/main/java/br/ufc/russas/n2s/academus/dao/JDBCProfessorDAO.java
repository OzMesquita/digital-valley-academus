package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.Professor;

public class JDBCProfessorDAO implements ProfessorDAO{
	//.:Observações:. 
	// - As disciplinas ministradas pelos professores não estão sendo setadas na busca

	@Override
	public Professor cadastrar(Professor professor) {
		String sql = "INSERT INTO academus.funcionario(id_perfil_academus, siape) VALUES (?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PerfilAcademusDAO perdao = new DAOFactoryJDBC().criarPerfilAcademusDAO();
			professor = (Professor) perdao.cadastrar(professor);
			professor.setId(perdao.buscarPorIdAcademus(professor.getIdGuardiao()));
			
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setInt(1, professor.getId());
			insert.setString(2, professor.getSiape());
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return professor;
	}

	@Override
	public Professor editar(Professor professor) {
		String sql = "UPDATE academus.funcionario SET siape = ? WHERE id_perfil_academus = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PerfilAcademusDAO perdao = new DAOFactoryJDBC().criarPerfilAcademusDAO();
			professor = (Professor) perdao.cadastrar(professor);
			
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setString(1, professor.getSiape());
			insert.setInt(2, professor.getId());
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return professor;
	}
	
	@Override
	public List<Professor> listar() {
		
		//String SQL = "SELECT * FROM professor AS p, pessoa_usuario AS u, servidor AS s WHERE u.id_pessoa_usuario = p.id_pessoa_prof AND u.id_pessoa_usuario = s.id_pessoa_usuario ORDER BY id_pessoa_prof;";
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND (p.id_nivel=? or p.id_nivel=?)  ORDER BY f.id_perfil_academus;";
		List<Professor> professores = new ArrayList<Professor>();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			int nivel= NivelAcademus.getCodigo(NivelAcademus.PROFESSOR);
			int nivel2=NivelAcademus.getCodigo(NivelAcademus.COORDENADOR);
			//
			ps.setInt(1, nivel);
			ps.setInt(2, nivel2);
			ResultSet rs = ps.executeQuery();
			
			CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
			
			while (rs.next()) {
				
				
				Professor professor = new Professor();
				
				professor.setId(rs.getInt("id_perfil_academus"));
				professor.setNome(rs.getString("nome"));
				professor.setCPF(rs.getString("cpf"));
				professor.setEmail(rs.getString("email"));
				professor.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				professor.setIsAdmin(rs.getBoolean("is_admin"));
				professor.setSiape(rs.getString("siape"));
				professor.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				
				//DisciplinaDAO ddao = new DAOFactoryJDBC().criarDisciplinaDAO();
				//falta setar as diciplinas do professor
				
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
	public List<Disciplina> listarDisciplinas(int id){
		String sql = "SELECT * FROM academus.disciplina AS d, academus.disciplina_professor AS dp WHERE dp.id_perfil_academus = ? AND d.id_disciplina = dp.id_disciplina ORDER BY dp.id_disciplina;";
		ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
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
	public void removeDisciplinas(int idProfessor) {
		String sql = "DELETE FROM academus.disciplina_professor where id_perfil_academus = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idProfessor);
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
	}

	@Override
	public Professor buscarPorId(int id) {
		//String SQL = "SELECT * FROM professor AS p, pessoa_usuario AS u, servidor AS s WHERE p.id_pessoa_prof=? AND u.id_pessoa_usuario = p.id_pessoa_prof AND p.id_pessoa_prof = s.id_pessoa_usuario";
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND p.id_perfil_academus = ? AND (p.id_nivel=? or p.id_nivel=?);";
		Professor professor = new Professor();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			ps.setInt(2, NivelAcademus.PROFESSOR.ordinal());
			ps.setInt(3, NivelAcademus.COORDENADOR.ordinal());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
				
				professor.setId(rs.getInt("id_perfil_academus"));
				professor.setNome(rs.getString("nome"));
				professor.setCPF(rs.getString("cpf"));
				professor.setEmail(rs.getString("email"));
				professor.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				professor.setIsAdmin(rs.getBoolean("is_admin"));
				professor.setSiape(rs.getString("siape"));
				professor.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				
				// falta setar as disciplinas que ele é professor

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
	public Professor buscarPorCPF(String cpf) {
		//String SQL = "SELECT * FROM professor AS p, pessoa_usuario AS u, servidor AS s WHERE p.id_pessoa_prof=? AND u.id_pessoa_usuario = p.id_pessoa_prof AND p.id_pessoa_prof = s.id_pessoa_usuario";
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND p.cpf = ?";
		Professor professor = null;
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, cpf);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
				
				professor = new Professor();
				
				professor.setId(rs.getInt("id_perfil_academus"));
				professor.setNome(rs.getString("nome"));
				professor.setCPF(rs.getString("cpf"));
				professor.setEmail(rs.getString("email"));
				professor.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				professor.setIsAdmin(rs.getBoolean("is_admin"));
				professor.setSiape(rs.getString("siape"));
				professor.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				
				// falta setar as disciplinas que ele é professor

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
		//String SQL = "SELECT * FROM servidor AS s, professor AS prof, pessoa_usuario AS u WHERE s.siape = ? AND s.id_pessoa_usuario = u.id_pessoa_usuario AND u.id_pessoa_usuario =  prof.id_pessoa_prof";
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND f.siape =? AND p.id_nivel=?";
		Professor professor = new Professor();
		
		Connection conn = ConnectionPool.getConnection();
		try {

			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, siape);
			ps.setInt(2, NivelAcademus.PROFESSOR.ordinal());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
				
				professor.setId(rs.getInt("id_perfil_academus"));
				professor.setNome(rs.getString("nome"));
				professor.setCPF(rs.getString("cpf"));
				professor.setEmail(rs.getString("email"));
				professor.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				professor.setIsAdmin(rs.getBoolean("is_admin"));
				professor.setSiape(rs.getString("siape"));
				professor.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				
				// falta setar as disciplinas que ele é professor
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
	public List<Professor> buscarPorNome(String nome) {
		
		//String SQL = "SELECT * FROM professor AS p, pessoa_usuario AS u, servidor AS s WHERE u.id_pessoa_usuario = p.id_pessoa_prof AND u.id_pessoa_usuario = s.id_pessoa_usuario ORDER BY id_pessoa_prof;";
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND p.id_nivel=? AND p.nome ilike '%"+nome+"%' ORDER BY f.id_perfil_academus;";
		List<Professor> professores = new ArrayList<Professor>();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, NivelAcademus.getCodigo(NivelAcademus.PROFESSOR));
			ResultSet rs = ps.executeQuery();
			
			CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
			
			while (rs.next()) {
				
				
				Professor professor = new Professor();
				
				professor.setId(rs.getInt("id_perfil_academus"));
				professor.setNome(rs.getString("nome"));
				professor.setCPF(rs.getString("cpf"));
				professor.setEmail(rs.getString("email"));
				professor.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				professor.setIsAdmin(rs.getBoolean("is_admin"));
				professor.setSiape(rs.getString("siape"));
				professor.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				
				//DisciplinaDAO ddao = new DAOFactoryJDBC().criarDisciplinaDAO();
				//falta setar as diciplinas do professor
				
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
	public boolean possuiCoordenador(int id_curso) {
		String sql = "SELECT COUNT(*) FROM academus.perfil_academus WHERE id_curso="+id_curso+" AND id_nivel="+NivelAcademus.getCodigo(NivelAcademus.COORDENADOR)+";";
		
		Connection conn = ConnectionPool.getConnection();
		int result = 0;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			if(result > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return false;
	}

	@Override
	public void retirarCoordenador(int id_curso, Professor professor) {
		String sql = "UPDATE academus.perfil_academus SET id_nivel=? id_curso=? WHERE id_perfil_academus = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement editar = conn.prepareStatement(sql);
			editar.setInt(1, NivelAcademus.getCodigo(NivelAcademus.PROFESSOR));
			editar.setInt(2, id_curso);
			
			editar.setInt(3, professor.getId());
			
			editar.executeUpdate();
			editar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
	}

	@Override
	public void cadastrarCoordenador(int id_curso, Professor professor) {
		String sql = "UPDATE academus.perfil_academus SET id_nivel=? id_curso=? WHERE id_perfil_academus = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement editar = conn.prepareStatement(sql);
			editar.setInt(1, NivelAcademus.getCodigo(NivelAcademus.COORDENADOR));
			editar.setInt(2, id_curso);
			
			editar.setInt(3, professor.getId());
			
			editar.executeUpdate();
			editar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
	}

	@Override
	public Professor isCoordenador(int idCurso) {
		String SQL = "SELECT * FROM academus.perfil_academus AS p, academus.funcionario AS f WHERE f.id_perfil_academus = p.id_perfil_academus AND p.id_curso=? AND p.id_nivel=?;";
		Professor professor = new Professor();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, idCurso);
			ps.setInt(2, NivelAcademus.getCodigo(NivelAcademus.COORDENADOR));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				CursoDAO cdao = new DAOFactoryJDBC().criarCursoDAO();
				
				professor.setId(rs.getInt("id_perfil_academus"));
				professor.setNome(rs.getString("nome"));
				professor.setCPF(rs.getString("cpf"));
				professor.setEmail(rs.getString("email"));
				professor.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				professor.setIsAdmin(rs.getBoolean("is_admin"));
				professor.setSiape(rs.getString("siape"));
				professor.setCurso(cdao.buscarPorId(rs.getInt("id_curso")));
				
				// falta setar as disciplinas que ele é professor

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
	

	
	/*@Override
	public boolean isProfessor(Pessoa pessoa) {
		String sql = "SELECT public.pessoa_usuario.nome, public.professor.id_pessoa_prof "
				+ "FROM public.professor INNER JOIN public.pessoa_usuario "
				+ "ON professor.id_pessoa_prof = pessoa_usuario.id_pessoa_usuario AND public.professor.id_pessoa_prof = ?;";
		
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
		}
		return false;
	}
	*/
	
}
