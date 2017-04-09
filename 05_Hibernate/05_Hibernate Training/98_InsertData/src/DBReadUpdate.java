import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Skill;

import Util.HibernateSessionFactory;


public class DBReadUpdate {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = HibernateSessionFactory.getInstance().openSession();

		DBInsert.dbInsert(session); // Insert Mock Data.
		
		dbReadUpdate(session); // Read and Update a record.
		
		session.close();
	}
	
	public static void dbReadUpdate(Session session) {
		
		Skill skill = (Skill) session.load(Skill.class, 1); // Read DB
		System.out.println("Editing-\n" + skill);
		
		skill.setName("Adobe Flex");
		Transaction transaction = session.beginTransaction(); // Mandatory in hibernate.
		session.saveOrUpdate(skill); // Update DB
		
		transaction.commit();
	}
}
