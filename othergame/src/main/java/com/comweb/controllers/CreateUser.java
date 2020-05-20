package com.comweb.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comweb.conection.DBManager;
import com.comweb.conection.UserDBManager;
import com.comweb.model.Users;

/**
 * Servlet que cra un nuevo usuario Comprueba si existe el email o nicname y
 * hashea la contraseña
 *
 */
@WebServlet("/createUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static byte[] getSalt() throws NoSuchAlgorithmException { // Metodo obtener salt
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException { // Metodo binario a hexadecimal
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

		// obtiene los parametros para registrar
		String uemail = request.getParameter("uemail");
		String usr = request.getParameter("usr");
		String psw = request.getParameter("psw");
		String desc = request.getParameter("desc");

		// Generar salt
		byte[] salt = null;
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException e3) {
			e3.printStackTrace();
			response.sendRedirect("error-pass.html");
		}

		// Binario a hex
		String saltTxt = null;
		try {
			saltTxt = toHex(salt);
		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
			response.sendRedirect("error-pass.html");
		}

		// Generar Hash
		int iterations = 1000;
		char[] chars = psw.toCharArray();
		byte[] hash = null;

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf;
		try {
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = skf.generateSecret(spec).getEncoded();

		} catch (Exception e1) {
			e1.printStackTrace();
			response.sendRedirect("error-pass.html");
		}

		// Pasa a hex
		String hashTxt = null;
		try {
			hashTxt = toHex(hash);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			response.sendRedirect("error-pass.html");
		}

		Users user = new Users(uemail, saltTxt, hashTxt, usr.replaceAll("[^\\w\\s]", ""),
				desc.replaceAll("[^\\w\\s]", ""));

		// Buscar en base de datos al usuario con dicho email y nickname
		try (DBManager db = new DBManager()) {
			UserDBManager userDb = new UserDBManager(db);
			boolean emailAvalaible = userDb.emailAvalaible(uemail);
			boolean nicknameAvalaible = userDb.nicknameAvalaible(usr);

			// Check email existente
			if (!emailAvalaible) {
				request.setAttribute("errorTxt", 1);
				RequestDispatcher rd = request.getRequestDispatcher("error-registro.jsp");
				rd.forward(request, response);

				// Check nick existente
			} else if (!nicknameAvalaible) {
				request.setAttribute("errorTxt", 2);
				RequestDispatcher rd = request.getRequestDispatcher("error-registro.jsp");
				rd.forward(request, response);
			} else {
				// Crea usuario
				int id = userDb.createUser(user);
				if (id > 0)
					response.sendRedirect("noticeRegistered");
				else
					response.sendRedirect("error-db.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error-db.html");
		}
	}

}