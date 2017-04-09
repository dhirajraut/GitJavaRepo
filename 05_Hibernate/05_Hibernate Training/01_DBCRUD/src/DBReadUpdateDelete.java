import org.hibernate.Session;
import org.hibernate.Transaction;

import emppackage.Employee;


public class DBReadUpdateDelete {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();

		Employee employee = (Employee) session.load(Employee.class, 1); // Read DB
		System.out.println(employee);
		
		employee.setSalary(1);
		Transaction transaction = session.beginTransaction(); // Mandatory in hibernate.
		session.saveOrUpdate(employee); // Update DB
		
		session.delete(employee); // Delete DB
		
		transaction.commit();
		session.close();
	}
	
}
