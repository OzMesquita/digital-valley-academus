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

import br.ufc.russas.n2s.academus.dao.JDBCCoordenadorDAO;
import br.ufc.russas.n2s.academus.dao.JDBCPerfilAcademusDAO;
import br.ufc.russas.n2s.academus.dao.JDBCProfessorDAO;
import br.ufc.russas.n2s.academus.dao.PerfilAcademusDAO;
import br.ufc.russas.n2s.academus.model.NivelAcademus;
import br.ufc.russas.n2s.academus.model.PerfilAcademus;
import dao.DAOFactory;
import dao.PessoaDAO;
import model.EnumPerfil;
import model.Pessoa;
import util.Constantes;

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
			
			//FOI PASSADO UM TOKEN E UM ID PELA URL
			if(request.getParameter("token") != null && request.getParameter("id") != null){
				
				String token = request.getParameter("token");
				int id = Integer.parseInt(request.getParameter("id"));
				
				
				PerfilAcademus user = new JDBCPerfilAcademusDAO().buscarPorId(id);
				
				//CASO SEJA O PRIMEIRO ACESSO DO USUÁRIO AO ACADEMUS, CADASTRE ELE NA NOSSA BASE DE DADOS
				if(user.getNivel() == NivelAcademus.INDEFINIDO){
					//USUÁRIO QUE SERA CADASTRADO
					PerfilAcademus perfil = new PerfilAcademus();
					
					//DAO'S NECESSÁRIAS
					PerfilAcademusDAO daoAcademus = new JDBCPerfilAcademusDAO();
					PessoaDAO daoPessoa = DAOFactory.criarPessoaDAO();
					
					
					Pessoa pessoaCore = daoPessoa.buscarPorId(id);
					if(pessoaCore.getUsuario().getPerfil() == EnumPerfil.ALUNO)
					{
						perfil.setNivel(NivelAcademus.ALUNO);
					} else if(pessoaCore.getUsuario().getPerfil() == EnumPerfil.SERVIDOR){
						
						if(new JDBCCoordenadorDAO().isCoordenador(pessoaCore)){
							perfil.setNivel(NivelAcademus.COORDENADOR);
						} else if(new JDBCProfessorDAO().isProfessor(pessoaCore)){
							perfil.setNivel(NivelAcademus.PROFESSOR);
						} else{
							perfil.setNivel(NivelAcademus.SECRETARIO);
						}
					}
					
					perfil.setPessoa(pessoaCore);
					daoAcademus.cadastrar(perfil);
					
					user = perfil;
				}
				
				
				//VERIFICANDO SE O TOKEN PASSADO É IGUAL AO TOKEN DO USUÁRIO DO ACADEMUS
				if (!token.equals("null") &&
					token.equals(user.getPessoa().getUsuario().getTokenUsuario()) && 
					id == user.getPessoa().getId()) {
					
					session.setAttribute("usuario", user);
					chain.doFilter(request, response);
					
				}else {
					
					((HttpServletResponse) response).sendRedirect(Constantes.getGuardiaoApp());
					
				}
			}else if(session.getAttribute("usuario") != null && 
					 DAOFactory.criarUsuarioDAO().buscarTokenTemp(((PerfilAcademus)session.getAttribute("usuario")).getPessoa().getId()) != null &&
					 ((PerfilAcademus)session.getAttribute("usuario")).getPessoa().getUsuario().getTokenUsuario().equals(DAOFactory.criarUsuarioDAO().buscarTokenTemp(((PerfilAcademus)session.getAttribute("usuario")).getPessoa().getId()))){
				
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