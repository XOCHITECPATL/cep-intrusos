package ar.gov.anses.seginf.intrusos.persistence;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ar.gov.anses.seginf.intrusos.convert.SyslogRawMessage;

/**
 * Objeto encargado de realizar las funcionalidades basicas para loggear los
 * mensajes que llegan al JBoss Rules
 * 
 * Es un Singleton, para poder ser utilizada la misma instancia desde cualquier
 * lado
 * 
 * @author aparedes
 * 
 */
public class Repository {

	private static Repository instance;

	public static Repository getInstance() {
		if (instance == null)
			instance = new Repository();
		return instance;
	}

	/*
	 * 
	 * OBJECT METHODS
	 */

	public SessionFactory sessionFactory;

	public Repository() {
		this.setUp();
	}

	private void setUp() {
		Configuration configuration = new Configuration().configure();

		configuration.addAnnotatedClass(SyslogRawMessage.class);

		this.sessionFactory = configuration.buildSessionFactory();
	}

	public void save(Object storable) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(storable);
			tx.commit();
			session.flush();
			session.close();
			System.out.println(storable);
		} catch (HibernateException e) {
			((SQLException) e.getCause()).getNextException().printStackTrace();
		}
	}

}
