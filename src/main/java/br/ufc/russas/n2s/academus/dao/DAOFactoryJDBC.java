package br.ufc.russas.n2s.academus.dao;

public class DAOFactoryJDBC implements DAOFactory{

	@Override
	public AlunoDAO criarAlunoDAO() {
		return new JDBCAlunoDAO();
	}

	@Override
	public ArquivoDAO criarArquivoDAO() {
		return new JDBCArquivoDAO();
	}

	@Override
	public ComponenteCurricularDAO criarComponenteCurricularDAO() {
		return new JDBCComponenteCurricularDAO();
	}

	@Override
	public CursoDAO criarCursoDAO() {
		return new JDBCCursoDAO();
	}

	@Override
	public DisciplinaCursadaDAO criarDisciplinaCursadaDAO() {
		return new JDBCDisciplinaCursadaDAO();
	}

	@Override
	public DisciplinaDAO criarDisciplinaDAO() {
		return new JDBCDisciplinaDAO();
	}
	
	@Override
	public FuncionarioDAO criarFuncionarioDAO() {
		return new JDBCFuncionarioDAO();
	}

	@Override
	public HistoricoDAO criarHistoricoDAO() {
		return new JDBCHistoricoDAO();
	}

	@Override
	public MatrizCurricularDAO criarMatrizCurricularDAO() {
		return new JDBCMatrizCurricularDAO();
	}

	@Override
	public NivelAcademusDAO criarNivelAcademusDAO() {
		return new JDBCNivelAcademusDAO();
	}

	@Override
	public PerfilAcademusDAO criarPerfilAcademusDAO() {
		return new JDBCPerfilAcademusDAO();
	}

	@Override
	public ProfessorDAO criarProfessorDAO() {
		return new JDBCProfessorDAO();
	}

	@Override
	public SolicitacaoDAO criarSolicitacaoDAO() {
		return new JDBCSolicitacaoDAO();
	}
	
	@Override
	public SegundaChamadaDAO criarSegundaChamadaDAO() {
		return new JDBCSegundaChamada();
	}

	@Override
	public RecorrecaoDeProvaDAO criarRecorrecaoDeProvaDAO() {
		return new JDBCRecorrecaoDeProvaDAO();
	}

}
