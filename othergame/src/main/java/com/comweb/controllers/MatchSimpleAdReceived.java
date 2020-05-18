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
import com.comweb.conection.UserDBManager;
import com.comweb.model.Ads;
import com.comweb.model.Matches;
import com.comweb.model.Users;

@WebServlet("/matchSimpleAdReceived")
public class MatchSimpleAdReceived extends HttpServlet {
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
				UserDBManager userDb = new UserDBManager(db);
				MatchDBManager matchDb = new MatchDBManager(db);

				// Obtiene anuncio y lo setea
				int idAd = Integer.parseInt(request.getParameter("idAd"));
				Ads singleAd = adDb.getAd(idAd);
				request.setAttribute("singleAd", singleAd);

				// Obtiene match y lo setea
				int idMatch = Integer.parseInt(request.getParameter("idMatch"));
				Matches match = matchDb.getMatch(idMatch);
				request.setAttribute("match", match);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}

			RequestDispatcher rd = request.getRequestDispatcher("matchSimpleAdReceived.jsp");
			rd.forward(request, response);
		}
	}

}