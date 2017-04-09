import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import deptpackage.Department;
import emppackage.Employee;


public class AssociationsHQL {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();
		
		/**
		 * create table DEPT2 (ID char(6) not null, NAME varchar (20), LOCATION varchar (20));
		 * create table EMPLOYEE_ASSOCIATION2 (ID char(6) not null, DEPARTMENT char(6) not null, NAME varchar (20), SALARY varchar (20),
			STREET varchar(20), CITY varchar(20), PIN varchar(20));
		 */
		Query query = session.createQuery("from Department");
		List<Department> departmentList = query.list();
		Iterator<Department> itr = departmentList.iterator();
		while (itr.hasNext()) {
			Department department = (Department)itr.next();
			System.out.println(department);
			Iterator<Employee> empItr = department.getEmployees().iterator();
			while (empItr.hasNext()) {
				Employee employee = (Employee)empItr.next();
				System.out.println(employee);
			}
		}
		session.close();
	}
	
}
