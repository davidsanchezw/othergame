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
import com.comweb.model.Ads;
import com.comweb.model.Users;

/**
 * Servlet que obtiene los 10 anuncios mas nuevos, permite realizar busquedas y
 * llegar al resto de la aplicacion
 * 
 */
@WebServlet("/principal")
public class Principal extends HttpServlet {
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

			// Buscar en base de datos los 10 anuncios mas nuevos
			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);
				int size = 10;
				int page = 0;
				List<Ads> principalAds = (List<Ads>) adDb.getLastAds(size, page);

				// setea como atributo la lista de anuncios, el numero de pagina y la cantidad
				request.setAttribute("principalAds", principalAds);
				request.setAttribute("page", page);
				request.setAttribute("quantity", adDb.getQuantity());

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
			RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
			rd.forward(request, response);
		}
	}
}
