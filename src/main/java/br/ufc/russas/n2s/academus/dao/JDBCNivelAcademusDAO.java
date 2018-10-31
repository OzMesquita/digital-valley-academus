package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.NivelAcademus;

public class JDBCNivelAcademusDAO extends JDBCDAO implements NivelAcademusDAO{

	@Override
	public NivelAcademus cadastrar(NivelAcademus nivel) {
		String sql = "INSERT INTO academus.nivel( id_nivel, rotulo) VALUES (?, ?);";
		
		super.open();
		try{
			PreparedStatement cadastrar = this.getConnection().prepareStatement(sql);
			cadastrar.setInt(1, NivelAcademus.getCodigo(nivel));
			cadastrar.setString(2, NivelAcademus.getDescricao(nivel));
			
			cadastrar.execute();
			cadastrar.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();
		}
		
		return nivel;
	}
	
	@Override
	public NivelAcademus buscar(int id) {
		String sql = "SELECT id_nivel, rotulo FROM academus.nivel WHERE id_nivel = ?;";
		NivelAcademus nv = NivelAcademus.INDEFINIDO;
		
		super.open();
		try{
			PreparedStatement buscar = this.getConnection().prepareStatement(sql);
			buscar.setInt(1, id);
			
			ResultSet rs = buscar.executeQuery();
			
			if(rs.next()){
				nv = NivelAcademus.getNivel(rs.getInt("id_nivel"));
			}
			
			buscar.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();
		}
		
		return nv;
	}

	@Override
	public NivelAcademus editar(NivelAcademus nivel) {
		String sql = "UPDATE academus.nivel SET id_nivel=?, rotulo=? WHERE id_nivel=?;";
		
		super.open();
		try{
			PreparedStatement ps = this.getConnection().prepareStatement(sql);
			ps.setInt(1, NivelAcademus.getCodigo(nivel));
			ps.setString(2, NivelAcademus.getDescricao(nivel));
			ps.setInt(3, NivelAcademus.getCodigo(nivel));
			
			ps.execute();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();
		}
		
		return nivel;
	}

	@Override
	public void excluir(NivelAcademus nivel) {
		String sql = "DELETE FROM academus.nivel WHERE id_nivel = ?;";
		
		super.open();
		try{
			PreparedStatement ps = this.getConnection().prepareStatement(sql);
			ps.setInt(1, NivelAcademus.getCodigo(nivel));
			
			ps.execute();
			ps.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
	}
	
}
