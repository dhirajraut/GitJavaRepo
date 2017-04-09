package com.intertek.webservice.handler;

import com.intertek.entity.BusinessUnit;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.UserService;


public class BusinessUnitHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof BusinessUnit)
    {
      UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
      userService.updateBusinessUnitHeaderOnly((BusinessUnit)entity);

      return entity;
    }

    return null;
  }
  
	public String getType() {
		return "BUSINESS_UNIT";
	}

	public String getId(Object entity) {
		if(entity instanceof BusinessUnit){
			return ((BusinessUnit)entity).getName();
		}
		return null;
	}   
}
