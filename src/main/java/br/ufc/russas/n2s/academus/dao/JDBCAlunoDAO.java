package br.ufc.russas.n2s.academus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.debug.IOConexao;
import br.ufc.russas.n2s.academus.debug.Operacao;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Curso;
import dao.DAOFactory;
import model.Usuario;

public class JDBCAlunoDAO extends JDBCDAO implements AlunoDAO{

	@Override
	public Aluno buscarPorId(int id) {
		String sql = "SELECT * FROM aluno AS u_a, pessoa_usuario AS u WHERE u_a.id_pessoa_usuario=? AND u_a.id_pessoa_usuario = u.id_pessoa_usuario;";
		Aluno aluno = new Aluno();
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				Usuario usuario = new Usuario();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informa��es de model.Usu�rio
				usuario.setLogin(rs.getString("login")); //ok
				usuario.setSenha(rs.getString("senha")); //ok
				usuario.setNivel(rs.getInt("nivel")); //ok
				usuario.setPerfil(rs.getInt("perfil")); //ok
				usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(id)); //ok N�o sei que tokens s�o esses
				usuario.setTokenUsuario(DAOFactory.criarUsuarioDAO().buscarTokenTemp(id)); //ok obs:informa��o do core
				
				//Informa��es de model.Pessoa
				aluno.setUsuario(usuario);
				aluno.setId(rs.getInt("id_pessoa_usuario")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCpf(rs.getString("cpf")); //ok
				aluno.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento"))); //ok
				aluno.setEmail(rs.getString("email")); //ok
				usuario.setPessoa(aluno); //ok informa��o recursiva, isso � estranho
								
				
				//Informa��es de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
			}
			
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return aluno;
	}

	@Override
	public Aluno buscarPorMatricula(String matricula) {
		String sql = "SELECT * FROM aluno AS a, pessoa_usuario AS p_u, curso AS c WHERE a.matricula= ? AND a.id_pessoa_usuario = p_u.id_pessoa_usuario AND a.id_curso = c.id_curso";
		Aluno aluno = new Aluno();
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ps.setString(1, matricula);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				Usuario usuario = new Usuario();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informa��es de model.Usu�rio
				usuario.setLogin(rs.getString("login")); //ok
				usuario.setSenha(rs.getString("senha")); //ok
				usuario.setNivel(rs.getInt("nivel")); //ok
				usuario.setPerfil(rs.getInt("perfil")); //ok
				usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(rs.getInt("id_pessoa_usuario"))); //ok N�o sei que tokens s�o esses
				usuario.setTokenUsuario(DAOFactory.criarUsuarioDAO().buscarTokenTemp(rs.getInt("id_pessoa_usuario"))); //ok obs:informa��o do core
				
				//Informa��es de model.Pessoa
				aluno.setUsuario(usuario);
				aluno.setId(rs.getInt("id_pessoa_usuario")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCpf(rs.getString("cpf")); //ok
				aluno.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento"))); //ok
				aluno.setEmail(rs.getString("email")); //ok
				usuario.setPessoa(aluno); //ok informa��o recursiva, isso � estranho
								
				
				//Informa��es de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
			}
			
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return aluno;
	}

	@Override
	public List<Aluno> buscarPorNome(String nome) {
		String sql = "SELECT * FROM aluno AS u_a, pessoa_usuario AS u, curso AS c WHERE u_a.id_pessoa_usuario = u.id_pessoa_usuario AND u_a.id_curso = c.id_curso AND  UPPER(u.nome) like UPPER(?)";
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ps.setString(1, '%'+nome+'%');
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				Usuario usuario = new Usuario();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informa��es de model.Usu�rio
				usuario.setLogin(rs.getString("login")); //ok
				usuario.setSenha(rs.getString("senha")); //ok
				usuario.setNivel(rs.getInt("nivel")); //ok
				usuario.setPerfil(rs.getInt("perfil")); //ok
				usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(rs.getInt("id_pessoa_usuario"))); //ok N�o sei que tokens s�o esses
				usuario.setTokenUsuario(DAOFactory.criarUsuarioDAO().buscarTokenTemp(rs.getInt("id_pessoa_usuario"))); //ok obs:informa��o do core
				
				//Informa��es de model.Pessoa
				aluno.setUsuario(usuario);
				aluno.setId(rs.getInt("id_pessoa_usuario")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCpf(rs.getString("cpf")); //ok
				aluno.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento"))); //ok
				aluno.setEmail(rs.getString("email")); //ok
				usuario.setPessoa(aluno); //ok informa��o recursiva, isso � estranho
								
				
				//Informa��es de Aluno
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
			super.close();
		}
		
		return alunos;
	}

	@Override
	public List<Aluno> listar() {
		String sql = "SELECT * FROM aluno AS u_a, pessoa_usuario AS u, curso AS c WHERE u_a.id_pessoa_usuario = u.id_pessoa_usuario AND u_a.id_curso = c.id_curso";
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				Usuario usuario = new Usuario();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informa��es de model.Usu�rio
				usuario.setLogin(rs.getString("login")); //ok
				usuario.setSenha(rs.getString("senha")); //ok
				usuario.setNivel(rs.getInt("nivel")); //ok
				usuario.setPerfil(rs.getInt("perfil")); //ok
				usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(rs.getInt("id_pessoa_usuario"))); //ok N�o sei que tokens s�o esses
				usuario.setTokenUsuario(DAOFactory.criarUsuarioDAO().buscarTokenTemp(rs.getInt("id_pessoa_usuario"))); //ok obs:informa��o do core
				
				//Informa��es de model.Pessoa
				aluno.setUsuario(usuario);
				aluno.setId(rs.getInt("id_pessoa_usuario")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCpf(rs.getString("cpf")); //ok
				aluno.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento"))); //ok
				aluno.setEmail(rs.getString("email")); //ok
				usuario.setPessoa(aluno); //ok informa��o recursiva, isso � estranho
								
				
				//Informa��es de Aluno
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
			super.close();
		}
		
		return alunos;
	}


}
