
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
 * Servlet que continua el proceso de una propuesta proponiendo el segundo
 * articulo
 * 
 */
@WebServlet("/matchToPending")
public class MatchToPending extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Obtiene el usuario desde la sesion. Redirecciona a index si no se encuentra.
		HttpSession session = request.getSession();
		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {

			try (DBManager db = new DBManager()) {
				MatchDBManager matchDb = new MatchDBManager(db);

				// Modificar match a Pendiente (estado aceptado)
				int idMatchToPending = Integer.parseInt(request.getParameter("idMatchToPending"));
				int idAdToPending = Integer.parseInt(request.getParameter("idAdToPending"));
				boolean estado = matchDb.matchToPending(idMatchToPending, idAdToPending);

				// Redirecciono a notificacion
				if (estado)
					response.sendRedirect("noticePending");
				else
					response.sendRedirect("error-db.html");

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}

}