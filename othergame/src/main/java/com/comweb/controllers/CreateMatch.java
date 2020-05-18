package com.comweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
				MatchDBManager matchDb = new MatchDBManager(db);

				// Ontengo caracteristicas
				int idAd = Integer.parseInt(request.getParameter("idAd"));
				Users usr2 = adDb.getUserAd(idAd);
				Ads ad1 = adDb.getAd(idAd);
				int statusMatchNumber = 1;
				StatusMatchTxt statusMatchTxt = matchDb.getstatusMatchTxt(statusMatchNumber);
				Date dateStart = new Date();

				// Contruyo nuevo match
				Matches match = new Matches(me, usr2, ad1, null, statusMatchTxt, dateStart);

				// Lo crea y si hay algun problema redirecciona a error
				int idMatch = matchDb.createMatch(match);
				if (idMatch > 0) {
					response.sendRedirect("matchView?idMatch=" + idMatch);
				} else
					response.sendRedirect("error-accion.html");

			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}
}