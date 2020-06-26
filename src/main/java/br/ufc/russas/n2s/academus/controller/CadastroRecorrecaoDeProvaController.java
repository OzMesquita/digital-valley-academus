package br.ufc.russas.n2s.academus.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Enumeration;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import br.ufc.russas.n2s.academus.dao.AlunoDAO;
import br.ufc.russas.n2s.academus.dao.ArquivoDAO;
import br.ufc.russas.n2s.academus.dao.DisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCAlunoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCArquivoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCProfessorDAO;
import br.ufc.russas.n2s.academus.dao.JDBCRecorrecaoDeProvaDAO;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.dao.RecorrecaoDeProvaDAO;
import br.ufc.russas.n2s.academus.model.Aluno;
import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.Disciplina;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.RecorrecaoDeProva;
import br.ufc.russas.n2s.academus.model.StatusRecorrecao;
import br.ufc.russas.n2s.academus.model.TipoArquivo;
import util.Constantes;



@MultipartConfig
public class CadastroRecorrecaoDeProvaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public CadastroRecorrecaoDeProvaController() {
		super();
	}
	
	RecorrecaoDeProvaDAO rpdao = new JDBCRecorrecaoDeProvaDAO();
	AlunoDAO alunodao = new JDBCAlunoDAO();
	ProfessorDAO professordao = new JDBCProfessorDAO();
	DisciplinaDAO discdao = new JDBCDisciplinaDAO();
	ArquivoDAO arqdao = new JDBCArquivoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("professor") != null &&
		   request.getParameter("matricula") != null &&
		   request.getParameter("disciplina") != null &&
		   request.getParameter("dataDaProva") != null &&
		   request.getParameter("justificativa") != null &&
		   request.getParameter("horarioDaProva") != null &&
		   request.getParameter("dataRecebimento") != null &&
		   request.getParameter("horarioRecebimento") != null ) {
			
			int idProfessor = Integer.parseInt(request.getParameter("professor"));
			String matricula = request.getParameter("matricula");
			String idDisciplina = request.getParameter("disciplina");
			String dataDaProva = request.getParameter("dataDaProva");
			String horarioDaProva = request.getParameter("horarioDaProva");
			String justificativa = request.getParameter("justificativa");
			String dataRecebimento = request.getParameter("dataRecebimento");
			String horarioRecebimento = request.getParameter("horarioRecebimento");
			
			RecorrecaoDeProva recorrecao =	new RecorrecaoDeProva();
			
			Aluno aluno = alunodao.buscarPorMatricula(matricula);
			Professor professor = professordao.buscarPorId(idProfessor);
			Disciplina disc = discdao.buscarPorId(idDisciplina);
			
			recorrecao.setAluno(aluno);
			recorrecao.setDisciplina(disc);
			recorrecao.setProfessor(professor);
			recorrecao.setDataProva(Date.valueOf(dataDaProva));
			recorrecao.setHorarioDaProva(Time.valueOf(horarioDaProva+":00"));
			recorrecao.setJustificativa(justificativa);
			recorrecao.setDataRecebimento(Date.valueOf(dataRecebimento));
			recorrecao.setHorarioRecebimento(Time.valueOf(horarioRecebimento+":00"));
			recorrecao.setStatus(StatusRecorrecao.SOLICITADO);
			
			//Manipula o anexo
			/*if(request.getPart("anexo") != null) {
				System.out.println("entrou");
				String caminhoRelativo = File.separator+matricula+File.separator+dataDaProva+File.separator+idDisciplina;
				String caminho = Constantes.getAnexoDir()+caminhoRelativo;
				
				File dir = new File(caminho+File.separator);
				
				if(!dir.isDirectory()) dir.mkdirs();
				
				if(!Files.isWritable(dir.toPath())) System.out.println("Não é possivel escrever um arquivo");
				
				Part part = request.getPart("anexo");
				String nome = "recorrecao-"+matricula+"-"+dataDaProva+"-"+idDisciplina+".pdf";
				System.out.println(nome);
				
				OutputStream out = null;
			    InputStream filecontent = null;
			    final PrintWriter writer = response.getWriter();
			    
			    Arquivo arq = new Arquivo();
			
			    try {
			        File file = new File(dir.getAbsolutePath() + File.separator + nome);
			    	out = new FileOutputStream(file);
			        filecontent = part.getInputStream();
			
			        int read = 0;
			        final byte[] bytes = new byte[1024];
			
			        while ((read = filecontent.read(bytes)) != -1) {
			            out.write(bytes, 0, read);
			        }
			        //LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", new Object[]{nome, caminho});
					
			        arq.setCaminho(caminhoRelativo + File.separator + nome);
			        arq.setTipo(TipoArquivo.getTipoArquivo(3));
			        arq.setNome(nome);
			        arq = arqdao.cadastrarArquivo(arq);
			        System.out.println("Cadastrou o arquivo");
					
			    } catch (FileNotFoundException fne) {
			        fne.getMessage();
			        System.out.println("Errooou");
			        System.out.println(fne.getMessage());
			       
			     
			
			        //LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", new Object[]{fne.getMessage()});
			    } finally {
			    	
			        if (out != null) {
			            out.close();
			            System.out.println("close 1");
			        }
			        if (filecontent != null) {
			            filecontent.close();
			            System.out.println("close 2");
			        }
			        if (writer != null) {
			            writer.close();
			            System.out.println("close 3");
			        }
			    }
			    System.out.println("Setar arquivo");
			    recorrecao.setArquivo(arq);
			}*/
		    //Fim manipulação do anexo	    
		    
			try {
				rpdao.cadastro(recorrecao);
				
				request.setAttribute("success", "Solicitação de correção de prova cadastrada com sucesso.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroRecorrecaoDeProva.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "O servidor não conseguiu cadastrar a solicitação.");
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroRecorrecaoDeProva.jsp");
				dispatcher.forward(request, response);
			}
			
		} else {
		
			try {
				//if((request.getParameter("componenteInput") != null))
				//request.setAttribute("erro", "Preencha todos os campos.");
				
				javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroRecorrecaoDeProva.jsp");
				
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
