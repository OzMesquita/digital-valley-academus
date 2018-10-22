package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Disciplina;

public class JDBCDisciplinaDAO implements DisciplinaDAO{
	private Connection connection;

	public JDBCDisciplinaDAO() {
		connection = Conexao.getConexao();
	}

	@Override
	public Disciplina cadastrar(Disciplina dis) {
		String sql = "insert into academus.disciplina(id_disciplina, nome, carga, creditos) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setString(1, dis.getId());
			insert.setString(2, dis.getNome());
			insert.setInt(3, dis.getCarga());
			insert.setInt(4, dis.getCreditos());
			
			insert.execute();
			insert.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return dis;
	}

	@Override
	public List<Disciplina> listar() {
		String sql = "select * from academus.disciplina";
		ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Disciplina aux = new Disciplina();
				aux.setId(rs.getString("id_disciplina"));
				aux.setNome(rs.getString("nome"));
				aux.setCarga(rs.getInt("carga"));
				aux.setCreditos(rs.getInt("creditos"));
				listaDisciplinas.add(aux);
			}
			
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			e.getMessage();
		}
		return listaDisciplinas;
	}

	@Override
	public Disciplina buscarPorId(int id) {
		String sql = "select * from academus.disciplina where id_disciplina = '"+id+"';";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Disciplina aux = new Disciplina();
			aux.setId(rs.getString("id_disciplina"));
			aux.setNome(rs.getString("nome"));
			aux.setCarga(rs.getInt("carga"));
			aux.setCreditos(rs.getInt("creditos"));
			rs.close();
			ps.close();
			return aux;
		}catch(SQLException e){
			e.getMessage();
			System.out.println("passa aki");
			return null;
		}
	}

	@Override
	public List<Disciplina> buscarPorNome(String nome) {
		String sql = "select * from academus.disciplina where nome like '%?%';";
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Disciplina aux = new Disciplina();
				aux.setId(rs.getString("id_disciplina"));
				aux.setNome(rs.getString("nome"));
				aux.setCarga(rs.getInt("carga"));
				aux.setCreditos(rs.getInt("creditos"));
				listaDisciplinas.add(aux);
			}
			
			rs.close();
			ps.close();
		}catch(SQLException e){
			e.getMessage();
		}
		return listaDisciplinas;
	}

	@Override
	public Disciplina editar(Disciplina dis) {
		String sql = "UPDATE academus.disciplina SET id_disciplina = ?, nome = ?, carga = ?, creditos = ? WHERE id_disciplina = ?";
		
		try {			
			PreparedStatement update = connection.prepareStatement(sql);
			
			update.setString(1, dis.getId());
			update.setString(2, dis.getNome());			
			update.setInt(3, dis.getCarga());
			update.setInt(4, dis.getCreditos());
			update.setString(5, dis.getId());
			
			update.executeQuery();
			update.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return dis;
	}

	@Override
	public void excluir(Disciplina dis) {
		String sql = "delete from academus.disciplina where id_disciplina = "+dis.getId()+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.execute();
		}catch(SQLException e){
			e.getMessage();
		}
	}
}
