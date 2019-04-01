package br.ufc.russas.n2s.academus.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
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
import br.ufc.russas.n2s.academus.dao.DisciplinaCursadaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCArquivoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaCursadaDAO;
import br.ufc.russas.n2s.academus.model.Arquivo;
import br.ufc.russas.n2s.academus.model.TipoArquivo;
import br.ufc.russas.n2s.academus.util.Constantes;

@MultipartConfig
public class GerenciarAnexosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(GerenciarAnexosController.class.getCanonicalName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciarAnexosController() {
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
		ArquivoDAO arqdao = new JDBCArquivoDAO();
		DisciplinaCursadaDAO dcdao = new JDBCDisciplinaCursadaDAO();
		
		int chave = Integer.parseInt(request.getParameter("chave"));
		
		String button = request.getParameter("button");
		String matricula = request.getParameter("matricula");
		String idSolicitacao = request.getParameter("id_solicitacao");
		String idDisciplinaCursada = request.getParameter("id_disciplina_cursada");
		int tipoArquivo = Integer.parseInt(request.getParameter("tipo_arquivo"));
		
		if(button.equals("2"))
			chave = 2;
		
		if(chave == 1){
			String caminhoRelativo = File.separator+matricula+File.separator+idSolicitacao+File.separator+idDisciplinaCursada;
			String caminho = Constantes.getAnexoDir()+caminhoRelativo;
			
			File dir = new File(caminho+File.separator);
			if(!dir.isDirectory()) dir.mkdirs();
			if(!Files.isWritable(dir.toPath())) System.out.println("Não é possivel escrever um arquivo");
			if(request.getPart("id_disciplina_cursada") == null) System.out.println("Part vazio");
			else{
					Part part = request.getPart("anexo");
					String nome = "";
					nome = (tipoArquivo == 1 ? "ementa-" : "historico-")+matricula+"-"+idSolicitacao+"-"+idDisciplinaCursada+".pdf";
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
				        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", new Object[]{nome, caminho});
						
				        Arquivo arq = new Arquivo();
				        arq.setCaminho(caminhoRelativo + File.separator + nome);
				        arq.setTipo(TipoArquivo.getTipoArquivo(tipoArquivo));
				        arq.setNome(nome);
				        if(arqdao.buscarPorDisciplinaCursada(dcdao.buscarPorId(Integer.parseInt(idDisciplinaCursada)), TipoArquivo.getTipoArquivo(tipoArquivo)).getIdArquivo() > 0){
				        	arqdao.editar(arq);
				        }else{
				        	arqdao.cadastrarArquivo(arq, Integer.parseInt(idDisciplinaCursada));
				        }
				        
				        
				        System.out.println("chegou aki");
				        request.setAttribute("id", idSolicitacao);
				        javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("anexarDocumentos.jsp");
						dispatcher.forward(request, response);
						
				    } catch (FileNotFoundException fne) {
				        fne.getMessage();
				
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
		}else if(chave == 2){
			String caminho = Constantes.getAnexoDir()+arqdao.buscarPorDisciplinaCursada(dcdao.buscarPorId(Integer.parseInt(idDisciplinaCursada)), TipoArquivo.getTipoArquivo(tipoArquivo)).getCaminho();
			File arquivo = new File(caminho) ;
			System.out.print(caminho);
			Path path = arquivo.toPath();
	        
	        String nome = arquivo.getName();
	        int tamanho = (int) arquivo.length();

	        response.setContentType(Files.probeContentType(path)); // tipo do conteúdo
	        response.setContentLength(tamanho);  // opcional
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");

	        OutputStream output = response.getOutputStream();
	        Files.copy(path, output);
		} else {
			System.out.println("Não funcionou o chave");
			
			request.setAttribute("id", idSolicitacao);
	        javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("anexarDocumentos.jsp");
	        dispatcher.forward(request, response);
	        
		}
	}
	
	public static boolean isWritable(File arquivo) {
	    return Files.isWritable(arquivo.toPath());
	}
}
