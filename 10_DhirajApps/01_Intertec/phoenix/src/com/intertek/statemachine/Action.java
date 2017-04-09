package com.intertek.statemachine;

/**
 * It represents a action in state machine. It contains a service name (like userService) and api name.
 *
 **/
public class Action
{
  private String serviceName;
  private String apiName;

  /**
   * .ctr
   **/
  public Action()
  {
  }

  /**
   * Get the service name of this action.
   *
   * @return - the service name.
   **/
  public String getServiceName() { return serviceName; }

  /**
   * Set the service name of this action.
   *
   * @param serviceName - the service name of this action.
   **/
  public void setServiceName(String serviceName) { this.serviceName = serviceName; }

  /**
   * Get the api name of the service.
   *
   * @return - the api name of the service.
   **/
  public String getApiName() { return apiName; }

  /**
   * Set the api name of the service.
   *
   * @param apiName - the api name of the service.
   **/
  public void setApiName(String apiName) { this.apiName = apiName; }
}