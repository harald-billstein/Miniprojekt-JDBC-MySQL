package controller;

import java.util.List;
import org.hibernate.Session;
import model.Employee;

/**
 * Class handling input and output from database.
 *
 * @author Harald & Cristoffer
 */
public class EmployeeIO extends DatabaseIO<Employee> {

  /**
   * Constructor setting an Object handling the connection to the database
   */
  public EmployeeIO(HibernateSessionManager hibernateSessionManager) {
    super(hibernateSessionManager, Employee.class);
  }

  /**
   * Search two columns (fname & lname) in the database for matches.
   *
   * @param name search string.
   * @return a list of all matches.
   */
  public List<Employee> seachEmployeeName(String name) {
    Session session = getSession();
    session.beginTransaction();

    String hql = "from employee e where e.firstName LIKE :firstName OR e.lastName LIKE :lastName";
    List<Employee> employee;

    try {
      employee =
          session.createQuery(hql, Employee.class).setParameter("firstName", "%" + name + "%")
              .setParameter("lastName", "%" + name + "%").list();
    } finally {
      session.close();
    }
    return employee;
  }

  /**
   * Updates columns (fname, lname, department_id and salary).
   *
   * @param updatedEmployee Employee to update.
   * @return true if success.
   */
  public boolean updateEmployee(Employee updatedEmployee) {
    boolean success = false;
    Session session = getSession();
    session.beginTransaction();

    try {
      Employee employee = session.get(updatedEmployee.getClass(), updatedEmployee.getEmployeeId());

      if (updatedEmployee.getFirstName() != null) {
        employee.setFirstName(updatedEmployee.getFirstName());
      }

      if (updatedEmployee.getLastName() != null) {
        employee.setLastName(updatedEmployee.getLastName());
      }

      if (updatedEmployee.getDepartmentId() != null) {
        employee.setDepartmentId(updatedEmployee.getDepartmentId());
      }

      if (updatedEmployee.getSalary() != null) {
        employee.setSalary(updatedEmployee.getSalary());
      }
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

}
