package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.Solicitacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDisciplinaCursadaDAO extends JDBCDAO implements DisciplinaCursadaDAO{

	@Override
	public void cadastrar(List<DisciplinaCursada> ldc, int idSol) {
		String sql = "INSERT INTO academus.disciplina_cursada(nome, carga, semestre, nota, id_solicitacao) VALUES (?, ?, ?, ?, ?)";
		
		super.open();
		try{
			DisciplinaCursada dc;
			while(!ldc.isEmpty()){
				
				PreparedStatement insert = super.getConnection().prepareStatement(sql);
				
				dc = ldc.remove(0);
				insert.setString(1, dc.getNome());
				insert.setInt(2, dc.getCarga());
				insert.setString(3, dc.getSemestre());
				insert.setFloat(4, dc.getNota());
				insert.setInt(5, idSol);
				
				insert.execute();
				insert.close();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
	}

	@Override
	public List<DisciplinaCursada> buscar(Solicitacao sol) {
		String sql = "select * from academus.disciplina_cursada where id_solicitacao = ?;";
		List<DisciplinaCursada> listaDisciplinaCursada = new ArrayList<DisciplinaCursada>();
		
		super.open();
		try {
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ps.setInt(1, sol.getIdSolicitacao());
			ResultSet rs = ps.executeQuery();
						
			while(rs.next()){
				DisciplinaCursada aux = new DisciplinaCursada();
				
				aux.setSemestre(rs.getString("semestre"));
				aux.setNota(rs.getFloat("nota"));//Talvez de erro, já que no banco a coluna 'nota' é do tipo double precision
				aux.setCarga(rs.getInt("carga"));
				aux.setNome(rs.getString("nome"));
				
				listaDisciplinaCursada.add(aux);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return listaDisciplinaCursada;
	}

	@Override
	public void editar(DisciplinaCursada disciplinaCursada, Solicitacao sol) {
		String sql = "UPDATE academus.disciplina_cursada SET nome=?, carga=?, semestre=?, nota=? WHERE id_solicitacao=?;";
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ps.setString(1, disciplinaCursada.getNome());
			ps.setInt(2, disciplinaCursada.getCarga());
			ps.setString(3, disciplinaCursada.getSemestre());
			ps.setFloat(4, disciplinaCursada.getNota());
			
			ps.setInt(5, sol.getIdSolicitacao());
			ps.execute();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		
	}

	@Override
	public void excluir(Solicitacao sol) {
		String sql = "DELETE FROM academus.disciplina_cursada WHERE id_solicitacao=?;";
		
		super.open();
		try{
			PreparedStatement ps = super.getConnection().prepareStatement(sql);
			ps.setInt(1, sol.getIdSolicitacao());
			
			ps.execute();
			ps.close();
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			super.close();
		}
		
	}
	
}
