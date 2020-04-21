package com.comweb.conection;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.comweb.model.Matches;
import com.comweb.model.StatusMatchTxt;

public class MatchDBManager {

	private EntityManager entity;

	public MatchDBManager(DBManager db) {
		entity = db.getEntityManagerFactory().createEntityManager();
	}

	public int createMatch(Matches match) throws SQLException {
		entity.getTransaction().begin();
		entity.persist(match);
		entity.getTransaction().commit();
		return match.getId();
	}

	// TO DO:
	// https://www.youtube.com/watch?v=L6mGNM_zyaQ&list=PLTd5ehIj0goPcnQs34i0F-Kgp5JHX8UUv&index=7

	/**
	 * Get a match from BD.
	 *
	 * @param id The id of the match.
	 * @return The Ad object, or null if not found.
	 * @throws SQLException If somthing fails with the DB.
	 */

	public Matches getMatch(int id) throws SQLException {

		return entity.find(Matches.class, id);
	}

	public StatusMatchTxt getstatusMatchTxt(int id) {
		return entity.find(StatusMatchTxt.class, id);
	}

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

}