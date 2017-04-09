package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.phoenix.search.entity.RoleSearch;

public class AddRole
{
    @CascadeValidation

	private Role role;
	private int roleCount;
	private String addOrDeleteRole;
	private int roleIndex;
	private String tabName = "0";
	private Permission[] permissions;
	private Permission permission;
	private String roleFlag;
	private String rowNum;
	private boolean viewOnly;
	private String styleVisibility="visibility:visible";
	// START : #119240 : 23/06/09
	private RoleSearch roleSearch;
	// END : #119240 : 23/06/09
	
	 public String getRoleFlag()
	  {
			return roleFlag;
		}

	  		public void setRoleFlag(String roleFlag)
		{
			this.roleFlag = roleFlag;
		}
		 public String getRowNum()
  {
		return rowNum;
	}

	public void setRowNum(String rowNum)
	{
		this.rowNum = rowNum;
	}
	public void setRole(Role role)
	{
	this.role = role;
	}
	public Role getRole()
	{
	return role;
	}

   public void setPermission(Permission permission)
	{
	this.permission = permission;
	}
	public Permission getPermission()
	{
	return permission;
	}

	public Permission[] getPermissions()
	{
	return  permissions;
	}

	public void setPermissions(Permission[] permissions)
	{
		this.permissions =  permissions;
	}
	
	public String getTabName()
	{
	return tabName;
	}

	public void setTabName(String tabName)
	{
	this.tabName = tabName;
	} 

	public int getRoleCount()
	{
	return roleCount;
	}

	public void setRoleCount(int roleCount)
	{
	this.roleCount = roleCount;
	}

	public String getAddOrDeleteRole()
	{
		return addOrDeleteRole;
	}

	public void setAddOrDeleteRole(String addOrDeleteRole)
	{
		this.addOrDeleteRole = addOrDeleteRole;
	}

	public int getRoleIndex()
	{
		return roleIndex;
	}

	public void setRoleIndex(int roleIndex)
	{
		this.roleIndex = roleIndex;
	}

	public boolean getViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public String getStyleVisibility() {
		return styleVisibility;
	}

	public void setStyleVisibility(String styleVisibility) {
		this.styleVisibility = styleVisibility;
	}
	
	// START : #119240 : 23/06/09
	public RoleSearch getRoleSearch() {
		return roleSearch;
	}

	public void setRoleSearch(RoleSearch roleSearch) {
		this.roleSearch = roleSearch;
	}
	// END : #119240 : 23/06/09
	
}
