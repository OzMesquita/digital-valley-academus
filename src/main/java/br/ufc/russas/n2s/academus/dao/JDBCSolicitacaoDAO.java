package br.ufc.russas.n2s.academus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.russas.n2s.academus.debug.IOConexao;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Coordenador;
import br.ufc.russas.n2s.academus.model.Historico;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import br.ufc.russas.n2s.academus.model.Status;

public class JDBCSolicitacaoDAO extends JDBCDAO implements SolicitacaoDAO{

	@Override
	public Solicitacao cadastrar(Solicitacao sol) {
		String sql = "insert into academus.solicitacao( id_solicitante, matricula_solicitante, status, id_componente, instituicao) VALUES ( ?, ?, ?, ?, ?)";
		
		super.open();
		try{
			
			PreparedStatement insert = this.getConnection().prepareStatement(sql);
			DisciplinaCursadaDAO dcd = new JDBCDisciplinaCursadaDAO();
			JDBCHistoricoDAO hd = new JDBCHistoricoDAO();
			
			insert.setInt(1, sol.getSolicitante().getId());
			insert.setString(2, sol.getSolicitante().getMatricula());
			insert.setInt(3, Status.getCodigo(sol.getStatus()));
			insert.setInt(4, sol.getDisciplinaAlvo().getIdComponente());
			insert.setString(5, sol.getInstituicao());
			insert.execute();
			
			int idSolicitacao = idUltimaSolicitacao(sol.getSolicitante().getMatricula());
			dcd.cadastrar(sol.getDisciplinasCursadas(), idSolicitacao);
			hd.cadastrar(new Historico(sol.getSolicitante(), 1), idSolicitacao);
			
			insert.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();
		}
		
		return sol;
	}
	
	@Override
	public int idUltimaSolicitacao(String matricula) {
		String sql =  "select id_solicitacao from academus.solicitacao where matricula_solicitante = '"+matricula+"' order by id_solicitacao desc LIMIT 1";
		int i = -1;
		
		super.open();
		try{
			PreparedStatement ps = this.getConnection().prepareStatement(sql);;
			ResultSet rs = ps.executeQuery();
 
			
			if(rs.next()){
				i =  rs.getInt("id_solicitacao");
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();
		}
		return i;
	}

	@Override
	public List<Solicitacao> listar() {
		String sql = "select * from academus.solicitacao";
		List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
		
		super.open();
		try{
			PreparedStatement ps = this.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			//DAOFactory df = new DAOFactoryJDBC();
			DAOFactoryJDBC df = new DAOFactoryJDBC();
			
			//DAO's necessárias
			AlunoDAO aludao = df.criarAlunoDAO();
			ComponenteCurricularDAO ccd = df.criarComponenteCurricularDAO(); //PROBLEMA
			DisciplinaCursadaDAO dcd = df.criarDisciplinaCursadaDAO();
			ArquivoDAO arqdao = df.criarArquivoDAO();
			HistoricoDAO hisdao = df.criarHistoricoDAO();
			
			while(rs.next()){
				
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
				//aux.setHistoricoOperacoes(hisdao.buscarPorSolicitacao(aux));
				
				
				listaSolicitacao.add(aux);
			}
			
			rs.close();
			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			super.close();
			IOConexao.info();
		}
		
		return listaSolicitacao;
	}

	@Override
	public Solicitacao buscarPorId(int id) {
		open();
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
		Solicitacao aux = new Solicitacao();
		
		try{
			PreparedStatement ps = this.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
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
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			super.close();
		}
		
		return aux;
	}

	@Override
	public Solicitacao editar(Solicitacao sol) {
		String sql = "UPDATE academus.solicitacao SET justificativa=?, id_componente=?, status=?, instituicao=?, resultado=? WHERE id_solicitacao = ?;";
		
		super.open();
		try{
			
			PreparedStatement update = getConnection().prepareStatement(sql);
			
			update.setString(1, sol.getJustificativa());
			update.setInt(2, sol.getDisciplinaAlvo().getIdComponente());
			update.setInt(3, Status.getCodigo(sol.getStatus()));
			update.setString(4, sol.getInstituicao());
			update.setString(5, sol.getResultado());
			update.setInt(6, sol.getIdSolicitacao());
			
			update.execute();
			update.close();
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			super.close();
		}
		
		return sol;
	}

	@Override
	public void excluir(Solicitacao sol) {
		String sql = "DELETE FROM academus.solicitaca WHERE id_solicitacao = "+sol.getIdSolicitacao()+";";
		
		super.open();
		try{
			PreparedStatement ps = this.getConnection().prepareStatement(sql);
			ps.execute();
			
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			super.close();
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