package br.ufc.russas.n2s.academus.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.FuncionarioDAO;
import br.ufc.russas.n2s.academus.dao.PerfilAcademusDAO;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.model.Funcionario;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import br.ufc.russas.n2s.academus.model.Professor;
import model.EnumCargo;
import model.EnumPerfil;
import model.Pessoa;
import model.Servidor;
import model.Usuario;
import dao.DAOFactory;
import util.Constantes;
import util.Facade;

public class AutenticadoFiltro implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();
		if (path.endsWith("/autentica")){
			chain.doFilter(request, response);
		}else{
			HttpSession session = ((HttpServletRequest) request).getSession();
			if(request.getParameter("token") != null && request.getParameter("id") != null){
				String token = request.getParameter("token");
				int id = Integer.parseInt(request.getParameter("id"));
				Pessoa user = Facade.buscarPessoaPorId(id);
				if (token.equals(user.getUsuario().getTokenUsuario()) && id == user.getId() && !token.equals("null")) {
					session.setAttribute("usuario", user.getUsuario());
					
					//academus
					
					PerfilAcademusDAO perDAO = new DAOFactoryJDBC().criarPerfilAcademusDAO();
					PerfilAcademus userAcademus = new PerfilAcademus();
					userAcademus.setIsAdmin(false);
					
					if(perDAO.buscarPorCPF(user.getCpf()).getCPF().equals("")) {
						if(user.getUsuario().getPerfil() == EnumPerfil.ALUNO) {
							br.ufc.russas.n2s.academus.model.Aluno userAluno = new br.ufc.russas.n2s.academus.model.Aluno();
							
							dao.AlunoDAO test = DAOFactory.criarAlunoDAO();
							model.Aluno alu = test.buscar(user.getId());
							
							userAluno.setIdGuardiao(user.getId());
							userAluno.setNome(user.getNome());
							userAluno.setCPF(user.getCpf());
							userAluno.setEmail(user.getEmail());
							userAluno.setIsAdmin(false);
							userAluno.setMatricula(alu.getMatricula());
							userAluno.setSemestreIngresso(alu.getSemestreIngresso());
							userAluno.setNivel(NivelAcademus.ALUNO);
							
							br.ufc.russas.n2s.academus.model.Curso curso = new br.ufc.russas.n2s.academus.model.Curso();
							curso.setIdCurso(alu.getCurso().getId());
							curso.setNome(alu.getCurso().getNome());
							userAluno.setCurso(curso);
							//banco
							AlunoDAO aludao = new DAOFactoryJDBC().criarAlunoDAO();
							aludao.cadastrar(userAluno);
							
							userAcademus = userAluno;
							
							
						} else if(user.getUsuario().getPerfil() == EnumPerfil.SERVIDOR) {
							
							dao.ServidorDAO serdao = DAOFactory.criarServidorDAO();
							model.Servidor serv = serdao.buscar(user.getId());
							
							if(serv.getCargo() == EnumCargo.PROFESSOR) {
								
								Professor userProfessor = new Professor();
								userProfessor.setIdGuardiao(user.getId());
								userProfessor.setNome(user.getNome());
								userProfessor.setCPF(user.getCpf());
								userProfessor.setIsAdmin(false);
								userProfessor.setEmail(user.getEmail());
								
								userProfessor.setSiape(serv.getSiape());
								userProfessor.setCurso(new br.ufc.russas.n2s.academus.model.Curso());
								userProfessor.setNivel(NivelAcademus.PROFESSOR);
								
								//banco
								ProfessorDAO prodao = new DAOFactoryJDBC().criarProfessorDAO();
								prodao.cadastrar(userProfessor);

								userAcademus = userProfessor;
								
							} else {
								
								Funcionario userFuncionario = new Funcionario();
								userFuncionario.setIdGuardiao(user.getId());
								userFuncionario.setNome(user.getNome());
								userFuncionario.setCPF(user.getCpf());
								userFuncionario.setEmail(user.getEmail());
								userFuncionario.setIsAdmin(false);
								userFuncionario.setSiape(serv.getSiape());
								userFuncionario.setCurso(new br.ufc.russas.n2s.academus.model.Curso());
								userFuncionario.setNivel(NivelAcademus.INDEFINIDO);
								
								//banco
								FuncionarioDAO fundao = new DAOFactoryJDBC().criarFuncionarioDAO();
								fundao.cadastrar(userFuncionario);
								
								userAcademus = userFuncionario;
								
							}
						} else {
							PerfilAcademus userVisitante = new PerfilAcademus();
							userVisitante.setIdGuardiao(user.getId());
							userVisitante.setNome(user.getNome());
							userVisitante.setEmail(user.getEmail());
							userVisitante.setCPF(user.getCpf());
							userVisitante.setIsAdmin(false);
							userVisitante.setNivel(NivelAcademus.INDEFINIDO);
							userVisitante.setCurso(new br.ufc.russas.n2s.academus.model.Curso());
							
							PerfilAcademusDAO perdao = new DAOFactoryJDBC().criarPerfilAcademusDAO();
							perdao.cadastrar(userVisitante);
							userAcademus = userVisitante;
							
						}
					} else {
						userAcademus = br.ufc.russas.n2s.academus.util.Facade.buscarPerfilPorCPF(user.getCpf());
					}
					
					session.setAttribute("userAcademus", userAcademus);
					
					// voltando pro Original
					chain.doFilter(request, response);
				}else {
					((HttpServletResponse) response).sendRedirect(Constantes.getGuardiaoApp());
				}
			}else if(session.getAttribute("usuario")!= null && DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Usuario)session.getAttribute("usuario")).getPessoa().getId())!=null && ((Usuario)session.getAttribute("usuario")).getTokenUsuario().equals(DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Usuario)session.getAttribute("usuario")).getPessoa().getId()))){
				chain.doFilter(request, response);
			}else {
				((HttpServletResponse) response).sendRedirect(Constantes.getGuardiaoApp());
			}
		}				
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}