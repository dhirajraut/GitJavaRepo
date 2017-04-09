package com.galaxy.forum.domain;

public class RoleLevel1 extends RoleAbstract{
	public RoleLevel1 () {
		setRoleId(1);
		setRoleName(DomainConstants.ROLE_LEVEL1_NAME);
	}
	
	public IUserRole getNextRole() {
		return new RoleLevel2();
	}
	public IUserRole getPreviousRole() {
		return null;
	}
}
