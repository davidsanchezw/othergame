package com.comweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
import com.comweb.model.StatusMatchTxt;
import com.comweb.model.Users;

@WebServlet("/createMatch")
public class CreateMatch extends HttpServlet {
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
				//
				int idAd = Integer.parseInt(request.getParameter("idAd"));
				Users usr2 = adDb.getUserAd(idAd);
				Ads ad1 = adDb.getAd(idAd);
				int statusMatchNumber = 1;
				StatusMatchTxt statusMatchTxt = matchDb.getstatusMatchTxt(statusMatchNumber);
				Date dateStart = new Date();

				Matches match = new Matches(me, usr2, ad1, null, statusMatchTxt, dateStart);

				// Buscar en base de datos al usuario con dicho email y contraseña

				System.out.println("id = 0");

				int id = matchDb.createMatch(match);
				System.out.println("id = " + id);
				Matches singleMatch = matchDb.getMatch(id);
				request.setAttribute("singleMatch", singleMatch);
				RequestDispatcher rd = request.getRequestDispatcher("mySingleMatchStarted.jsp");
				rd.forward(request, response);

//NamingException
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}

}