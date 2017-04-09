package com.galaxy.login.impls;

import com.galaxy.base.commands.ICommand;
import com.galaxy.base.delegators.IDelegator;
import com.galaxy.base.services.IService;

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
	}
}
