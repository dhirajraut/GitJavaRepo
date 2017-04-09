package com.intertek.webservice.handler;

import com.intertek.entity.GLDepartment;
import com.intertek.entity.GLDepartmentId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.GLService;


public class GLDepartmentHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof GLDepartment)
    {
      GLService glService = (GLService)ServiceLocator.getInstance().getBean("glService");
      glService.saveGLDepartment((GLDepartment)entity);
    }

    return null;
  }
  
	public String getType() {
		return "GL_DEPARTMENT";
	}

	public String getId(Object entity) {
		if(entity instanceof GLDepartment){
			GLDepartmentId id=((GLDepartment)entity).getGlDepartmentId();
			return
			  "("+
			  id.getDeptId()+", "+
			  id.getEffDate()+
			  ")";
		}
		return null;
	} 
}
