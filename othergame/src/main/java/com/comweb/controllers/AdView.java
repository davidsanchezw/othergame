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
 * Servlet que recoge los datos del .html y si es correcto manda a principal si
 * no devuelve a index
 *
 */
@WebServlet("/adView")
public class AdView extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {

			// Buscar en base de datos al usuario con dicho email y contraseña
			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);
				// obtiene el parametro id para autenticar
				int idAd = Integer.parseInt(request.getParameter("idAd"));

				int caso = adDb.adCheck(idAd, me.getId());

				if (caso == 1) {
					response.sendRedirect("adViewMine?idAd=" + idAd);
				} else if (caso == 2) {
					response.sendRedirect("adViewOther?idAd=" + idAd);
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}

}