package com.galaxy.login.delegators;

import com.galaxy.base.commands.ICommand;
import com.galaxy.base.daos.IDao;
import com.galaxy.base.delegators.IDelegator;

public class LoginDelegator implements IDelegator {
	
	private IDao dao;
	
	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public Object execute(ICommand loginCommand) {
		Object returnObj = dao.execute(loginCommand);
		System.out.println("ReturnObj = " + returnObj);
		return returnObj;
	}
}
