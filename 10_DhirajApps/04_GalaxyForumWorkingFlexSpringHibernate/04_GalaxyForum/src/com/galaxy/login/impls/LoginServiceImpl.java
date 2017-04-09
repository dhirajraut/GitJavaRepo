package com.galaxy.login.impls;

import java.util.Date;

import com.galaxy.forum.domain.ForumUser;
import com.galaxy.login.command.ICommand;
import com.galaxy.login.command.LoginCommand;
import com.galaxy.login.delegators.IDelegator;

public class LoginServiceImpl implements IService {
	
	private IDelegator delegator;

	public IDelegator getDelegator() {
		return delegator;
	}
	public void setDelegator(IDelegator delegator) {
		this.delegator = delegator;
	}

	public Object execute (ICommand loginCommand) {
		
		return delegator.execute(loginCommand);

//		String userId = ((LoginCommand) loginCommand).getUserId();
//		String password = ((LoginCommand) loginCommand).getPassword();
//		if ("Dhiraj".equals(userId)
//				&& "Raut".equals(password)) {
//			ForumUser user = new ForumUser(1, "dhiraj.raut", "password", "Dhiraj", "Raut", new Date());
//			return user;
//		}
//		else {
//			return null;
//		}
	}

}
