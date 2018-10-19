package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.ComponenteCurricular;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;
import br.ufc.russas.n2s.academus.connection.Conexao;

public class JDBCSolicitacaoDAO implements SolicitacaoDAO{
	
	private Connection connection;

	public JDBCSolicitacaoDAO() {
		connection = Conexao.getConexao();
	}
	
	public ArrayList<Solicitacao> listarSolicitacoes(){
		String sql = "select * from academus.solicitacao";
		ArrayList<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				//DAO's necess�rios
				DAOFactory df = new DAOFactoryJDBC();
				JDBCComponenteCurricularDAO ccd = new JDBCComponenteCurricularDAO();
				JDBCDisciplinaCursadaDAO dcd = new JDBCDisciplinaCursadaDAO();
				
				//Solicita��es pegas do banco
				Solicitacao aux = new Solicitacao();
				
				//Informa��es da solicita��o
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(df.criarAlunoDAO().buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				
				
				listaSolicitacao.add(aux);
			}
			rs.close();
			ps.close();
			return listaSolicitacao;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public Solicitacao inserirSolicitacao(Solicitacao s){
		try{
			
			String sql = "insert into academus.solicitacao( id_solicitante, resultado, justificativa, matricula_solicitante, status, id_componente, instituicao) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			DisciplinaCursadaDAO ddc = new JDBCDisciplinaCursadaDAO();
			insert.setInt(1, s.getSolicitante().getId());
			insert.setString(2, s.getResultado());
			insert.setString(3, s.getJustificativa());
			insert.setString(4, s.getSolicitante().getMatricula());
			insert.setInt(5, Status.getCodigo(s.getStatus()));
			insert.setInt(6, s.getDisciplinaAlvo().getIdComponente());
			insert.setString(7, s.getInstituicao());
			insert.execute();
			insert.close();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return s;
	}
	
	public Solicitacao buscarSolicitacaoId(int idSolicitacao){
		
		String sql = "select "
				+ "id_solicitacao, "
				+ "id_solicitante, "
				+ "justificativa, "
				+ "matricula_solicitante, "
				+ "id_componente, "
				+ "status, "
				+ "instituicao, "
				+ "resultado "
				+ "from academus.solicitacao "
				+ "where academus.solicitacao.id_solicitacao = "+ idSolicitacao;
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
						
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			//DAO's necess�rios
			DAOFactory df = new DAOFactoryJDBC();
			JDBCComponenteCurricularDAO ccd = new JDBCComponenteCurricularDAO();
			JDBCDisciplinaCursadaDAO dcd = new JDBCDisciplinaCursadaDAO();
			
			
			Solicitacao sol = new Solicitacao();
			
			sol.setIdSolicitacao(rs.getInt("id_solicitacao"));
			sol.setSolicitante(df.criarAlunoDAO().buscarPorId(rs.getInt("id_solicitante")));
			sol.setResultado(rs.getString("resultado"));
			sol.setJustificativa(rs.getString("justificativa"));
			sol.setStatus(Status.getStatus(rs.getInt("status")));
			sol.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
			sol.setInstituicao(rs.getString("instituicao"));
			sol.setDisciplinasCursadas(dcd.buscar(sol));
						
			ps.close();
			rs.close();
			
			return sol;
		}catch(SQLException e){
			e.getMessage();
			return null;
		}
	}
	
	public void atualizarSolicitacao(Solicitacao sol){
		
		try{
			String sql = "UPDATE academus.solicitacao SET id_solicitante=?, resultado=?, justificativa=?, matricula_solicitante=?, status=?, id_componente=? WHERE id_solicitacao = ?";
			PreparedStatement update = connection.prepareStatement(sql);
			
			update.setInt(1, sol.getSolicitante().getId());
			update.setString(2, sol.getResultado());
			update.setString(3, sol.getJustificativa());
			update.setString(4, sol.getSolicitante().getMatricula());
			update.setInt(5, Status.getCodigo(sol.getStatus()));
			update.setInt(6, sol.getDisciplinaAlvo().getIdComponente());
			update.setInt(7, sol.getIdSolicitacao());
			
			
			//Verificando a string gerada
			//System.out.println(update.toString());
			
			update.execute();
			update.close();
			
		} catch(SQLException e){
			e.getMessage();
		}
	}
	
	public boolean deletarSolicitacao(Solicitacao sol){
		String sql = "DELETE FROM academus.solicitaca WHERE id_solicitacao = "+sol.getIdSolicitacao()+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			return ps.execute();
		}catch(SQLException e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public Solicitacao cadastrar(Solicitacao sol) {
		try{
			
			String sql = "insert into academus.solicitacao( id_solicitante, matricula_solicitante, status, id_componente, instituicao) VALUES ( ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			DisciplinaCursadaDAO ddc = new JDBCDisciplinaCursadaDAO();
			
			insert.setInt(1, sol.getSolicitante().getId());
			insert.setString(2, sol.getSolicitante().getMatricula());
			insert.setInt(3, Status.getCodigo(sol.getStatus()));
			insert.setInt(4, sol.getDisciplinaAlvo().getIdComponente());
			insert.setString(5, sol.getInstituicao());
			
			insert.execute();
			
			ddc.cadastrar(sol.getDisciplinasCursadas(), ultimaSolicitacao());
			System.out.println("DEU CERTO");
			insert.close();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return sol;
	}
	
	public int ultimaSolicitacao() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql =  "select id_solicitacao from academus.solicitacao order by id_solicitacao desc LIMIT 1";
		try{
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			int i =  rs.getInt("id_solicitacao");
			rs.close();
			ps.close();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
		}
		return -1;
	}

	@Override
	public List<Solicitacao> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solicitacao buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solicitacao editar(Solicitacao sol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Solicitacao sol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Solicitacao> listar(Coordenador c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Solicitacao> listar(Aluno a) {
		// TODO Auto-generated method stub
		return null;
	}
	
}