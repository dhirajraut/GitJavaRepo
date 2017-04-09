package ExceptionChaining;

public class TestExceptionChaining
	{	public static void main(String [] argv)
			{	TestExceptionChaining tec = new TestExceptionChaining();
				try { tec.method1();	}		// 6
				catch(Exception e)
					{	e.printStackTrace();}
			}

		void method1() throws AException
			{	try	{	method2();	}				// 12
				catch(BException be)
					{	throw new AException("A Exception", be);	}
			}
		
		void method2() throws BException
			{	try	{	method3();	}				// 18
				catch(CException ce)
					{	throw new BException("B Exception", ce);	}
			}
		
		void method3() throws CException
			{	throw new CException("C Exception");	}	// 24
	}

/*
Number of elements of StackTrace : 2
Exception Message : A Exception
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-29||Method-method1
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-6||Method-main
===================
Number of elements of StackTrace : 3
Exception Message : B Exception
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-36||Method-method2
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-27||Method-method1
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-6||Method-main
===================
Number of elements of StackTrace : 4
Exception Message : C Exception
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-40||Method-method3
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-34||Method-method2
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-27||Method-method1
Class-ExceptionChaining.TestExceptionChaining||File-TestExceptionChaining.java||Line-6||Method-main
===================
 */
/*
catch(Throwable ae)
	{	StackTraceElement [] s;
		while (ae != null)
			{	s = ae.getStackTrace();
				System.out.println("Number of elements of StackTrace : "+s.length);
				System.out.println("Exception Message : "+ae.getMessage());
				for(int i=0; i<s.length; i++)
					{	System.out.print("Class-"+s[i].getClassName());
						System.out.print("||File-"+s[i].getFileName());
						System.out.print("||Line-"+s[i].getLineNumber());
						System.out.println("||Method-"+s[i].getMethodName());
					}
				ae = ae.getCause();
				System.out.println("===================");
			}
	}*/
