import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import deptpackage.Department;

import emppackage.Address;
import emppackage.Employee;


public class Util {

	public static Session configureHibernate() {
		Configuration configuration = new Configuration ();
		configuration.configure(); // Automatically checks cfg file.
	
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}
	
	public static void printEmployees(List employeeList) {
		Iterator<Employee> itr = employeeList.iterator();
		while (itr.hasNext()) {
			Employee employee = (Employee) itr.next();
			System.out.println(employee);
		}
	}
	
	public static Address createMockAddress(int deptNo, int empNo) {
		Address address = new Address();
		address.setStreet("Street" + deptNo + "." + empNo);
		address.setCity("City" + deptNo + "." + empNo);
		address.setPinCode("PinCode" + deptNo + "." + empNo);
		return address;
	}
	
	public static Employee createMockEmployee(int deptNo, int empNo){
		Employee employee = new Employee();
		employee.setName("Employee" + deptNo + "." + empNo);
		employee.setSalary(deptNo);
		employee.setAddress(createMockAddress(deptNo, empNo));
		return employee;
	}
	
	public static Department createMockDepartment(int deptNo){
		Department department = new Department();
		department.setId(deptNo);
		department.setName("Department" + deptNo);
		department.setLocation("Location" + deptNo);
		return department;
	}
}
