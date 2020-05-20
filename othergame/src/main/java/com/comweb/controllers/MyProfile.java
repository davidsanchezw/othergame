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
import com.comweb.conection.MatchDBManager;
import com.comweb.conection.UserDBManager;
import com.comweb.model.Ads;
import com.comweb.model.Users;

/**
 * Servlet que muestra mi perfil
 * 
 */
@WebServlet("/myprofile")
public class MyProfile extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Obtiene el usuario desde la sesion. Redirecciona a index si no se encuentra.
		HttpSession session = request.getSession();
		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {

			try (DBManager db = new DBManager()) {
				UserDBManager userDb = new UserDBManager(db);
				AdDBManager adDb = new AdDBManager(db);
				MatchDBManager matchDb = new MatchDBManager(db);

				// Obtiene anuncios y los setea
				Users user = userDb.getUser(me.getId());
				List<Ads> ads = adDb.getUserAds(user.getId(), 1);
				request.setAttribute("ads", ads);

				int quantity1 = matchDb.quantity1(me.getId());
				request.setAttribute("quantity1", quantity1);

				int quantity2 = matchDb.quantity2(me.getId());
				request.setAttribute("quantity2", quantity2);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}

			RequestDispatcher rd = request.getRequestDispatcher("myprofile.jsp");
			rd.forward(request, response);
		}
	}

}
