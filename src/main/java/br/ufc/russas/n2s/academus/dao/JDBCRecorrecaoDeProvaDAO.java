package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.RecorrecaoDeProva;

public class JDBCRecorrecaoDeProvaDAO implements RecorrecaoDeProvaDAO {
	
	

	@Override
	public RecorrecaoDeProva cadastro(RecorrecaoDeProva recorrecaoDeProva) {
		String sql = "insert into academus.recorrecao_de_prova(id_aluno, id_professor, data_prova, data_recebimento, hora_prova, hora_recebimento, justificativa, id_disciplina) VALUES (?,?,?,?,?,?,?,?)";
		
		Connection conn = ConnectionPool.getConnection();
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			
			insert.setInt(1, recorrecaoDeProva.getAluno().getId());
			insert.setInt(2, recorrecaoDeProva.getProfessor().getId());
			insert.setDate(3, recorrecaoDeProva.getDataProva());
			insert.setDate(4, recorrecaoDeProva.getDataRecebimento());
			insert.setTime(5, recorrecaoDeProva.getHorarioDaProva());
			insert.setTime(6, recorrecaoDeProva.getHorarioRecebimento());
			insert.setString(7, recorrecaoDeProva.getJustificativa());
			insert.setString(8, recorrecaoDeProva.getDisciplina().getId());
			
			insert.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		
		return recorrecaoDeProva;
	}

	@Override
	public List<RecorrecaoDeProva> listar(int limitInf, int limitSup) {
		String sql = "select * from academus.recorrecao_de_prova order by id_recorrecao_de_prova offset ? limit ?;";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, limitInf);
			ps.setInt(2, limitSup);
			ResultSet rs = ps.executeQuery();
			
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			AlunoDAO aludao = df.criarAlunoDAO();
			ProfessorDAO profdao = df.criarProfessorDAO();
			DisciplinaDAO discdao = df.criarDisciplinaDAO();
			
			while(rs.next()) {
				RecorrecaoDeProva aux = new RecorrecaoDeProva();
				
				aux.setIdRecorrecao(rs.getInt("id_recorrecao_de_prova"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				aux.setDataRecebimento(Date.valueOf(rs.getString("data_recebimento")));
				aux.setHorarioDaProva(Time.valueOf(rs.getString("hora_prova")));
				aux.setHorarioRecebimento(Time.valueOf(rs.getString("hora_recebimento")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				
				listaRecorrecaoDeProva.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaRecorrecaoDeProva;
	}

	@Override
	public List<RecorrecaoDeProva> listar(Aluno aluno, int limitInf, int limitSup) {
		String sql = "select * from academus.recorrecao_de_prova WHERE id_aluno= ? order by id_recorrecao_de_prova offset ? limit ?;";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aluno.getId());
			ps.setInt(2, limitInf);
			ps.setInt(3, limitSup);
			
			ResultSet rs = ps.executeQuery();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			AlunoDAO aludao = df.criarAlunoDAO();
			ProfessorDAO profdao = df.criarProfessorDAO();
			DisciplinaDAO discdao = df.criarDisciplinaDAO();
			
			while(rs.next()) {
				RecorrecaoDeProva aux = new RecorrecaoDeProva();
				
				aux.setIdRecorrecao(rs.getInt("id_recorrecao_de_prova"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				aux.setDataRecebimento(Date.valueOf(rs.getString("data_recebimento")));
				aux.setHorarioDaProva(Time.valueOf(rs.getString("hora_prova")));
				aux.setHorarioRecebimento(Time.valueOf(rs.getString("hora_recebimento")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				
				listaRecorrecaoDeProva.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaRecorrecaoDeProva;
	}

	@Override
	public List<RecorrecaoDeProva> listar(Professor professor, int limitInf, int limitSup) {
		String sql = "select * from academus.recorrecao_de_prova WHERE id_professor= ? order by id_recorrecao_de_prova offset ? limit ?;";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
			ps.setInt(2, limitInf);
			ps.setInt(3, limitSup);
			
			ResultSet rs = ps.executeQuery();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			AlunoDAO aludao = df.criarAlunoDAO();
			ProfessorDAO profdao = df.criarProfessorDAO();
			DisciplinaDAO discdao = df.criarDisciplinaDAO();
			
			while(rs.next()) {
				RecorrecaoDeProva aux = new RecorrecaoDeProva();
				
				aux.setIdRecorrecao(rs.getInt("id_recorrecao_de_prova"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				aux.setDataRecebimento(Date.valueOf(rs.getString("data_recebimento")));
				aux.setHorarioDaProva(Time.valueOf(rs.getString("hora_prova")));
				aux.setHorarioRecebimento(Time.valueOf(rs.getString("hora_recebimento")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				
				listaRecorrecaoDeProva.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaRecorrecaoDeProva;
	}

	@Override
	public List<RecorrecaoDeProva> listarCoordenador(Professor professor, int limitInf, int limitSup) {
		String sql = "select * from academus.recorrecao_de_prova as rp, academus.perfil_academus as p  WHERE rp.id_professor= ? AND rp.id_aluno = p.id_perfil_academus AND p.id_curso = ? order by id_recorrecao_de_prova offset ? limit ?; ";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
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
			ProfessorDAO profdao = df.criarProfessorDAO();
			DisciplinaDAO discdao = df.criarDisciplinaDAO();
			
			while(rs.next()) {
				RecorrecaoDeProva aux = new RecorrecaoDeProva();
				
				aux.setIdRecorrecao(rs.getInt("id_recorrecao_de_prova"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				aux.setDataRecebimento(Date.valueOf(rs.getString("data_recebimento")));
				aux.setHorarioDaProva(Time.valueOf(rs.getString("hora_prova")));
				aux.setHorarioRecebimento(Time.valueOf(rs.getString("hora_recebimento")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				
				listaRecorrecaoDeProva.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		
		return listaRecorrecaoDeProva;
	}

	@Override
	public RecorrecaoDeProva buscarPorId(int idRecorrecaoDeProva) {
		Connection conn = ConnectionPool.getConnection();
		String sql = "select * from academus.recorrecao_de_prova as rp where rp.id_segunda_chamada = ?;";
		RecorrecaoDeProva aux = new RecorrecaoDeProva();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idRecorrecaoDeProva);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				DAOFactoryJDBC df = new DAOFactoryJDBC();
				
				AlunoDAO aludao = df.criarAlunoDAO();
				ProfessorDAO profdao = df.criarProfessorDAO();
				DisciplinaDAO discdao = df.criarDisciplinaDAO();
				
				aux.setIdRecorrecao(rs.getInt("id_recorrecao_de_prova"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				aux.setDataRecebimento(Date.valueOf(rs.getString("data_recebimento")));
				aux.setHorarioDaProva(Time.valueOf(rs.getString("hora_prova")));
				aux.setHorarioRecebimento(Time.valueOf(rs.getString("hora_recebimento")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
			}
			
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		
		
		return aux;
	}

	@Override
	public RecorrecaoDeProva editar(RecorrecaoDeProva recorrecaoDeProva) {
		String sql = "UPDATE academus.recorrecao_de_prova SET id_aluno = ?, id_professor = ?, data_prova = ?, hora_prova = ?, hora_recebimento = ?, justificativa = ?, id_disciplina = ?, data_prova = ? WHERE id_recorrecao_de_prova = ?;";
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement update = conn.prepareStatement(sql);
			
			update.setInt(1, recorrecaoDeProva.getAluno().getId());
			update.setInt(2, recorrecaoDeProva.getProfessor().getId());
			update.setDate(3, recorrecaoDeProva.getDataProva());
			update.setDate(4, recorrecaoDeProva.getDataRecebimento());
			update.setTime(5, recorrecaoDeProva.getHorarioDaProva());
			update.setTime(6, recorrecaoDeProva.getHorarioRecebimento());
			update.setString(7, recorrecaoDeProva.getJustificativa());
			update.setString(8, recorrecaoDeProva.getDisciplina().getId());
			
			update.executeQuery();
			update.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return recorrecaoDeProva;
	}

	@Override
	public void excluir(RecorrecaoDeProva recorrecaoDeProva) {
		String sql = "DELETE FROM academus.recorrecao_de_prova WHERE id_recorrecao_de_prova = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, recorrecaoDeProva.getIdRecorrecao());
			
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numSolicitacoes(int pagina, Aluno aluno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numSolicitacoes(int pagina, Professor professor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numSolicitacoesCoordenador(int pagina, Professor professor) {
		// TODO Auto-generated method stub
		return 0;
	}

}
