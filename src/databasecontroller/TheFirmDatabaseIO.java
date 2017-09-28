package databasecontroller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import databasemodel.CompanyCar;
import databasemodel.Department;
import databasemodel.Employee;

public class TheFirmDatabaseIO<T> {

	private SessionFactory factory;
	private Session session;
	private Class<?> clazz;
	private Object object;
	private String tableName;

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public TheFirmDatabaseIO() {

	}

	public TheFirmDatabaseIO(Class<T> clazz) {
		this.clazz = clazz;
	}

	public TheFirmDatabaseIO(Class<T> clazz, Object object) {
		this.clazz = clazz;
		this.object = object;
	}

	public TheFirmDatabaseIO(Class<T> clazz, String tableName) {
		this.clazz = clazz;
		this.tableName = tableName;
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
	}

	public void save(Object object) {
		createFactory();
		getSession();
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			System.out.println("Succsses!");
		} finally {
			factory.close();
		}
	}

	public List<?> retrive(String tableName) {
		createFactory();
		getSession();

		List<?> theObjects;
		try {
			session.beginTransaction();
			theObjects = session.createQuery("from " + tableName).list();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return theObjects;
	}
	
	public List<?> retriveDepartmentEmployeeList(Integer departmentId) {
		createFactory();
		getSession();

		List<?> theObjects;
		try {
			session.beginTransaction();
			theObjects = session.createQuery("from employee WHERE department.department_id = '" + departmentId  + "'").list();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return theObjects;
	}

	public void delete(Integer id) {
		createFactory();
		getSession();
		session.beginTransaction();
		Employee employee = session.get(Employee.class, id);
		session.delete(employee);
		session.getTransaction().commit();
		session.close();
	}

	public List<Employee> seachEmployeeName(String name) {
		createFactory();
		getSession();
		List<Employee> employee;

		try {
			session.beginTransaction();
			employee = session
					.createQuery("from employee e where e.fname LIKE '%" + name + "%' OR e.lname LIKE '%" + name + "%'")
					.list();
		} finally {
			factory.close();
		}
		return employee;
	}

	public String getDatabaseInfo() {
		createFactory();
		getSession();
		session.beginTransaction();
		Map<String, Object> properties = factory.getProperties();
		return "Connected as: " + properties.get("hibernate.connection.username") + " at: "
				+ properties.get("hibernate.connection.url");
	}

	public void update(Integer id, Integer salary) {
		createFactory();
		getSession();
		session.beginTransaction();
		Employee employee = session.get(Employee.class, id);
		employee.setSalary(salary);
		session.getTransaction().commit();
		session.close();
	}
}
