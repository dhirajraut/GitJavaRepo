import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {

	public static Session configureHibernate() {
		Configuration configuration = new Configuration ();
		configuration.configure(); // Automatically checks cfg file.
	
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}
	

}
