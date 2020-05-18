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
 * Servlet que obtiene los 10 anuncios mas nuevos de acuerdo a la búsqueda y
 * llegar al resto de la aplicacion
 * 
 */
@WebServlet("/search")
public class Search extends HttpServlet {
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

			// Buscar en base de datos los 10 anuncios mas nuevos que cumplen la busqueda
			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);
				String search = (String) request.getParameter("search");
				request.setAttribute("searchTxt", search);

				int page = Integer.parseInt(request.getParameter("page"));
				request.setAttribute("page", page);
				int size = 10;

				List<Ads> searchedAds = adDb.getResultSearch(search, size, page);
				request.setAttribute("searchedAds", searchedAds);

				// Busca el numero de cantidad
				int quantitySearched = adDb.getQuantitySearched(search);
				request.setAttribute("quantitySearched", quantitySearched);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
			RequestDispatcher rd = request.getRequestDispatcher("resultSearched.jsp");
			rd.forward(request, response);
		}
	}
}
