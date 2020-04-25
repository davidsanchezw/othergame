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
		entity.getTransaction().begin();
		entity.persist(match);
		entity.getTransaction().commit();
		return match.getId();
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
		Matches matchToCompleted = entity.find(Matches.class, idMatchToCompleted);
		StatusMatchTxt statusMatchTxt = entity.find(StatusMatchTxt.class, 3);
		Date date = new Date();

		// Modifica el match a completado
		matchToCompleted.setStatusMatchTxt(statusMatchTxt);
		matchToCompleted.setDateEnd(date);

		// Modifica los anuncios a completados
		StatusPostTxt statusPostTxt = entity.find(StatusPostTxt.class, 3);
		matchToCompleted.getAd1().setStatusPostTxt(statusPostTxt);
		matchToCompleted.getAd2().setStatusPostTxt(statusPostTxt);

		ok = true;
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
		Matches matchToCompleted = entity.find(Matches.class, idMatchToCancelled);
		StatusMatchTxt statusMatchTxt = entity.find(StatusMatchTxt.class, 4);
		Date date = new Date();

		// Modifica el match a cancelled
		matchToCompleted.setStatusMatchTxt(statusMatchTxt);
		matchToCompleted.setDateEnd(date);

		ok = true;
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
		Matches matchToCompleted = entity.find(Matches.class, idMatchToPending);
		StatusMatchTxt statusMatchTxt = entity.find(StatusMatchTxt.class, 2);
		Ads ad2 = entity.find(Ads.class, idAdToPending);
		Date datePreEnd = new Date();

		// Modifica el match a cancelled
		matchToCompleted.setStatusMatchTxt(statusMatchTxt);
		matchToCompleted.setAd2(ad2);
		matchToCompleted.setDatePreEnd(datePreEnd);

		ok = true;
		entity.getTransaction().commit();
		return ok;
	}

}