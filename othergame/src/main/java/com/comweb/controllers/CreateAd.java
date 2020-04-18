package com.comweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
import com.comweb.model.StatusItemTxt;
import com.comweb.model.StatusPostTxt;
import com.comweb.model.Users;

@WebServlet("/createAd")
public class CreateAd extends HttpServlet {
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
				// obtiene los parametros para autenticar
				String nameAd = request.getParameter("name");
				String explanation = request.getParameter("desc");
				int statusItemNumber = Integer.parseInt(request.getParameter("status"));
				StatusItemTxt statusItemTxt = adDb.getstatusItemTxt(statusItemNumber);
				int statusPostNumber = 1;
				StatusPostTxt statusPostTxt = adDb.getstatusPostTxt(statusPostNumber);
				Date date = new Date();

				Ads ad = new Ads(nameAd, explanation, date, null, statusItemTxt, statusPostTxt, me);

				// Buscar en base de datos al usuario con dicho email y contraseña

				System.out.println("id = 0");

				int id = adDb.createAd(ad);
				System.out.println("id = " + id);
				Ads singleAd = adDb.getAd(id);
				request.setAttribute("singleAd", singleAd);
				RequestDispatcher rd = request.getRequestDispatcher("singleAd.jsp");
				rd.forward(request, response);

//NamingException
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}

}