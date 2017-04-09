package ExceptionChaining;

public class AnalyseException
	{	
		public static void main(String [] argv)
			{	AnalyseException tec = new AnalyseException();
				try { tec.method1();	}					// 7
				catch(Throwable ae)
					{	StackTraceElement [] s;
						
						s = ae.getStackTrace();
						System.out.println("Number of elements of StackTrace : "+s.length);
						System.out.println("Exception Message : "+ae.getMessage());
						for(int i=0; i<s.length; i++)
							{	System.out.println("Class Name : "+s[i].getClassName());
								System.out.println("File Name  : "+s[i].getFileName());
								System.out.println("Line Number: "+s[i].getLineNumber());
								System.out.println("Method Name: "+s[i].getMethodName());
								System.out.println("String     : "+s[i].toString());
								System.out.println("-------------------------------");
							}
						System.out.println("===================");
						ae.printStackTrace();
					}
			}
		void method1() throws CException
			{	method2();	}								// 27
	
		void method2() throws CException
			{	method3();	}								// 30
	
		void method3() throws CException
			{	throw new CException("C Exception");	}	// 33
	}

// The output is...
/*
Number of elements of StackTrace : 4
Exception Message : C Exception
Class Name : ExceptionChaining.AnalyseException
File Name  : AnalyseException.java
Line Number: 33
Method Name: method3
String     : ExceptionChaining.AnalyseException.method3(AnalyseException.java:32)
-------------------------------
Class Name : ExceptionChaining.AnalyseException
File Name  : AnalyseException.java
Line Number: 30
Method Name: method2
String     : ExceptionChaining.AnalyseException.method2(AnalyseException.java:29)
-------------------------------
Class Name : ExceptionChaining.AnalyseException
File Name  : AnalyseException.java
Line Number: 27
Method Name: method1
String     : ExceptionChaining.AnalyseException.method1(AnalyseException.java:26)
-------------------------------
Class Name : ExceptionChaining.AnalyseException
File Name  : AnalyseException.java
Line Number: 7
Method Name: main
String     : ExceptionChaining.AnalyseException.main(AnalyseException.java:7)
-------------------------------
===================
*/
