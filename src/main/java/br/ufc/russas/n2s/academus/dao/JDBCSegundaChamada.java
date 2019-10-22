package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.SegundaChamada;


public class JDBCSegundaChamada implements SegundaChamadaDAO {
	

	@Override
	public SegundaChamada cadastro(SegundaChamada segChamada) {
		String sql = "insert into academus.segunda_chamada( id_aluno, id_professor, id_disciplina, data_prova, justificativa) VALUES ( ?, ?, ?, ?, ?)";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			
			PreparedStatement insert = conn.prepareStatement(sql);			
			
			insert.setInt(1, segChamada.getAluno().getId());
			insert.setInt(2, segChamada.getProfessor().getId());
			insert.setString(3, segChamada.getDisciplina().getId());
			insert.setDate(4, segChamada.getDataProva());
			insert.setString(5, segChamada.getJustificativa());
			insert.execute();
			
			insert.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return segChamada;
	}

	@Override
	public List<SegundaChamada> listar(int limitInf, int limitSup) {
		
		String sql = "select * from academus.segunda_chamada order by id_segunda_chamada offset ? limit ?;";
		List<SegundaChamada> listaSegundaChamada = new ArrayList<SegundaChamada>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, limitInf);
			ps.setInt(2, limitSup);
			ResultSet rs = ps.executeQuery();
			
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			AlunoDAO aludao = df.criarAlunoDAO();
			DisciplinaDAO discdao = df.criarDisciplinaDAO();
			ProfessorDAO profdao = df.criarProfessorDAO();
			
			
			while(rs.next()){
				
				SegundaChamada aux = new SegundaChamada();
				
				aux.setIdSegundaChamada(rs.getInt("id_segunda_chamada"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				
				
				listaSegundaChamada.add(aux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaSegundaChamada;
	}

	@Override
	public List<SegundaChamada> listar(Aluno aluno, int limitInf, int limitSup) {
		String sql = "select * from academus.segunda_chamada WHERE id_aluno= ? order by id_segunda_chamada offset ? limit ?; ";
		List<SegundaChamada> listaSegundaChamada = new ArrayList<SegundaChamada>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aluno.getId());
			ps.setInt(2, limitInf);
			ps.setInt(3, limitSup);
			
			ResultSet rs = ps.executeQuery();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			AlunoDAO aludao = df.criarAlunoDAO();
			DisciplinaDAO discdao = df.criarDisciplinaDAO();
			ProfessorDAO profdao = df.criarProfessorDAO();
			
			while(rs.next()) {
				SegundaChamada aux = new SegundaChamada();
				
				aux.setIdSegundaChamada(rs.getInt("id_segunda_chamada"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				
				
				listaSegundaChamada.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		return listaSegundaChamada;
	}

	@Override
	public List<SegundaChamada> listar(Professor professor, int limitInf, int limitSup) {
		String sql = "select * from academus.segunda_chamada WHERE id_professor= ? order by id_segunda_chamada offset ? limit ?; ";
		List<SegundaChamada> listaSegundaChamada = new ArrayList<SegundaChamada>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
			ps.setInt(2, limitInf);
			ps.setInt(3, limitSup);
			
			ResultSet rs = ps.executeQuery();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			AlunoDAO aludao = df.criarAlunoDAO();
			DisciplinaDAO discdao = df.criarDisciplinaDAO();
			ProfessorDAO profdao = df.criarProfessorDAO();
			
			while(rs.next()) {
				SegundaChamada aux = new SegundaChamada();
				
				aux.setIdSegundaChamada(rs.getInt("id_segunda_chamada"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				
				
				listaSegundaChamada.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		return listaSegundaChamada;
	}

	@Override
	public List<SegundaChamada> listarCoordenador(Professor professor, int limitInf, int limitSup) {
		String sql = "select * from academus.segunda_chamada as sc, academus.perfil_academus as p  WHERE sc.id_professor= ? AND sc.id_aluno = p.id_perfil_academus AND p.id_curso = ? order by id_segunda_chamada offset ? limit ?; ";
		List<SegundaChamada> listaSegundaChamada = new ArrayList<SegundaChamada>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
			ps.setInt(2, professor.getCurso().getIdCurso());
			ps.setInt(3, limitInf);
			ps.setInt(4, limitSup);
			
			ResultSet rs = ps.executeQuery();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			AlunoDAO aludao = df.criarAlunoDAO();
			DisciplinaDAO discdao = df.criarDisciplinaDAO();
			ProfessorDAO profdao = df.criarProfessorDAO();
			
			while(rs.next()) {
				SegundaChamada aux = new SegundaChamada();
				
				aux.setIdSegundaChamada(rs.getInt("id_segunda_chamada"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				
				
				listaSegundaChamada.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		return listaSegundaChamada;
	}

	@Override
	public SegundaChamada editar(SegundaChamada segundaChamada) {

		String sql = "UPDATE academus.segunda_chamada SET id_aluno = ?, id_professor = ?, id_disciplina = ?, justificativa = ?, data_prova = ? WHERE id_segunda_chamada = ?;";
		Connection conn = ConnectionPool.getConnection();
		
		try{
			
			PreparedStatement update = conn.prepareStatement(sql);
			
			update.setInt(1, segundaChamada.getAluno().getId());
			update.setInt(2, segundaChamada.getProfessor().getId());
			update.setString(3, segundaChamada.getDisciplina().getId());
			update.setString(4, segundaChamada.getJustificativa());
			update.setDate(5, segundaChamada.getDataProva());
			update.setInt(6, segundaChamada.getIdSegundaChamada());
			
			update.executeUpdate();
			update.close();
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return segundaChamada;
	}

	@Override
	public void excluir(SegundaChamada segundaChamada) {
		String sql = "DELETE FROM academus.segunda_chamada WHERE id_segunda_chamada = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, segundaChamada.getIdSegundaChamada());
			
			ps.execute();
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
	}

	@Override
	public int numSolicitacoes(int pagina) {
		String sql = "WITH cte AS (SELECT * FROM academus.segunda_chamada) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pagina*10);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				resultSql = rs.getInt(1);
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return resultSql;
	}

	@Override
	public int numSolicitacoes(int pagina, Aluno aluno) {
		String sql = "WITH cte AS (SELECT * FROM academus.segunda_chamada where id_aluno=?) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aluno.getId());
			ps.setInt(2, pagina*10);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				resultSql = rs.getInt(1);
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return resultSql;
	}

	@Override
	public int numSolicitacoes(int pagina, Professor professor) {
		String sql = "WITH cte AS (SELECT * FROM academus.segunda_chamada where id_professor=?) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
			ps.setInt(2, pagina*10);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				resultSql = rs.getInt(1);
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return resultSql;
	}

	@Override
	public int numSolicitacoesCoordenador(int pagina, Professor professor) {
		String sql = "WITH cte AS (SELECT * FROM academus.segunda_chamada as sc, academus.perfil_academus as p  WHERE sc.id_professor= ? AND sc.id_aluno = p.id_perfil_academus AND p.id_curso = ?) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
			ps.setInt(2, professor.getCurso().getIdCurso());
			ps.setInt(3, pagina*10);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				resultSql = rs.getInt(1);
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return resultSql;
	}

}
