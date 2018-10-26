package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.NivelAcademus;

public class JDBCNivelAcademusDAO implements NivelAcademusDAO{
	
	private Connection connection;

	public JDBCNivelAcademusDAO() {
		connection = Conexao.getConexao();
	}

	@Override
	public NivelAcademus cadastrar(NivelAcademus nivel) {
		String sql = "INSERT INTO academus.nivel( id_nivel, rotulo) VALUES (?, ?);";
		
		try{
			PreparedStatement cadastrar = this.connection.prepareStatement(sql);
			cadastrar.setInt(1, NivelAcademus.getCodigo(nivel));
			cadastrar.setString(2, NivelAcademus.getDescricao(nivel));
			
			cadastrar.execute();
			cadastrar.close();
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return nivel;
	}
	
	@Override
	public NivelAcademus buscar(int id) {
		String sql = "SELECT id_nivel, rotulo FROM academus.nivel WHERE id_nivel = ?;";
		NivelAcademus nv = null;
		
		try{
			PreparedStatement buscar = this.connection.prepareStatement(sql);
			buscar.setInt(1, id);
			
			ResultSet rs = buscar.executeQuery();
			
			if(rs.next()){
				nv = NivelAcademus.getNivel(rs.getInt("id_nivel"));
				
			}
			
			buscar.close();
			return nv;
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public NivelAcademus editar(NivelAcademus nivel) {
		String sql = "UPDATE academus.nivel SET id_nivel=?, rotulo=? WHERE id_nivel=?;";
		NivelAcademus nv = null;
		try{
			
		} catch() {
			
		}
	}

	@Override
	public void excluir(NivelAcademus nivel) {
		// TODO Auto-generated method stub
		
	}
	
}
