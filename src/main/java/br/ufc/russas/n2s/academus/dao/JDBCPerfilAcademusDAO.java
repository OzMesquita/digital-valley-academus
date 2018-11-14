package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;

import dao.DAOFactory;
import dao.JDBCPessoaDAO;
import dao.JDBCServidorDAO;
import model.Pessoa;

public class JDBCPerfilAcademusDAO implements PerfilAcademusDAO{

	@Override
	public PerfilAcademus cadastrar(PerfilAcademus perfil) {
		String sql = "INSERT INTO academus.perfil_academus(id_pessoa_usuario, id_nivel) VALUES (?, ?);";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setInt(1, perfil.getPessoa().getId());
			insert.setInt(2, NivelAcademus.getCodigo(perfil.getNivel()));
			
			insert.execute();
			insert.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfil;
	}

	@Override
	public List<PerfilAcademus> listar() {
		String sql = "SELECT id_pessoa_usuario, id_nivel FROM academus.perfil_academus;";
		ArrayList<PerfilAcademus> perfis = new ArrayList<PerfilAcademus>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			JDBCPessoaDAO daoPessoa = (JDBCPessoaDAO) DAOFactory.criarPessoaDAO();
			
			PreparedStatement ps = conn.prepareStatement(sql);
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
					//Esta cadastrado no Academus mas nãoo esta na base de dados
					//que o JDBCPessoaDAO esta utilizando, se tiver dando erro atualize
					//o bd.txt no c:\n2s
				}
				
			}
			
			ps.close();
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfis;
	}

	@Override
	public PerfilAcademus buscarPorId(int id) {
		String sql = "SELECT id_pessoa_usuario, id_nivel FROM academus.perfil_academus WHERE id_pessoa_usuario = "+ id +";";
		PerfilAcademus perfil = null;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			JDBCServidorDAO daoServidor = (JDBCServidorDAO) DAOFactory.criarServidorDAO();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				perfil = new PerfilAcademus();
				
				perfil.setNivel(NivelAcademus.getNivel(rs.getInt("id_nivel")));
				
				if(perfil.getNivel() == NivelAcademus.ALUNO){
					perfil.setPessoa(new JDBCAlunoDAO().buscarPorId(rs.getInt("id_pessoa_usuario")));
				} else if(perfil.getNivel() == NivelAcademus.COORDENADOR){
					perfil.setPessoa(new JDBCCoordenadorDAO().buscarPorId(rs.getInt("id_pessoa_usuario")));
				} else if(perfil.getNivel() == NivelAcademus.SECRETARIO){
					perfil.setPessoa(daoServidor.buscar(rs.getInt("id_pessoa_usuario")));
				} else if(perfil.getNivel() == NivelAcademus.PROFESSOR){
					perfil.setPessoa(new JDBCProfessorDAO().buscarPorId(rs.getInt("id_pessoa_usuario")));
				}
				
			}
			
			ps.close();
			rs.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfil;
	}

	@Override
	public PerfilAcademus editar(PerfilAcademus perfil) {
		String sql = "UPDATE academus.perfil_academus SET id_nivel = ? WHERE id_pessoa_usuario = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement editar = conn.prepareStatement(sql);
			editar.setInt(1, NivelAcademus.getCodigo(perfil.getNivel()));
			editar.setInt(2, perfil.getPessoa().getId());
			
			editar.executeUpdate();
			editar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return perfil;
	}

	@Override
	public void excluir(PerfilAcademus perfil) {
		String sql = "DELETE FROM academus.perfil_academus WHERE id_pessoa_usuario = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement excluir = conn.prepareStatement(sql);
			excluir.setInt(1, perfil.getPessoa().getId());
			
			excluir.execute();
			excluir.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
	}

}
