package com.galaxy.login.delegators;

import com.galaxy.login.command.ICommand;

public interface IDelegator {
	public Object execute(ICommand loginCommand);
}
