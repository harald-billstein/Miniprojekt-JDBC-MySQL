package databasecontroller;

import java.util.List;

import org.hibernate.Session;
import databasemodel.Employee;

public class EmployeeIO extends DatabaseIO {
	
	public EmployeeIO(HibernateSessionManager hibernateSessionManager) {
		super(hibernateSessionManager);
	}
	
	public List<Employee> getDepartmentEmployeeList(Integer departmentId) {
		Session session = getSession();
		
		List<Employee> employees;
		try {
			session.beginTransaction();
			employees = session.createQuery("FROM employee WHERE department.department_id = '" + departmentId  + "'").list();

			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return employees;
	}
	
	public List<Employee> seachEmployeeName(String name) {
		Session session = getSession();
		session.beginTransaction();
		
		List<Employee> employee;
		try {
			employee = session
					.createQuery("from employee e where e.fname LIKE '%" + name + "%' OR e.lname LIKE '%" + name + "%'")
					.list();
		} finally {
			session.close();
		}
		return employee;
	}

	public void updateEmployee(Employee updatedEmployee) {
		Session session = getSession();
		
		try {
			Employee employee = session.get(updatedEmployee.getClass(), updatedEmployee.getEmployee_id());

			if (updatedEmployee.getFname() != null) {
			employee.setFname(updatedEmployee.getFname());
			}
			
			if (updatedEmployee.getLname() != null) {
			employee.setLname(updatedEmployee.getLname());
			}
			
			if (updatedEmployee.getDepartment_id() != null) {
			employee.setDepartment_id(updatedEmployee.getDepartment_id());
			}
			
			if (updatedEmployee.getSalary() != null) {
			employee.setSalary(updatedEmployee.getSalary());
			}
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
}
