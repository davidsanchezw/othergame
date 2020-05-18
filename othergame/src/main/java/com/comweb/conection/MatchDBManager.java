package com.comweb.conection;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.comweb.model.Ads;
import com.comweb.model.Matches;
import com.comweb.model.StatusMatchTxt;
import com.comweb.model.StatusPostTxt;
import com.comweb.model.Users;

public class MatchDBManager {

	private EntityManager entity;

	public MatchDBManager(DBManager db) {
		entity = db.getEntityManagerFactory().createEntityManager();
	}

	/**
	 * Crea un match y devuelve su id.
	 *
	 * @param match The match to create.
	 * @return id of the new Match.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public int createMatch(Matches match) throws SQLException {
		int id = 0;
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT a FROM Ads a WHERE a.id = :ad1 AND a.statusPostTxt.id = 1", Ads.class);
		query.setParameter("ad1", match.getAd1().getId());
		List<Ads> ads = (List<Ads>) query.getResultList();
		if (ads.size() > 0) {
			entity.persist(match);
			id = match.getId();
		}
		entity.getTransaction().commit();
		return id;
	}

	/**
	 * Devuelve un match.
	 *
	 * @param id The id of the user.
	 * @return A Match.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public Matches getMatch(int id) throws SQLException {

		return entity.find(Matches.class, id);
	}

	/**
	 * Devuelve el objeto StatusMatchTxt.
	 *
	 * @param id The id of the StatusMatchTxt.
	 * @return A StatusMatchTxt.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public StatusMatchTxt getstatusMatchTxt(int id) {
		return entity.find(StatusMatchTxt.class, id);
	}

	/**
	 * Devuelve la lista de los matches en proceso: los que he iniciado y los que
	 * debo confirmacion.
	 *
	 * @param usr1              The id of the user.
	 * @param statusMatchNumber The id of the statusMatch.
	 * @return A list of Matches.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public List<Matches> getFirstMatch(int usr1, int statusMatchNumber) throws SQLException { // A
		entity.getTransaction().begin();
		Query query = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.usr1.id = :usr1 AND m.statusMatchTxt.id = :statusMatchNumber",
				Matches.class);
		query.setParameter("usr1", usr1);
		query.setParameter("statusMatchNumber", statusMatchNumber);
		List<Matches> matches = (List<Matches>) query.getResultList();
		entity.getTransaction().commit();
		return matches;
	}

	/**
	 * Devuelve la lista de los matches en proceso: los que debo buscar otro anuncio
	 * y los que espero a confirmacion.
	 *
	 * @param usr2              The id of the user.
	 * @param statusMatchNumber The id of the statusMatch.
	 * @return A list of Matches.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public List<Matches> getSecondMatch(int usr2, int statusMatchNumber) throws SQLException {
		entity.getTransaction().begin();
		Query query = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.usr2.id = :usr2 AND m.statusMatchTxt.id = :statusMatchNumber",
				Matches.class);
		query.setParameter("usr2", usr2);
		query.setParameter("statusMatchNumber", statusMatchNumber);
		List<Matches> matches = (List<Matches>) query.getResultList();
		entity.getTransaction().commit();
		return matches;
	}

	/**
	 * Devuelve la lista de los matches que han finalizado: completados, cancelados,
	 * no disponibles.
	 *
	 * @param usr               The id of the user.
	 * @param statusMatchNumber The id of the statusMatch.
	 * @return A list of Matches.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public List<Matches> getEndedMatch(int usr, int statusMatchNumber) throws SQLException {
		entity.getTransaction().begin();
		Query query = entity.createQuery(
				"SELECT m FROM Matches m WHERE (m.usr1.id = :usr1 OR m.usr2.id = :usr2) AND m.statusMatchTxt.id = :statusMatchNumber",
				Matches.class);
		query.setParameter("usr1", usr);
		query.setParameter("usr2", usr);
		query.setParameter("statusMatchNumber", statusMatchNumber);

		List<Matches> matches = (List<Matches>) query.getResultList();
		entity.getTransaction().commit();
		return matches;
	}

	/**
	 * Convierte una propuesta en proceso a completada, y los anuncios
	 * correspondientes a completados.
	 *
	 * @param idMatchToCompleted The id of the match.
	 * @return A boolean if the process is done right.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public boolean matchToConfirm(int idMatchToCompleted) throws SQLException {
		boolean ok = false;
		entity.getTransaction().begin();

		// Primero comprueba el estado del match
		Query query1 = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.id = :idMatchToCompleted AND m.statusMatchTxt.id = 2", Matches.class);
		query1.setParameter("idMatchToCompleted", idMatchToCompleted);
		List<Matches> matches1 = (List<Matches>) query1.getResultList();
		if (matches1.size() > 0) {
			Matches matchToCompleted = matches1.get(0);
			// Modifica los anuncios a completados
			StatusPostTxt statusPostTxt = entity.find(StatusPostTxt.class, 3);
			matchToCompleted.getAd1().setStatusPostTxt(statusPostTxt);
			matchToCompleted.getAd2().setStatusPostTxt(statusPostTxt);

			// Modifica las propuestas relacionadas a no disponibles y setea fecha
			StatusMatchTxt statusMatchTxt = entity.find(StatusMatchTxt.class, 5);
			Date dateEnd = new Date();

			Query query2 = entity.createQuery(
					"SELECT m FROM Matches m WHERE (m.ad1.id = :idAd1 OR m.ad1.id = :idAd2 OR m.ad2.id = :idAd1 OR m.ad2.id = :idAd2) AND (m.statusMatchTxt.id = 1 OR m.statusMatchTxt.id = 2)",
					Matches.class);
			query2.setParameter("idAd1", matchToCompleted.getAd1().getId());
			query2.setParameter("idAd2", matchToCompleted.getAd2().getId());
			List<Matches> matches2 = (List<Matches>) query2.getResultList();
			for (int i = 0; i < matches2.size(); i++) {
				matches2.get(i).setStatusMatchTxt(statusMatchTxt);
				matches2.get(i).setDateEnd(dateEnd);
			}

			// Modifica el match a completado
			statusMatchTxt = entity.find(StatusMatchTxt.class, 3);
			matchToCompleted.setStatusMatchTxt(statusMatchTxt);
			matchToCompleted.setDateEnd(dateEnd);

			ok = true;
		}
		entity.getTransaction().commit();
		return ok;
	}

	/**
	 * Convierte una propuesta en proceso a cancelado.
	 *
	 * @param idMatchToCancelled The id of the match.
	 * @return A boolean if the process is done right.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public boolean matchToCancelled(int idMatchToCancelled) throws SQLException {
		boolean ok = false;
		entity.getTransaction().begin();
		Query query = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.id = :idMatchToCancelled AND (m.statusMatchTxt.id = 1 OR m.statusMatchTxt.id = 2)",
				Matches.class);
		query.setParameter("idMatchToCancelled", idMatchToCancelled);
		List<Matches> matches = (List<Matches>) query.getResultList();

		if (matches.size() > 0) {
			Matches matchToCompleted = matches.get(0);
			StatusMatchTxt statusMatchTxt = entity.find(StatusMatchTxt.class, 4);
			Date date = new Date();

			// Modifica el match a cancelled y la fecha
			matchToCompleted.setStatusMatchTxt(statusMatchTxt);
			matchToCompleted.setDateEnd(date);
			ok = true;
		}
		entity.getTransaction().commit();
		return ok;
	}

	/**
	 * Convierte una propuesta en proceso a cancelado.
	 *
	 * @param idMatchToCancelled The id of the match.
	 * @return A boolean if the process is done right.
	 * @throws SQLException If somthing fails with the DB.
	 */
	public boolean matchToPending(int idMatchToPending, int idAdToPending) throws SQLException {
		boolean ok = false;
		entity.getTransaction().begin();
		// Primero comprueba que sigue activa la propuesta
		Query query1 = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.id = :idMatchToPending AND m.statusMatchTxt.id = 1", Matches.class);
		query1.setParameter("idMatchToPending", idMatchToPending);
		List<Matches> matches = (List<Matches>) query1.getResultList();
		if (matches.size() > 0) {
			// Segundo comprueba que el anuncio siga disponible
			Query query2 = entity.createQuery(
					"SELECT a FROM Ads a WHERE a.id = :idAdToPending AND a.statusPostTxt.id = 1", Ads.class);
			query2.setParameter("idAdToPending", idAdToPending);
			List<Ads> ads = (List<Ads>) query2.getResultList();
			if (ads.size() > 0) {
				StatusMatchTxt statusMatchTxt = entity.find(StatusMatchTxt.class, 2);
				Ads ad2 = entity.find(Ads.class, idAdToPending);
				Date datePreEnd = new Date();

				// Modifica el match a pending y setea el anuncio 2
				matches.get(0).setStatusMatchTxt(statusMatchTxt);
				matches.get(0).setAd2(ad2);
				matches.get(0).setDatePreEnd(datePreEnd);

				ok = true;
			}
		}
		entity.getTransaction().commit();
		return ok;
	}

//OK
	public List<Matches> getMatchesRelationatedOne(int idAd) throws SQLException {
		entity.getTransaction().begin();

		Query query = entity.createQuery("SELECT m FROM Matches m WHERE m.ad1.id = :idAd AND m.statusMatchTxt.id = 1",
				Matches.class);
		query.setParameter("idAd", idAd);

		List<Matches> matches = (List<Matches>) query.getResultList();
		entity.getTransaction().commit();
		return matches;
	}

	// OK
	public List<Matches> getMatchesRelationatedTwoMe(int idAd) throws SQLException {
		entity.getTransaction().begin();

		Query query = entity.createQuery("SELECT m FROM Matches m WHERE m.ad2.id = :idAd AND m.statusMatchTxt.id = 2",
				Matches.class);
		query.setParameter("idAd", idAd);

		List<Matches> matches = (List<Matches>) query.getResultList();
		entity.getTransaction().commit();
		return matches;
	}

	// OK
	public List<Matches> getMatchesRelationatedTwoOther(int idAd) throws SQLException {
		entity.getTransaction().begin();

		Query query = entity.createQuery("SELECT m FROM Matches m WHERE m.ad1.id = :idAd AND m.statusMatchTxt.id = 2",
				Matches.class);
		query.setParameter("idAd", idAd);

		List<Matches> matches = (List<Matches>) query.getResultList();
		entity.getTransaction().commit();
		return matches;
	}

//OK
	public Matches getMatchRelationatedOne(int idAd, int idUser) throws SQLException {
		entity.getTransaction().begin();

		Query query = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.ad1.id = :idAd AND m.usr1.id = :idUser AND m.statusMatchTxt.id = 1",
				Matches.class);
		query.setParameter("idAd", idAd);
		query.setParameter("idUser", idUser);
		List<Matches> matches = (List<Matches>) query.getResultList();

		entity.getTransaction().commit();
		if (matches.size() > 0)
			return matches.get(0);
		else
			return null;
	}

	// OK
	public Matches getMatchRelationatedTwoMe(int idAd, int idUser) throws SQLException {
		entity.getTransaction().begin();

		Query query = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.ad1.id = :idAd AND m.usr1.id = :idUser AND m.statusMatchTxt.id = 2",
				Matches.class);
		query.setParameter("idAd", idAd);
		query.setParameter("idUser", idUser);
		List<Matches> matches = (List<Matches>) query.getResultList();

		entity.getTransaction().commit();
		if (matches.size() > 0)
			return matches.get(0);
		else
			return null;
	}

	// OK
	public Matches getMatchRelationatedTwoOther(int idAd, int idUser) throws SQLException {
		entity.getTransaction().begin();

		Query query = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.ad2.id = :idAd AND m.usr2.id = :idUser AND m.statusMatchTxt.id = 2",
				Matches.class);
		query.setParameter("idAd", idAd);
		query.setParameter("idUser", idUser);
		List<Matches> matches = (List<Matches>) query.getResultList();

		entity.getTransaction().commit();
		if (matches.size() > 0)
			return matches.get(0);
		else
			return null;
	}

	// OK
	public int matchCheck(int idMatch, int idUser) {
		int caso = 0;
		entity.getTransaction().begin();
		Users me = entity.find(Users.class, idUser);
		// Estado propuesta y el usuario actual es el propietario del anuncio por el que
		// se creó

		Query query = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.usr2.id = :idUser AND m.id = :idMatch AND m.statusMatchTxt.id = 1",
				Matches.class);
		query.setParameter("idUser", idUser);
		query.setParameter("idMatch", idMatch);
		List<Ads> ads1 = (List<Ads>) query.getResultList();

		// Estado aceptado, y el usuario actual es quien ha iniciado la oferta
		Query query2 = entity.createQuery(
				"SELECT m FROM Matches m WHERE m.usr1.id = :idUser AND m.id = :idMatch AND m.statusMatchTxt.id = 2",
				Matches.class);
		query2.setParameter("idUser", idUser);
		query2.setParameter("idMatch", idMatch);
		List<Ads> ads2 = (List<Ads>) query2.getResultList();

		Query query3 = entity.createQuery(
				"SELECT m FROM Matches m WHERE ((m.usr1.id = :idUser AND m.statusMatchTxt.id = 1) OR (m.usr2.id = :idUser AND m.statusMatchTxt.id = 2)) AND m.id = :idMatch",
				Matches.class);
		query3.setParameter("idUser", idUser);
		query3.setParameter("idMatch", idMatch);
		List<Ads> ads3 = (List<Ads>) query3.getResultList();

		if (ads1.size() > 0)
			caso = 1;
		else if (ads2.size() > 0) {
			caso = 2;
		} else if (ads3.size() > 0) {
			caso = 3;
		} else
			caso = 4;
		entity.getTransaction().commit();
		return caso;
	}

}