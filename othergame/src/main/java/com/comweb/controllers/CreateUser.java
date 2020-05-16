package com.comweb.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		// obtiene los parametros para autenticar
		String uemail = request.getParameter("uemail");
		String usr = request.getParameter("usr");
		String psw = request.getParameter("psw");
		String desc = request.getParameter("desc");

		// Generar salt
		byte[] salt = null;
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		// Pasa a hex
		String saltTxt = null;
		try {
			saltTxt = toHex(salt);
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		System.out.println("1");
		// Generar Hash
		int iterations = 1000;
		char[] chars = psw.toCharArray();
		byte[] hash = null;
		System.out.println("2");

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf;
		try {
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = skf.generateSecret(spec).getEncoded();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Pasa a hex
		String hashTxt = null;
		try {
			hashTxt = toHex(hash);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Users user = new Users(uemail, saltTxt, hashTxt, usr, desc);

		// Buscar en base de datos al usuario con dicho email y nickname
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
				response.sendRedirect("noticeRegistered");
			}
//NamingException
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

}