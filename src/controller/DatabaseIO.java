package controller;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

/**
 * Takes a POJO and handles C.R.U.D.
 *
 * @param <T> Class mirroring the database entity.
 * @author Harald & Cristoffer
 */
public abstract class DatabaseIO<T> {

  private HibernateSessionManager hibernateSessionManager;
  private Class<T> clazz;

  DatabaseIO(HibernateSessionManager hibernateSessionManager, Class<T> clazz) {
    this.hibernateSessionManager = hibernateSessionManager;
    this.clazz = clazz;
  }

  /**
   * Creates a tuple of type T
   * @param object Object of type T to save
   * @return True if object is successfully saved.
   */
  public boolean create(T object) {
    boolean success;
    Session session = hibernateSessionManager.getSession();
    session.beginTransaction();

    try {
      session.save(object);
      session.getTransaction().commit();
      success = true;
    } catch (Exception e) {
      e.printStackTrace();
      success = false;
    } finally {
      session.close();
    }
    return success;
  }

  /**
   * Reads object T
   * @return List of objects of type T
   */
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

  /**
   * Deletes an object of type T
   * @param id Primary key of object to delete
   * @return True if delete is successfull
   */
  public boolean delete(int id) {
    boolean success;
    Session session = hibernateSessionManager.getSession();
    session.beginTransaction();

    try {
      T object = session.get(clazz, id);
      session.delete(object);
      session.getTransaction().commit();
      success = true;
    } catch (Exception e) {
      e.printStackTrace();
      success = false;
    } finally {
      session.close();
    }
    return success;
  }

  /**
   * Updates an object of type T
   * @param object Object T to update
   * @return True if update is successfull
   */
  public boolean update(T object) {
    boolean success;

    try (Session session = hibernateSessionManager.getSession()) {
      session.beginTransaction();
      session.save(object);
      session.getTransaction().commit();
      success = true;
    } catch (Exception e) {
      e.printStackTrace();
      success = false;
    }
    return success;
  }

  public Session getSession() {
    return hibernateSessionManager.getSession();
  }
}
