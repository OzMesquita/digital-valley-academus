package br.ufc.russas.n2s.academus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.model.Curso;

public class JDBCCursoDAO extends JDBCDAO implements CursoDAO{

	@Override
	public Curso cadastrar(Curso curso) {
		String sql = "INSERT INTO academus.curso(nome) VALUES (?);";
		
		super.open();
		try{
			PreparedStatement inserir = super.getConnection().prepareStatement(sql);
			inserir.setString(1, curso.getNome());
			
			inserir.execute();
			inserir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return curso;
	}

	@Override
	public List<Curso> listar() {
		String sql = "SELECT id_curso, nome FROM academus.curso;";
		List<Curso> cursos = new ArrayList<Curso>();
		
		super.open();
		try{
			PreparedStatement listar = super.getConnection().prepareStatement(sql);
			ResultSet rs = listar.executeQuery();
			MatrizCurricularDAO matrizDao = new DAOFactoryJDBC().criarMatrizCurricularDAO();
			
			while(rs.next()){
				Curso curso = new Curso();
				curso.setIdCurso(rs.getInt("id_curso"));
				curso.setNome(rs.getString("nome"));
				//curso.setMatrizes(matrizDao.buscarPorCurso(curso.getIdCurso()));
				
				cursos.add(curso);
			}
			
			listar.close();
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return cursos;
	}
	
	@Override
	public Curso buscarPorId(int idCurso) {
		String sql = "SELECT id_curso, nome FROM academus.curso WHERE id_curso=?;";
		Curso curso = new Curso();
		
		super.open();
		try{
			PreparedStatement listar = super.getConnection().prepareStatement(sql);
			listar.setInt(1, idCurso);
			
			ResultSet rs = listar.executeQuery();
			MatrizCurricularDAO matrizDao = new DAOFactoryJDBC().criarMatrizCurricularDAO();
			
			if(rs.next()){
				curso.setIdCurso(rs.getInt("id_curso"));
				curso.setNome(rs.getString("nome"));
				//curso.setMatrizes(matrizDao.buscarPorCurso(curso.getIdCurso()));
			}
			
			listar.close();
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return curso;
	}

	@Override
	public Curso editar(Curso curso) {
		String sql = "UPDATE academus.curso SET nome=? WHERE id_curso=?;";
		
		super.open();
		try{
			PreparedStatement editar = super.getConnection().prepareStatement(sql);
			editar.setString(1, curso.getNome());
			editar.setInt(2, curso.getIdCurso());
			
			editar.execute();
			editar.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return curso;
	}

	@Override
	public void excluir(Curso curso) {
		String sql = "DELETE FROM academus.curso WHERE id_curso=?;";
		
		super.open();
		try{
			PreparedStatement excluir = super.getConnection().prepareStatement(sql);
			excluir.setInt(1, curso.getIdCurso());
			
			excluir.execute();
			excluir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
	}

}
