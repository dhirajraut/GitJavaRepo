import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import emppackage.Employee;


public class HQLParameterisedRead {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();

		System.out.println("Printing Parameterised Select");
		String queryString = "from Employee as emp where emp.salary = :salary" +
				" and emp.address.city = :city";
		Query query = session.createQuery(queryString);
		query.setInteger("salary", 1);
		query.setString("city", "City1");
		List<Employee> employeeList = query.list();
		Util.printEmployees(employeeList);

		session.close();
	}
}
