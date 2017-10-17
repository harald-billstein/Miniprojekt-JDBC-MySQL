package controller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

public abstract class DatabaseIO<T> {
	private HibernateSessionManager hibernateSessionManager;
	private Class<T> clazz;

	public DatabaseIO(HibernateSessionManager hibernateSessionManager, Class<T> clazz) {
		this.hibernateSessionManager = hibernateSessionManager;
		this.clazz = clazz;
	}

	public void create(T object) {
		Session session = hibernateSessionManager.getSession();
		session.beginTransaction();
		
		try {
			session.save(object);
			session.getTransaction().commit();
			System.out.println("Succsses!");			
		} finally {
			session.close();
		}
	}

	public List<T> read() {
		Session session = hibernateSessionManager.getSession();
		session.beginTransaction();

		List<T> objects;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(clazz);
			criteria.from(clazz);
			objects = session.createQuery(criteria).getResultList();
		} finally {
			session.close();
		}
		return objects;
	}

	public void delete(int id) {
		Session session = hibernateSessionManager.getSession();
		session.beginTransaction();
		
		try {
			T object = session.get(clazz, id);
			session.delete(object);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	public void update(T object) {
		Session session = hibernateSessionManager.getSession();
		
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	public Session getSession() {
		return hibernateSessionManager.getSession();
	}
}