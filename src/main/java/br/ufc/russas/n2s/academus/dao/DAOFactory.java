package br.ufc.russas.n2s.academus.dao;

public interface DAOFactory {
	
	public AlunoDAO criarAlunoDAO();
	
	public ArquivoDAO criarArquivoDAO();
	
	public ComponenteCurricularDAO criarComponenteCurricularDAO();
	
	public CoordenadorDAO criarCoordenadorDAO();
	
	public CursoDAO criarCursoDAO();
	
	public DisciplinaCursadaDAO criarDisciplinaCursadaDAO();
	
	public DisciplinaDAO criarDisciplinaDAO();
	
	public FuncionarioDAO criarFuncionarioDAO();
	
	public HistoricoDAO criarHistoricoDAO();
	
	public MatrizCurricularDAO criarMatrizCurricularDAO();
	
	public NivelAcademusDAO criarNivelAcademusDAO();
	
	public PerfilAcademusDAO criarPerfilAcademusDAO();
	
	public ProfessorDAO criarProfessorDAO();
	
	public SolicitacaoDAO criarSolicitacaoDAO();
	
}
