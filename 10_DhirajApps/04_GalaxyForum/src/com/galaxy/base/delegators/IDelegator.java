package com.galaxy.base.delegators;

import com.galaxy.base.commands.ICommand;

public interface IDelegator {
	public Object execute(ICommand loginCommand);
}
