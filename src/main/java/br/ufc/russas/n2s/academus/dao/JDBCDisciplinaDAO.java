package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.modelo.Disciplina;

public class JDBCDisciplinaDAO implements DisciplinaDAO{
	private Connection connection;

	public JDBCDisciplinaDAO() {
		connection = Conexao.getConexao();
	}

	public Disciplina insereDisciplina(Disciplina d) {
		try {
			String sql = "insert into academus.disciplina(id_disciplina, nome, carga, creditos) values (?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, d.getId());
			insert.setString(2, d.getNome());
			insert.setInt(3, d.getCarga());
			insert.setInt(4, d.getCreditos());
			insert.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return d;
	}

	public ArrayList<Disciplina> ListarDisciplinas(){
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
			return listaDisciplinas;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public Disciplina buscaDisciplina(Disciplina d){
		String sql = "select * from academus.disciplina where id_disciplina=?";
		Disciplina retorno = null;
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, d.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				retorno = new Disciplina();
				retorno.setId(rs.getString("id_disciplina"));
				retorno.setNome(rs.getString("nome"));
				retorno.setCarga(rs.getInt("carga"));
				retorno.setCreditos(rs.getInt("creditos"));
				
			}
			//rs.close();
			//ps.close();
			return retorno;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public void atualizarDisciplina (Disciplina d){
		try {
			String sql = "UPDATE academus.disciplina SET id_disciplina = ?, nome = ?, carga = ?, creditos = ? WHERE id_disciplina = ?";			
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, d.getId());
			update.setString(2, d.getNome());			
			update.setInt(3, d.getCarga());
			update.setInt(4, d.getCreditos());
			update.setString(5, d.getId());
			update.executeQuery();
			//connection.commit();
			update.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public Disciplina buscarPorId(String busca){
		String sql = "select * from academus.disciplina where id_disciplina = '"+busca+"';";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Disciplina aux = new Disciplina();
			aux.setId(rs.getString("id_disciplina"));
			aux.setNome(rs.getString("nome"));
			aux.setCarga(rs.getInt("carga"));
			aux.setCreditos(rs.getInt("creditos"));
			//System.out.println("passa aki tbm");
			rs.close();
			ps.close();
			return aux;
		}catch(SQLException e){
			e.getMessage();
			System.out.println("passa aki");
			return null;
		}
	}
	
	public boolean buscarPorId2(String busca){
		String sql = "select * from academus.disciplina where id_disciplina = '"+busca+"';";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Disciplina aux = new Disciplina();
			aux.setId(rs.getString("id_disciplina"));
			aux.setNome(rs.getString("nome"));
			aux.setCarga(rs.getInt("carga"));
			aux.setCreditos(rs.getInt("creditos"));
			System.out.println("passa aki tbm");
			rs.close();
			ps.close();
			return true;
		}catch(SQLException e){
			e.getMessage();
			System.out.println("passa aki");
			return false;
		}
	}
	
	public List<Disciplina> buscarPorNome1(String busca){
		String sql = "select * from academus.disciplina where nome like '%"+busca+"%';";
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
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
			return listaDisciplinas;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public boolean deletarDisciplina(String busca){
		String sql = "delete from academus.disciplina where id_disciplina = "+busca+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			return ps.execute();
		}catch(SQLException e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public Disciplina cadastrar(Disciplina dis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Disciplina> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Disciplina buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Disciplina> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Disciplina editar(Disciplina dis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Disciplina dis) {
		// TODO Auto-generated method stub
		
	}
}
