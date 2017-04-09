package com.intertek.entity;

public class UserWsResponse extends WsResponse
{
  private User user;

  public void setUser(User user)
  {
    this.user = user;
  }

  public User getUser()
  {
    return user;
  }
}