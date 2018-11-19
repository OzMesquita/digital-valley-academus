package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.Solicitacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDisciplinaCursadaDAO implements DisciplinaCursadaDAO{

	@Override
	public void cadastrar(List<DisciplinaCursada> ldc, int idSol) {
		String sql = "INSERT INTO academus.disciplina_cursada(nome, carga, semestre, nota, id_solicitacao, instituicao) VALUES (?, ?, ?, ?, ?, ?)";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			DisciplinaCursada dc;
			while(!ldc.isEmpty()){
				
				PreparedStatement insert = conn.prepareStatement(sql);
				
				dc = ldc.remove(0);
				insert.setString(1, dc.getNome());
				insert.setInt(2, dc.getCarga());
				insert.setString(3, dc.getSemestre());
				insert.setFloat(4, dc.getNota());
				insert.setInt(5, idSol);
				insert.setString(6, dc.getInstituicao());
				
				insert.execute();
				insert.close();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
	}

	@Override
	public List<DisciplinaCursada> buscar(Solicitacao sol) {
		String sql = "select * from academus.disciplina_cursada where id_solicitacao = ?;";
		List<DisciplinaCursada> listaDisciplinaCursada = new ArrayList<DisciplinaCursada>();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sol.getIdSolicitacao());
			ResultSet rs = ps.executeQuery();
						
			while(rs.next()){
				DisciplinaCursada aux = new DisciplinaCursada();
				
				aux.setSemestre(rs.getString("semestre"));
				aux.setNota(rs.getFloat("nota"));//Talvez de erro, já que no banco a coluna 'nota' é do tipo double precision
				aux.setCarga(rs.getInt("carga"));
				aux.setNome(rs.getString("nome"));
				aux.setInstituicao(rs.getString("instituicao"));
				
				listaDisciplinaCursada.add(aux);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaDisciplinaCursada;
	}

	@Override
	public void editar(DisciplinaCursada disciplinaCursada, Solicitacao sol) {
		String sql = "UPDATE academus.disciplina_cursada SET nome=?, carga=?, semestre=?, nota=?, instituicao=? WHERE id_solicitacao=?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, disciplinaCursada.getNome());
			ps.setInt(2, disciplinaCursada.getCarga());
			ps.setString(3, disciplinaCursada.getSemestre());
			ps.setFloat(4, disciplinaCursada.getNota());
			ps.setString(5, disciplinaCursada.getInstituicao());
			ps.setInt(6, sol.getIdSolicitacao());
			
			
			ps.execute();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
	}

	@Override
	public void excluir(Solicitacao sol) {
		String sql = "DELETE FROM academus.disciplina_cursada WHERE id_solicitacao=?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sol.getIdSolicitacao());
			
			ps.executeUpdate();
			ps.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
	}
	
}
