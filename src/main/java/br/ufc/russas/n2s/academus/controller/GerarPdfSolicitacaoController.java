package br.ufc.russas.n2s.academus.controller;

import java.io.File;
import java.nio.file.Path;
import java.util.Calendar;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.ufc.russas.n2s.academus.dao.DAOFactoryJDBC;
import br.ufc.russas.n2s.academus.dao.ProfessorDAO;
import br.ufc.russas.n2s.academus.dao.SolicitacaoDAO;
import br.ufc.russas.n2s.academus.model.DisciplinaCursada;
import br.ufc.russas.n2s.academus.model.Professor;
import br.ufc.russas.n2s.academus.model.Solicitacao;
import util.Constantes;

public class GerarPdfSolicitacaoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public GerarPdfSolicitacaoController() {
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
		System.out.print(request.getParameter("id"));
		SolicitacaoDAO soldao = new DAOFactoryJDBC().criarSolicitacaoDAO();
		Solicitacao solicitacao = soldao.buscarPorId(Integer.parseInt(request.getParameter("id")));
		
		try {
			File pasta = new File(Constantes.getTempDir());
			if(!pasta.exists())
				pasta.mkdirs();
			
			File arquivo = new File(Constantes.getTempDir(),"Relatorio-"+solicitacao.getIdSolicitacao()+"-"+solicitacao.getSolicitante().getMatricula()+".pdf");
			
			Document documento = new Document();
			PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(arquivo.getAbsolutePath()));
			documento.open();
			documento.setPageSize(PageSize.A4);
			documento.setMargins(3, 2, 3, 2);
			
			//cabecalho
			Image image = Image.getInstance(Constantes.getLogoUfc());
			image.setAlignment(Image.MIDDLE);
			image.scaleAbsoluteWidth(60);
			image.scaleAbsoluteHeight(90);
			documento.add(image);
			Paragraph cabecalho = new Paragraph("UNIVERDADE FEDERAL DO CEARÁ \n"
					+ "CAMPUS RUSSAS \n\n");
			cabecalho.setAlignment(Paragraph.ALIGN_CENTER);
			documento.add(cabecalho);
			Font tituloIndentificacao = new Font(FontFamily.UNDEFINED, 13, Font.BOLD, BaseColor.BLACK);
			Font bold = new Font(FontFamily.UNDEFINED, 12, Font.BOLD, BaseColor.BLACK);
			Font normal = new Font(FontFamily.UNDEFINED, 12, Font.NORMAL, BaseColor.BLACK);
			
			//Conteudo Coordenação
			Paragraph coord = new Paragraph();
			coord.setFont(tituloIndentificacao);
			coord.setAlignment(Paragraph.ALIGN_CENTER);
			coord.add("COORDENAÇÃO DO CURSO DE "+solicitacao.getCurso().getNome()+"\n");
			coord.add("REQUERIMANTO PARA APROVEITAMENTO DE DISCIPLINA\n\n");
			documento.add(coord);
			
			//Conteudo Atenção
			coord.clear();
			coord.setFont(normal);
			coord.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			coord.add("ATENÇÃO: ANEXAR HISTÓRICO ESCOLAR (ORIGINAL OU CÓPIA AUTENTICADA) E CÓPIA AUTENTICADA"+
					" DO PROGRAMA DA DISCIPLINA (AS CERTIFICAÇÕES NÃO PRECISAM SER CARTORIAIS, PODENDO SER "+
					"DO ÓRGÃO EXPEDIDOR OU DIGITAL).\n\n");
			documento.add(coord);
			
			//Conteudo Identificador
			Paragraph identificador = new Paragraph();
			Phrase frase = new Phrase();
			frase.setFont(tituloIndentificacao);
			frase.add("I. IDENTIFICADOR DO REQUIRENTE:\n\n");
			identificador.add(frase);
			
			frase.clear();
			frase.setFont(bold);
			frase.add("Código da Solicitação: ");
			frase.setFont(normal);
			frase.add(solicitacao.getIdSolicitacao()+"\n");
			identificador.add(frase);
			
			frase.clear();
			frase.setFont(bold);
			frase.add("Nome: ");
			frase.setFont(normal);
			frase.add(solicitacao.getSolicitante().getNome()+"\n");
			identificador.add(frase);
			
			frase.clear();
			frase.setFont(bold);
			frase.add("Matrícula: ");
			frase.setFont(normal);
			frase.add(solicitacao.getSolicitante().getMatricula());
			frase.setFont(bold);
			frase.add("     Curso: ");
			frase.setFont(normal);
			frase.add(solicitacao.getCurso().getNome()+"\n");
			identificador.add(frase);
			
			frase.clear();
			ProfessorDAO prodao = new DAOFactoryJDBC().criarProfessorDAO();
			Professor professor = prodao.isCoordenador(solicitacao.getCurso().getIdCurso());
			frase.setFont(bold);
			frase.add("Coordenador: ");
			frase.setFont(normal);
			frase.add(professor.getNome()+"\n\n");
			identificador.add(frase);
			
			identificador.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			documento.add(identificador);
			
			//Conteúdo Disciplinas
			identificador.clear();
			frase.clear();
			frase.setFont(tituloIndentificacao);
			frase.add("II. IDENTIFICAÇÃO DA DISCIPLINA: \n\n");
			identificador.add(frase);
			
			frase.clear();
			frase.setFont(bold);
			frase.add("O aluno acima especificado vem requerer o aproveitamento da disiplina / carga horária: \n");
			identificador.setAlignment(Paragraph.ALIGN_JUSTIFIED_ALL);
			identificador.add(frase);
			
			frase.clear();
			frase.setFont(normal);
			frase.add(solicitacao.getDisciplinaAlvo().getDisciplina().getNome()+" / "+solicitacao.getDisciplinaAlvo().getDisciplina().getCarga()+" horas \n");
			frase.setFont(bold);
			frase.add("por ter cursado a(s) disciplina(s):\n\n");
			identificador.add(frase);
			frase.setFont(normal);
			identificador.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			documento.add(identificador);
			
			PdfPTable table = new PdfPTable(3);
			PdfPCell celula1 = new PdfPCell(new Paragraph("Disciplina/carga",bold));
			celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celula2 = new PdfPCell(new Paragraph("Ano/semestre",bold));
			celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celula3 = new PdfPCell(new Paragraph("Instituição",bold));
			celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.setWidths(new int[] {300,300,300});
			table.addCell(celula1);
			table.addCell(celula2);
			table.addCell(celula3);
			
			for(DisciplinaCursada disciplinaCursada : solicitacao.getDisciplinasCursadas()) {
				/*frase.clear();
				frase.add(disciplinaCursada.getNome()+" / "+disciplinaCursada.getCarga()+" horas \n");
				frase.add("na instituição: "+disciplinaCursada.getInstituicao());
				frase.add(" no ano/semestre: "+disciplinaCursada.getSemestre()+"\n\n");
				identificador.add(frase);*/
				
				PdfPCell cel1 = new PdfPCell(new Paragraph(disciplinaCursada.getNome()+" / "+disciplinaCursada.getCarga()+" horas"));
				cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cel2 = new PdfPCell(new Paragraph(disciplinaCursada.getSemestre()));
				cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cel3 = new PdfPCell(new Paragraph(disciplinaCursada.getInstituicao()));
				cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				
			}
			
			documento.add(table);
			
			identificador.clear();
			int numDia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			int numMes = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int ano = Calendar.getInstance().get(Calendar.YEAR);
			String dia = Integer.toString(numDia);
			String mes = Integer.toString(numMes);
			if(numDia < 10) {
				dia = '0'+dia;
			}
			if(numMes < 10) {
				mes = '0'+mes;
			}
			identificador.add("\nRussas-CE, "+dia+"/"+mes+"/"+ano+".\n");
			identificador.setAlignment(Paragraph.ALIGN_RIGHT);
			documento.add(identificador);
			//rodapé
			Font fontFooter = new Font(FontFamily.UNDEFINED, 7, Font.NORMAL, BaseColor.BLACK);
			PdfContentByte cb = writer.getDirectContent();
	        Paragraph footer = new Paragraph("Universidade Federal do Ceará - CNPJ: 07.272.636/001-31", fontFooter);
	        Paragraph footer2 = new Paragraph("Campus da UFC em Russas\n", fontFooter); 
	        
	        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
	                footer,
	                (documento.right() - documento.left()) / 2 + documento.leftMargin(),
	                documento.bottom() - 10, 0);
	        
	        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, footer, (documento.right() - documento.left()) / 2 + documento.leftMargin(), 40, 0);
	        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, footer2, (documento.right() - documento.left()) / 2 + documento.leftMargin(), 30, 0);
	        
			//baixando o documento
			documento.close();
			Path path = arquivo.toPath();
            
            String nome = arquivo.getName();
            int tamanho = (int) arquivo.length();
    
            response.setContentType(Files.probeContentType(path)); // tipo do conteudo
            response.setContentLength(tamanho);  // opcional
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");
            OutputStream output = response.getOutputStream();
            Files.copy(path, output);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	
	}
		
}
