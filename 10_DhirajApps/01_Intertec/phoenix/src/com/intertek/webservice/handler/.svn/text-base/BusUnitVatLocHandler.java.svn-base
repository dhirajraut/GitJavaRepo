package com.intertek.webservice.handler;

import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusUnitVatLocId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.UserService;


public class BusUnitVatLocHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof BusUnitVatLoc)
    {
      UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
      userService.saveBusUnitVatLoc((BusUnitVatLoc)entity);

      return entity;
    }

    return null;
  }
  
	public String getType() {
		return "BUS_UNIT_VAT_LOC";
	}

	public String getId(Object entity) {
		if(entity instanceof BusUnitVatLoc){
			BusUnitVatLocId id=((BusUnitVatLoc)entity).getBusUnitVatLocId();
			return
			  "("+
			  id.getCountryCode()+", "+
			  id.getBuName()+
			  ")";
		}
		return null;
	} 
}
