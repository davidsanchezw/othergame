package com.comweb.conection;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.comweb.model.Ads;
import com.comweb.model.Matches;
import com.comweb.model.StatusItemTxt;
import com.comweb.model.StatusMatchTxt;
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

//ok
	public Ads getMyAd(int idAd, int idUser) throws SQLException {
		entity.getTransaction().begin();

		Query query = entity.createQuery(
				"SELECT a FROM Ads a WHERE a.user.id = :idUser AND a.id = :idAd AND a.statusPostTxt.id = 1", Ads.class);
		query.setParameter("idUser", idUser);
		query.setParameter("idAd", idAd);

		List<Ads> ads = (List<Ads>) query.getResultList();

		entity.getTransaction().commit();
		if (ads.size() > 0)
			return ads.get(0);
		else
			return null;
	}

//ok
	public Ads getOtherAd(int idAd, int idUser) throws SQLException {
		entity.getTransaction().begin();

		Query query = entity.createQuery(
				"SELECT a FROM Ads a WHERE a.user.id != :idUser AND a.id = :idAd AND a.statusPostTxt.id = 1",
				Ads.class);
		query.setParameter("idUser", idUser);
		query.setParameter("idAd", idAd);

		List<Ads> ads = (List<Ads>) query.getResultList();

		entity.getTransaction().commit();
		if (ads.size() > 0)
			return ads.get(0);
		else
			return null;
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

//OK 
	public List<Ads> getResultSearch(String search, int size, int page) {
		entity.getTransaction().begin();
		Query query = entity.createQuery("FROM Ads a WHERE a.nameAd like :search AND a.statusPostTxt.id = 1",
				Ads.class);
		query.setParameter("search", "%" + search + "%");
		query.setMaxResults(size);
		query.setFirstResult(page * size);
		List<Ads> ads = (List<Ads>) query.getResultList();
		entity.getTransaction().commit();
		return ads;

	}

//OK
	public int getQuantitySearched(String search) {
		entity.getTransaction().begin();
		Query query = entity.createQuery("FROM Ads a WHERE a.nameAd like :search AND a.statusPostTxt.id = 1",
				Ads.class);
		query.setParameter("search", "%" + search + "%");

		int quantitySearched = (int) query.getResultList().size();
		entity.getTransaction().commit();
		return quantitySearched;
	}

//OK
	public boolean adToRetired(int idAdToRetired) {
		boolean ok = false;
		entity.getTransaction().begin();
		// Modifica el ad a retirado
		Ads adToRetired = entity.find(Ads.class, idAdToRetired);
		StatusPostTxt statusPostTxt = entity.find(StatusPostTxt.class, 2);
		adToRetired.setStatusPostTxt(statusPostTxt);

		// Modifica los match a no disponibles
		StatusMatchTxt statusMatchTxt = entity.find(StatusMatchTxt.class, 5);

		ListIterator<Matches> list = adToRetired.getMatchesFirst().listIterator();
		while (list.hasNext()) {
			list.next().setStatusMatchTxt(statusMatchTxt);
		}
		list = adToRetired.getMatchesSecond().listIterator();
		while (list.hasNext()) {
			list.next().setStatusMatchTxt(statusMatchTxt);
		}

		ok = true;
		entity.getTransaction().commit();
		return ok;

	}

//OK
	public boolean adToRestored(int idAdToRestoredd) {
		boolean ok = false;
		entity.getTransaction().begin();
		// Modifica el ad a retirado
		Ads adToRetired = entity.find(Ads.class, idAdToRestoredd);
		StatusPostTxt statusPostTxt = entity.find(StatusPostTxt.class, 1);
		adToRetired.setStatusPostTxt(statusPostTxt);

		ok = true;
		entity.getTransaction().commit();
		return ok;
	}

//OK
	public int adCheck(int idAd, int idUser) {
		int caso = 0;
		entity.getTransaction().begin();
		Users me = entity.find(Users.class, idUser);
		// Caso anuncio mio
		Query query = entity.createQuery("SELECT a FROM Ads a WHERE a.user.id = :idUser AND a.id = :idAd", Ads.class);
		query.setParameter("idUser", idUser);
		query.setParameter("idAd", idAd);

		List<Ads> ads = (List<Ads>) query.getResultList();
		if (ads.size() > 0)
			caso = 1;
		else {
			caso = 2;
		}
		entity.getTransaction().commit();
		return caso;
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