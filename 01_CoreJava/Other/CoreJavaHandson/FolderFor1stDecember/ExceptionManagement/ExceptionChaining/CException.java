package ExceptionChaining;

public class CException extends Exception
	{	public CException(String msg, Throwable t)
			{	super(msg, t);	}
		public CException(String msg)
			{	super(msg);	}
	}
