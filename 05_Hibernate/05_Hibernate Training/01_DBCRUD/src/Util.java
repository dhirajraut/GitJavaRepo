import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import emppackage.Employee;


public class Util {

	public static Session configureHibernate() {
		Configuration configuration = new Configuration ();
		configuration.configure(); // Automatically checks cfg file.
	
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}
	
	public static void printEmployees(List employeeList) {
		Iterator<Employee> itr = employeeList.iterator();
		while (itr.hasNext()) {
			Employee employee = (Employee) itr.next();
			System.out.println(employee);
		}
	}
}
