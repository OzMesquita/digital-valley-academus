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
import br.ufc.russas.n2s.academus.model.Funcionario;
import br.ufc.russas.n2s.academus.model.RecorrecaoDeProva;
import br.ufc.russas.n2s.academus.model.StatusRecorrecao;
import br.ufc.russas.n2s.academus.model.Arquivo;
public class JDBCRecorrecaoDeProvaDAO implements RecorrecaoDeProvaDAO {
	
	

	@Override
	public RecorrecaoDeProva cadastro(RecorrecaoDeProva recorrecaoDeProva) {
		
		String sql;
		//sql = "insert into academus.recorrecao_de_prova(id_aluno, id_professor, data_prova, data_recebimento, hora_prova, hora_recebimento, justificativa, id_disciplina, status,id_arquivo) VALUES (?,?,?,?,?,?,?,?,?,?)";
		if(recorrecaoDeProva.getIdArquivo() != 0) {
			System.out.println("Arquivo salvo");
			sql = "insert into academus.recorrecao_de_prova(id_aluno, id_professor, data_prova, data_recebimento, hora_prova, hora_recebimento, justificativa, id_disciplina, status, id_arquivo) VALUES (?,?,?,?,?,?,?,?,?,?)";
		} else {
			sql = "insert into academus.recorrecao_de_prova(id_aluno, id_professor, data_prova, data_recebimento, hora_prova, hora_recebimento, justificativa, id_disciplina, status) VALUES (?,?,?,?,?,?,?,?,?)";
		}
		
		
		
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
			insert.setInt(9, StatusRecorrecao.getCodigo(recorrecaoDeProva.getStatus()));
			if(recorrecaoDeProva.getIdArquivo()!=0) {
				insert.setInt(10, recorrecaoDeProva.getIdArquivo());
			}
			
			//insert.setInt(10,);
			
			
			insert.execute();
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
				aux.setStatus(StatusRecorrecao.getStatus(rs.getInt("status")));
				
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
				aux.setStatus(StatusRecorrecao.getStatus(rs.getInt("status")));
				
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
				aux.setStatus(StatusRecorrecao.getStatus(rs.getInt("status")));
				
				listaRecorrecaoDeProva.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaRecorrecaoDeProva;
	}

	@Override
	public List<RecorrecaoDeProva> listarCoordenador(Professor professor, int limitInf, int limitSup) {
		String sql = "select * from academus.recorrecao_de_prova as rp, academus.perfil_academus as p  WHERE rp.id_aluno = p.id_perfil_academus AND p.id_curso = ? order by id_recorrecao_de_prova offset ? limit ?; ";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
		Connection conn = ConnectionPool.getConnection();
		
		
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getCurso().getIdCurso());
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
				aux.setStatus(StatusRecorrecao.getStatus(rs.getInt("status")));
				
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
	public List<RecorrecaoDeProva> listarPorStatus(StatusRecorrecao status, int limitInf, int limitSup) {
		String sql = "SELECT * FROM academus.recorrecao_de_prova WHERE status = ? ORDER BY id_recorrecao_de_prova offset ? limit ?;";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, StatusRecorrecao.getCodigo(status));
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
				aux.setStatus(StatusRecorrecao.getStatus(rs.getInt("status")));
				
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
	public List<RecorrecaoDeProva> listarPorStatus(Aluno aluno, StatusRecorrecao status, int limitInf, int limitSup) {
		String sql = "select * from academus.recorrecao_de_prova WHERE id_aluno= ? AND status = ? order by id_recorrecao_de_prova offset ? limit ?;";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aluno.getId());
			ps.setInt(2, StatusRecorrecao.getCodigo(status));
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
				aux.setStatus(StatusRecorrecao.getStatus(rs.getInt("status")));
				
				listaRecorrecaoDeProva.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaRecorrecaoDeProva;
	}
	
	@Override
	public List<RecorrecaoDeProva> listarPorStatus(Professor professor, StatusRecorrecao status, int limitInf,
			int limitSup) {
		String sql = "select * from academus.recorrecao_de_prova WHERE id_professor= ? AND status = ? order by id_recorrecao_de_prova offset ? limit ?;";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
		Connection conn = ConnectionPool.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
			ps.setInt(2, StatusRecorrecao.getCodigo(status));
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
				aux.setStatus(StatusRecorrecao.getStatus(rs.getInt("status")));
				
				listaRecorrecaoDeProva.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaRecorrecaoDeProva;
	}
	
	@Override
	public List<RecorrecaoDeProva> listarPorStatusCoordenador(Professor coordenador, StatusRecorrecao status, int limitInf,
			int limitSup) {
		String sql = "SELECT * FROM academus.recorrecao_de_prova AS rp, academus.perfil_academus AS pa WHERE rp.id_aluno = pa.id_perfil_academus AND pa.id_curso = ? AND rp.status = ? ORDER BY rp.id_recorrecao_de_prova OFFSET ? LIMIT ?;";
		List<RecorrecaoDeProva> listaRecorrecaoDeProva = new ArrayList<RecorrecaoDeProva>();
		
		Connection conn = ConnectionPool.getConnection();
		System.out.println(coordenador.getId());
		System.out.println(StatusRecorrecao.getCodigo(status));
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, coordenador.getCurso().getIdCurso());
			ps.setInt(2, StatusRecorrecao.getCodigo(status));
			ps.setInt(3, limitInf);
			ps.setInt(4, limitSup);
			System.out.println(coordenador.getId());
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
				aux.setStatus(StatusRecorrecao.getStatus(rs.getInt("status")));
				
				listaRecorrecaoDeProva.add(aux);
				System.out.println(listaRecorrecaoDeProva.size());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaRecorrecaoDeProva;
	}

	@Override
	public RecorrecaoDeProva buscarPorId(int idRecorrecaoDeProva) {
		Connection conn = ConnectionPool.getConnection();
		String sql = "select * from academus.recorrecao_de_prova as rp where rp.id_recorrecao_de_prova = ?;";
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
				ArquivoDAO arqdao = df.criarArquivoDAO();
				
				
				aux.setIdRecorrecao(rs.getInt("id_recorrecao_de_prova"));
				aux.setAluno(aludao.buscarPorId(rs.getInt("id_aluno")));
				aux.setProfessor(profdao.buscarPorId(rs.getInt("id_professor")));
				aux.setDataProva(Date.valueOf(rs.getString("data_prova")));
				aux.setDataRecebimento(Date.valueOf(rs.getString("data_recebimento")));
				aux.setHorarioDaProva(Time.valueOf(rs.getString("hora_prova")));
				aux.setHorarioRecebimento(Time.valueOf(rs.getString("hora_recebimento")));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setDisciplina(discdao.buscarPorId(rs.getString("id_disciplina")));
				aux.setIdArquivo(arqdao.buscarPorId(rs.getInt("id_arquivo")).getIdArquivo());
				aux.setStatus(StatusRecorrecao.SOLICITADO);
			
				
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
		String sql = "WITH cte AS (select * from academus.recorrecao_de_prova) " + 
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
		String sql = "WITH cte AS (select * from academus.recorrecao_de_prova WHERE id_aluno= ?) " + 
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
		String sql = "WITH cte AS (select * from academus.recorrecao_de_prova WHERE id_professor= ?) " + 
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
	public int numSolicitacoesCoordenador(int pagina, Professor coordenador) {
		String sql = "WITH cte AS (select * from academus.recorrecao_de_prova as rp, academus.perfil_academus as p  WHERE rp.id_aluno = p.id_perfil_academus AND p.id_curso = ?) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, coordenador.getId());
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
	public int numSolicitacoesPorStatus(int pagina, StatusRecorrecao status) {
		String sql = "WITH cte AS (SELECT * FROM academus.recorrecao_de_prova WHERE status = ?) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, StatusRecorrecao.getCodigo(status));
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
	public int numSolicitacoesPorStatus(int pagina, Aluno aluno, StatusRecorrecao status) {
		String sql = "WITH cte AS (select * from academus.recorrecao_de_prova WHERE id_aluno= ? AND status = ?) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aluno.getId());
			ps.setInt(2, StatusRecorrecao.getCodigo(status));
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
	
	@Override
	public int numSolicitacoesPorStatus(int pagina, Professor professor, StatusRecorrecao status) {
		String sql = "WITH cte AS (select * from academus.recorrecao_de_prova WHERE id_professor= ? AND status = ?) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
			ps.setInt(2, StatusRecorrecao.getCodigo(status));
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
	
	@Override
	public int numSolicitacoesCoordenadorPorStatus(int pagina, Professor coordenador, StatusRecorrecao status) {
		String sql = "WITH cte AS (SELECT * FROM academus.recorrecao_de_prova AS rp, academus.perfil_academus AS pa WHERE rp.id_aluno = pa.id_perfil_academus AND pa.id_curso = ? AND rp.status = ?) " + 
				"SELECT count(*) FROM ( TABLE cte OFFSET ?) AS foo;";
		int resultSql = 0;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, coordenador.getId());
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

}
