package com.comweb.controllers;

import java.io.IOException;

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
import com.comweb.model.Matches;
import com.comweb.model.Users;

/**
 * Servlet que checkea si no es mi anuncio y lo muestra
 *
 */
@WebServlet("/adViewOther")
public class AdViewOther extends HttpServlet {
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
				AdDBManager adDb = new AdDBManager(db);
				UserDBManager userDb = new UserDBManager(db);
				MatchDBManager matchDb = new MatchDBManager(db);

				// Obtengo parametro
				int idAd = Integer.parseInt(request.getParameter("idAd"));
				// Chequea que es de otro y lo obtiene
				Ads otherAd = adDb.getOtherAd(idAd, me.getId());
				if (otherAd != null) {

					// Seteo el anuncio
					request.setAttribute("otherAd", otherAd);

					// Obtiene matches relacionados
					Matches match1 = (Matches) matchDb.getMatchRelationatedOne(idAd, me.getId());
					request.setAttribute("match1", match1);
					Matches match2 = (Matches) matchDb.getMatchRelationatedTwoMe(idAd, me.getId());
					request.setAttribute("match2", match2);
					Matches match3 = (Matches) matchDb.getMatchRelationatedTwoOther(idAd, me.getId());

					request.setAttribute("match3", match3);

					// Obtienes datos basicos del usuario
					Users otherUser = userDb.getSimpleUserByAd(idAd);
					request.setAttribute("otherUser", otherUser);

					RequestDispatcher rd = request.getRequestDispatcher("adView-Other.jsp");
					rd.forward(request, response);
				}
				response.sendRedirect("adView?idAd=" + idAd);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error-db.html");
			}
		}
	}
}