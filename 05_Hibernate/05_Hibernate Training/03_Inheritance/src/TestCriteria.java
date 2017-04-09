import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import deptpackage.Department;
import emppackage.Employee;


public class TestCriteria {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();
		Criteria cr = session.createCriteria(Employee.class);
//		cr.add(Expression.eq("name", "ManagerName"));
//		cr.add(Expression.like("name", "ManagerName", MatchMode.ANYWHERE));
		cr.add(Expression.eq("salary", new Integer(5000)));
		List employeeList = cr.list();
		System.out.println(employeeList.size());
		Util.printEmployees(employeeList);
	}
}
