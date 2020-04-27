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

@WebServlet("/search")
public class Search extends HttpServlet {
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
				AdDBManager adDb = new AdDBManager(db);
				String search = (String) request.getParameter("search");
				request.setAttribute("searchTxt", search);

				int page = Integer.parseInt(request.getParameter("page"));
				request.setAttribute("page", page);
				int size = 10;

				List<Ads> searchedAds = adDb.getResultSearch(search, size, page);
				request.setAttribute("searchedAds", searchedAds);

				int quantitySearched = adDb.getQuantitySearched(search);
				request.setAttribute("quantitySearched", quantitySearched);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}

			RequestDispatcher rd = request.getRequestDispatcher("resultSearched.jsp");
			rd.forward(request, response);
		}
	}

}
