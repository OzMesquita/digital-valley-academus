package br.ufc.russas.n2s.academus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;
import br.ufc.russas.n2s.academus.model.Natureza;
import br.ufc.russas.n2s.academus.model.ComponenteCurricular;

public class JDBCComponenteCurricularDAO extends JDBCDAO implements ComponenteCurricularDAO{
	
	@Override
	public ComponenteCurricular cadastrar(ComponenteCurricular comp, MatrizCurricular matriz) {
		String sql = "INSERT INTO academus.componente_curricular(id_matriz, id_disciplina, natureza, semestre) VALUES (?, ?, ?, ?);";
		
		super.open();
		try {			
			PreparedStatement insert = super.getConnection().prepareStatement(sql);
			
			insert.setInt(1, matriz.getIdMatriz());			
			insert.setString(2, comp.getDisciplina().getId());
			insert.setString(3, Natureza.getDescricao(comp.getNatureza()));
			
			insert.execute();
			insert.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return comp;
	}

	@Override
	public List<ComponenteCurricular> listar(int idMatriz) {
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "SELECT * FROM academus.componente_curricular WHERE id_matriz = "+ idMatriz +";";
		List<ComponenteCurricular> listaComponentes = new ArrayList<ComponenteCurricular>();
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
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
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			super.close();
		}
		return listaComponentes;
	}

	@Override
	public ComponenteCurricular buscarPorId(int idComponente, MatrizCurricular matriz) {
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "SELECT * FROM academus.componente_curricular WHERE id_disciplina_matriz = "+idComponente+" AND id_matriz = "+matriz.getIdMatriz()+";";
		ComponenteCurricular componente = new ComponenteCurricular();
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				componente.setDisciplina(d.buscarPorId(rs.getString("id_disciplina")));
				componente.setIdComponente(rs.getInt("id_disciplina_matriz"));
				componente.setNatureza(Natureza.getNatureza(rs.getString("natureza")));
				componente.setPreRequisitos(buscarPreRequisitos(rs.getInt("id_disciplina_matriz")));
			}
			
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		return componente;
	}

	@Override
	public ComponenteCurricular buscarPorId(int idComponente) {
		JDBCDisciplinaDAO d = new JDBCDisciplinaDAO();
		String sql = "SELECT * FROM academus.componente_curricular WHERE id_disciplina_matriz = "+idComponente+";";
		ComponenteCurricular componente = new ComponenteCurricular();
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				componente.setDisciplina(d.buscarPorId(rs.getString("id_disciplina")));
				componente.setIdComponente(rs.getInt("id_disciplina_matriz"));
				componente.setNatureza(Natureza.getNatureza(rs.getString("natureza")));
				componente.setPreRequisitos(buscarPreRequisitos(rs.getInt("id_disciplina_matriz")));
			}
			
			rs.close();
			ps.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		return componente;
	}

	@Override
	public void inserirPreRequsitos(List<Disciplina> d, int idComponente) {
		String sql = "INSERT INTO academus.disciplina_pre_requisito VALUES (?, ?)";
		
		super.open();
		try {
			PreparedStatement insert = super.getConnection().prepareStatement(sql);
			
			for(int i=0; i < d.size(); i++){
				insert.setInt(1, idComponente);
				insert.setString(2, d.get(i).getId());		
				insert.execute();
			}
			
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
	}

	@Override
	public void excluirPreRequisito(int idComponente, Disciplina disciplina) {
		String sql = "delete from academus.disciplina_pre_requisito where id_disciplina_matriz = "+idComponente+" and id_disciplina_pre_requisito = "+disciplina.getId()+";";
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ps.execute();
			
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			super.close();
		}
		
	}

	@Override
	public void excluirComponente(ComponenteCurricular comp) {
		String sql = "delete from academus.componente_curricular where id_disciplina_matriz = "+comp.getIdComponente()+";";
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ps.execute();
			
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			super.close();
		}
	}
	

	@Override
	public List<Disciplina> buscarPreRequisitos(int idComponente) {
		String sql = "select distinct academus.disciplina.* from academus.disciplina, academus.componente_curricular, academus.disciplina_pre_requisito "
				+ "where academus.componente_curricular.id_disciplina_matriz = "+idComponente+" "
						+ "and academus.disciplina.id_disciplina = academus.disciplina_pre_requisito.id_disciplina_pre_requisito "
						+ "and academus.componente_curricular.id_disciplina_matriz = academus.disciplina_pre_requisito.id_disciplina_matriz;";
		
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
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
			e.printStackTrace();
		} finally {
			super.close();
		}

		return listaDisciplinas;
	}

}
