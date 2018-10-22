package br.ufc.russas.n2s.academus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;
import br.ufc.russas.n2s.academus.connection.Conexao;

public class JDBCSolicitacaoDAO implements SolicitacaoDAO{
	
	private Connection connection;

	public JDBCSolicitacaoDAO() {
		connection = Conexao.getConexao();
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
			
			ddc.cadastrar(sol.getDisciplinasCursadas(), idUltimaSolicitacao());
			System.out.println("DEU CERTO");
			insert.close();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return sol;
	}
	
	@Override
	public int idUltimaSolicitacao() {
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
		String sql = "select * from academus.solicitacao";
		List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				DAOFactory df = new DAOFactoryJDBC();
				
				//DAO's necessárias
				AlunoDAO aludao = df.criarAlunoDAO();
				ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO();
				DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
				ArquivoDAO arqdao = df.criarArquivoDAO();
				HistoricoDAO hisdao = df.criarHistoricoDAO();
				
				Solicitacao aux = new Solicitacao();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setInstituicao(rs.getString("instituicao"));
				aux.setArquivo(arqdao.buscarPorSolicitacao(aux));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				
				
				listaSolicitacao.add(aux);
			}
			rs.close();
			ps.close();
			return listaSolicitacao;
		}catch(SQLException e){
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public Solicitacao buscarPorId(int id) {
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
				+ "where academus.solicitacao.id_solicitacao = "+ id;
		
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
						
			ResultSet rs = ps.executeQuery();
			
			Solicitacao aux = null;
			if(rs.next()){
				aux = new Solicitacao();
				DAOFactory df = new DAOFactoryJDBC();
				
				//DAO's necessárias
				AlunoDAO aludao = df.criarAlunoDAO();
				ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO();
				DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
				ArquivoDAO arqdao = df.criarArquivoDAO();
				HistoricoDAO hisdao = df.criarHistoricoDAO();
				
				aux.setIdSolicitacao(rs.getInt("id_solicitacao"));
				aux.setStatus(Status.getStatus(rs.getInt("status")));
				aux.setSolicitante(aludao.buscarPorId(rs.getInt("id_solicitante")));
				aux.setDisciplinaAlvo(ccd.buscarPorId(rs.getInt("id_componente")));
				aux.setDisciplinasCursadas(dcd.buscar(aux));
				aux.setJustificativa(rs.getString("justificativa"));
				aux.setResultado(rs.getString("resultado"));
				aux.setInstituicao(rs.getString("instituicao"));
				aux.setArquivo(arqdao.buscarPorSolicitacao(aux));
				aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				
			}
						
			ps.close();
			rs.close();
			
			return aux;
		}catch(SQLException e){
			e.getMessage();
		}
		return null;
	}

	@Override
	public Solicitacao editar(Solicitacao sol) {
		String sql = "UPDATE academus.solicitacao SET justificativa=?, id_componente=?, status=?, instituicao=?, resultado=? WHERE id_solicitacao = ?;";
		try{
			
			PreparedStatement update = connection.prepareStatement(sql);
			
			update.setString(1, sol.getJustificativa());
			update.setInt(2, sol.getDisciplinaAlvo().getIdComponente());
			update.setInt(3, Status.getCodigo(sol.getStatus()));
			update.setString(4, sol.getInstituicao());
			update.setString(5, sol.getResultado());
			update.setInt(6, sol.getIdSolicitacao());
			
			update.execute();
			update.close();
			return sol;
		} catch(SQLException e){
			e.getMessage();
		}
		return null;
	}

	@Override
	public void excluir(Solicitacao sol) {
		String sql = "DELETE FROM academus.solicitaca WHERE id_solicitacao = "+sol.getIdSolicitacao()+";";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.execute();
		}catch(SQLException e){
			e.getMessage();
		}
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