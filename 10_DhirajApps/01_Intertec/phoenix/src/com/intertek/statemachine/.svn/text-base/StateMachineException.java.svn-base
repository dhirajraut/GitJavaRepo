package com.intertek.statemachine;

/**
 * An unchecked Exception thrown by the state machine.
 **/
public class StateMachineException extends RuntimeException
{
  private Object[] params;

  /**
   * .ctr
   **/
  public StateMachineException()
  {
  }

  /**
   * .ctr
   *
   * @param msg - the message of this exception.
   **/
  public StateMachineException(String msg)
  {
    super(msg);
  }

  /**
   * .ctr
   *
   * @param msg - the message of this exception.
   * @param t - the wrapped Throwable object.
   **/
  public StateMachineException(String msg, Throwable t)
  {
    super(msg, t);
  }

  /**
   * .ctr
   *
   * @param t - the wrapped Throwable object.
   **/
  public StateMachineException(Throwable t)
  {
    super(t);
  }

  /**
   * .ctr
   *
   * @param msg - the message of this exception.
   * @param params - an array of parameters which come with the exception.
   **/
  public StateMachineException(String msg, Object[] params)
  {
    super(msg);
    this.params = params;
  }

  /**
   * .ctr
   *
   * @param msg - the message of this exception.
   * @param params - an array of objects which come with the exception.
   * @param t - the wrapped Throwable object.
   **/
  public StateMachineException(String msg, Object[] params, Throwable t)
  {
    super(msg, t);
    this.params = params;
  }

  /**
   * Get the array of parameters.
   *
   * @return - the array of parameters which come with the exception.
   **/
  public Object[] getParams() { return params; }
}
