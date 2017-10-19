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
   * 
   * @param hibernateSessionManager
   */
  public EmployeeIO(HibernateSessionManager hibernateSessionManager) {
    super(hibernateSessionManager, Employee.class);
  }

  public List<Employee> getDepartmentEmployeeList(Integer departmentId) {
    Session session = getSession();
    session.beginTransaction();

    String hql = "FROM employee WHERE department.department_id = :departmentId ";
    List<Employee> employees;

    try {
      employees = session.createQuery(hql, Employee.class)
          .setParameter("departmentId", departmentId).list();
    } finally {
      session.close();
    }
    return employees;
  }

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
    } finally {
      session.close();
    }
    return success;
  }

}
