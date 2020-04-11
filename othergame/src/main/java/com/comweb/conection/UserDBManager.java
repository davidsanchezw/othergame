package com.comweb.conection;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import com.comweb.model.User;

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
	public int checkLogin(String email, String password) throws SQLException {
		User user = entity.find(User.class, 2);
		/*
		 * String query = "SELECT id " + "FROM Users " + "WHERE email = ? AND pass = ?";
		 * try (PreparedStatement stmt = connection.prepareStatement(query)) {
		 * stmt.setString(1, email); stmt.setString(2, password); ResultSet rs =
		 * stmt.executeQuery(); if (rs.next()) { return rs.getInt("id"); } }
		 */
		return user.getId();
	}

	/**
	 * Search user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object, or null if not found.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public User userInfo(int id) throws SQLException {
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
