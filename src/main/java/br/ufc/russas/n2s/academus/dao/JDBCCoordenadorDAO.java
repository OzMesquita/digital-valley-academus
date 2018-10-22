package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Curso;
import br.ufc.russas.n2s.academus.model.Professor;
import dao.DAOFactory;

public class JDBCCoordenadorDAO implements CoordenadorDAO{
	private Connection connection;
	
	public JDBCCoordenadorDAO() {
		this.connection = Conexao.getConexao();
	}

	@Override
	public Coordenador cadastrarCoordedanador(Coordenador cord) {
		
		String sql = "INSERT INTO academus.coordenador(id_pessoa, id_curso) VALUES (?, ?);";
		
		try {
			PreparedStatement insert = this.connection.prepareStatement(sql);
			insert.setInt(1, cord.getId());
			insert.setInt(2, cord.getCurso().getIdCurso());
			
			insert.execute();
			insert.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return cord;
	}

	@Override
	public Coordenador buscarPorId(int idCoordenador) {
		String sql = "SELECT id_pessoa, id_curso FROM academus.coordenador WHERE id_pessoa = ?;";
		Coordenador cord = null;
		
		try {
			PreparedStatement buscar = this.connection.prepareStatement(sql);
			buscar.setInt(1, idCoordenador);
			
			ResultSet rs = buscar.executeQuery();
			
			if(rs.next())
			{
				cord = new Coordenador();
				
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
			e.getMessage();
		}
		return cord;
	}
	
	@Override
	public Coordenador buscarPorCurso(Curso curso) {
		String sql = "SELECT id_pessoa FROM academus.coordenador WHERE id_curso = ?;";
		Coordenador cord = null;
		
		try{
			PreparedStatement buscar = this.connection.prepareStatement(sql);
			buscar.setInt(1, curso.getIdCurso());
			
			ResultSet rs = buscar.executeQuery();
			
			if(rs.next()){
				cord = new Coordenador();
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
				
		} catch(SQLException e) {
			e.getMessage();
		}
		return cord;
	}

	@Override
	public Coordenador editar(Coordenador cord) {
		String sql = "UPDATE academus.coordenador SET id_curso=? WHERE id_pessoa=?;";
		
		try{
			PreparedStatement editar = this.connection.prepareStatement(sql);
			editar.setInt(1, cord.getCurso().getIdCurso());
			editar.setInt(2, cord.getId());
			
			editar.execute();
			editar.close();
			
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return cord;
		
	}

	@Override
	public void excluir(Coordenador cord) {
		String sql = "DELETE FROM academus.coordenador WHERE id_pessoa=?;";
		
		try{
			PreparedStatement excluir = this.connection.prepareStatement(sql);
			excluir.setInt(1, cord.getId());
			
			excluir.execute();
			
		} catch(SQLException e) {
			e.getMessage();
		}
	}

}
