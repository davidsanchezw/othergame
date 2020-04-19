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
import com.comweb.conection.UserDBManager;
import com.comweb.model.Ads;
import com.comweb.model.Users;

@WebServlet("/myprofile")
public class MyProfile extends HttpServlet {
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
				System.out.println("prueba0");
				Users user = userDb.getUser(me.getId());
				System.out.println("prueba1");
				List<Ads> ads = userDb.getAdsUser(user.getId());
				System.out.println(ads);
				request.setAttribute("me", me);
				request.setAttribute("ads", ads);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}

			RequestDispatcher rd = request.getRequestDispatcher("myprofile.jsp");
			rd.forward(request, response);
		}
	}

}
