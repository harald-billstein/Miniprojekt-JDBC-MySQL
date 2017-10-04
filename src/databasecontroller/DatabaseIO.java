package databasecontroller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;

public abstract class DatabaseIO {
	
	private HibernateSessionManager hibernateSessionManager;
	
	public DatabaseIO(HibernateSessionManager hibernateSessionManager) {
		this.hibernateSessionManager = hibernateSessionManager;
	}

	public String getDatabaseInfo(Session session) {
		Object object;
		session.beginTransaction();
		try {
			Query<?> query = session
					.createNativeQuery("select TIME_FORMAT(SEC_TO_TIME(VARIABLE_VALUE ),'%Hh %im')  as Uptime "
							+ "from information_schema.GLOBAL_STATUS " + "where VARIABLE_NAME='Uptime'");
			object = query.uniqueResult();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return "uptime: " + object.toString();
	}

	public void create(Object object) {
		Session session = hibernateSessionManager.getSession();
		session.beginTransaction();

		session.save(object);
		session.getTransaction().commit();
		System.out.println("Succsses!");
		session.close();
	}

	public <T> List<T> read(Session session, Class<T> clazz) {
		session.beginTransaction();

		List<T> objects;

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		criteria.from(clazz);
		objects = session.createQuery(criteria).getResultList();
		session.close();
		return objects;
	}
	
	public <T> void delete(Class<T> clazz, int id) {
		Session session = hibernateSessionManager.getSession();
		session.beginTransaction();
		try {
			Object object = session.get(clazz, id);
			session.delete(object);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	public void update() {

	}

	public Session getSession() {
		return this.hibernateSessionManager.getSession();
	}
}
