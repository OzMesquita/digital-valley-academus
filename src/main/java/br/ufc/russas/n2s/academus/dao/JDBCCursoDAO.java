package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Curso;

public class JDBCCursoDAO implements CursoDAO{
	
	private Connection connection;
	
	public JDBCCursoDAO(){
		this.connection = Conexao.getConexao();
	}

	@Override
	public Curso cardastrar(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Curso> listar() {
		String sql = "SELECT id_curso, nome FROM academus.curso;";
		List<Curso> cursos = new ArrayList<Curso>();
		
		try{
			PreparedStatement listar = this.connection.prepareStatement(sql);
			ResultSet rs = listar.executeQuery();
			MatrizCurricularDAO matrizDao = new DAOFactoryJDBC().criarMatrizCurricularDAO();
			
			while(rs.next()){
				Curso curso = new Curso();
				curso.setIdCurso(rs.getInt("id_curso"));
				curso.setNome(rs.getString("nome"));
				curso.setMatrizes(matrizDao.buscarPorCurso(curso.getIdCurso()));
				
				cursos.add(curso);
			}
					
		} catch(SQLException e) {
			e.getMessage();
		}
		return cursos;
	}
	
	@Override
	public Curso buscarPorId(int idCurso) {
		String sql = "SELECT id_curso, nome FROM academus.curso WHERE id_curso=?;";
		
		Curso curso = null;
		try{
			PreparedStatement listar = this.connection.prepareStatement(sql);
			ResultSet rs = listar.executeQuery();
			
			if(rs.next()){
				curso = new Curso();
			}
			
		} catch(SQLException e) {
			e.getMessage();
		}
		return curso;
	}

	@Override
	public Curso editar(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Curso curso) {
		// TODO Auto-generated method stub
		
	}

}
