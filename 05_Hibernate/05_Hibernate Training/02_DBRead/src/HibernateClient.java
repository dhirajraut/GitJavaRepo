import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import deptpackage.Department;
import emppackage.Address;
import emppackage.Employee;


public class HibernateClient {
	public static void main (String args[]) {

		/* Common Steps. */
		Configuration configuration = new Configuration ();
		configuration.configure(); // Automatically checks cfg file.

		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		/* Create Mock Data. */
		int recordNo = 3;
		Department department = new Department();
		department.setId(recordNo);
		department.setName("Department" + recordNo);
		department.setLocation("Location2" + recordNo);

		Address address = new Address();
		address.setStreet("Street" + recordNo);
		address.setCity("City" + recordNo);
		address.setPinCode("PinCode" + recordNo);

		Employee employee = new Employee();
		employee.setName("Employee" + recordNo);
		employee.setSalary(recordNo);
		employee.setAddress(address);
		
		HashSet employeeSet = new HashSet();
		employeeSet.add(employee);
		department.setEmployees(employeeSet);
		
		/* Insert Department. */
		Transaction transaction = session.beginTransaction(); // Mandatory in hibernate.
		session.save(employee);
		transaction.commit();
		session.close();
		factory.close();
	}
	
}
