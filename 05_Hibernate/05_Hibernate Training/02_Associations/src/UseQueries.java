import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import emppackage.Employee;


public class UseQueries {
	public static void main(String args[]) {
		Session session = Util.configureHibernate();
		
		/* Provide Sum of Salaries */
//		String queryString = "select sum(e.salary) " +
	//			"from Employee e group by e.department";
		
		/* Provide count of employees */
		String queryString = "select e.department.id, count(e.id) " +
				"from Employee e group by e.department";
		
		Query query = session.createQuery(queryString);
		List<Employee> employeeList = (List)query.list();
		//Util.printEmployees(employeeList);
		
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
