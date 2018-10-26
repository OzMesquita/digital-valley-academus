package br.ufc.russas.n2s.academus.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import br.ufc.russas.n2s.academus.connection.Conexao;
import br.ufc.russas.n2s.academus.model.Historico;
import br.ufc.russas.n2s.academus.model.Solicitacao;

public class JDBCHistoricoDAO extends JDBCDAO implements HistoricoDAO{
	
	@Override
	public Historico cadastrar(Historico his, int idSolicitacao) throws Exception {
		open();
		PreparedStatement ps = null;
		String sql = "insert into academus.historico (data_resultado, horario, descricao, id_pessoa_usuario, id_solicitacao) "
				+ "values (?,?,?,?,?)";
		try {
			System.out.println("Entrou");
			ps = getConnection().prepareStatement(sql);
			ps.setDate(1, Date.valueOf(his.getData()));
			ps.setTime(2, Time.valueOf(his.getHorario()));
			System.out.println("Deu certo");
			ps.setString(3, his.getDescricao());
			ps.setInt(4, his.getResponsavel().getPessoa().getId());
			ps.setInt(5, idSolicitacao);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ps.close();
			close();
		}
		return his;
	}

	@Override
	public java.util.List<Historico> buscarPorSolicitacao(Solicitacao sol) {
		open();
		PreparedStatement ps = null;
		String sql =  "select * from academus.historico where id_solicitacao = "+sol+";";
		ArrayList<Historico> lh = new ArrayList<Historico>();
		PerfilAcademusDAO pad = new JDBCPerfilAcademusDAO();
		try{
			ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Historico h = new Historico();
				h.setData(rs.getDate("data").toLocalDate());
				h.setHorario(rs.getTime("horario").toLocalTime());
				h.setResponsavel(pad.buscarPorId(rs.getInt("id_pessoa_usuario")));
				h.setDescricao(rs.getString("descricao"));
				lh.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return lh;
	}
	
	//select id_solicitacao from academus.solicitacao order by id_solicitacao desc LIMIT 1
	@Override
	public Historico editar(Historico his) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Historico his) {
		// TODO Auto-generated method stub
		
	}


}
