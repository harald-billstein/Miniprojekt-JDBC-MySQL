package controller;

import model.Department;

public class DepartmentIO extends DatabaseIO<Department> {

	public DepartmentIO(HibernateSessionManager hibernateSessionManager) {
		super(hibernateSessionManager, Department.class);
	}
}
