package com.comweb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comweb.conection.AdDBManager;
import com.comweb.conection.DBManager;
import com.comweb.model.Users;

/**
 * Servlet que retira los anuncios
 *
 */
@WebServlet("/adToRetired")
public class AdToRetired extends HttpServlet {
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
				AdDBManager adDb = new AdDBManager(db);

				// Modificar ad a retirado e invalidar matches
				int idAdToRetired = Integer.parseInt(request.getParameter("idAdToRetired"));
				boolean estado = adDb.adToRetired(idAdToRetired);
				if (estado)
					response.sendRedirect("noticeRetired");
				else
					response.sendRedirect("error-db.html");

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}
}