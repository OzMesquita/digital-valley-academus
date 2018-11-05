package br.ufc.russas.n2s.academus.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.russas.n2s.academus.connection.ServiceLocator;

public class ServletExemplo extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServletExemplo() {
			super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Filmes cadastrados</h1>");
		
		Connection con = null;
		try {
			con = ServiceLocator.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		String sql = "SELECT * FROM academus.disciplina";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				out.println("Código -> " + rs.getString("id_disciplina") + " Nome -> " + rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}
}