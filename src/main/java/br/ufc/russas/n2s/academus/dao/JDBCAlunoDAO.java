package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Curso;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.Professor;
import dao.DAOFactory;
import model.Usuario;

public class JDBCAlunoDAO implements AlunoDAO{

	@Override
	public Aluno cadastrar(Aluno aluno) {
		String sql = "INSERT INTO academus.aluno(id_perfil_academus, matricula, semestre_ingresso) VALUES (?, ?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PerfilAcademusDAO perdao = new DAOFactoryJDBC().criarPerfilAcademusDAO();
			aluno = (Aluno) perdao.cadastrar(aluno);
			
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setInt(1, aluno.getId());
			insert.setString(2, aluno.getMatricula());
			insert.setString(3, aluno.getSemestreIngresso());
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return aluno;
	}

	@Override
	public Aluno editar(Aluno aluno) {

		String sql = "UPDATE academus.aluno SET matricula = ?, semestre_ingresso = ? WHERE id_perfil_academus = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PerfilAcademusDAO perdao = new DAOFactoryJDBC().criarPerfilAcademusDAO();
			aluno = (Aluno) perdao.cadastrar(aluno);
			
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setString(1, aluno.getMatricula());
			insert.setString(2, aluno.getSemestreIngresso());
			insert.setInt(3, aluno.getId());
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return aluno;
	}
	
	@Override
	public Aluno buscarPorId(int id) {
		String sql = "SELECT * FROM aluno AS ALUNO, perfil_academus AS PA WHERE ALUNO.id_perfil_academus=? AND ALUNO.id_perfil_academus = PA.id_perfil_academus;";
		Aluno aluno = new Aluno();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				
				//Informações de perfil_academus
				aluno.setId(rs.getInt("id_perfil_academus")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setEmail(rs.getString("email")); //ok
				aluno.setCPF(rs.getString("cpf")); //ok
				aluno.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				aluno.setCurso(curso);						
				
				//Informações de Aluno
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
			}
			
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return aluno;
	}

	@Override
	public Aluno buscarPorMatricula(String matricula) {
		String sql = "SELECT * FROM aluno AS ALUNO, perfil_academus AS PA, curso AS CURSO WHERE ALUNO.matricula= ? AND ALUNO.id_perfil_academus = PA.id_perfil_academus AND PA.id_curso = CURSO.id_curso";
		Aluno aluno = new Aluno();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, matricula);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informações de perfil_academus
				aluno.setId(rs.getInt("id_perfil_academus")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCPF(rs.getString("cpf")); //ok
				aluno.setEmail(rs.getString("email")); //ok
				aluno.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
								
				
				//Informações de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
			}
			
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return aluno;
	}

	@Override
	public List<Aluno> buscarPorNome(String nome) {
		String sql = "SELECT * FROM aluno AS ALUNO, perfil_academus AS PA, curso AS CURSO WHERE ALUNO.id_perfil_academus = PA.id_perfil_academus AND PA.id_curso = CURSO.id_curso AND  UPPER(PA.nome) like UPPER(?) ;";
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, '%'+nome+'%');
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informações de perfil_academus
				aluno.setId(rs.getInt("id_perfil_academus")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCPF(rs.getString("cpf")); //ok
				aluno.setEmail(rs.getString("email")); //ok
				aluno.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
								
				
				//Informações de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
				alunos.add(aluno);
			}
			
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return alunos;
	}

	@Override
	public List<Aluno> listar() {
		String sql = "SELECT * FROM aluno AS ALUNO, perfil_academus AS PA, curso AS CURSO WHERE ALUNO.id_perfil_academus = PA.id_perfil_academus AND PA.id_curso = CURSO.id_curso order by matricula;";
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informações de perfil_academus
				aluno.setId(rs.getInt("id_perfil_academus")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCPF(rs.getString("cpf")); //ok
				aluno.setEmail(rs.getString("email")); //ok
				aluno.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
								
				
				//Informações de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
				alunos.add(aluno);
			}
			
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return alunos;
	}


}
