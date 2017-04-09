import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import deptpackage.Department;
import emppackage.Employee;


public class AssociationsWrite {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();

		/**
		 * create table DEPT2 (ID char(6) not null, NAME varchar (20), LOCATION varchar (20));
		 * create table EMPLOYEE_ASSOCIATION2 (ID char(6) not null, DEPARTMENT char(6) not null, NAME varchar (20), SALARY varchar (20),
			STREET varchar(20), CITY varchar(20), PIN varchar(20));
		 */
		Department department = new Department();
		/* Create Mock Data. */
		for (int deptNo = 1; deptNo < 3; deptNo ++) {
			department = Util.createMockDepartment(deptNo);
	
			List<Employee> employeeList = new ArrayList<Employee>();
			for (int empNo = 1; empNo < 4; empNo ++) {
		
				Employee employee = Util.createMockEmployee(deptNo, empNo);
						//Internally creating Mock Address Object.
				employee.setDepartment(department);
				employeeList.add(employee);
			}
			department.setEmployees(employeeList);

			/* Insert Department. */
			Transaction transaction = session.beginTransaction(); // Mandatory in hibernate.
			session.save(department);
			transaction.commit();
		}
		session.close();
	}
	
}
