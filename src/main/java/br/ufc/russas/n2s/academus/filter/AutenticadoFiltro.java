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

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.PerfilAcademusDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
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
					
					if(perDAO.buscarPorCPF(user.getCpf()) == null) {
						if(user.getUsuario().getPerfil() == EnumPerfil.ALUNO) {
							Aluno userAluno = new Aluno();
							userAluno.setNome(user.getNome());
							userAluno.setCPF(user.getCpf());
							userAluno.setEmail(user.getEmail());
							userAluno.setMatricula(((model.Aluno)user).getMatricula());
							userAluno.setSemestreIngresso(((model.Aluno)user).getSemestreIngresso());							
							
							br.ufc.russas.n2s.academus.model.Curso curso = new br.ufc.russas.n2s.academus.model.Curso();
							curso.setIdCurso(((model.Aluno)user).getCurso().getId());
							curso.setNome(((model.Aluno)user).getCurso().getNome());
							userAluno.setCurso(curso);
							//Falta colocar no banco
							
							userAcademus = userAluno;
							
						} else if(user.getUsuario().getPerfil() == EnumPerfil.SERVIDOR) {
							if(((Servidor)user).getCargo() == EnumCargo.PROFESSOR) {
								Professor userProfessor = new Professor();
								userProfessor.setNome(user.getNome());
								userProfessor.setCPF(user.getCpf());
								userProfessor.setEmail(user.getEmail());
								userProfessor.setSiape(((model.Servidor)user).getSiape());
								userProfessor.setCurso(new br.ufc.russas.n2s.academus.model.Curso());
								userProfessor.setNivel(NivelAcademus.PROFESSOR);
								
								//Falta colocar no banco
								
								userAcademus = userProfessor;
								
							} else if(((Servidor)user).getCargo() == EnumCargo.SECRETARIO) {
								Funcionario userFuncionario = new Funcionario();
								userFuncionario.setNome(user.getNome());
								userFuncionario.setCPF(user.getCpf());
								userFuncionario.setEmail(user.getEmail());
								userFuncionario.setSiape(((model.Servidor)user).getSiape());
								userFuncionario.setCurso(new br.ufc.russas.n2s.academus.model.Curso());
								userFuncionario.setNivel(NivelAcademus.SECRETARIO);
								
								//Falta colocar no banco
								
								userAcademus = userFuncionario;
								
							} else {
								Funcionario userFuncionario = new Funcionario();
								userFuncionario.setNome(user.getNome());
								userFuncionario.setCPF(user.getCpf());
								userFuncionario.setEmail(user.getEmail());
								userFuncionario.setSiape(((model.Servidor)user).getSiape());
								userFuncionario.setNivel(NivelAcademus.INDEFINIDO);
								
								//Falta colocar no banco
								
								userAcademus = userFuncionario;
								
							}
						} else {
							PerfilAcademus userVisitante = new PerfilAcademus();
							userVisitante.setNome(user.getNome());
							userVisitante.setEmail(user.getEmail());
							userVisitante.setCPF(user.getCpf());
							userVisitante.setNivel(NivelAcademus.INDEFINIDO);
							
							userAcademus = userVisitante;
							
						}
					} else {
						userAcademus = perDAO.buscarPorCPF(user.getCpf());
					}
					
					session.setAttribute("userAcademus", userAcademus);
					
					// voltando pro Original
					chain.doFilter(request, response);
				}else {
					((HttpServletResponse) response).sendRedirect("/Controle_de_Acesso/");
				}
			}else if(session.getAttribute("usuario")!= null && DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Usuario)session.getAttribute("usuario")).getPessoa().getId())!=null && ((Usuario)session.getAttribute("usuario")).getTokenUsuario().equals(DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Usuario)session.getAttribute("usuario")).getPessoa().getId()))){
				chain.doFilter(request, response);
			}else {
				((HttpServletResponse) response).sendRedirect("/Controle_de_Acesso/");
			}
		}				
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}