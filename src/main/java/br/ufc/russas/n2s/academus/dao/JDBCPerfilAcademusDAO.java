package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;

import dao.DAOFactory;
import dao.JDBCPessoaDAO;
import model.Pessoa;

public class JDBCPerfilAcademusDAO implements PerfilAcademusDAO{
	
	private Connection connection;
	
	public JDBCPerfilAcademusDAO() {
		this.connection = Conexao.getConexao();
	}

	@Override
	public PerfilAcademus cadastrar(PerfilAcademus perfil) {
		String sql = "INSERT INTO academus.perfil_academus(id_pessoa_usuario, id_nivel) VALUES (?, ?);";
		
		try{
			PreparedStatement insert = this.connection.prepareStatement(sql);
			insert.setInt(1, perfil.getPessoa().getId());
			insert.setInt(2, NivelAcademus.getCodigo(perfil.getNivel()));
			insert.execute();
			
			insert.close();
			return perfil;
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public List<PerfilAcademus> listar() {
		String sql = "SELECT id_pessoa_usuario, id_nivel FROM academus.perfil_academus;";
		
		try{
			JDBCPessoaDAO daoPessoa = (JDBCPessoaDAO) DAOFactory.criarPessoaDAO();
			ArrayList<PerfilAcademus> perfis = new ArrayList<PerfilAcademus>();
			
			
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				PerfilAcademus temp = new PerfilAcademus();
				
				Pessoa tempPessoa = daoPessoa.buscarPorId(rs.getInt("id_pessoa_usuario"));
				
				if(tempPessoa != null){
					temp.setPessoa(tempPessoa);
					temp.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
					
					perfis.add(temp);
				}
				else{
					//Esta cadastrado no Academus mas n�o esta na base de dados
					//que o JDBCPessoaDAO esta utilizando, se tiver dando erro atualize
					//o bd.txt no c:\n2s
				}
				
			}
			
			ps.close();
			rs.close();
			return perfis;
			
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public PerfilAcademus buscarPorId(int id) {
		String sql = "SELECT id_pessoa_usuario, id_nivel FROM academus.perfil_academus WHERE id_pessoa_usuario = "+ id +";";
		
		try{
			JDBCPessoaDAO daoPessoa = (JDBCPessoaDAO) DAOFactory.criarPessoaDAO();			
			
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				PerfilAcademus perfil = new PerfilAcademus();
				
				perfil.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				perfil.setPessoa(daoPessoa.buscarPorId(rs.getInt("id_pessoa_usuario")));
				
				ps.close();
				rs.close();
				return perfil;
			}
						
		} catch(SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public PerfilAcademus editar(PerfilAcademus perfil) {
		String sql = "UPDATE academus.perfil_academus SET id_nivel = ? WHERE id_pessoa_usuario = ?;";
		
		try{
			PreparedStatement editar = this.connection.prepareStatement(sql);
			editar.setInt(1, NivelAcademus.getCodigo(perfil.getNivel()));
			editar.setInt(2, perfil.getPessoa().getId());
			editar.executeUpdate();
			editar.close();
			
			return perfil;
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public void excluir(PerfilAcademus perfil) {
		String sql = "DELETE FROM academus.perfil_academus WHERE id_pessoa_usuario = ?;";
		
		try{
			PreparedStatement excluir = this.connection.prepareStatement(sql);
			excluir.setInt(1, perfil.getPessoa().getId());
			excluir.execute();
			excluir.close();
			
		} catch(SQLException e) {
			e.getMessage();
		}
	}

}
