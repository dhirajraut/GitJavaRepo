package ExceptionChaining;

public class AException extends Exception
	{	public AException(String msg, Throwable t)
			{	super(msg, t);	}
		public AException(String msg)
			{	super(msg);	}
	}
