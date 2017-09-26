package databasecontroller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import databasemodel.Employee;



public class TheFirmDatabaseIO<T> {
	
	private SessionFactory factory;
	private Session session;
	private Class<T> clazz;
	private Object object;
	private String tableName;

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
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(clazz).buildSessionFactory();
	}

	public void getSession() {
		session = factory.getCurrentSession();
	}
	
	public List<T> retrive(String tableName) {
		createFactory();
		getSession();

		List<T> theObjects;
		try {
			session.beginTransaction();
			theObjects = session.createQuery("from " + tableName).list();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return theObjects;
	}
	
	public List<Employee> seachEmployeeName(String name) {
		createFactory();
		getSession();
		List<Employee> employee;
		
		try {	
			session.beginTransaction();
			employee = session.createQuery("   from employee e where e.fname='" + name +"' OR e.lname='" + name + "'").list();
		} finally {
			factory.close();
		}		
		return employee;
	}
	
	

}
