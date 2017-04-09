import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Util.HibernateSessionFactory;
import entities.Skill;


public class HQLRead {
	
	public static void main(String args[]) {
		Session session = HibernateSessionFactory.getInstance().openSession();
		
		DBInsert.dbInsert(session); // Insert Mock Data.
		hqlRead(session);
		hqlUpdate(session);
		session.close();
	}
	public static void hqlRead(Session session) {
		
		String queryString = "from Skill where id > :id";
		Query query = session.createQuery(queryString);
		query.setInteger("id", 2);
		
		List<Skill> skillList = query.list();;
		
		for (int ctr = 0; ctr < skillList.size(); ctr++) {
			System.out.println(skillList.get(ctr));
		}
	}

	public static void hqlUpdate(Session session) {
		
		String queryString = "update Skill set name = :newName where id = :id";
		Query query = session.createQuery(queryString);
		Transaction transaction = session.beginTransaction();
		query.setString("newName", "Adobe Flex");
		query.setInteger("id", 3);
		query.executeUpdate();
		transaction.commit();
	}
}
