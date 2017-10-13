package controller;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

	private void createConfiguration() throws ServiceException {
		this.configuration = new Configuration().configure("hibernate.cfg.xml");
	}

	private void addAnnotatedClasses() {
		for (Class<?> clazz : clazzes) {
			configuration.addAnnotatedClass(clazz);
		}
	}

	private void buildFactory() {
		this.factory = configuration.buildSessionFactory();
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	public void close() {
		if (factory.isClosed()) {
			factory.close();
		}
	}
}
