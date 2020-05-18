package com.comweb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comweb.conection.DBManager;
import com.comweb.conection.MatchDBManager;
import com.comweb.model.Users;

/**
 * Servlet que comprueba que condicion cumple una propuesta
 * 
 */
@WebServlet("/matchView")
public class MatchView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Obtiene el usuario desde la sesion. Redirecciona a index si no se encuentra.
		HttpSession session = request.getSession();
		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {

			// Buscar en base de datos al usuario con dicho email y contraseña
			try (DBManager db = new DBManager()) {
				MatchDBManager matchDb = new MatchDBManager(db);

				// obtiene el parametro id para autenticar
				int idMatch = Integer.parseInt(request.getParameter("idMatch"));
				int caso = matchDb.matchCheck(idMatch, me.getId());

				if (caso == 1) { // Estado propuesta y user actual es propietario del anuncio por el que se creó
					response.sendRedirect("matchViewOtherAdsOffered?idMatch=" + idMatch);
				} else if (caso == 2) { // Estado aceptado, y el usuario actual es quien ha iniciado la oferta
					response.sendRedirect("matchViewConfirmation?idMatch=" + idMatch);
				} else if (caso == 3) // otro caso, pero en el que intervenimos
					response.sendRedirect("matchViewWait?idMatch=" + idMatch);
				else
					response.sendRedirect("error-match.html");

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}

}