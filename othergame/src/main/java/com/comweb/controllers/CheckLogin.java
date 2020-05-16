package com.comweb.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comweb.conection.DBManager;
import com.comweb.conection.UserDBManager;
import com.comweb.model.Users;

/**
 * Servlet que recoge los datos del .html y si es correcto manda a principal si
 * no devuelve a index
 *
 */
@WebServlet("/check")
public class CheckLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		// obtiene los parametros para autenticar
		String uemail = request.getParameter("uemail");
		String psw = request.getParameter("psw");

		// Buscar en base de datos al usuario con dicho email y contraseña
		try (DBManager db = new DBManager()) {
			UserDBManager userDb = new UserDBManager(db);

			Users me = userDb.checkLogin(uemail, psw);
			if (me == null)
				response.sendRedirect("error-autenticacion.html");
			else {
				System.out.println("id1=" + me.getId());
				session.setAttribute("me", me);
				response.sendRedirect("principal");

			}
//NamingException
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}