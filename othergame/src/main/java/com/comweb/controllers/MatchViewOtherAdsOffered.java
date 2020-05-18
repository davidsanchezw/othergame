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
 * Servlet que lleva a la vista de anuncios ofertados de otro usuario para
 * responder a una oferta
 * 
 *
 */
@WebServlet("/matchViewOtherAdsOffered")
public class MatchViewOtherAdsOffered extends HttpServlet {
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
				MatchDBManager matchDb = new MatchDBManager(db);
				AdDBManager adDb = new AdDBManager(db);

				// Obtiene match actual y lo setea
				int idMatch = Integer.parseInt(request.getParameter("idMatch"));
				Matches currentMatch = matchDb.getMatch(idMatch);
				request.setAttribute("currentMatch", currentMatch);

				// Obtiene ads del otro y los setea
				List<Ads> otherAds = adDb.getOtherUserAdsByMatch(idMatch);
				request.setAttribute("otherAds", otherAds);

				RequestDispatcher rd = request.getRequestDispatcher("matchView-OtherAdsOffered.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}
}