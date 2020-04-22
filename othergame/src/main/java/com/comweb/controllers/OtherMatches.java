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
import com.comweb.conection.UserDBManager;
import com.comweb.model.Matches;
import com.comweb.model.Users;

@WebServlet("/otherMatches")
public class OtherMatches extends HttpServlet {
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
			RequestDispatcher rd = null;
			try (DBManager db = new DBManager()) {
				MatchDBManager matchDb = new MatchDBManager(db);
				UserDBManager userDb = new UserDBManager(db);
				int idUser = Integer.parseInt(request.getParameter("idUser"));
				Users otherUser = userDb.getOtherUser(idUser);
				request.setAttribute("otherUser", otherUser);

				System.out.println("prueba00");
				List<Matches> matches = (List<Matches>) matchDb.getEndedMatch(idUser, 3);
				System.out.println("prueba01");
				request.setAttribute("matches", matches);
				request.setAttribute("title", "Propuestas completadas");

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
			rd = request.getRequestDispatcher("matchesOtherUser.jsp");
			rd.forward(request, response);
		}
	}

}