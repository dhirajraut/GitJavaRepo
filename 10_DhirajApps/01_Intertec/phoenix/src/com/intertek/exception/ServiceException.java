package com.intertek.exception;

/**
 * An exception extended from RuntimeException.
 *
 * Normally it is used by service objects to throw meaningful exception to the UI controllers.
 *
 * The message can be the key from resource bundle and the params can be the parameters to format the message from the resource bundle.
 *
 **/

public class ServiceException extends RuntimeException
{
  private Object[] params;

  /**
   * .ctr
   **/
  public ServiceException()
  {
  }

  /**
   * .ctr
   *
   * @param message - the message of this exception.
   **/
  public ServiceException(String message)
  {
    super(message);
  }

  /**
   * .ctr
   *
   * @param message - the message of this exception.
   * @param cause - the wrapped Throwable object.
   **/
  public ServiceException(String message, Throwable cause)
  {
    super(message, cause);
  }

  /**
   * .ctr
   *
   * @param cause - the wrapped Throwable object.
   **/
  public ServiceException(Throwable cause)
  {
    super(cause);
  }

  /**
   * .ctr
   *
   * @param message - the message of this exception.
   * @param params - an array of parameters which come with the exception.
   **/
  public ServiceException(String message, Object[] params)
  {
    super(message);

    this.params = params;
  }

  /**
   * .ctr
   *
   * @param message - the message of this exception.
   * @param params - an array of objects which come with the exception.
   * @param cause - the wrapped Throwable object.
   **/
  public ServiceException(String message, Object[] params, Throwable cause)
  {
    super(message, cause);

    this.params = params;
  }

  /**
   * Get the array of parameters.
   *
   * @return - the array of parameters which come with the exception.
   **/
  public Object[] getParams()
  {
    return params;
  }
}
