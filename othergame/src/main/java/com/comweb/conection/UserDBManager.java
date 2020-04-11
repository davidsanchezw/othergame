package com.comweb.conection;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.comweb.model.Users;

public class UserDBManager {

	private EntityManager entity;

	public UserDBManager(DBManager db) {
		entity = db.getEntityManagerFactory().createEntityManager();
	}

	/**
	 * Return the id of an user if email and pass match.
	 *
	 * @param email The email from user to check.
	 * @return The number of id, or 0 if the email and pass does not match.
	 */
	public Users checkLogin(String email, String pass) throws SQLException {
		/*
		 * String query = "SELECT id " + "FROM Users " + "WHERE email = ? AND pass = ?";
		 * } try (PreparedStatement stmt = connection.prepareStatement(query)) {
		 * stmt.setString(1, email); stmt.setString(2, password); ResultSet rs =
		 * stmt.executeQuery(); if (rs.next()) { return rs.getInt("id"); } }
		 */
		Query query = entity.createQuery("SELECT u FROM Users u WHERE u.email = :email AND pass = :pass", Users.class);
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		List<Users> results = query.getResultList();
		if (results.isEmpty())
			return null;
		else
			return (Users) results.get(0);

	}

	/**
	 * Search user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object, or null if not found.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public Users userInfo(int id) throws SQLException {
		/*
		 * String query = "SELECT publicName, explanation " + "FROM Users " +
		 * "WHERE id = ?"; try (PreparedStatement stmt =
		 * connection.prepareStatement(query)) { stmt.setInt(1, id); ResultSet rs =
		 * stmt.executeQuery(); if (rs.next()) { User user = new User();
		 * user.setPublicName(rs.getString("publicName"));
		 * user.setExplanation(rs.getString("explanation")); user.setId(id); return
		 * user; } }
		 */
		return null;
	}
}
