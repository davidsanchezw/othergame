package com.comweb.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comweb.model.Users;

/**
 * Servlet que reenvía a la notificacion de registro exitoso
 *
 */
@WebServlet("/noticeRegistered")
public class NoticeRegistered extends HttpServlet {
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
			RequestDispatcher rd = request.getRequestDispatcher("notice-registered.html");
			rd.forward(request, response);
		}
	}
}