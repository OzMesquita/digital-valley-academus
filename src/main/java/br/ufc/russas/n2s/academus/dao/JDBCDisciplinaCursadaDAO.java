package br.ufc.russas.n2s.academus.dao;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.modelo.DisciplinaCursada;
import br.ufc.russas.n2s.academus.modelo.Solicitacao;

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
	public void cadasatrar(List<DisciplinaCursada> disciplinas, Solicitacao sol) {
		// TODO Auto-generated method stub
		
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
				aux.setNota(rs.getFloat("nota"));//Talvez de erro, já que no banco a coluna 'nota' é do tipo double precision
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
