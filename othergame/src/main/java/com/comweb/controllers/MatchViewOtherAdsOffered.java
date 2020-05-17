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

@WebServlet("/matchViewOtherAdsOffered")
public class MatchViewOtherAdsOffered extends HttpServlet {
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
				MatchDBManager matchDb = new MatchDBManager(db);
				AdDBManager adDb = new AdDBManager(db);

				// Modificar match a
				int idMatch = Integer.parseInt(request.getParameter("idMatch"));

				Matches currentMatch = matchDb.getMatch(idMatch);
				request.setAttribute("currentMatch", currentMatch);

				List<Ads> otherAds = adDb.getOtherUserAdsByMatch(idMatch);

				request.setAttribute("otherAds", otherAds);

				RequestDispatcher rd = request.getRequestDispatcher("matchView-OtherAdsOffered.jsp");
				rd.forward(request, response);

//NamingException
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}

}