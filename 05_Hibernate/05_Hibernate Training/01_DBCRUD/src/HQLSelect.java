import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import emppackage.Employee;


public class HQLSelect {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();

		System.out.println("Printing HBM Select");
		String queryString = "select emp.id, emp.name from Employee as emp";
		Query query = session.createQuery(queryString);
		List<Employee> employeeList = query.list();
		Iterator itr = employeeList.iterator();
		while (itr.hasNext()) {
			Object[] object = (Object[])itr.next();
			for (int ctr = 0; ctr < object.length; ctr++) {
				System.out.println(object[ctr]);
			}
		}

		session.close();
	}
}
