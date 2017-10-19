package controller;

import model.CompanyCar;

/**
 * Class handling input and output from database.
 * 
 * @author Harald & Cristoffer
 */
public class CompanyCarIO extends DatabaseIO<CompanyCar> {

  /**
   * Constructor setting an Object handling the connection to the database
   * 
   * @param hibernateSessionManager
   */
  public CompanyCarIO(HibernateSessionManager hibernateSessionManager) {
    super(hibernateSessionManager, CompanyCar.class);
  }
}
