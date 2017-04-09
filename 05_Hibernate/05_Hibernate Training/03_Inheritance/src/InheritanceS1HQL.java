

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import deptpackage.Department;
import emppackage.Address;
import emppackage.Manager;


public class InheritanceS1HQL {
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
		Query qry= session.createQuery("from Manager");
		/* Scrollable can be used*/
		
		/*ScrollableResults scr = qry.scroll();
		do{
			scr.next();
			System.out.println(((Manager)scr.get()[0]).getPerks());
		}while(scr.isLast());*/
		
		/* Or as below 
		ScrollableResults scr = qry.scroll();
		while(scr.next()){
			Manager mgr= (Manager)scr.get(0);
			System.out.println(mgr.getSalary());
		}*/

		Iterator<Manager> itr = qry.list().iterator();
		while(itr.hasNext()){
			Manager obj= (Manager)itr.next();
			System.out.println(obj.getName());
			System.out.println(obj.getName());
		}
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
