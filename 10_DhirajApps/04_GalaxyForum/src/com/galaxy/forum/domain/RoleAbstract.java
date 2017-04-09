package com.galaxy.forum.domain;

public abstract class RoleAbstract implements IUserRole {
	private int roleId;
	private String roleName;
	
	
	/* Getters and Setters. */
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
