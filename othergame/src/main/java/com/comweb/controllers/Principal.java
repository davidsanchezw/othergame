package com.comweb.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/principal")
public class Principal extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Obtiene el usuario desde la sesiÃ³n. A login si no se encuentra.
		/*
		 * HttpSession session = request.getSession(); User me = (User)
		 * session.getAttribute("me"); if (me == null) {
		 * response.sendRedirect("index.jsp"); } // Buscar en base de datos al usuario
		 * con dicho email y contraseña try (DBManager db = new DBManager())
		 */ {

			RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
			rd.forward(request, response);

//NamingException
		}
	}

}
