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

@WebServlet("/matchesStarted")
public class MatchesStarted extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Obtiene el usuario desde la sesiÃ³n. A login si no se encuentra.

		HttpSession session = request.getSession();
		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {

			try (DBManager db = new DBManager()) {
				MatchDBManager matchDb = new MatchDBManager(db);
				int usr1 = me.getId();
				System.out.println("prueba0");
				List<Matches> matches = (List<Matches>) matchDb.getFirstMatch(usr1, 1);
				request.setAttribute("matches", matches);
				request.setAttribute("title", "Propuestas iniciadas");

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}

			RequestDispatcher rd = request.getRequestDispatcher("matchesView.jsp");
			rd.forward(request, response);
		}
	}

}
