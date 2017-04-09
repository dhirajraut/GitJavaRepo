package com.galaxy.login.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.galaxy.base.commands.ICommand;
import com.galaxy.base.daos.IDao;
import com.galaxy.login.commands.LoginCommand;
import com.galaxy.login.domain.User;
import com.galaxy.login.utils.HibernateUtils;

public class LoginDao implements IDao {

	public Object execute(ICommand loginCommand) {
		System.out.println("In delegator");
		Session session = HibernateUtils.getHibernateSession();
		System.out.println("got session");

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

		System.out.println("criteria");
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("userId", ((LoginCommand)loginCommand).getUserId()));
		criteria.add(Restrictions.like("password", ((LoginCommand)loginCommand).getPassword()));
		System.out.println("Listing");
		List<User> userList = criteria.list();
		HibernateUtils.closeSession(session);
		
		System.out.println("From Hibernate" + userList);
		
		if (null != userList && userList.size() > 0) {
			System.out.println("dao returning " + userList.get(0));
			return userList.get(0);
		}
		return null;
	}
}
