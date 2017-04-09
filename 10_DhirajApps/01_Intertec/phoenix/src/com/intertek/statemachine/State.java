package com.intertek.statemachine;

/**
 * It represent a state node of the state machine. It just contains a name.
 **/
public class State
{
  private String name;

  /**
   * .ctr
   *
   * @param name - the name of the state.
   **/
  public State(String name)
  {
    this.name = name;
  }

  /**
   * Get the name of the state.
   *
   * @return - the name of the state.
   **/
  public String getName() { return name; }

  /**
   * Set the name of the state.
   *
   * @param name - the name of the state.
   **/
  public void setName(String name) { this.name = name; }

  public boolean equals(Object obj)
  {
    if(obj == null) return false;

    if(obj instanceof State)
    {
      State state = (State)obj;
      if(name.equals(state.getName())) return true;
    }

    return false;
  }

  public int hashCode()
  {
    if(name == null) return System.identityHashCode(this);

    return name.hashCode();
  }

  public String toString()
  {
    return name;
  }
}