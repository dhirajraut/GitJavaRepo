package com.intertek.exception;

public class JasperFillException extends RuntimeException
{
  private Object[] params;

  /**
   * .ctr
   **/
  public JasperFillException()
  {
  }

  /**
   * .ctr
   *
   * @param message - the message of this exception.
   **/
  public JasperFillException(String message)
  {
    super(message);
  }

  /**
   * .ctr
   *
   * @param message - the message of this exception.
   * @param cause - the wrapped Throwable object.
   **/
  public JasperFillException(String message, Throwable cause)
  {
    super(message, cause);
  }

  /**
   * .ctr
   *
   * @param cause - the wrapped Throwable object.
   **/
  public JasperFillException(Throwable cause)
  {
    super(cause);
  }

  /**
   * .ctr
   *
   * @param message - the message of this exception.
   * @param params - an array of parameters which come with the exception.
   **/
  public JasperFillException(String message, Object[] params)
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
  public JasperFillException(String message, Object[] params, Throwable cause)
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
