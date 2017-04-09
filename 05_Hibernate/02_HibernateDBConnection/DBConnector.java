import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.galaxy.hibernate.beans.Employees;

public class DBConnector {

	 public static void main(String args[]) {
		 
		 try {
			 Configuration cfg = new Configuration();
			 cfg = cfg.configure("/hibernate.cfg.xml");
			 SessionFactory sessionFactory = cfg.buildSessionFactory();
			 Session session = sessionFactory.openSession();
			  
			 Employees employees = new Employees();
			 session.load(employees, new Integer (1));
			 session.close();
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 
	      
	 }
}
