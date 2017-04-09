import org.hibernate.Session;
import org.hibernate.Transaction;

import emppackage.Address;
import emppackage.Employee;


public class DBWrite {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();
		
		/**
		 * Prerequisite Query:
		 * create table EMPLOYEE
		 * 		(ID char(6) not null, NAME varchar (20), SALARY numeric(6, 2),
		 * 		STREET varchar(20), CITY varchar(20), PIN varchar(20));
		 */
		
		/* Create Mock Data. */
		int recordNo = 1;

		Address address = new Address();
		address.setStreet("Street" + recordNo);
		address.setCity("City" + recordNo);
		address.setPinCode("PinCode" + recordNo);

		Employee employee = new Employee();
		employee.setName("Employee" + recordNo);
		employee.setSalary(recordNo);
		employee.setAddress(address);
		
		/* Insert Department. */
		Transaction transaction = session.beginTransaction(); // Mandatory in hibernate.
		session.save(employee);
		transaction.commit();
		session.close();
	}
	
}
