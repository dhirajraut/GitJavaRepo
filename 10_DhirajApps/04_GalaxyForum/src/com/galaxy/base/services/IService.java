package com.galaxy.base.services;

import com.galaxy.base.commands.ICommand;

public interface IService {

	public Object execute(ICommand command);
}
