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

@WebServlet("/matchSimpleAd")
public class MatchSimpleAd extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Obtiene el usuario desde la sesiÃ³n. A login si no se encuentra.

		HttpSession session = request.getSession();
		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {

			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);
				UserDBManager userDb = new UserDBManager(db);
				MatchDBManager matchDb = new MatchDBManager(db);
				System.out.println("JJEJEJEEJE");

				int idAd = Integer.parseInt(request.getParameter("idAd"));
				System.out.println(idAd);

				Ads singleAd = adDb.getAd(idAd);
				System.out.println(singleAd.getId());
				request.setAttribute("singleAd", singleAd);

				int idMatch = Integer.parseInt(request.getParameter("idMatch"));
				System.out.println("idMatch" + idMatch);
				Matches match = matchDb.getMatch(idMatch);
				request.setAttribute("match", match);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}

			RequestDispatcher rd = request.getRequestDispatcher("matchesSimpleAd.jsp");
			rd.forward(request, response);
		}
	}

}