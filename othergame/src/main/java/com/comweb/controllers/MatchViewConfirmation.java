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

/**
 * Servlet que lleva a la vista de confirmacion o cancelacion de una propuesta
 * 
 */
@WebServlet("/matchViewConfirmation")
public class MatchViewConfirmation extends HttpServlet {
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
				int idMatch = Integer.parseInt(request.getParameter("idMatch"));

				if (matchDb.matchCheck(idMatch, me.getId()) == 2) {
					// Obtiene match y lo setea
					Matches match = matchDb.getMatch(idMatch);
					request.setAttribute("match", match);
					RequestDispatcher rd = request.getRequestDispatcher("matchView-Confirmation.jsp");
					rd.forward(request, response);
				} else
					response.sendRedirect("matchView?idMatch=" + idMatch);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}
}