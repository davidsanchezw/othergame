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

import com.comweb.conection.DBManager;
import com.comweb.conection.MatchDBManager;
import com.comweb.model.Matches;
import com.comweb.model.StatusMatchTxt;
import com.comweb.model.Users;

/**
 * Servlet que muestra mis propuestas no disponibles
 * 
 */
@WebServlet("/matchesNonAvaibles")
public class MatchesNonAvaibles extends HttpServlet {
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

				// Obtiene propuestas y setea
				int statusMatchNumber = 5;
				int usr = me.getId();
				List<Matches> matches = (List<Matches>) matchDb.getEndedMatch(usr, statusMatchNumber);
				request.setAttribute("matches", matches);
				StatusMatchTxt statusMatchTxt = matchDb.getstatusMatchTxt(statusMatchNumber);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}

			RequestDispatcher rd = request.getRequestDispatcher("matchesNonAvaibles.jsp");
			rd.forward(request, response);
		}
	}

}