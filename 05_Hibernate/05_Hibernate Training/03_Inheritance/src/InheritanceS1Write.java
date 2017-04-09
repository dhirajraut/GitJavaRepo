import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import deptpackage.Department;
import emppackage.Address;
import emppackage.Clerk;
import emppackage.Employee;
import emppackage.Manager;


public class InheritanceS1Write {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();

		/**
		 * Prerequisite Queries:
		 * create table DEPT_INHERITANCE
		 * 		(ID char(6) not null, NAME varchar (20), LOCATION varchar (20));
		 * create table EMPLOYEE_INHERITANCE
		 * 		(ID char(6) not null, NAME varchar (20), SALARY numeric(6, 2),
		 * 		STREET varchar(20), CITY varchar(20), PIN varchar(20));
		 */
		
		Department department = getMockDepartment();
		
		Manager manager = new Manager();
		manager.setName("ManagerName");
		manager.setSalary(5000);
		manager.setPerks(1000.00);
		manager.setAddress(getMockAddress());
		manager.setDepartment(department);

		Clerk clerk = new Clerk();
		clerk.setName("ClerkName");
		clerk.setSalary(1000);
		clerk.setOvertime(100.00);
		clerk.setAddress(getMockAddress());
		clerk.setDepartment(department);
		
		List<Employee> list = new ArrayList<Employee>();
		list.add(manager);
		list.add(clerk);
		department.setEmployees(list);

		/* Insert Department. */
		Transaction transaction = session.beginTransaction(); // Mandatory in hibernate.
		session.save(department);
		transaction.commit();
		session.close();
	}
	
	private static Department getMockDepartment(){
		Department department = new Department();
		department.setName("Department");
		department.setLocation("Mumbai");
		return department;
	}
	private static Address getMockAddress(){
		Address address = new Address();
		address.setCity("Mumbai");
		address.setPinCode("400092");
		address.setStreet("ABC Street");
		return address;
	}
}
