package com.galaxy.login.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	public static Session getHibernateSession() {
		try {
		System.out.println("In getHibernateSession");
		Configuration configuration = new Configuration();
		System.out.println("Creating session factory");
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
		System.out.println("creating Session");
		Session session = sessionFactory.openSession();
		System.out.println("returning");

		return session;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void closeSession(Session session) {
		session.close();
	}
}
