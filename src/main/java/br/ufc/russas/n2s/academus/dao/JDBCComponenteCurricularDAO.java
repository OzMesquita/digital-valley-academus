package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;
import br.ufc.russas.n2s.academus.model.Natureza;
import br.ufc.russas.n2s.academus.model.ComponenteCurricular;

public class JDBCComponenteCurricularDAO implements ComponenteCurricularDAO{
	private Connection connection;

	public JDBCComponenteCurricularDAO() {
		connection = Conexao.getConexao();
	}
	
	@Override
	public ComponenteCurricular cadastrar(ComponenteCurricular comp, MatrizCurricular matriz) {
		try {
			String sql = "INSERT INTO academus.componente_curricular(id_matriz, id_disciplina, natureza, semestre) VALUES (?, ?, ?, ?);";			
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setInt(1, matriz.getIdMatriz());			
			insert.setString(2, comp.getDisciplina().getId());
			insert.setString(3, Natureza.getDescricao(comp.getNatureza()));
			
			insert.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);			
		}
		return d;
	}

	@Override
	public List<ComponenteCurricular> listar(MatrizCurricular matriz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComponenteCurricular buscarPorId(int idComponente, MatrizCurricular matriz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComponenteCurricular buscarPorId(int idComponente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserirPreRequsitos(List<Disciplina> d, int idComponente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirPreRequisitos(int idComponente, Disciplina disciplina) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirComponente(ComponenteCurricular comp) {
		// TODO Auto-generated method stub
		
	}
	public ComponenteCurricular insereDisciplinaMatriz(ComponenteCurricular d) {
		try {
			String sql = "insert into academus.componente_curricular(id_matriz, id_disciplina, natureza) values (?, ?, ?)";			
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setInt(1, d.getIdMatriz());			
			insert.setString(2, d.getDisciplina().getId());
			insert.setString(3, d.getNatureza().);
			insert.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);			
		}
		return d;
	}
	public ArrayList<ComponenteCurricular> ListarComponentes(int matriz){
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "select * from academus.componente_curricular where id_matriz = "+matriz+";";
		ArrayList<ComponenteCurricular> listaComponentes = new ArrayList<ComponenteCurricular>();
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ComponenteCurricular aux = new ComponenteCurricular();
				aux.setDisciplina(d.buscarPorId(rs.getString("id_disciplina")));
				aux.setIdComponente((rs.getInt("id_disciplina_matriz")));
				aux.setNatureza(Natureza.getNatureza(rs.getString("natureza")));
				aux.setPreRequisitos(buscarPreRequisitos(rs.getInt("id_disciplina_matriz")));
				listaComponentes.add(aux);
			}
			rs.close();
			ps.close();
			return listaComponentes;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public ComponenteCurricular buscarPorId(int busca, int matriz){
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "select * from academus.componente_curricular where id_disciplina_matriz = "+busca+" and id_matriz = "+matriz+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			ComponenteCurricular componente = new ComponenteCurricular();
			componente.setDisciplina(d.buscarPorId(rs.getString("id_disciplina")));
			componente.setIdDisciplinaMatriz(rs.getInt("id_disciplina_matriz"));
			componente.setNatureza(rs.getString("natureza"));
			componente.setPreRequisitos(buscarPreRequisitos(rs.getInt("id_disciplina_matriz")));
			rs.close();
			ps.close();
			return componente;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public ComponenteCurricular buscarComponente(int busca){
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "select * from academus.componente_curricular where id_disciplina_matriz = "+busca+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			ComponenteCurricular componente = new ComponenteCurricular();
			componente.setDisciplina(d.buscarPorId(rs.getString("id_disciplina")));
			componente.setIdDisciplinaMatriz(rs.getInt("id_disciplina_matriz"));
			componente.setNatureza(rs.getString("natureza"));
			componente.setPreRequisitos(buscarPreRequisitos(rs.getInt("id_disciplina_matriz")));
			rs.close();
			ps.close();
			return componente;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public ArrayList<ComponenteCurricular> buscarPorNome(String busca, int matriz){
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "select * from academus.componente_curricular where nome like '%"+busca+"%' and id_matriz = "+matriz+";";
		ArrayList<ComponenteCurricular> listaComponentes = new ArrayList<ComponenteCurricular>();
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ComponenteCurricular aux = new ComponenteCurricular();
				aux.setDisciplina(d.buscarPorId(rs.getString("id_disciplina")));
				aux.setIdDisciplinaMatriz(rs.getInt("id_disciplina_matriz"));
				aux.setNatureza(rs.getString("natureza"));
				aux.setPreRequisitos(buscarPreRequisitos(rs.getInt("id_disciplina_matriz")));
				listaComponentes.add(aux);
			}
			rs.close();
			ps.close();
			return listaComponentes;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public void inserirPreRequsitos(ArrayList<Disciplina> d, int componente) {
		try {
			String sql = "insert into academus.disciplina_pre_requisito values (?, ?)";			
			PreparedStatement insert = connection.prepareStatement(sql);
			for(int i=0; i < d.size(); i++){
				insert.setInt(1, componente);
				insert.setString(2, d.get(i).getId());		
				insert.execute();
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public void deletarPreRequisitos(int idComponente, String idDisciplina){
		String sql = "delete from academus.disciplina_pre_requisito where id_disciplina_matriz = "+idComponente+" and id_disciplina_pre_requisito = "+idDisciplina+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.execute();
		}catch(SQLException e){
			e.getMessage();
			//return false;
		}
	}
	
	public boolean deletarComponente(int busca){
		String sql = "delete from academus.componente_curricular where id_disciplina_matriz = "+busca+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			return ps.execute();
		}catch(SQLException e){
			e.getMessage();
			return false;
		}
	}


	@Override
	public List<Disciplina> buscarPreRequisitos(int idComponente) {
		String sql = "select distinct academus.disciplina.* from academus.disciplina, academus.componente_curricular, academus.disciplina_pre_requisito "
				+ "where academus.componente_curricular.id_disciplina_matriz = "+idComponente+" "
						+ "and academus.disciplina.id_disciplina = academus.disciplina_pre_requisito.id_disciplina_pre_requisito "
						+ "and academus.componente_curricular.id_disciplina_matriz = academus.disciplina_pre_requisito.id_disciplina_matriz;";
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


}
