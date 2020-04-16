package com.comweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comweb.conection.AdDBManager;
import com.comweb.conection.DBManager;
import com.comweb.model.Ads;
import com.comweb.model.Users;

@WebServlet("/principal")
public class Principal extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Obtiene el usuario desde la sesiÃ³n. A login si no se encuentra.

		HttpSession session = request.getSession();
		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {
			// Buscar en base de datos al usuario con dicho email y contraseña
			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);
				List<Ads> principalAds = (List<Ads>) adDb.getLastAds();
				// Reenvia la peticion a una plantilla JSP, pasando el los anuncios como
				// atributo
				request.setAttribute("principalAds", principalAds);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}

			RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
			rd.forward(request, response);
//NamingException
		}
	}

}
