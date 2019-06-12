package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.MatrizCurricular;

public class JDBCMatrizCurricularDAO implements MatrizCurricularDAO{
	
	@Override
	public MatrizCurricular cadastrar(MatrizCurricular mat) {
		String sql = "insert into academus.matriz_curricular(nome, periodo_letivo, carga_horario, prazo_minimo, prazo_maximo, vigente, ativo, id_curso) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			
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
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return mat;
	}

	@Override
	public List<MatrizCurricular> listar(int limiteInf, int limiteSup) {
		String sql = "select * from academus.matriz_curricular order by id_matriz offset ? limit ?;";
		ComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		List<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, limiteInf);
			ps.setInt(2, limiteSup);
			
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
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaMatrizes;
	}
	
	@Override
	public List<MatrizCurricular> listar() {
		String sql = "select * from academus.matriz_curricular order by id_matriz;";
		ComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		List<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
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
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaMatrizes;
	}
	
	@Override
	public MatrizCurricular buscarPorId(int idMatriz){
		String sql = "select * from academus.matriz_curricular where id_matriz = "+idMatriz+";";
		ComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		MatrizCurricular aux = null;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
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
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return aux;
	}
	
	@Override
	public List<MatrizCurricular> buscarPorCurso(int idCurso){
		String sql = "select * from academus.matriz_curricular where id_curso = "+idCurso+" order by id_matriz;";
		ComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		List<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
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
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaMatrizes;
	}
	
	@Override
	public List<MatrizCurricular> buscarPorCurso(int idCurso, int limiteInf, int limiteSup){
		String sql = "select * from academus.matriz_curricular where id_curso = "+idCurso+" order by id_matriz offset ? limit ?;";
		ComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		List<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, limiteInf);
			ps.setInt(2, limiteSup);
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
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaMatrizes;
	}
	
	@Override
	public List<MatrizCurricular> buscarPorNome(String nome, int limiteInf, int limiteSup){
		String sql = "select * from academus.matriz_curricular where nome like '%"+nome+"%' order by id_matriz offset ? limit ?;";
		List<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, limiteInf);
			ps.setInt(2, limiteSup);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				MatrizCurricular aux = new MatrizCurricular();
				aux.setIdMatriz(rs.getInt("id_matriz"));
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
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaMatrizes;
	}

	@Override
	public MatrizCurricular editar(MatrizCurricular mat) {
		String sql = "UPDATE academus.matriz_curricular SET nome = ?, periodo_letivo = ?, carga_horario = ?, prazo_minimo = ?, prazo_maximo = ?, vigente = ?, ativo = ?, id_curso = ? WHERE id_matriz = ?";
		
		Connection conn = ConnectionPool.getConnection();
		try {                
            PreparedStatement update = conn.prepareStatement(sql);
            update.setString(1, mat.getNome());            
            update.setString(2, mat.getPeriodoLetivo());
            update.setInt(3, mat.getCarga());
            update.setInt(4, mat.getPrazoMinimo());
            update.setInt(5, mat.getPrazoMaximo());
            update.setBoolean(6, mat.isVigente());
            update.setBoolean(7, mat.isAtivo());
            update.setInt(8, mat.getIdCurso());
            update.setInt(9, mat.getIdMatriz());
            
            update.executeUpdate();
            update.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	ConnectionPool.releaseConnection(conn);
        }
		
		return mat;
	}

	@Override
	public void excluir(MatrizCurricular mat) {
		String sql = "delete from academus.matriz_curricular where id_matriz = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mat.getIdMatriz());
			
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
	}

	@Override
	public MatrizCurricular buscarPorSolicitacao(int idSolicitacao) {
		String sql = "SELECT DISTINCT academus.matriz_curricular.* FROM academus.solicitacao "
				+ "INNER JOIN academus.componente_curricular ON academus.solicitacao.id_componente = academus.componente_curricular.id_disciplina_matriz AND academus.solicitacao.id_solicitacao = ? "
				+ "LEFT JOIN academus.matriz_curricular ON academus.componente_curricular.id_matriz = academus.matriz_curricular.id_matriz;";
		
		MatrizCurricular matriz = new MatrizCurricular();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idSolicitacao);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				matriz.setIdMatriz(rs.getInt("id_matriz"));
				matriz.setNome(rs.getString("nome"));
				matriz.setCarga(rs.getInt("carga_horario"));
				matriz.setPrazoMinimo(rs.getInt("prazo_minimo"));
				matriz.setPrazoMaximo(rs.getInt("prazo_maximo"));
				matriz.setVigente(rs.getBoolean("vigente"));
				matriz.setAtivo(rs.getBoolean("ativo"));
				matriz.setIdCurso(rs.getInt("id_curso"));

			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return matriz;
	}

	@Override
	public boolean verificarSeEstaSendoUtilizada(int idMat) {
		String sql = "SELECT COUNT(academus.matriz_curricular.*) FROM academus.solicitacao "
				+ "INNER JOIN academus.componente_curricular ON academus.solicitacao.id_componente = academus.componente_curricular.id_disciplina_matriz "
				+ "INNER JOIN academus.matriz_curricular ON academus.componente_curricular.id_matriz = academus.matriz_curricular.id_matriz "
				+ "WHERE academus.matriz_curricular.id_matriz = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idMat);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				int qtdUtilizacao = rs.getInt("count");
				
				if(qtdUtilizacao > 0){
					return true;
				}
				
				return false;
			}
			
			ps.close();
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return false;
	}

}
