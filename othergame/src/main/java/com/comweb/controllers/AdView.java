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
 * Servlet que checkea el tipo de anuncio a mostrar
 *
 */
@WebServlet("/adView")
public class AdView extends HttpServlet {
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

			// Obtiene el id del anuncio y lo checkea
			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);

				int idAd = Integer.parseInt(request.getParameter("idAd"));
				int caso = adDb.adCheck(idAd, me.getId());

				if (caso == 1) {
					response.sendRedirect("adViewMine?idAd=" + idAd);
				} else if (caso == 2) {
					response.sendRedirect("adViewOther?idAd=" + idAd);
				}
				if (caso == 0)
					response.sendRedirect("error-anuncio.html");

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}
}