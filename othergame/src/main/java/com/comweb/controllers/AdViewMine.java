package com.comweb.controllers;

import java.io.IOException;
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
import com.comweb.conection.MatchDBManager;
import com.comweb.model.Ads;
import com.comweb.model.Matches;
import com.comweb.model.Users;

/**
 * Servlet que checkea si es mi anuncio y lo muestra
 *
 */
@WebServlet("/adViewMine")
public class AdViewMine extends HttpServlet {
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

			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);
				MatchDBManager matchDb = new MatchDBManager(db);

				// Obtengo parametro id del anuncio a mostrar
				int idAd = Integer.parseInt(request.getParameter("idAd"));
				// Chequea que es mio y lo obtengo
				Ads myAd = adDb.getMyAd(idAd, me.getId());
				if (myAd != null) {

					// Setea el anuncio
					request.setAttribute("myAd", myAd);

					// Obtiene matches relacionados y los setea
					List<Matches> matches1 = (List<Matches>) matchDb.getMatchesRelationatedOne(idAd);
					request.setAttribute("matches1", matches1);
					List<Matches> matches2 = (List<Matches>) matchDb.getMatchesRelationatedTwoMe(idAd);
					request.setAttribute("matches2", matches2);
					List<Matches> matches3 = (List<Matches>) matchDb.getMatchesRelationatedTwoOther(idAd);
					request.setAttribute("matches3", matches3);

					RequestDispatcher rd = request.getRequestDispatcher("adView-Mine.jsp");
					rd.forward(request, response);
				}
				response.sendRedirect("adView?idAd=" + idAd);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}
}