package com.galaxy.forum.domain;

public class RoleLevel2 extends RoleAbstract {
	public RoleLevel2 () {
		setRoleId(2);
		setRoleName(DomainConstants.ROLE_LEVEL2_NAME);
	}
	public IUserRole getNextRole() {
		return new RoleAdmin();
	}
	public IUserRole getPreviousRole() {
		return new RoleLevel1();
	}
}
