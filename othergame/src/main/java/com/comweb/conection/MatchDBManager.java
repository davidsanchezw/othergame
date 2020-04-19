package com.comweb.conection;

import java.sql.SQLException;

import javax.persistence.EntityManager;

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

}