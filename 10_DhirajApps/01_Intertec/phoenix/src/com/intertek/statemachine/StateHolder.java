package com.intertek.statemachine;

/**
 * An interface to get and set State.
 **/
public interface StateHolder
{
  /**
   * Get the state.
   *
   * @return the state name.
   **/
  String getState();

  /**
   * Set the state name.
   *
   * @param state - the state name.
   **/
  void setState(String state);
}
