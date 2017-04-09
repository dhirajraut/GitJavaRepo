package com.intertek.acegi;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.SecurityConfig;
import org.acegisecurity.intercept.AbstractSecurityInterceptor;
import org.acegisecurity.intercept.web.FilterInvocationDefinitionSource;
import org.acegisecurity.intercept.web.PathBasedFilterInvocationDefinitionMap;

import com.intertek.dao.Dao;
import com.intertek.entity.Link;
import com.intertek.entity.Permission;

/**
 * Maintains a map of url of Link (key), permissions (object). It is used by Acegi Security framework to obtain the permissions per url.
 **/

public class AcegiObjectDefinitionSource implements FilterInvocationDefinitionSource
{
  private PathBasedFilterInvocationDefinitionMap ods;

  /**
   * .ctor
   *
   * Load all the links and their permissions into a map.
   *
   * @param dao Dao interface to access database through Spring-Managed hibernate.
   */
  public AcegiObjectDefinitionSource(Dao dao)
  {
    ods = new PathBasedFilterInvocationDefinitionMap();

    // all URLs should be converted to lowercase before mapping
    ods.setConvertUrlToLowercaseBeforeComparison( true );

    List resources = dao.find(
      "select distinct l from Link l left join fetch l.permissions",
      null
    );

    for( int i=0; i<resources.size(); i++ )
    {
      Link link = (Link)resources.get(i);
      Iterator it = link.getPermissions().iterator();
      //if(it.hasNext())
      //{
        ConfigAttributeDefinition defn = new ConfigAttributeDefinition();
        while(it.hasNext())
        {
          Permission perm = (Permission)it.next();
          defn.addConfigAttribute(new SecurityConfig(perm.getName()));
        }
        ods.addSecureUrl(link.getUrl(), defn);
      //}
    }
  }

  /**
   * Accesses the <code>ConfigAttributeDefinition</code> that applies to a given secure object.<P>Returns
   * <code>null</code> if no <code>ConfigAttribiteDefinition</code> applies.</p>
   *
   * @param object the object being secured
   *
   * @return the <code>ConfigAttributeDefinition</code> that applies to the passed object
   *
   * @throws IllegalArgumentException if the passed object is not of a type supported by the
   *         <code>ObjectDefinitionSource</code> implementation
   */
  public ConfigAttributeDefinition getAttributes(Object object) throws IllegalArgumentException
  {
    return ods.getAttributes(object);
  }

  /**
   * If available, all of the <code>ConfigAttributeDefinition</code>s defined by the implementing class.<P>This
   * is used by the {@link AbstractSecurityInterceptor} to perform startup time validation of each
   * <code>ConfigAttribute</code> configured against it.</p>
   *
   * @return an iterator over all the <code>ConfigAttributeDefinition</code>s or <code>null</code> if unsupported
   */
  public Iterator getConfigAttributeDefinitions()
  {
    return ods.getConfigAttributeDefinitions();
  }

  /**
   * Indicates whether the <code>ObjectDefinitionSource</code> implementation is able to provide
   * <code>ConfigAttributeDefinition</code>s for the indicated secure object type.
   *
   * @param clazz the class that is being queried
   *
   * @return true if the implementation can process the indicated class
   */
  public boolean supports(Class clazz)
  {
    return ods.supports(clazz);
  }
}
