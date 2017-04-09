package com.intertek.util;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.intertek.exception.ServiceException;

public class BeanUtil
{
  public static Class[] copyTypes = new Class[] {
    java.lang.String.class,
    java.util.Date.class,
    java.lang.Boolean.class,
    java.lang.Integer.class,
    java.lang.Long.class,
    java.lang.Double.class,
    java.lang.Float.class,
    boolean.class,
    int.class,
    long.class,
    double.class,
    float.class
  };

  public static boolean isInCopyTypes(Class clazz)
  {
    for(int i=0; i<copyTypes.length; i++)
    {
      if(clazz.isAssignableFrom(copyTypes[i])) return true;
    }

    return false;
  }

  public static void copySimpleProperties(
    Object dest,
    Object orig,
    boolean copyId
  )
  {
    // Validate existence of the specified beans
    if((orig == null) || (dest == null)) return;

    try
    {
      PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(orig);
      for (int i = 0; i < origDescriptors.length; i++)
      {
        String name = origDescriptors[i].getName();
        if ("class".equals(name))
        {
          continue;
        }
        Class clazz = origDescriptors[i].getPropertyType();
        if(isInCopyTypes(clazz))
        {
        	if(!(!copyId && name.equals("id"))){
		          if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name))
		          {
		            try
		            {
		            	
		              Object value = PropertyUtils.getSimpleProperty(orig, name);
		              BeanUtils.copyProperty(dest, name, value);
		            }
		            catch (NoSuchMethodException e)
		            {
		              e.printStackTrace();
		            }
		          }
		        }
        }
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      throw new ServiceException(t);
    }
  }

  public static Object getProperty(Object obj, String propertyName)
  {
    if((obj == null) || (propertyName == null)) return null;

    Object result = null;
    try
    {
      int index = propertyName.indexOf(".");
      if(index >= 0) result = PropertyUtils.getNestedProperty(obj, propertyName);
      else result = PropertyUtils.getSimpleProperty(obj, propertyName);
    }
    catch (Throwable e)
    {
      e.printStackTrace();
    }

    return result;
  }
}
