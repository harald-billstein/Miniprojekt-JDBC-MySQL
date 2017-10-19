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

  public boolean create(T object) {
    boolean success = false;
    Session session = hibernateSessionManager.getSession();
    session.beginTransaction();

    try {
      session.save(object);
      session.getTransaction().commit();
      success = true;
    } finally {
      session.close();
    }
    return success;
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

  public boolean delete(int id) {
    boolean success = false;
    Session session = hibernateSessionManager.getSession();
    session.beginTransaction();

    try {
      T object = session.get(clazz, id);
      session.delete(object);
      session.getTransaction().commit();
      success = true;
    } finally {
      session.close();
    }
    return success;
  }

  public boolean update(T object) {
    boolean success = false;
    Session session = hibernateSessionManager.getSession();

    try {
      session.beginTransaction();
      session.save(object);
      session.getTransaction().commit();
      success = true;
    } finally {
      session.close();
    }
    return success;
  }

  public Session getSession() {
    return hibernateSessionManager.getSession();
  }
}
