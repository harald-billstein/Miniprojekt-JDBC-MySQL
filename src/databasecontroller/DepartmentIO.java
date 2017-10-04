package databasecontroller;

import java.util.List;

import org.hibernate.Session;

import databasemodel.Department;

public class DepartmentIO extends DatabaseIO {
	
	public DepartmentIO(HibernateSessionManager hibernateSessionManager) {
		super(hibernateSessionManager);
	}
	
	public List<Department> getDepartmentEmployeeList(Integer departmentId) {
		Session session = getSession();
		
		List<Department> departments;
		try {
			session.beginTransaction();
			departments = session.createQuery("FROM employee WHERE department.department_id = '" + departmentId  + "'").list();

			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return departments;
	}
}
