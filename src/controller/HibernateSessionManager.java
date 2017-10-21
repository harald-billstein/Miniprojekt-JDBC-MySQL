package controller;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Class handling the communication to the database.
 * 
 * @author Harald & Cristoffer
 */
public class HibernateSessionManager {

	private SessionFactory factory;
	private Configuration configuration;
	private List<Class<?>> clazzes;

	public HibernateSessionManager(List<Class<?>> clazzes) {
		this.clazzes = clazzes;
		createConfiguration();
		addAnnotatedClasses();
		buildFactory();
	}

	/**
	 * Configure the connection.
	 * 
	 * @throws ServiceException
	 */
	private void createConfiguration() throws ServiceException {
		this.configuration = new Configuration().configure("hibernate.cfg.xml");
	}

	/**
	 * Associate classes needed to retrieve the entity from the database to Hibernate.
	 */
	private void addAnnotatedClasses() {
		for (Class<?> clazz : clazzes) {
			configuration.addAnnotatedClass(clazz);
		}
	}

	/**
	 * Builds the Hibernate factory.
	 */
	private void buildFactory() {
		this.factory = configuration.buildSessionFactory();
	}

	/**
	 * Retrieves a session needed to communicate to the database.
	 * @return the session
	 */
	public Session getSession() {
		return factory.getCurrentSession();
	}

	/**
   * Shuts down the factory, when communication to the database is not needed anymore.
   */
	public void close() {
		if (factory.isClosed()) {
			factory.close();
		}
	}
	
}
