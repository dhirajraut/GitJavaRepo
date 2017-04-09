import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Skill;

import Util.HibernateSessionFactory;


public class DBDelete {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = HibernateSessionFactory.getInstance().openSession();

		DBInsert.dbInsert(session); // Insert Mock Data.
		
		dbDelete(session); // Read and Update a record.
		
		session.close();
	}
	
	public static void dbDelete(Session session) {
		
		Skill skill = (Skill) session.load(Skill.class, 1); // Read DB
		System.out.println("Deleting -\n" + skill);
		
		Transaction transaction = session.beginTransaction(); // Mandatory in hibernate.
		session.delete(skill); // Update DB
		
		transaction.commit();
	}	
}
