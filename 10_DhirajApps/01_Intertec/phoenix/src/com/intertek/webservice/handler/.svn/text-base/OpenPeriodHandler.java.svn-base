package com.intertek.webservice.handler;

import com.intertek.entity.OpenPeriod;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.UserService;


public class OpenPeriodHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof OpenPeriod)
    {
      UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
      userService.saveOpenPeriod((OpenPeriod)entity);
    }

    return null;
  }
  
	public String getType() {
		return "OPEN_PERIOD";
	}

	public String getId(Object entity) {
		if(entity instanceof OpenPeriod){
			return ((OpenPeriod)entity).getBuName();
		}
		return null;
	} 
}
