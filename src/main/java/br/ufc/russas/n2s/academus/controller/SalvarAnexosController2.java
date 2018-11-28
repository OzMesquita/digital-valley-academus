package br.ufc.russas.n2s.academus.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import br.ufc.russas.n2s.academus.dao.ArquivoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCArquivoDAO;
import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.util.Constantes;

@MultipartConfig
public class SalvarAnexosController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(SalvarAnexosController.class.getCanonicalName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvarAnexosController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String matricula = request.getParameter("matricula");
		String idSolicitacao = request.getParameter("id_solicitacao");
		String idDisciplinaCursada = request.getParameter("id_disciplina_cursada");
		String caminho = Constantes.getAnexoDir()+File.separator+matricula+File.separator+idSolicitacao;
		boolean chave = Boolean.parseBoolean(request.getParameter("chave"));
		
		if(chave){
			File dir = new File(caminho+File.separator);
			if(!dir.isDirectory()) dir.mkdirs();
			System.out.println(dir.getAbsolutePath());
			if(!Files.isWritable(dir.toPath())) System.out.println("Não é possivel escrever um arquivo");
			if(request.getPart("id_disciplina_cursada") == null) System.out.println("Part vazio");
			else{
				//System.out.println(request.getParts().size());
					Part part = request.getPart("id_disciplina_cursada");
					String nome = "";
					nome = "anexo-"+matricula+"-"+idSolicitacao+"-"+idDisciplinaCursada+".pdf";
					System.out.println(nome);
					
					OutputStream out = null;
				    InputStream filecontent = null;
				    final PrintWriter writer = response.getWriter();
				
				    try {
				        File file = new File(dir.getAbsolutePath() + File.separator + nome);
				    	out = new FileOutputStream(file);
				        filecontent = part.getInputStream();
				
				        int read = 0;
				        final byte[] bytes = new byte[1024];
				
				        while ((read = filecontent.read(bytes)) != -1) {
				            out.write(bytes, 0, read);
				        }
				        //writer.println("New file " + nome + " created at " + caminho);
				        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", new Object[]{nome, caminho});
						
				        Arquivo arq = new Arquivo();
				        arq.setCaminho(file.getAbsolutePath());
				        ArquivoDAO arqdao = new JDBCArquivoDAO();
				        arqdao.cadastrarArquivo(arq, Integer.parseInt(idDisciplinaCursada));
				        
				        request.setAttribute("id", idSolicitacao);
				        javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("anexarDocumentos.jsp");
						dispatcher.forward(request, response);
						
				    } catch (FileNotFoundException fne) {
				        //writer.println("You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location.");
				        //writer.println("<br/> ERROR: " + fne.getMessage());
				
				        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", new Object[]{fne.getMessage()});
				    } finally {
				        if (out != null) {
				            out.close();
				        }
				        if (filecontent != null) {
				            filecontent.close();
				        }
				        if (writer != null) {
				            writer.close();
				        }
				    }
			}
		}else{
			
		}
		
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	public static boolean isWritable(File arquivo) {
	    return Files.isWritable(arquivo.toPath());
	}
}
