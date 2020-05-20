package com.comweb.conection;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
	 * @return The number of id
	 * @throws SQLException
	 */
	public int createAd(Ads ad) throws SQLException {
		entity.getTransaction().begin();
		entity.persist(ad);
		entity.getTransaction().commit();
		return ad.getId();
	}

	/**
	 * Obtiene anuncio por su id, usado con post.
	 *
	 * @param id The id of the ad.
	 * @return The Ad object, or null if not found.
	 * @throws SQLException If something fails with the DB.
	 */
	public Ads getAd(int id) throws SQLException {

		return entity.find(Ads.class, id);
	}

	/**
	 * Obtiene anuncio mio por su id, comprueba que es mio.
	 *
	 * @param idAd   The id of the ad.
	 * @param idUser The id of the user mine.
	 * @return The Ad object, or null if not found.
	 * @throws SQLException If something fails with the DB.
	 */
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

	/**
	 * Obtiene anuncio de otro por su id, comprueba que no es mio.
	 *
	 * @param idAd   The id of the ad.
	 * @param idUser The id of the user mine.
	 * @return The Ad object, or null if not found.
	 * @throws SQLException If something fails with the DB.
	 */
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

	/**
	 * Obtiene lista de anuncios ultimos disponibles.
	 *
	 * @param size The size of the page.
	 * @param page The page.
	 * @return The list of Ads object.
	 * @throws SQLException If something fails with the DB.
	 */
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

	/**
	 * Obtiene StatusItemTxt por su id.
	 *
	 * @param id The id of the StatusItemTxt.
	 * @return The StatusItemTxt object.
	 */
	public StatusItemTxt getstatusItemTxt(int id) {
		return entity.find(StatusItemTxt.class, id);
	}

	/**
	 * Obtiene StatusPostTxt por su id.
	 *
	 * @param id The id of the StatusPostTxt.
	 * @return The StatusPostTxt object.
	 */
	public StatusPostTxt getstatusPostTxt(int id) {
		return entity.find(StatusPostTxt.class, id);
	}

	/**
	 * Obtiene el numero de anuncios existentes disponibles.
	 * 
	 * @return The quantity of Ads.
	 */
	public int getQuantity() {
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT a FROM Ads a WHERE a.statusPostTxt.id = 1", Ads.class);
		int quantity = (int) query.getResultList().size();
		entity.getTransaction().commit();
		return quantity;
	}

	/**
	 * Get list of ads by its id, with the status wished.
	 *
	 * @param idUser           The id of the user.
	 * @param statusPostNumber The statusPostNumber of the statusPost.
	 * @return The User object, or null if not found.
	 * @throws SQLException If something fails with the DB.
	 */
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

	/**
	 * Get list of ads by its id, with the status wished.
	 *
	 * @param idUser           The id of the user.
	 * @param statusPostNumber The statusPostNumber of the statusPost.
	 * @return The User object, or null if not found.
	 * @throws SQLException If something fails with the DB.
	 */
	public List<Ads> getResultSearch(String search, int size, int page) {
		entity.getTransaction().begin();
		Query query = entity.createQuery(
				"FROM Ads a WHERE (a.nameAd like :search OR a.explanation like :search) AND a.statusPostTxt.id = 1",
				Ads.class);
		query.setParameter("search", "%" + search + "%");
		query.setMaxResults(size);
		query.setFirstResult(page * size);
		List<Ads> ads = (List<Ads>) query.getResultList();
		entity.getTransaction().commit();
		return ads;

	}

	/**
	 * Obtiene el numero de anuncios existentes disponibles para al busqueda.
	 * 
	 * @param search The search txt.
	 * @return The quantity of Ads.
	 */
	public int getQuantitySearched(String search) {
		entity.getTransaction().begin();
		Query query = entity.createQuery("FROM Ads a WHERE a.nameAd like :search AND a.statusPostTxt.id = 1",
				Ads.class);
		query.setParameter("search", "%" + search + "%");

		int quantitySearched = (int) query.getResultList().size();
		entity.getTransaction().commit();
		return quantitySearched;
	}

	/**
	 * Retira un anuncio.
	 *
	 * @param idAdToRetired The id of the ad to be retired.
	 * @return boolean if ok.
	 * @throws SQLException If something fails with the DB.
	 */
	public boolean adToRetired(int idAdToRetired) {
		boolean ok = false;
		entity.getTransaction().begin();
		Query query1 = entity.createQuery("SELECT a FROM Ads a WHERE a.id = :idAdToRetired AND a.statusPostTxt.id = 1",
				Ads.class);
		query1.setParameter("idAdToRetired", idAdToRetired);
		List<Ads> ads = (List<Ads>) query1.getResultList();
		if (ads.size() > 0) {
			// Modifica el ad a retirado
			Ads adToRetired = ads.get(0);
			StatusPostTxt statusPostTxt = entity.find(StatusPostTxt.class, 2);
			adToRetired.setStatusPostTxt(statusPostTxt);

			// Modifica la hora de ser retirado
			adToRetired.setDateEnd(new Date());

			// Modifica los match a no disponibles y setea fecha
			StatusMatchTxt statusMatchTxt = entity.find(StatusMatchTxt.class, 5);
			Date date = new Date();
			Query query2 = entity.createQuery(
					"SELECT m FROM Matches m WHERE (m.ad1.id = :idAdToRetired OR m.ad2.id = :idAdToRetired) AND (m.statusMatchTxt.id = 1 OR m.statusMatchTxt.id = 2)",
					Matches.class);
			query2.setParameter("idAdToRetired", idAdToRetired);
			List<Matches> matches = (List<Matches>) query2.getResultList();
			for (int i = 0; i < matches.size(); i++) {
				matches.get(i).setStatusMatchTxt(statusMatchTxt);
				matches.get(i).setDateEnd(date);
			}

			ok = true;
		}
		entity.getTransaction().commit();
		return ok;
	}

	/**
	 * Restaura un anuncio.
	 *
	 * @param idAdToRestored The id of the ad to be restored.
	 * @return boolean if ok.
	 * @throws SQLException If something fails with the DB.
	 */
	public boolean adToRestored(int idAdToRestored) {
		boolean ok = false;
		entity.getTransaction().begin();
		// Modifica el ad a retirado
		Query query1 = entity
				.createQuery("SELECT a FROM Ads a WHERE a.id = :idAdToRestoredd AND a.statusPostTxt.id = 2", Ads.class);
		query1.setParameter("idAdToRestoredd", idAdToRestored);
		List<Ads> ads = (List<Ads>) query1.getResultList();
		if (ads.size() > 0) {
			Ads adToRetired = ads.get(0);
			StatusPostTxt statusPostTxt = entity.find(StatusPostTxt.class, 1);
			adToRetired.setStatusPostTxt(statusPostTxt);
			ok = true;
		}
		entity.getTransaction().commit();
		return ok;
	}

	/**
	 * Chekea el caso de un anuncio para mostrar su vista correspondiente.
	 *
	 * @param idAd   The id of the ad.
	 * @param idUser The id of my user
	 * @return int with the case.
	 * @throws SQLException If something fails with the DB.
	 */
	public int adCheck(int idAd, int idUser) {
		int caso = 0;
		entity.getTransaction().begin();
		Users me = entity.find(Users.class, idUser);
		// Caso anuncio mio
		Query query1 = entity.createQuery(
				"SELECT a FROM Ads a WHERE a.user.id = :idUser AND a.id = :idAd AND a.statusPostTxt.id = 1", Ads.class);
		query1.setParameter("idUser", idUser);
		query1.setParameter("idAd", idAd);
		List<Ads> ads1 = (List<Ads>) query1.getResultList();

		// Caso anuncio otro
		Query query2 = entity.createQuery(
				"SELECT a FROM Ads a WHERE a.user.id != :idUser AND a.id = :idAd AND a.statusPostTxt.id = 1",
				Ads.class);
		query2.setParameter("idUser", idUser);
		query2.setParameter("idAd", idAd);
		List<Ads> ads2 = (List<Ads>) query2.getResultList();

		if (ads1.size() > 0)
			caso = 1;
		else if (ads2.size() > 0) {
			caso = 2;
		}
		entity.getTransaction().commit();
		return caso;
	}

	/**
	 * Obtiene la lista de anuncios que ofrece el otro usuario en un match.
	 *
	 * @param idMatch The id of the match.
	 * @return list of ads.
	 * @throws SQLException If something fails with the DB.
	 */
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