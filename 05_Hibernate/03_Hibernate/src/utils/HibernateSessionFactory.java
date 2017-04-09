package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	private static SessionFactory hibernateSessionFactory = null;
	
	public static synchronized SessionFactory getInstance() {
		if (hibernateSessionFactory == null) {
			Configuration configuration = new Configuration ();
			configuration.configure(); // Automatically checks hibernate.cfg.xml file.
			hibernateSessionFactory = configuration.buildSessionFactory();
		}
		return hibernateSessionFactory;
	}
}
