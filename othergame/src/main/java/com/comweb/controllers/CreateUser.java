package com.comweb.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comweb.conection.DBManager;
import com.comweb.conection.UserDBManager;
import com.comweb.model.Users;

@WebServlet("/createUser")
public class CreateUser extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		// obtiene los parametros para autenticar
		String uemail = request.getParameter("uemail");
		String usr = request.getParameter("usr");
		String psw = request.getParameter("psw");
		String desc = request.getParameter("desc");

		Users user = new Users(uemail, psw, usr, desc);

		// Buscar en base de datos al usuario con dicho email y contraseña
		try (DBManager db = new DBManager()) {
			UserDBManager userDb = new UserDBManager(db);
			boolean emailAvalaible = userDb.emailAvalaible(uemail);
			boolean nicknameAvalaible = userDb.nicknameAvalaible(usr);
			if (!emailAvalaible) {
				request.setAttribute("errorText", "Email existente");
				RequestDispatcher rd = request.getRequestDispatcher("error-registro.jsp");
				rd.forward(request, response);

			} else if (!nicknameAvalaible) {
				request.setAttribute("errorText", "Nombre de usuario existente");
				RequestDispatcher rd = request.getRequestDispatcher("error-registro.jsp");
				rd.forward(request, response);
			} else {

				int id = userDb.createUser(user);
				Users me = userDb.getUser(id);
				session.setAttribute("me", me);
				response.sendRedirect("principal");
			}
//NamingException
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

}