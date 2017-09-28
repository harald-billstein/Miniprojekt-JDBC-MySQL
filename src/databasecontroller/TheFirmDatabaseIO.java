package databasecontroller;

import java.util.List;
import java.util.Map;
import java.util.Objects;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToStdout;

import databasemodel.CompanyCar;
import databasemodel.DatabaseInfo;
import databasemodel.Department;
import databasemodel.Employee;

public class TheFirmDatabaseIO<T> {

	private SessionFactory factory;
	private Session session;

	public TheFirmDatabaseIO() {
		createFactory();
	}

	public void createFactory() {
			factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Employee.class)
					.addAnnotatedClass(Department.class)
					.addAnnotatedClass(CompanyCar.class)
					.buildSessionFactory();
	}

	public void getSession() {
		session = factory.getCurrentSession();
		session.beginTransaction();
	}

	public void save(Object object) {
		getSession();
		
		try {
			session.save(object);
			session.getTransaction().commit();
			System.out.println("Succsses!");
		} finally {
			session.close();
		}
	}

	public List<?> retrive(String tableName) {
		getSession();
		List<?> theObjects;
		try {
			theObjects = session.createQuery("from " + tableName).list();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return theObjects;
	}
	
	public List<?> retriveDepartmentEmployeeList(Integer departmentId) {
		getSession();
		List<?> theObjects;
		try {
			theObjects = session.createQuery("from employee WHERE department.department_id = '" + departmentId  + "'").list();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return theObjects;
	}

	public void delete(Class<?> clazz, Integer id) {
		getSession();
		try {
			Object object = session.get(clazz, id);
			session.delete(object);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	public List<?> seachEmployeeName(String name) {
		getSession();
		
		List<?> employee;
		try {
			employee = session
					.createQuery("from employee e where e.fname LIKE '%" + name + "%' OR e.lname LIKE '%" + name + "%'")
					.list();
		} finally {
			session.close();
		}
		return employee;
	}

	public String getDatabaseInfo() {		
		getSession();
		Object object;
		
		try {
			Query<?> query = session.createNativeQuery("select TIME_FORMAT(SEC_TO_TIME(VARIABLE_VALUE ),'%Hh %im')  as Uptime " + 
					"from information_schema.GLOBAL_STATUS " +
					"where VARIABLE_NAME='Uptime'");
			object = query.uniqueResult();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return "uptime: " + object.toString();
	}

	public void update(Integer id, Integer salary) {
		getSession();
		
		try {
			Employee employee = session.get(Employee.class, id);
			employee.setSalary(salary);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
	
	public void close() {
		session.close();
		factory.close();
	}
}
