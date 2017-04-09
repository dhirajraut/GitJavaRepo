package ExceptionChaining;

public class BException extends Exception
	{	public BException(String msg, Throwable t)
			{	super(msg, t);	}
		public BException(String msg)
			{	super(msg);	}
	}
