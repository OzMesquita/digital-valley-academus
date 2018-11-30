package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.connection.ConnectionPool;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Historico;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;

public class JDBCSolicitacaoDAO implements SolicitacaoDAO{

	@Override
	public Solicitacao cadastrar(Solicitacao sol) {
		String sql = "insert into academus.solicitacao( id_solicitante, matricula_solicitante, status, id_componente, id_curso, justificativa, resultado) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			
			PreparedStatement insert = conn.prepareStatement(sql);
			DisciplinaCursadaDAO dcd = new JDBCDisciplinaCursadaDAO();
			JDBCHistoricoDAO hd = new JDBCHistoricoDAO();
			
			insert.setInt(1, sol.getSolicitante().getId());
			insert.setString(2, sol.getSolicitante().getMatricula());
			insert.setInt(3, Status.getCodigo(sol.getStatus()));
			insert.setInt(4, sol.getDisciplinaAlvo().getIdComponente());
			insert.setInt(5, sol.getCurso().getIdCurso());
			insert.setString(6, sol.getJustificativa());
			insert.setString(7, sol.getResultado());
			insert.execute();
			
			int idSolicitacao = idUltimaSolicitacao(sol.getSolicitante().getMatricula());
			dcd.cadastrar(sol.getDisciplinasCursadas(), idSolicitacao);
			hd.cadastrar(new Historico(sol.getSolicitante(), 1), idSolicitacao);
			
			insert.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return sol;
	}
	
	@Override
	public int idUltimaSolicitacao(String matricula) {
		String sql =  "select id_solicitacao from academus.solicitacao where matricula_solicitante = '"+matricula+"' order by id_solicitacao desc LIMIT 1";
		int i = -1;
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);;
			ResultSet rs = ps.executeQuery();
 
			
			if(rs.next()){
				i =  rs.getInt("id_solicitacao");
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		return i;
	}

	@Override
	public List<Solicitacao> listar() {
		String sql = "select * from academus.solicitacao";
		List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				listaSolicitacao.add(aux);
			}
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaSolicitacao;
	}

	@Override
	public Solicitacao buscarPorId(int id) {
		Connection conn = ConnectionPool.getConnection();
		String sql = "select * from academus.solicitacao where academus.solicitacao.id_solicitacao = "+ id + ".";
		Solicitacao aux = new Solicitacao();
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				DAOFactory df = new DAOFactoryJDBC();
				
				//DAO's necessárias
				AlunoDAO aludao = df.criarAlunoDAO();
				ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO();
				DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
				HistoricoDAO hisdao = df.criarHistoricoDAO();
				CursoDAO curdao = df.criarCursoDAO();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
			}
						
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return aux;
	}

	@Override
	public Solicitacao editar(Solicitacao sol) {
		String sql = "UPDATE academus.solicitacao SET justificativa=?, id_componente=?, status=?, resultado=? WHERE id_solicitacao = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			
			PreparedStatement update = conn.prepareStatement(sql);
			
			update.setString(1, sol.getJustificativa());
			update.setInt(2, sol.getDisciplinaAlvo().getIdComponente());
			update.setInt(3, Status.getCodigo(sol.getStatus()));
			update.setString(4, sol.getResultado());
			update.setInt(5, sol.getIdSolicitacao());
			
			update.executeUpdate();
			update.close();
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			ConnectionPool.releaseConnection(conn);
		}
		
		return sol;
	}

	@Override
	public void excluir(Solicitacao sol) {
		String sql = "DELETE FROM academus.solicitaca WHERE id_solicitacao = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sol.getIdSolicitacao());
			
			ps.execute();
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
	}

	@Override
	public List<Solicitacao> listar(Coordenador c) {
		List<Solicitacao> solicitacoes = new ArrayList<>();
		String sql = "SELECT * FROM academus.solicitacao WHERE status != ? AND id_curso = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Status.getCodigo(Status.SUBMETIDO));
			ps.setInt(2, c.getCurso().getIdCurso());
			
			ResultSet rs = ps.executeQuery();
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				solicitacoes.add(aux);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return solicitacoes;
	}

	@Override
	public List<Solicitacao> listar(Aluno a) {
		List<Solicitacao> solicitacoes = new ArrayList<>();
		String sql = "SELECT * FROM academus.solicitacao WHERE id_solicitante = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getId());
			
			ResultSet rs = ps.executeQuery();
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				solicitacoes.add(aux);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return solicitacoes;
	}

	@Override
	public List<Solicitacao> listarAnalizado(Coordenador c){
		List<Solicitacao> solicitacoes = new ArrayList<>();
		String sql = "SELECT * FROM academus.solicitacao WHERE status = ? AND id_curso = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Status.getCodigo(Status.ANALIZANDO));
			ps.setInt(2, c.getCurso().getIdCurso());
			
			ResultSet rs = ps.executeQuery();
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				solicitacoes.add(aux);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return solicitacoes;
	}
	
	@Override
	public List<Solicitacao> listarAndamento(Aluno a) {
		List<Solicitacao> solicitacoes = new ArrayList<>();
		String sql = "SELECT * FROM academus.solicitacao WHERE id_solicitante = ? AND status != ? AND status != ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getId());
			ps.setInt(2, Status.getCodigo(Status.FINALIZADO));
			ps.setInt(3, Status.getCodigo(Status.CANCELADO));
			ResultSet rs = ps.executeQuery();
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				solicitacoes.add(aux);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return solicitacoes;
	}

	@Override
	public List<Solicitacao> listarAndemanto(Coordenador c) {
		List<Solicitacao> solicitacoes = new ArrayList<>();
		String sql = "SELECT * FROM academus.solicitacao WHERE id_curso = ? AND status != ? AND status != ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getCurso().getIdCurso());
			ps.setInt(2, Status.getCodigo(Status.FINALIZADO));
			ps.setInt(3, Status.getCodigo(Status.CANCELADO));
			ResultSet rs = ps.executeQuery();
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				solicitacoes.add(aux);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return solicitacoes;
	}

	@Override
	public List<Solicitacao> listarFinalizado(Aluno a) {
		List<Solicitacao> solicitacoes = new ArrayList<>();
		String sql = "SELECT * FROM academus.solicitacao WHERE id_solicitante = ? AND status = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getId());
			ps.setInt(2, Status.getCodigo(Status.FINALIZADO));
			ResultSet rs = ps.executeQuery();
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				solicitacoes.add(aux);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return solicitacoes;
	}

	@Override
	public List<Solicitacao> listarFinalizado(Coordenador c) {
		List<Solicitacao> solicitacoes = new ArrayList<>();
		String sql = "SELECT * FROM academus.solicitacao WHERE id_curso = ? AND status = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getCurso().getIdCurso());
			ps.setInt(2, Status.getCodigo(Status.FINALIZADO));
			ResultSet rs = ps.executeQuery();
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				solicitacoes.add(aux);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return solicitacoes;
	}

	@Override
	public List<Solicitacao> listarSubmetida(Aluno a){
		List<Solicitacao> solicitacoes = new ArrayList<>();
		String sql = "SELECT * FROM academus.solicitacao WHERE id_solicitante = ? AND status = ?;";
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getId());
			ps.setInt(2, Status.getCodigo(Status.SUBMETIDO));
			ResultSet rs = ps.executeQuery();
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				solicitacoes.add(aux);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return solicitacoes;
	}
	
	@Override
	public List<Solicitacao> listarAnalizado(){
		String sql = "select * from academus.solicitacao where status=?";
		List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Status.getCodigo(Status.ANALIZANDO));
			ResultSet rs = ps.executeQuery();
			
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				listaSolicitacao.add(aux);
			}
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaSolicitacao;
	}
	
	@Override
	public List<Solicitacao> listarAndamento() {
		String sql = "select * from academus.solicitacao where status!=? and status!=?";
		List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Status.getCodigo(Status.FINALIZADO));
			ps.setInt(2, Status.getCodigo(Status.CANCELADO));
			ResultSet rs = ps.executeQuery();
			
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				listaSolicitacao.add(aux);
			}
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaSolicitacao;
	}

	@Override
	public List<Solicitacao> listarFinalizado() {
		String sql = "select * from academus.solicitacao where status=?";
		List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Status.getCodigo(Status.FINALIZADO));
			ResultSet rs = ps.executeQuery();
			
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				listaSolicitacao.add(aux);
			}
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaSolicitacao;
	}

	@Override
	public List<Solicitacao> listarSubmetida(){
		String sql = "select * from academus.solicitacao where status=?";
		List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Status.getCodigo(Status.SUBMETIDO));
			ResultSet rs = ps.executeQuery();
			
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				listaSolicitacao.add(aux);
			}
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaSolicitacao;
	}
	
	@Override
	public List<Solicitacao> listarValidada(){
		String sql = "select * from academus.solicitacao where status=?";
		List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		
		Connection conn = ConnectionPool.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Status.getCodigo(Status.VALIDANDO));
			ResultSet rs = ps.executeQuery();
			
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			CursoDAO curdao = df.criarCursoDAO();
			
			while(rs.next()){
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				aux.setCurso(curdao.buscarPorId(rs.getInt("id_curso")));
				
				listaSolicitacao.add(aux);
			}
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionPool.releaseConnection(conn);
		}
		
		return listaSolicitacao;
	}
	
}