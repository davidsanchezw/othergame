package com.comweb.controllers;

import java.io.IOException;

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

@WebServlet("/adViewOther")
public class AdViewOther extends HttpServlet {
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
			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);
				MatchDBManager matchDb = new MatchDBManager(db);

				// Obtengo parametro
				int idAd = Integer.parseInt(request.getParameter("idAd"));
				// Obtiene anuncio y comprueba que es mio
				Ads otherAd = adDb.getOtherAd(idAd, me.getId());
				if (otherAd != null) {

					request.setAttribute("otherAd", otherAd);
					// Obtiene matches relacionados
					Matches match1 = (Matches) matchDb.getMatchRelationated(idAd, 1, me.getId());
					request.setAttribute("match1", match1);

					// Obtiene matches relacionados
					Matches match2 = (Matches) matchDb.getMatchRelationated(idAd, 2, me.getId());
					request.setAttribute("match2", match2);

					RequestDispatcher rd = request.getRequestDispatcher("adView-Other.jsp");
					rd.forward(request, response);
				}
				response.sendRedirect("error-anuncio.html");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}
}