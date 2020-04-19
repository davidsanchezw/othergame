package com.comweb.conection;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.comweb.model.Ads;
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
	 * Insert in BD a new user.
	 *
	 * @param ad The user created.
	 * @return The number of id, or 0 if ....
	 */
	public int createUser(Users user) throws SQLException {
		entity.getTransaction().begin();
		entity.persist(user);
		entity.getTransaction().commit();
		return user.getId();
	}

	/**
	 * Check in BD if an email exists.
	 *
	 * @param email The email to check.
	 * @return True if is available, or false if not
	 */
	public boolean emailAvalaible(String email) throws SQLException {
		Query query = entity.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
		query.setParameter("email", email);
		List<Users> results = query.getResultList();
		if (results.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Search user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object, or null if not found.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public Users getUser(int id) throws SQLException {

		return entity.find(Users.class, id);
	}

	/**
	 * Search user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object, or null if not found.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public List<Ads> getAdsUser(int id) throws SQLException {
		entity.getTransaction().begin();
		Users user = entity.find(Users.class, id);
		List<Ads> ads = user.getAds();
		ads.size();
		entity.getTransaction().commit();
		return ads;

	}

}
