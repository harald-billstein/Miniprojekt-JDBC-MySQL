package controller;

import model.Department;

/**
 * Class handling input and output from database.
 * 
 * @author Harald & Cristoffer
 */
public class DepartmentIO extends DatabaseIO<Department> {

  /**
   * Constructor setting an Object handling the connection to the database
   * 
   * @param hibernateSessionManager
   */
  public DepartmentIO(HibernateSessionManager hibernateSessionManager) {
    super(hibernateSessionManager, Department.class);
  }
}
