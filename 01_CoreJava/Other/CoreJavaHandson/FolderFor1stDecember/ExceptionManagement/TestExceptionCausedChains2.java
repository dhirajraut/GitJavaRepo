
public class TestExceptionCausedChains2
	{	public static void main(String[] args)
			{	float[] v = {};
		    	float f = 0;
		    	try {	f = new TestExceptionCausedChains2().mean(v);	} 
		    	catch (RuntimeException r)
		    		{	//printExceptionChain(r);
		    			r.printStackTrace();
		    			System.exit(1);
		    		}  
		    	System.out.println(f);
		 	}

		 private static void printExceptionChain(Throwable thr)
		  	{	StackTraceElement[] s;
		  		Throwable t = thr;
		  		System.out.println("Exception chain (top to bottom):");
		  		while (t != null)
		  			{	System.out.println("-------------------------------");
					      s = t.getStackTrace();
					      StackTraceElement s0 = s[0];
					      System.out.println(t.toString());
					      System.out.println("  at " + s0.toString());
					      if (t.getCause() == null)
					      	{	System.out.println("-------------------------------");
					      		System.out.println("Complete traceback (bottom to top):");
					      		t.printStackTrace();
					      	}  
					      t = t.getCause();
	  				}  
		  	}

		  private float mean(float[] w)
		  	{	try {	return divide(sum(w), w.length);	}
		  		catch (RuntimeException r)
		  			{	throw new RuntimeException("w contains [" + toString(w) + "]", r);}  
		  	}

		  private float sum(float[] s)
		  	{	float f = 0;
		  		for (int i = 0; i < s.length; i++)
		  			f += s[i];
		  		return f;
		  	}

		  private float divide(float a, float b)
		  	{	if (b == 0)
		  			throw new RuntimeException("Division by zero");
		  		return a / b;
		  	}

		  private String toString(float[] w)
		  	{	String s = "";
			    for (int i = 0; i < w.length; i++)
			    	{	if (i != 0) s += ',';
			    		s += w[i];
			    	}
			    return s;
		  	}  
		}
/*  the output is...
 * Exception chain (top to bottom):
-------------------------------
java.lang.RuntimeException: w contains []
  at TestExceptionCausedChains2.mean(TestExceptionCausedChains2.java:37)
-------------------------------
java.lang.RuntimeException: Division by zero
  at TestExceptionCausedChains2.divide(TestExceptionCausedChains2.java:49)
-------------------------------
Complete traceback (bottom to top):
java.lang.RuntimeException: Division by zero
	at TestExceptionCausedChains2.divide(TestExceptionCausedChains2.java:49)
	at TestExceptionCausedChains2.mean(TestExceptionCausedChains2.java:35)
	at TestExceptionCausedChains2.main(TestExceptionCausedChains2.java:6)
 */
