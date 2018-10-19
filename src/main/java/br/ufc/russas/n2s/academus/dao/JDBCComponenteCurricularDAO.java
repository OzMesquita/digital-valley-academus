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
		String sql = "INSERT INTO academus.componente_curricular(id_matriz, id_disciplina, natureza, semestre) VALUES (?, ?, ?, ?);";
		
		try {			
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setInt(1, matriz.getIdMatriz());			
			insert.setString(2, comp.getDisciplina().getId());
			insert.setString(3, Natureza.getDescricao(comp.getNatureza()));
			
			insert.execute();
			insert.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return comp;
	}

	@Override
	public List<ComponenteCurricular> listar(MatrizCurricular matriz) {
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "SELECT * FROM academus.componente_curricular WHERE id_matriz = "+ matriz.getIdMatriz() +";";
		
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
		} catch(SQLException e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public ComponenteCurricular buscarPorId(int idComponente, MatrizCurricular matriz) {
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "SELECT * FROM academus.componente_curricular WHERE id_disciplina_matriz = "+idComponente+" AND id_matriz = "+matriz.getIdMatriz()+";";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			ComponenteCurricular componente = new ComponenteCurricular();
			componente.setDisciplina(d.buscarPorId(rs.getString("id_disciplina")));
			componente.setIdComponente(rs.getInt("id_disciplina_matriz"));
			componente.setNatureza(Natureza.getNatureza(rs.getString("natureza")));
			componente.setPreRequisitos(buscarPreRequisitos(rs.getInt("id_disciplina_matriz")));
			
			rs.close();
			ps.close();
			return componente;
		} catch(SQLException e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public ComponenteCurricular buscarPorId(int idComponente) {
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "SELECT * FROM academus.componente_curricular WHERE id_disciplina_matriz = "+idComponente+";";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			ComponenteCurricular componente = new ComponenteCurricular();
			componente.setDisciplina(d.buscarPorId(rs.getString("id_disciplina")));
			componente.setIdComponente(rs.getInt("id_disciplina_matriz"));
			componente.setNatureza(Natureza.getNatureza(rs.getString("natureza")));
			componente.setPreRequisitos(buscarPreRequisitos(rs.getInt("id_disciplina_matriz")));
			
			rs.close();
			ps.close();
			return componente;
		} catch(SQLException e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public void inserirPreRequsitos(List<Disciplina> d, int idComponente) {
		String sql = "INSERT INTO academus.disciplina_pre_requisito VALUES (?, ?)";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			
			for(int i=0; i < d.size(); i++){
				insert.setInt(1, idComponente);
				insert.setString(2, d.get(i).getId());		
				insert.execute();
			}
			
			insert.close();
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	@Override
	public void excluirPreRequisito(int idComponente, Disciplina disciplina) {
		String sql = "delete from academus.disciplina_pre_requisito where id_disciplina_matriz = "+idComponente+" and id_disciplina_pre_requisito = "+disciplina.getId()+";";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.execute();
			
		}catch(SQLException e){
			e.getMessage();
		}
		
	}

	@Override
	public void excluirComponente(ComponenteCurricular comp) {
		String sql = "delete from academus.componente_curricular where id_disciplina_matriz = "+comp.getIdComponente()+";";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.execute();
			
		}catch(SQLException e){
			e.getMessage();
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
		}
		return null;
	}


}
