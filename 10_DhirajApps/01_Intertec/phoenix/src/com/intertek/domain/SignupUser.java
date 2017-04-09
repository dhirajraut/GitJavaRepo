package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Expression;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import com.intertek.entity.Employee;
import com.intertek.entity.Role;
import com.intertek.entity.User;

public class SignupUser
{
  @CascadeValidation
  private User user;
  //@NotBlank
  //@Length(min=6, max=255)
  private String password;
  private Employee employee;
  @Expression(value="passwordConfirmation == password", errorCode="password.not.match")
  private String passwordConfirmation;
  private String status;
  private String addOrDeleteRole;
	   private Role[] roles;
	   private int 	roleCount ;
	   private int roleIndex; 
	    private String tabName = "0";
private String userRoleFlag;
private String rowNum;
private String searchForm;
private String timeFlag;
private String addFlag;
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
		  public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
      public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
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



public String getSearchForm()
{return searchForm;
}

public void setSearchForm(String searchForm)
{this.searchForm=searchForm;}
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
}
