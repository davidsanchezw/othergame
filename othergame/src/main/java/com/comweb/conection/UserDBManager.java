package com.comweb.conection;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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
	 *         PBKDF2WithHmacSHA1
	 * @throws SQLException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public Users checkLogin(String email, String pass)
			throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		entity.getTransaction().begin();
		// Obtengo al usuario a comprobar
		Query query = entity.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
		query.setParameter("email", email);
		List<Users> me = query.getResultList();
		entity.getTransaction().commit();

		if (me.isEmpty())
			return null;
		else {
			// Compruebo
			boolean matched = validatePassword(pass, me.get(0).getSalt(), me.get(0).getPass());
			if (matched)
				return (Users) me.get(0);
			else
				return null;
		}

	}

	// Metodo para checklogin
	private static boolean validatePassword(String toCheckPassword, String storedSalt, String storedHash)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		byte[] salt = fromHex(storedSalt);
		byte[] hash = fromHex(storedHash);

		PBEKeySpec spec = new PBEKeySpec(toCheckPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	// Metodo para checklogin
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	/**
	 * Insert in BD a new user.
	 *
	 * @param ad The user created.
	 * @return The number of id
	 * @throws SQLException
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
	 * @throws SQLException
	 */
	public boolean emailAvalaible(String email) throws SQLException {
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
		query.setParameter("email", email);
		List<Users> results = query.getResultList();
		entity.getTransaction().commit();
		if (results.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check in BD if a nickName exists.
	 *
	 * @param nickName The nickName to check.
	 * @return True if is available, or false if not
	 * @throws SQLException
	 */
	public boolean nicknameAvalaible(String nickName) throws SQLException {
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT u FROM Users u WHERE u.publicName = :nickName", Users.class);
		query.setParameter("nickName", nickName);
		List<Users> results = query.getResultList();
		entity.getTransaction().commit();
		if (results.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Get my user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object, or null if not found.
	 * @throws SQLException If something fails with the DB.
	 */
	public Users getUser(int id) throws SQLException {

		return entity.find(Users.class, id);
	}

	/**
	 * Get other user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object, or null if not found.
	 * @throws SQLException If something fails with the DB.
	 */
	public Users getOtherUser(int idOtherUser) throws SQLException {
		entity.getTransaction().begin();
		Users tempUser = entity.find(Users.class, idOtherUser);
		if (tempUser != null) {
			Users otherUser = new Users(tempUser.getId(), tempUser.getPublicName(), tempUser.getExplanation());
			entity.getTransaction().commit();
			return otherUser;
		} else {
			entity.getTransaction().commit();
			return null;
		}
	}

	/**
	 * Get simple user by Id ad.
	 *
	 * @param idAd The id of the ad.
	 * @return The User object, or null if not found.
	 * @throws SQLException If something fails with the DB.
	 */
	public Users getSimpleUserByAd(int idAd) throws SQLException {
		entity.getTransaction().begin();
		Ads ad = entity.find(Ads.class, idAd);
		Users simpleUser = new Users(ad.getUser().getId(), ad.getUser().getPublicName(), ad.getUser().getExplanation());
		entity.getTransaction().commit();
		return simpleUser;
	}

	/**
	 * Search user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object.
	 * @throws SQLException If something fails with the DB.
	 */
	public Users getUserAd(int id) throws SQLException {
		entity.getTransaction().begin();
		Ads ad = entity.find(Ads.class, id);
		Users user = ad.getUser();
		user.getId();
		entity.getTransaction().commit();
		return user;

	}

}
