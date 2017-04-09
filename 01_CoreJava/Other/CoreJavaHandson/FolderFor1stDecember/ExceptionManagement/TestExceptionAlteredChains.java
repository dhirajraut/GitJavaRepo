/* 	Case 1 : Statement throw2 is remarked. 
 * 			Observe, Runtime exception condition checked at line no. 53
 * 			The runtime exception is thrown from line 54.  It doesn't get catch immediately.
 * 			So, it catches at line 34.  But this catch creates new object of an exception
 * 				so earlier path from 53 is lost and new path from line 35 begins.
 * 				So, printstacktrace displays lines 35th and 27.
 * 			The output is :	java.lang.RuntimeException: w contains []
							at TestExceptionAlteredChains.mean(TestExceptionAlteredChains.java:35)
							at TestExceptionAlteredChains.main(TestExceptionAlteredChains.java:27)
							Exception in thread "main" 
	Case 2 : Statement throw1 is remarked.
 * 			Observe, runtime exception occurs at line 53  which throws a message to the 'r'.
 * 							That message is printed in the statement throw2.
 * 			The output is : java.lang.RuntimeException: w contains []
							at TestExceptionAlteredChains.mean(TestExceptionAlteredChains.java:36)
							at TestExceptionAlteredChains.main(TestExceptionAlteredChains.java:27)
							Caused by: java.lang.RuntimeException: Division by zero
							at TestExceptionAlteredChains.divide(TestExceptionAlteredChains.java:54)
							at TestExceptionAlteredChains.mean(TestExceptionAlteredChains.java:33)
							... 1 more
							Exception in thread "main" 
 */
public class TestExceptionAlteredChains
	{	public static void main(String[] args)
			{	//float[] v = {1,2,3};
				float [] v = {};
				float f = new TestExceptionAlteredChains().mean(v);
				System.out.println(f);
			}
		//private float mean(float[] w)
			//{	return divide(sum(w), w.length);	}
		private float mean(float[] w)
			{	try {	return divide(sum(w), w.length);	}
				catch (RuntimeException r)
					{	throw new RuntimeException("w contains [" + toString(w) + "]");	// Statement throw1
						//throw new RuntimeException("w contains [" + toString(w) + "]",r); 	// Statement throw2
					}
			}
		private String toString(float[] w)
			{	String s = "";
		    	for (int i = 0; i < w.length; i++)
		    		{	if (i != 0) s += ',';
		    				s += w[i];
		    		}
		    	return s;
			}
		private float sum(float[] s)
			{	float f = 0;
				for (int i = 0; i < s.length; i++) f += s[i];
				return f;
			}
		private float divide(float a, float b)
			{	if (b == 0)
					throw new RuntimeException("Division by zero");
				return a / b;
			}
	}
/* Observe, This was what we wanted--we can see that the array is empty--but we "lost"
 		the "Division by zero" message and the full traceback, which generally is nice
 		to have. 
java.lang.RuntimeException: w contains []
at TestExceptionAlteredChains.mean(TestExceptionAlteredChains.java:13)
at TestExceptionAlteredChains.main(TestExceptionAlteredChains.java:5)
Exception in thread "main" */