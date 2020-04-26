package com.comweb.conection;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.comweb.model.Ads;
import com.comweb.model.Matches;
import com.comweb.model.StatusItemTxt;
import com.comweb.model.StatusPostTxt;
import com.comweb.model.Users;

public class AdDBManager {

	private EntityManager entity;

	public AdDBManager(DBManager db) {
		entity = db.getEntityManagerFactory().createEntityManager();
	}

	/**
	 * Insert in BD a new ad.
	 *
	 * @param ad The ad created.
	 * @return The number of id, or 0 if ....
	 */

	public int createAd(Ads ad) throws SQLException {
		entity.getTransaction().begin();
		entity.persist(ad);
		entity.getTransaction().commit();
		return ad.getId();
	}

	// TO DO:
	// https://www.youtube.com/watch?v=L6mGNM_zyaQ&list=PLTd5ehIj0goPcnQs34i0F-Kgp5JHX8UUv&index=7

	/**
	 * Get an ad from BD.
	 *
	 * @param id The id of the ad.
	 * @return The Ad object, or null if not found.
	 * @throws SQLException If somthing fails with the DB.
	 */

	public Ads getAd(int id) throws SQLException {

		return entity.find(Ads.class, id);
	}

//OK
	public List<Ads> getLastAds(int size, int page) throws SQLException {
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT a FROM Ads a WHERE a.statusPostTxt.id = 1 ORDER BY a.id DESC",
				Ads.class);
		query.setMaxResults(size);
		query.setFirstResult(page * size);
		List<Ads> ads = (List<Ads>) query.getResultList();
		entity.getTransaction().commit();
		return ads;

	}

//OK
	public StatusItemTxt getstatusItemTxt(int id) {
		return entity.find(StatusItemTxt.class, id);
	}

//OK
	public StatusPostTxt getstatusPostTxt(int id) {
		return entity.find(StatusPostTxt.class, id);
	}

//OK
	public int getQuantity() {
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT a FROM Ads a WHERE a.statusPostTxt.id = 1", Ads.class);
		int quantity = (int) query.getResultList().size();
		entity.getTransaction().commit();
		return quantity;
	}

	/**
	 * Search user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object, or null if not found.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public Users getUserAd(int id) throws SQLException {
		entity.getTransaction().begin();
		Ads ad = entity.find(Ads.class, id);
		Users user = ad.getUser();
		user.getId();
		entity.getTransaction().commit();
		return user;

	}

	/**
	 * Search user by Id.
	 *
	 * @param id The id of the user.
	 * @return The User object, or null if not found.
	 * @throws SQLException If somthing fails with the DB.
	 */
//OK
	public List<Ads> getUserAds(int idUser, int statusPostNumber) throws SQLException {
		entity.getTransaction().begin();
		Query query = entity.createQuery(
				"SELECT a FROM Ads a WHERE a.user.id = :idUser AND a.statusPostTxt.id = :statusPostNumber", Ads.class);
		query.setParameter("idUser", idUser);
		query.setParameter("statusPostNumber", statusPostNumber);
		List<Ads> ads = (List<Ads>) query.getResultList();
		entity.getTransaction().commit();
		return ads;

	}

	public List<Ads> getOtherUserAdsByMatch(int idMatch) throws SQLException {
		entity.getTransaction().begin();
		int idUser = entity.find(Matches.class, idMatch).getUsr1().getId();
		Query query = entity.createQuery("SELECT a FROM Ads a WHERE a.user.id = :idUser AND a.statusPostTxt.id = 1",
				Ads.class);
		query.setParameter("idUser", idUser);
		List<Ads> ads = (List<Ads>) query.getResultList();
		entity.getTransaction().commit();
		return ads;

	}

}