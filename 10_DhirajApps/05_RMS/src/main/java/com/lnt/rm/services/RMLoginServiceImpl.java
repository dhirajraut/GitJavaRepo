/**
 * 
 */
package com.lnt.rm.services;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.Message;
import com.lnt.rm.utils.LoggerUtil;

/**
 * @author 284773
 *
 */
public class RMLoginServiceImpl implements RMServiceInterface {

	public void execute () {

		LoggerUtil.log(this, "In LoginService");
		SessionFactory sessionFactory = 
			new Configuration().configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();
//		Criteria criteria = new Criteria();
//		criteria.createAlias("com.Message" , Message.)
		
		Query query = session.createQuery("FROM MESSAGE");
		List result = query.list();
		
	}
}
