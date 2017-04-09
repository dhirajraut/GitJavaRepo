package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Expression;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;

import com.intertek.entity.Role;
import com.intertek.entity.User;
import com.intertek.phoenix.search.entity.UserSearch;

public class EditUser
{
  @CascadeValidation
  private User user;

  //@Length(min=0, max=255)
  private String password;

  @Expression(value="passwordConfirmation == password", errorCode="password.not.match")
  private String passwordConfirmation;
  private String addOrDeleteRole;
  private Role[] roles;
  private int 	roleCount ;
  private int roleIndex; 
  private String tabName = "0";
    	private String userRoleFlag;
		private String rowNum;
        private String timeFlag;
		private String addFlag;
		
// START : #119240 : 19/06/09 
private UserSearch userSearch;
// END : #119240 : 19/06/09		
		

  public void setUser(User user)
  {
    this.user = user;
  }

  public User getUser()
  {
    return user;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPasswordConfirmation(String passwordConfirmation)
  {
    this.passwordConfirmation = passwordConfirmation;
  }

  public String getPasswordConfirmation()
  {
    return passwordConfirmation;
  }
    public String getAddOrDeleteRole()
	  {
			return addOrDeleteRole;
		}

		public void setAddOrDeleteRole(String addOrDeleteRole)
		{
			this.addOrDeleteRole = addOrDeleteRole;
		}
	
		 public Role[]  getRoles()
	  {
		return roles;
	  }

	  public void  setRoles(Role[]  roles)
	  {
		this.roles = roles;
	  }


	   public int getRoleCount()
	  {
		return roleCount;
	  }

	  public void setRoleCount(int roleCount)
	  {
		this.roleCount = roleCount;
	  }

	 public int getRoleIndex()
		{
			return roleIndex;
		}

		public void setRoleIndex(int roleIndex)
		{
			this.roleIndex = roleIndex;
		}
	   public String getTabName()
     {
		return tabName;
 	}

	public void setTabName(String tabName)
	{
		this.tabName = tabName;
	}
	 public String getUserRoleFlag()
	  {
			return userRoleFlag;
		}

		public void setUserRoleFlag(String userRoleFlag)
		{
			this.userRoleFlag = userRoleFlag;
		}

		 public String getRowNum()
       {
		return rowNum;
     	}

	public void setRowNum(String rowNum)
	{
		this.rowNum = rowNum;
	}
	public String getTimeFlag(){
 return timeFlag;
}

public void setTimeFlag(String timeFlag){
  this.timeFlag=timeFlag;
}
public String getAddFlag(){
 return addFlag;
}

public void setAddFlag(String addFlag){
  this.addFlag=addFlag;
}

//START : #119240 : 19/06/09 
public UserSearch getUserSearch() {
	return userSearch;
}

public void setUserSearch(UserSearch userSearch) {
	this.userSearch = userSearch;
}
//END : #119240 : 19/06/09 

}
