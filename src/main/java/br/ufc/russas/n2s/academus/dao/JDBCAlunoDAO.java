package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Curso;
import dao.DAOFactory;
import dao.JDBCUsuarioDAO;
import dao.UsuarioDAO;
import model.Usuario;

public class JDBCAlunoDAO implements AlunoDAO{
	
	private Connection connection;
	
	public JDBCAlunoDAO(){
		this.connection = Conexao.getConexao();
	}

	@Override
	public Aluno buscarPorId(int id) {
		String sql = "SELECT * FROM aluno AS u_a, pessoa_usuario AS u WHERE u_a.id_pessoa_usuario=? AND u_a.id_pessoa_usuario = u.id_pessoa_usuario";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				Aluno aluno = new Aluno();
				Usuario usuario = new Usuario();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informações de model.Usuário
				usuario.setLogin(rs.getString("login")); //ok
				usuario.setSenha(rs.getString("senha")); //ok
				usuario.setNivel(rs.getInt("nivel")); //ok
				usuario.setPerfil(rs.getInt("perfil")); //ok
				usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(id)); //ok Não sei que tokens são esses
				usuario.setTokenUsuario(DAOFactory.criarUsuarioDAO().buscarTokenTemp(id)); //ok obs:informação do core
				
				//Informações de model.Pessoa
				aluno.setUsuario(usuario);
				aluno.setId(rs.getInt("id_pessoa_usuario")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCpf(rs.getString("cpf")); //ok
				aluno.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento"))); //ok
				aluno.setEmail(rs.getString("email")); //ok
				usuario.setPessoa(aluno); //ok informação recursiva, isso é estranho
								
				
				//Informações de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
				rs.close();
				ps.close();
				
				return aluno;
			}
			
			
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public Aluno buscarPorMatricula(String matricula) {
		String sql = "SELECT * FROM aluno AS a, pessoa_usuario AS p_u, curso AS c WHERE a.matricula= ? AND a.id_pessoa_usuario = p_u.id_pessoa_usuario AND a.id_curso = c.id_curso";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, matricula);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				Aluno aluno = new Aluno();
				Usuario usuario = new Usuario();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informações de model.Usuário
				usuario.setLogin(rs.getString("login")); //ok
				usuario.setSenha(rs.getString("senha")); //ok
				usuario.setNivel(rs.getInt("nivel")); //ok
				usuario.setPerfil(rs.getInt("perfil")); //ok
				usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(rs.getInt("id_pessoa_usuario"))); //ok Não sei que tokens são esses
				usuario.setTokenUsuario(DAOFactory.criarUsuarioDAO().buscarTokenTemp(rs.getInt("id_pessoa_usuario"))); //ok obs:informação do core
				
				//Informações de model.Pessoa
				aluno.setUsuario(usuario);
				aluno.setId(rs.getInt("id_pessoa_usuario")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCpf(rs.getString("cpf")); //ok
				aluno.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento"))); //ok
				aluno.setEmail(rs.getString("email")); //ok
				usuario.setPessoa(aluno); //ok informação recursiva, isso é estranho
								
				
				//Informações de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
				rs.close();
				ps.close();
				
				return aluno;
			}
			
			
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public List<Aluno> buscarPorNome(String nome) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		String sql = "SELECT * FROM aluno AS u_a, pessoa_usuario AS u, curso AS c WHERE u_a.id_pessoa_usuario = u.id_pessoa_usuario AND u_a.id_curso = c.id_curso AND  UPPER(u.nome) like UPPER(?)";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, '%'+nome+'%');
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				Usuario usuario = new Usuario();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informações de model.Usuário
				usuario.setLogin(rs.getString("login")); //ok
				usuario.setSenha(rs.getString("senha")); //ok
				usuario.setNivel(rs.getInt("nivel")); //ok
				usuario.setPerfil(rs.getInt("perfil")); //ok
				usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(rs.getInt("id_pessoa_usuario"))); //ok Não sei que tokens são esses
				usuario.setTokenUsuario(DAOFactory.criarUsuarioDAO().buscarTokenTemp(rs.getInt("id_pessoa_usuario"))); //ok obs:informação do core
				
				//Informações de model.Pessoa
				aluno.setUsuario(usuario);
				aluno.setId(rs.getInt("id_pessoa_usuario")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCpf(rs.getString("cpf")); //ok
				aluno.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento"))); //ok
				aluno.setEmail(rs.getString("email")); //ok
				usuario.setPessoa(aluno); //ok informação recursiva, isso é estranho
								
				
				//Informações de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
				alunos.add(aluno);
			}
			
			rs.close();
			ps.close();
			
			return alunos;
		} catch(SQLException e) {
			
		}
		return null;
	}

	@Override
	public List<Aluno> listar() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		String sql = "SELECT * FROM aluno AS u_a, pessoa_usuario AS u, curso AS c WHERE u_a.id_pessoa_usuario = u.id_pessoa_usuario AND u_a.id_curso = c.id_curso";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				Usuario usuario = new Usuario();
				
				Curso curso = new DAOFactoryJDBC().criarCursoDAO().buscarPorId(rs.getInt("id_curso"));
				
				//Informações de model.Usuário
				usuario.setLogin(rs.getString("login")); //ok
				usuario.setSenha(rs.getString("senha")); //ok
				usuario.setNivel(rs.getInt("nivel")); //ok
				usuario.setPerfil(rs.getInt("perfil")); //ok
				usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(rs.getInt("id_pessoa_usuario"))); //ok Não sei que tokens são esses
				usuario.setTokenUsuario(DAOFactory.criarUsuarioDAO().buscarTokenTemp(rs.getInt("id_pessoa_usuario"))); //ok obs:informação do core
				
				//Informações de model.Pessoa
				aluno.setUsuario(usuario);
				aluno.setId(rs.getInt("id_pessoa_usuario")); //ok
				aluno.setNome(rs.getString("nome")); //ok
				aluno.setCpf(rs.getString("cpf")); //ok
				aluno.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento"))); //ok
				aluno.setEmail(rs.getString("email")); //ok
				usuario.setPessoa(aluno); //ok informação recursiva, isso é estranho
								
				
				//Informações de Aluno
				aluno.setCurso(curso);
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSemestreIngresso(rs.getString("semestre_ingresso"));
				
				alunos.add(aluno);
			}
			
			rs.close();
			ps.close();
			
			return alunos;
		} catch(SQLException e) {
			
		}
		return null;
	}


}
