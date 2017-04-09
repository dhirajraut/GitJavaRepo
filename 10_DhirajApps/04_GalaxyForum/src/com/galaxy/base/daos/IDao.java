package com.galaxy.base.daos;

import com.galaxy.base.commands.ICommand;

public interface IDao {
	public Object execute(ICommand loginCommand);
}
