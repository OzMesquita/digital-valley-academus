package br.ufc.russas.n2s.academus.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
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
		//ArquivoDAO arqdao = new JDBCArquivoDAO();
		//DisciplinaCursadaDAO dcdao = new JDBCDisciplinaCursadaDAO();
		
		System.out.print("Passou aqui!!!!!!");
		
		String button = request.getParameter("button");
		String ementa = request.getParameter("anexo");
		String matricula = request.getParameter("matricula");
		String idSolicitacao = request.getParameter("id_solicitacao");
		String idDisciplinaCursada = request.getParameter("id_disciplina_cursada");
		//String historico = request.getParameter("historico");
		//for(int i = 0; i < ementa.length; i++) {
		System.out.println(ementa+" "+button);
		System.out.println(matricula+" "+idSolicitacao+" "+idDisciplinaCursada);
		
		//Baixa o documento
		/*
		String caminho = Constantes.getAnexoDir()+arqdao.buscarPorDisciplinaCursada(dcdao.buscarPorId(Integer.parseInt(idDisciplinaCursada)), TipoArquivo.getTipoArquivo(1)).getCaminho();
		File arquivo = new File(caminho);//ementa
		System.out.print(caminho);
		Path path = arquivo.toPath();
        
        String nome = arquivo.getName();
        int tamanho = (int) arquivo.length();

        response.setContentType(Files.probeContentType(path)); // tipo do conteúdo
        response.setContentLength(tamanho);  // opcional
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");

        OutputStream output = response.getOutputStream();
        Files.copy(path, output);
		*/
		//}
	
	}
	
	
}
