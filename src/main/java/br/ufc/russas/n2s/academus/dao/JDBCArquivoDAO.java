package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public class JDBCArquivoDAO implements ArquivoDAO{
	
	private Connection connection;
	
	public JDBCArquivoDAO(){
		this.connection = Conexao.getConexao();
	}

	public Arquivo cadastrarArquivo(Arquivo arq, Solicitacao sol) {
		String sql = "INSERT INTO academus.arquivo(caminho, id_solicitacao) VALUES (?, ?);";
		
		try{
			
			PreparedStatement insert = this.connection.prepareStatement(sql);
			insert.setString(1, arq.getCaminho());
			insert.setInt(2, sol.getIdSolicitacao());
			insert.execute();
			
			insert.close();
			return arq;
		} catch(SQLException e) {	
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public Arquivo buscarPorSolicitacao(Solicitacao sol){
		String sql = "SELECT id_arquivo, caminho, id_solicitacao FROM academus.arquivo WHERE id_solicitacao = " + sol.getIdSolicitacao() + ";";
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Arquivo arq = new Arquivo();
			arq.setIdArquivo(rs.getInt("id_arquivo"));
			arq.setCaminho(rs.getString("caminho"));
			
			rs.close();
			ps.close();
			
			return arq;
		} catch (SQLException e){
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public Arquivo editar(Arquivo arquivo) {
		String sql = "UPDATE academus.arquivo SET caminho=? WHERE id_arquivo = ?;";
		
		try{
			PreparedStatement editar = this.connection.prepareStatement(sql);
			editar.setString(1, arquivo.getCaminho());
			editar.setInt(2, arquivo.getIdArquivo());
			editar.execute();
			
			editar.close();
			return arquivo;
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public void excluir(Arquivo arquivo) {
		String sql = "DELETE FROM academus.arquivo WHERE id_arquivo = ?;";
		
		try{
			PreparedStatement excluir = this.connection.prepareStatement(sql);
			excluir.setInt(1, arquivo.getIdArquivo());
			excluir.execute();
			
		} catch(SQLException e) {
			e.getMessage();
		}
	}
}
	
