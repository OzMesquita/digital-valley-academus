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

	public MatrizCurricular insereMatriz(MatrizCurricular m) {
		try {
			String sql = "insert into academus.matriz_curricular(nome, periodo_letivo, carga_horario, prazo_minimo, prazo_maximo, vigente, ativo, id_curso) values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, m.getNome());
			insert.setString(2, m.getPeriodoLetivo());
			insert.setInt(3, m.getCarga());
			insert.setInt(4, m.getPrazoMinimo());
			insert.setInt(5, m.getPrazoMaximo());
			insert.setBoolean(6, m.isVigente());
			insert.setBoolean(7, m.isAtivo());
			insert.setInt(8, m.getIdCurso());
			insert.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return m;
	}

	public List<MatrizCurricular> listarMatrizes() {
		JDBCComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		String sql = "select * from academus.matriz_curricular";
		ArrayList<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
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
			return listaMatrizes;
		} catch (SQLException e) {
			e.getMessage();
			return null;
		}
	}
	public MatrizCurricular buscarPorId(int busca){
		JDBCComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		String sql = "select * from academus.matriz_curricular where id_matriz = "+busca+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
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
			rs.close();
			ps.close();
			return aux;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public ArrayList<MatrizCurricular> buscarPorNome(String busca){
		String sql = "select * from academus.matriz_curricular where nome like '%"+busca+"%';";
		ArrayList<MatrizCurricular> listaMatrizes = new ArrayList<MatrizCurricular>();
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
			return listaMatrizes;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public boolean atualizarMatriz(MatrizCurricular m){
        try {
            String sql = "UPDATE academus.matriz_curricular SET nome = ?, periodo_letivo = ?, carga_horario = ?, prazo_minimo = ?, prazo_maximo = ?, vigente = ?, ativo = ?, id_curso = ? WHERE id_matriz = ?";                
            PreparedStatement update = connection.prepareStatement(sql);
            update.setString(1, m.getNome());            
            update.setString(2, m.getPeriodoLetivo());
            update.setInt(3, m.getCarga());
            update.setInt(4, m.getPrazoMinimo());
            update.setInt(5, m.getPrazoMaximo());
            update.setBoolean(6, m.isVigente());
            update.setBoolean(7, m.isAtivo());
            update.setInt(8, m.getIdCurso());
            update.setInt(9, m.getIdMatriz());
            return update.execute();
            //connection.commit();
            //update.close();
        } catch (Exception e) {
            e.getMessage();
        }
		return false;
    }
	
	public void gerenciarComponentes(ArrayList<ComponenteCurricular> comps, int id_matriz) {
		JDBCComponenteCurricularDAO ccdao = new JDBCComponenteCurricularDAO();
		List<ComponenteCurricular> listacc = ccdao.listar(id_matriz);
		for(int i=0;i<comps.size();i++) {
			boolean aux = true;
			for(int j=0;j<listacc.size();j++) {
				if(comps.get(i).getDisciplina().getId() == listacc.get(j).getDisciplina().getId()) {
					aux = false;
					listacc.remove(j);
					break;
				}
			}
			if(aux){
				//ccdao.insereDisciplinaMatriz(comps.get(i));
			}
		}
		for(int i=0;i<listacc.size();i++) {
			//ccdao.excluirComponente(listacc.get(i).getIdComponente());
		}
	}
	
	public MatrizCurricular buscaMatriz(MatrizCurricular d){
        String sql = "select * from academus.matriz_curricular where id_matriz=?";
        MatrizCurricular aux = null;
        try{
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, d.getIdMatriz());
            ResultSet rs = ps.executeQuery();
            //MatrizCurricular aux = new MatrizCurricular();
            while(rs.next()){
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
            }
            //rs.close();
            //ps.close();
            return aux;
        }catch(SQLException e){
            e.getMessage();
            return null;
        }
    }
	
	public boolean deletarMatriz(int busca){
		String sql = "delete from academus.matriz_curricular where id_matriz = "+busca+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			return ps.execute();
		}catch(SQLException e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public MatrizCurricular cadastrar(MatrizCurricular mat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MatrizCurricular> listar() {
		JDBCComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		String sql = "select * from academus.matriz_curricular";
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
			return listaMatrizes;
		} catch (SQLException e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public MatrizCurricular editar(MatrizCurricular mat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(MatrizCurricular mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gerenciarComponentes(List<ComponenteCurricular> comps, int idMatriz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MatrizCurricular> buscarPorCurso(int idCurso){
		JDBCComponenteCurricularDAO cc = new JDBCComponenteCurricularDAO();
		String sql = "select * from academus.matriz_curricular where id_curso = "+idCurso+";";
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
			return listaMatrizes;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
}
