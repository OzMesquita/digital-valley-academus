package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDisciplinaCursadaDAO implements DisciplinaCursadaDAO{
	
	private Connection connection;
	
	public JDBCDisciplinaCursadaDAO() {
		connection = Conexao.getConexao();
		
	}
	

	@Override
	public void cadastrar(List<DisciplinaCursada> ldc, int idSol) {
		try{
			DisciplinaCursada dc;
			while(!ldc.isEmpty()){
				String sql = "INSERT INTO academus.disciplina_cursada(nome, carga, semestre, nota, id_solicitacao) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement insert = connection.prepareStatement(sql);
				dc = ldc.remove(0);
				insert.setString(1, dc.getNome());
				insert.setInt(2, dc.getCarga());
				insert.setString(3, dc.getSemestre());
				insert.setFloat(4, dc.getNota());
				insert.setInt(5, idSol);
				insert.execute();
				insert.close();
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DisciplinaCursada> buscar(Solicitacao sol) {
		ArrayList<DisciplinaCursada> listaDisciplinaCursada = new ArrayList<DisciplinaCursada>();
		String sql = "select * from academus.disciplina_cursada where id_solicitacao = ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, sol.getIdSolicitacao());
			ResultSet rs = ps.executeQuery();
						
			while(rs.next()){
				DisciplinaCursada aux = new DisciplinaCursada();
				
				aux.setSemestre(rs.getString("semestre"));
				aux.setNota(rs.getFloat("nota"));//Talvez de erro, j� que no banco a coluna 'nota' � do tipo double precision
				aux.setCarga(rs.getInt("carga"));
				aux.setNome(rs.getString("nome"));
				
				listaDisciplinaCursada.add(aux);
			}
			rs.close();
			ps.close();
			return listaDisciplinaCursada;
			
		} catch (SQLException e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public void editar(List<DisciplinaCursada> disciplinaCursadas, Solicitacao sol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Solicitacao sol) {
		// TODO Auto-generated method stub
		
	}
}
