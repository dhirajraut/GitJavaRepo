import java.util.List;

import mockdata.MockDataHelper;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateSessionFactory;
import entities.Skill;


public class DBInsert {
	
	public static void main(String args[]) {
		Session session = HibernateSessionFactory.getInstance().openSession();
		dbInsert(session);

	}
	public static void dbInsert(Session session) {
		
		List<Skill> skillList = MockDataHelper.getSkillList();
		for (int ctr = 0; ctr < skillList.size(); ctr++) {
			
			/* Insert In DB. */
			Transaction transaction = session.beginTransaction();
			session.save(skillList.get(ctr));
			
			/**
			 * if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[SKILL]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
			 * 	drop table [dbo].[SKILL]
			 * GO
			 * CREATE TABLE [dbo].[SKILL] (
			 * 	[ID] [int] NOT NULL,
			 *  [NAME] [varchar] (255)
			 *  COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL) ON [PRIMARY]
			 * GO
			 * 
			 * select max(ID) from SKILL
			 * insert into SKILL (NAME, ID) values (?, ?)
			 */
			
			transaction.commit();
		}
	}
}
