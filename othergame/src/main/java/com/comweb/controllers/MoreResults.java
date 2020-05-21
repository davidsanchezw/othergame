package com.comweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
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
import com.comweb.model.Ads;
import com.comweb.model.Users;

/**
 * Servlet que obtiene los 10 anuncios de la pagina solicitada, permite realizar
 * busquedas y llegar al resto de la aplicacion
 *
 */
@WebServlet("/moreResults")
public class MoreResults extends HttpServlet {
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

			// Buscar en base de datos los 10 anuncios mas nuevos de la pagina solicitada
			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);
				int size = 10;
				int page = Integer.parseInt(request.getParameter("page"));
				List<Ads> principalAds = (List<Ads>) adDb.getLastAds(size, page);

				// setea como atributo la lista de anuncios, el numero de pagina y la cantidad
				request.setAttribute("principalAds", principalAds);
				request.setAttribute("page", page);
				request.setAttribute("quantity", adDb.getQuantity());
				// Cantidad de tareas
				MatchDBManager matchDb = new MatchDBManager(db);

				int quantity1 = matchDb.quantity1(me.getId());
				request.setAttribute("quantity1", quantity1);

				int quantity2 = matchDb.quantity2(me.getId());
				request.setAttribute("quantity2", quantity2);

			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
			RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
			rd.forward(request, response);
		}
	}
}
