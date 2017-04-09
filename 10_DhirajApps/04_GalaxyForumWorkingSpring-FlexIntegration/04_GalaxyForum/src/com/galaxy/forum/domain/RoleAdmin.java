package com.galaxy.forum.domain;

public class RoleAdmin extends RoleAbstract {
	public RoleAdmin () {
		setRoleId(0);
		setRoleName(DomainConstants.ROLE_ADMIN_NAME);
	}

	public IUserRole getNextRole() {
		return null;
	}
	public IUserRole getPreviousRole() {
		return new RoleLevel2();
	}
}
