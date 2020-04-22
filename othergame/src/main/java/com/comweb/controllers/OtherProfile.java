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
import com.comweb.conection.UserDBManager;
import com.comweb.model.Ads;
import com.comweb.model.Users;

@WebServlet("/otherProfile")
public class OtherProfile extends HttpServlet {
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
				UserDBManager userDb = new UserDBManager(db);
				AdDBManager adDb = new AdDBManager(db);

				System.out.println("000000");

				int idOtherUser = Integer.parseInt(request.getParameter("idOtherUser"));

				System.out.println("pruebaUsers");
				Users otherUser = userDb.getOtherUser(idOtherUser);
				request.setAttribute("otherUser", otherUser);
				System.out.println(otherUser.toString());

				System.out.println("pruebaAds");
				List<Ads> otherAds = adDb.getOtherUserAds(idOtherUser, 1);
				System.out.println("pruebageted");
				request.setAttribute("otherAds", otherAds);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}

			RequestDispatcher rd = request.getRequestDispatcher("otherProfile.jsp");
			rd.forward(request, response);
		}
	}

}
