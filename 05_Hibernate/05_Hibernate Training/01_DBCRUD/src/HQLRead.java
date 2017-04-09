import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import emppackage.Employee;


public class HQLRead {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();

		System.out.println("Printing Simple Select");
		String queryString = "from Employee as emp where emp.salary > 2";
		Query query = session.createQuery(queryString);
		List<Employee> employeeList = query.list();
		Util.printEmployees(employeeList);

		System.out.println("Printing Complex Select");
		query = session.createQuery("from Employee as emp where emp.address.city = 'City1'");
		employeeList = query.list();
		Util.printEmployees(employeeList);

		session.close();
	}
}
