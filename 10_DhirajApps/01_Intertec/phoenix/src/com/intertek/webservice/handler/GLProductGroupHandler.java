package com.intertek.webservice.handler;

import com.intertek.entity.GLProductGroup;
import com.intertek.entity.GLProductGroupId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.GLService;


public class GLProductGroupHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof GLProductGroup)
    {
      GLService glService = (GLService)ServiceLocator.getInstance().getBean("glService");
      glService.saveGLProductGroup((GLProductGroup)entity);
    }

    return null;
  }
  
	public String getType() {
		return "GL_PRODUCT_GROUP";
	}

	public String getId(Object entity) {
		if(entity instanceof GLProductGroup){
			GLProductGroupId id=((GLProductGroup)entity).getGlProductGroupId();
			return
			  "("+
			  id.getProduct()+", "+
			  id.getEffDate()+
			  ")";
		}
		return null;
	} 
}
