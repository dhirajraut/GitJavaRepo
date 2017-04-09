
public class TestExceptionCausedChains1
	{	public static void main(String[] args)
			{	float[] v = {};
				float f = 0;
				try {	f = new TestExceptionCausedChains1().mean(v);	}
				catch (RuntimeException r)
					{	printExceptionChain(r);
						//r.printStackTrace();
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
	  					System.out.println(t.toString());
	  					s = t.getStackTrace();
					    for (int i = 0; i < s.length; i++)
					    	{	StackTraceElement si = s[i];
					        	System.out.println(i + ": " + si.toString());
					    	}  
					    t = t.getCause();
					}  
	  		}
	  
	  private float mean(float[] w)
	  		{	try {	return divide(sum(w), w.length);	}
	  			catch (RuntimeException r)
	  				{	throw new RuntimeException("w contains [" + toString(w) + "]", r);    }  
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
/*	The above program is taken from
 * 		http://javaboutique.internet.com/tutorials/Chained_Exceptions/chained_exceptions1_3.html
 *	In JDK 1.4 SUN has added chained exceptions by adding the "cause" property which can
 * 		hold an instance of a Throwable. The idea is that if you decide to throw an
 * 		exception after having caught another exception then simply save this exception
 * 		--the "cause"-- as part of the new exception. It's easiest to add the cause with
 * 		a constructor: 
*/