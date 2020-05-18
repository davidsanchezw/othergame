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
import com.comweb.model.Users;

/**
 * Servlet que muestra mis propuestas comenzadas por mi
 * 
 */
@WebServlet("/matchesReceived")
public class MatchesReceived extends HttpServlet {
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

				// Obtiene match y lo setea
				int usr2 = me.getId();
				List<Matches> matches = (List<Matches>) matchDb.getSecondMatch(usr2, 1);
				request.setAttribute("matches", matches);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}

			RequestDispatcher rd = request.getRequestDispatcher("matchesReceived.jsp");
			rd.forward(request, response);
		}
	}

}
