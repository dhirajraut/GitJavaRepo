package com.galaxy.login.commands;

import com.galaxy.base.commands.ICommand;

public class LoginCommand implements ICommand {

	private String userId = "";
	private String password = "";
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
