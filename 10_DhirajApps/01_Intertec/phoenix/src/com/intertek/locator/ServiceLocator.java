package com.intertek.locator;

import org.springframework.context.ApplicationContext;

/**
 * A singleton class used to locate all the managed beans from Spring Managed Context.
 *
 * A normal usage is like this:
 *   A a = (A)ServiceLocation.getInstace().getBean("a");
 *
 **/
public class ServiceLocator
{
  private ApplicationContext context;
  private static ServiceLocator instance_ = new ServiceLocator();

  /**
   * .ctr
   *
   */
  private ServiceLocator()
  {
  }

  /**
   * Get the instance of this singleton class.
   *
   **/
  public static ServiceLocator getInstance()
  {
    return instance_;
  }

  /**
   * Get the Spring application context.
   *
   * @return the Spring application context.
   */
  public void setApplicationContext(ApplicationContext context)
  {
    this.context = context;
  }

  /**
   * Get a bean from the Spring framework.
   *
   * @param name the name of the bean.
   * @return an instance of the bean.
   */
  public Object getBean(String name)
  {
    return context.getBean(name);
  }
}
