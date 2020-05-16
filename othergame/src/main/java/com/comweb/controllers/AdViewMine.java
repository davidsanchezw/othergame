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

@WebServlet("/adViewMine")
public class AdViewMine extends HttpServlet {
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
				Ads myAd = adDb.getMyAd(idAd, me.getId());
				if (myAd != null) {

					request.setAttribute("myAd", myAd);

					// Obtiene matches relacionados
					List<Matches> matches1 = (List<Matches>) matchDb.getMatchesRelationated(idAd, 1);
					request.setAttribute("matches1", matches1);

					List<Matches> matches2 = (List<Matches>) matchDb.getMatchesRelationated(idAd, 2);
					request.setAttribute("matches2", matches2);

					RequestDispatcher rd = request.getRequestDispatcher("adView-Mine.jsp");
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