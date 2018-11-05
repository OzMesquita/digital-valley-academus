package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.connection.ServiceLocator;

public class TesteAPP extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public TesteAPP() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			ServiceLocator.getInstance();
			System.out.println("conexão estabelecida com sucesso");
 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}}
}
