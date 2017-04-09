package com.intertek.domain;

import com.intertek.entity.Link;
import com.intertek.entity.Permission;
import com.intertek.phoenix.search.entity.PermissionSearch;

public class AddPermissionList
{
private String addOrDeletePermission;
private Permission permission;
private Link[] links;
private int 	permissionCount ;
private int permissionIndex;
private String controlFlag;
private String rowNum;
private String searchForm;
// START : #119240 : 22/06/09
private PermissionSearch permissionSearch;
//END : #119240 : 22/06/09

public String getAddOrDeletePermission()
{
return addOrDeletePermission;
}

public void setAddOrDeletePermission(String addOrDeletePermission)
{
this.addOrDeletePermission = addOrDeletePermission;
}

public Permission getPermission()
{
return permission;
}

public void  setPermission(Permission  permission)
{
this.permission = permission;
}   

public Link[] getLinks()
{
return links;
}

public void setLinks(Link[] links)
{
this.links=links;
}

public int getPermissionCount()
{
return permissionCount;
}

public void setPermissionCount(int permissionCount)
{
this.permissionCount = permissionCount;
}

public int getPermissionIndex()
{
return permissionIndex;
}

public void setPermissionIndex(int permissionIndex)
{
this.permissionIndex = permissionIndex;
}

public String getControlFlag()
{
return controlFlag;
}

public void setControlFlag(String controlFlag)
{
this.controlFlag = controlFlag;
}

public String getRowNum()
{
return rowNum;
}

public void setRowNum(String rowNum)
{
this.rowNum = rowNum;
}
public void setSearchForm(String searchForm) 
{
this.searchForm = searchForm;
}

public String getSearchForm() 
{
return searchForm;
}
//START : #119240 : 22/06/09

public PermissionSearch getPermissionSearch() {
	return permissionSearch;
}

public void setPermissionSearch(PermissionSearch permissionSearch) {
	this.permissionSearch = permissionSearch;
}
//END : #119240 : 22/06/09
}
