package controller;

import org.hibernate.Session;

/**
 * Class handling information output from database.
 * 
 * @author Harald & Cristoffer
 *
 */
public class DatabaseInfoIO {

  private HibernateSessionManager hibernateSessionManager;

  /**
   * Constructor setting an Object handling the connection to the database
   * 
   * @param hibernateSessionManager
   */
  public DatabaseInfoIO(HibernateSessionManager hibernateSessionManager) {
    this.hibernateSessionManager = hibernateSessionManager;
  }

  /**
   * Method accessing how long the database have been online.
   * 
   * @return a string of the current value.
   */
  public String getUptime() {
    Session session = hibernateSessionManager.getSession();
    session.beginTransaction();

    final String hql = "SELECT TIME_FORMAT(SEC_TO_TIME(VARIABLE_VALUE ),'%Hh %im')  as Uptime "
        + "FROM information_schema.GLOBAL_STATUS WHERE VARIABLE_NAME='Uptime'";

    Object object;
    try {
      object = session.createNativeQuery(hql).uniqueResult();
      session.getTransaction().commit();
    } finally {
      session.close();
    }
    return "uptime: " + object.toString();
  }

}
