/* Exception Chaining : In short, exception chaining reflects the actual method calling
 * 		sequence when a severe error is detected by storing an instance of the Exception
 * 		class for each method call.
 */ 
import java.io.*;

public class TestExceptionChains1
	{	public static FileInputStream f1(String fileName) throws FileNotFoundException
			{	FileInputStream fis = new FileInputStream(fileName);
				System.out.println("f1: File input stream created");
				return fis;
			}
		public static FileInputStream f2(String fileName)
			{	FileInputStream fis = null;
			    try
				    {	fis = new FileInputStream(fileName);	}
			    catch (FileNotFoundException ex)
			    	{	System.out.println("f2: Oops, FileNotFoundException caught");
			    		ex.printStackTrace();
			    	}
			    finally
			    	{	System.out.println("f2: finally block");	}
			    System.out.println("f2: Returning from f2");
			    return fis;
			 }
		public static void main(String args[])
			  {		FileInputStream fis1 = null;
				    FileInputStream fis2 = null;
				    String fileName = "foo.bar";
				    // String fileName = null;
				    System.out.println(  "main: Starting " + TestExceptionChains1.class.getName()
				                       + " with file name = " + fileName);
				    // get file input stream 1
				    try {	fis1 = f1(fileName);	}
				    catch (FileNotFoundException ex)
				    	{	System.out.println("main: Oops, FileNotFoundException caught");
				    		ex.printStackTrace();
				    	}
				    catch (Exception ex)
				    	{	System.out.println("main: Oops, genreal exception caught");	}
				    // get file input stream 2
				    fis2 = f2(fileName);
			
				    System.out.println("main: " + TestExceptionChains1.class.getName() + " ended");
			  }
	}
/* The OUTPUT is...
 * main: Starting TestExceptionChains1 with file name = foo.bar
main: Oops, FileNotFoundException caught
f2: Oops, FileNotFoundException caught
f2: finally block
f2: Returning from f2
main: TestExceptionChains1 ended
java.io.FileNotFoundException: foo.bar (The system cannot find the file specified)
	at java.io.FileInputStream.open(Native Method)
	at java.io.FileInputStream.<init>(FileInputStream.java:103)
	at java.io.FileInputStream.<init>(FileInputStream.java:66)
	at TestExceptionChains1.f1(TestExceptionChains1.java:5)
	at TestExceptionChains1.main(TestExceptionChains1.java:30)
java.io.FileNotFoundException: foo.bar (The system cannot find the file specified)
	at java.io.FileInputStream.open(Native Method)
	at java.io.FileInputStream.<init>(FileInputStream.java:103)
	at java.io.FileInputStream.<init>(FileInputStream.java:66)
	at TestExceptionChains1.f2(TestExceptionChains1.java:12)
	at TestExceptionChains1.main(TestExceptionChains1.java:38)
*/

