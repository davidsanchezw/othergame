package com.comweb.conection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBManager implements AutoCloseable {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	private EntityManagerFactory factory;

	public EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}

	public void close() {
		if (factory != null) {
			factory.close();
		}
	}
}
