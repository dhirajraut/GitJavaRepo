package com.galaxy.login.delegators;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.galaxy.login.command.ICommand;
import com.galaxy.login.command.LoginCommand;
import com.galaxy.login.domain.User;
import com.galaxy.login.utils.HibernateUtils;

public class LoginDelegator implements IDelegator {
	public Object execute(ICommand loginCommand) {

		Session session = HibernateUtils.getHibernateSession();
//		Transaction transaction = session.beginTransaction();
//
//		session.save(MockDataHelper.getTestUser());
//		transaction.commit();
		
		User user = new User();
		user.setUserId(((LoginCommand)loginCommand).getUserId());
		user.setPassword(((LoginCommand)loginCommand).getPassword());
//		String queryString = "from User";
//		Query query = session.createQuery(queryString);
//		List<User> userList = query.list();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("userId", ((LoginCommand)loginCommand).getUserId()));
		criteria.add(Restrictions.like("password", ((LoginCommand)loginCommand).getPassword()));
		List<User> userList = criteria.list();
		HibernateUtils.closeSession(session);
		
		System.out.println("From Hibernate" + userList);
		
		if (null != userList && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
}
