package com.comweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comweb.conection.AdDBManager;
import com.comweb.conection.DBManager;
import com.comweb.model.Ads;
import com.comweb.model.StatusItemTxt;
import com.comweb.model.StatusPostTxt;
import com.comweb.model.Users;

/**
 * Servlet que recoge los datos para crear un nuevo anuncio
 *
 */
@WebServlet("/createAd")
public class CreateAd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Obtiene el usuario desde la sesion. Redirecciona a index si no se encuentra.
		HttpSession session = request.getSession();
		Users me = (Users) session.getAttribute("me");
		if (me == null) {
			response.sendRedirect("index.jsp");
		} else {
			try (DBManager db = new DBManager()) {
				AdDBManager adDb = new AdDBManager(db);

				// obtiene los parametros para crear el nuevo anuncio
				String nameAd = request.getParameter("name");
				String explanation = request.getParameter("desc");
				int statusItemNumber = Integer.parseInt(request.getParameter("status"));
				StatusItemTxt statusItemTxt = adDb.getstatusItemTxt(statusItemNumber);
				int statusPostNumber = 1;
				StatusPostTxt statusPostTxt = adDb.getstatusPostTxt(statusPostNumber);
				Date date = new Date();

				Ads ad = new Ads(nameAd.replaceAll("[^\\w\\s,.¿?¡!]", ""),
						explanation.replaceAll("[^\\w\\s,.¿?¡!]", ""), date, null, statusItemTxt, statusPostTxt, me);

				// Crea el anuncio y lleva a la vista del nuevo anuncio

				int idAd = adDb.createAd(ad);
				response.sendRedirect("adView?idAd=" + idAd);

			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}

}