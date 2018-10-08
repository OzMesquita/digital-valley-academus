package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Aluno;

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
				Aluno alu = new Aluno();
				//N�o est� terminado
			}
			
			
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public Aluno buscarPorMatricula(String matricula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aluno> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aluno> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluno editar(Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}

}
