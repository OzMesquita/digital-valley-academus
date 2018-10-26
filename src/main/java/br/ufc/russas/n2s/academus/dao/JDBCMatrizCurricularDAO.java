package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.ComponenteCurricular;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;

public class JDBCMatrizCurricularDAO implements MatrizCurricularDAO{
	
	private Connection connection;

	public JDBCMatrizCurricularDAO() {
		connection = Conexao.getConexao();
	}
	
	@Override
	public MatrizCurricular cadastrar(MatrizCurricular mat) {
		String sql = "insert into academus.matriz_curricular(nome, periodo_letivo, carga_horario, prazo_minimo, prazo_maximo, vigente, ativo, id_curso) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setString(1, mat.getNome());
			insert.setString(2, mat.getPeriodoLetivo());
			insert.setInt(3, mat.getCarga());
			insert.setInt(4, mat.getPrazoMinimo());
			insert.setInt(5, mat.getPrazoMaximo());
			insert.setBoolean(6, mat.isVigente());
			insert.setBoolean(7, mat.isAtivo());
			insert.setInt(8, mat.getIdCurso());
			
			insert.execute();
			insert.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return mat;
	}

	@Override
	public List<MatrizCurricular> listar() {
		String sql = "select * from academus.matriz_curricular";
		JDBCComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		List<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MatrizCurricular aux = new MatrizCurricular();
				aux.setIdMatriz(rs.getInt("id_matriz"));
				aux.setNome(rs.getString("nome"));
				aux.setPeriodoLetivo(rs.getString("periodo_letivo"));
				aux.setCarga(rs.getInt("carga_horario"));
				aux.setPrazoMinimo(rs.getInt("prazo_minimo"));
				aux.setPrazoMaximo(rs.getInt("prazo_maximo"));
				aux.setVigente(rs.getBoolean("vigente"));
				aux.setAtivo(rs.getBoolean("ativo"));
				aux.setIdCurso(rs.getInt("id_curso"));
				aux.setComponentes(cc.listar(rs.getInt("id_matriz")));
				
				listaMatrizes.add(aux);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaMatrizes;
	}
	
	@Override
	public MatrizCurricular buscarPorId(int idMatriz){
		JDBCComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		String sql = "select * from academus.matriz_curricular where id_matriz = "+idMatriz+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			MatrizCurricular aux = null;
			if(rs.next()){
				aux = new MatrizCurricular();
				aux.setIdMatriz(rs.getInt("id_matriz"));
				aux.setNome(rs.getString("nome"));
				aux.setPeriodoLetivo(rs.getString("periodo_letivo"));
				aux.setCarga(rs.getInt("carga_horario"));
				aux.setPrazoMinimo(rs.getInt("prazo_minimo"));
				aux.setPrazoMaximo(rs.getInt("prazo_maximo"));
				aux.setVigente(rs.getBoolean("vigente"));
				aux.setAtivo(rs.getBoolean("ativo"));
				aux.setIdCurso(rs.getInt("id_curso"));
				aux.setComponentes(cc.listar(rs.getInt("id_matriz")));
			}
			
			rs.close();
			ps.close();
			return aux;
		}catch(SQLException e){
			e.getMessage();
		}
		return null;
	}
	
	@Override
	public List<MatrizCurricular> buscarPorCurso(int idCurso){
		String sql = "select * from academus.matriz_curricular where id_curso = "+idCurso+";";
		JDBCComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		List<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				MatrizCurricular aux = new MatrizCurricular();
				aux.setIdMatriz(rs.getInt("id_matriz"));
				aux.setNome(rs.getString("nome"));
				aux.setPeriodoLetivo(rs.getString("periodo_letivo"));
				aux.setCarga(rs.getInt("carga_horario"));
				aux.setPrazoMinimo(rs.getInt("prazo_minimo"));
				aux.setPrazoMaximo(rs.getInt("prazo_maximo"));
				aux.setVigente(rs.getBoolean("vigente"));
				aux.setAtivo(rs.getBoolean("ativo"));
				aux.setIdCurso(rs.getInt("id_curso"));
				aux.setComponentes(cc.listar(rs.getInt("id_matriz")));
				
				listaMatrizes.add(aux);
			}
			
			rs.close();
			ps.close();
		}catch(SQLException e){
			e.getMessage();
		}
		return listaMatrizes;
	}
	
	@Override
	public List<MatrizCurricular> buscarPorNome(String nome){
		String sql = "select * from academus.matriz_curricular where nome like '%"+nome+"%';";
		List<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				MatrizCurricular aux = new MatrizCurricular();
				aux.setIdMatriz(rs.getInt("id_matriz_curricular"));
				aux.setNome(rs.getString("nome"));
				aux.setCarga(rs.getInt("carga_horario"));
				aux.setPrazoMinimo(rs.getInt("prazo_minimo"));
				aux.setPrazoMaximo(rs.getInt("prazo_maximo"));
				aux.setVigente(rs.getBoolean("vigente"));
				aux.setAtivo(rs.getBoolean("ativo"));
				aux.setIdCurso(rs.getInt("id_curso"));
				listaMatrizes.add(aux);
			}
			
			rs.close();
			ps.close();
		}catch(SQLException e){
			e.getMessage();
		}
		return listaMatrizes;
	}

	@Override
	public MatrizCurricular editar(MatrizCurricular mat) {
		String sql = "UPDATE academus.matriz_curricular SET nome = ?, periodo_letivo = ?, carga_horario = ?, prazo_minimo = ?, prazo_maximo = ?, vigente = ?, ativo = ?, id_curso = ? WHERE id_matriz = ?";
		
		try {                
            PreparedStatement update = connection.prepareStatement(sql);
            update.setString(1, mat.getNome());            
            update.setString(2, mat.getPeriodoLetivo());
            update.setInt(3, mat.getCarga());
            update.setInt(4, mat.getPrazoMinimo());
            update.setInt(5, mat.getPrazoMaximo());
            update.setBoolean(6, mat.isVigente());
            update.setBoolean(7, mat.isAtivo());
            update.setInt(8, mat.getIdCurso());
            update.setInt(9, mat.getIdMatriz());
            
            update.execute();
            update.close();
        } catch (SQLException e) {
            e.getMessage();
        }
		return mat;
	}

	@Override
	public void excluir(MatrizCurricular mat) {
		String sql = "delete from academus.matriz_curricular where id_matriz = "+mat.getIdMatriz()+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.execute();
			ps.close();
		}catch(SQLException e){
			e.getMessage();
		}
	}

}
