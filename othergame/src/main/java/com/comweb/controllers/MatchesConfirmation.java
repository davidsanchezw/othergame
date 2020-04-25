package com.comweb.controllers;

import java.io.IOException;

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

@WebServlet("/matchesConfirmation")
public class MatchesConfirmation extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {
			try (DBManager db = new DBManager()) {
				MatchDBManager matchDb = new MatchDBManager(db);
				// Modificar match a confirmado e invalidar anuncios
				int idMatch = Integer.parseInt(request.getParameter("idMatch"));
				Matches match = matchDb.getMatch(idMatch);
				request.setAttribute("match", match);
				RequestDispatcher rd = request.getRequestDispatcher("matchesConfirmation.jsp");
				rd.forward(request, response);

//NamingException
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}

}