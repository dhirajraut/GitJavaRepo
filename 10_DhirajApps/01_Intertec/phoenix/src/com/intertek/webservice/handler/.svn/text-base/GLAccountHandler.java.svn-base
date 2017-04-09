package com.intertek.webservice.handler;

import com.intertek.entity.GLAccount;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.GLService;


public class GLAccountHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof GLAccount)
    {
      GLService glService = (GLService)ServiceLocator.getInstance().getBean("glService");
      glService.saveGLAccount((GLAccount)entity);
    }

    return null;
  }
  
	public String getType() {
		return "GL_ACCOUNT";
	}

	public String getId(Object entity) {
		if(entity instanceof GLAccount){
			return ((GLAccount)entity).getAccount();
		}
		return null;
	} 
}
