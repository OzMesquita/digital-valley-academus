package br.ufc.russas.n2s.academus.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.dao.ArquivoDAO;
import br.ufc.russas.n2s.academus.dao.DisciplinaCursadaDAO;
import br.ufc.russas.n2s.academus.dao.JDBCArquivoDAO;
import br.ufc.russas.n2s.academus.dao.JDBCDisciplinaCursadaDAO;
import br.ufc.russas.n2s.academus.model.TipoArquivo;
import util.Constantes;

public class DownloadAnexosController extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadAnexosController() {
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
		
		System.out.print("Passou aqui!!!!!!");
		
		String ementa[] = request.getParameterValues("anexo");
		String matricula = request.getParameter("matricula");
		String idSolicitacao = request.getParameter("id_solicitacao");
		String idDisciplinaCursada = request.getParameter("id_disciplina_cursada");
		if(ementa!= null) {
			ArquivoDAO arqdao = new JDBCArquivoDAO();
			DisciplinaCursadaDAO dcdao = new JDBCDisciplinaCursadaDAO();
			
			ArrayList<File> files = new ArrayList<>();
			
			for(int i = 0; i < ementa.length; i++) {
				System.out.println(ementa[i]+" anexo");
				System.out.println(matricula+" "+idSolicitacao+" "+idDisciplinaCursada);
				int idTipoArquivo = Integer.parseInt(ementa[i]);
				
				String caminho = Constantes.getAnexoDir()+arqdao.buscarPorDisciplinaCursada(dcdao.buscarPorId(Integer.parseInt(idDisciplinaCursada)), TipoArquivo.getTipoArquivo(idTipoArquivo)).getCaminho();
				File arquivo = new File(caminho);
				files.add(arquivo);
			}
			
			if(files.size() >= 2) {
				/*
				 * baixando 2 ou mais arquivos
				 * 
				 * link onde foi baseado:
				 * https://javadigest.wordpress.com/2012/02/27/downloading-multiple-files-as-zip/
				 * */
				
				ServletOutputStream out = response.getOutputStream();
				ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));
	
				for (File file : files) {
	
					System.out.println("Adding " + file.getName());
					zos.putNextEntry(new ZipEntry(file.getName()));
	
					// Get the file
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(file);
	
					} catch (FileNotFoundException fnfe) {
						// If the file does not exists, write an error entry instead of
						// file
						// contents
						zos.write(("ERRORld not find file " + file.getName())
								.getBytes());
						zos.closeEntry();
						System.out.println("Couldfind file "
								+ file.getAbsolutePath());
						continue;
					}
	
					BufferedInputStream fif = new BufferedInputStream(fis);
	
					// Write the contents of the file
					int data = 0;
					while ((data = fif.read()) != -1) {
						zos.write(data);
					}
					fif.close();
	
					zos.closeEntry();
					System.out.println("Finishedng file " + file.getName());
				}
	
				zos.close();
				
			} else {
			
				//Baixando 1 arquivo
			 
				File arquivo = files.remove(0);
				Path path = arquivo.toPath();
        
				String nome = arquivo.getName();
				int tamanho = (int) arquivo.length();

				response.setContentType(Files.probeContentType(path)); // tipo do conteúdo
				response.setContentLength(tamanho);  // opcional
				response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");

				OutputStream output = response.getOutputStream();
				Files.copy(path, output);
				
			}
		}
	
	}
	
	
}
