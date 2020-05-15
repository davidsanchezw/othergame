package com.comweb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comweb.conection.AdDBManager;
import com.comweb.conection.DBManager;
import com.comweb.model.Users;

@WebServlet("/adToRestored")
public class AdToRestored extends HttpServlet {
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
				AdDBManager adDb = new AdDBManager(db);
				// Modificar ad a retirado e invalidar matches

				int idAdToRestored = Integer.parseInt(request.getParameter("idAdToRestored"));
				System.out.println("Estado = " + idAdToRestored);

				boolean estado = adDb.adToRestored(idAdToRestored);
				System.out.println("Estado = " + estado);

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
			response.sendRedirect("noticeRestored");

		}
	}

}