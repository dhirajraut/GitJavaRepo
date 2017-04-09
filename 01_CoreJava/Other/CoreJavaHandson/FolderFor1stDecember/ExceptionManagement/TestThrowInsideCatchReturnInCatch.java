/* Summary
	Normal program execution is immediately branched when an exception is thrown.
	Checked exceptions must be caught or forwarded. This can be done in a try ... catch
		statement or by defining the exception in the method definition.
    The exception is caught by the first catch block whose associated exception class
    	matches the class or a superclass of the thrown exception.
    If no matching catch block is found in the exception chain, the thread containing
    	the thrown exception is terminated.
    The finally block after a try ... catch statement is executed regardless whether an
    	exception is caught or not.
    Returning within a finally block breaks the exception chain to the invoker even for
    	uncaught exceptions.
*/
import java.io.*;

public class TestThrowInsideCatchReturnInCatch
	{	public static FileInputStream f1(String fileName)
			{	FileInputStream fis = null;
				try	{	fis = new FileInputStream(fileName);	}
				catch (FileNotFoundException ex)
				    {
				      System.out.println("f1: Oops, FileNotFoundException caught");
				      throw new Error("f1: File not found");
				    }
			    System.out.println("f1: File input stream created");
			    return fis;
			}
		public static FileInputStream f2(String fileName)
			{	FileInputStream fis = null;
				try
					{	fis = new FileInputStream(fileName);	}
				catch (FileNotFoundException ex)
					{	System.out.println("f2: Oops, FileNotFoundException caught");
						return fis;
					}
				finally
				    {	System.out.println("f2: finally block");
				      	return fis;
				    }
			}
		public static void main(String args[])
			{	FileInputStream fis1 = null;
				FileInputStream fis2 = null;
				String fileName = "foo.bar";
				// String fileName = null;
			    System.out.println(  "main: Starting " + TestThrowInsideCatchReturnInCatch.class.getName()
			                       + " with file name = " + fileName);
			    // get file input stream 1
			    try {	fis1 = f1(fileName);	}
			    catch (Exception ex)
			    	{	System.out.println("main: Oops, general exception caught");   }
			    catch (Throwable th)
			    	{	System.out.println("main: Oops, throwable object caught");	  }
			    // get file input stream 2
			    fis2 = f2(fileName);
			    System.out.println("main: " + TestThrowInsideCatchReturnInCatch.class.getName() + " ended");
			}
	}

/* Compile and run Demo2 will generate the following output:

	main: Starting Demo2 with file name = foo.bar
	f1: Oops, FileNotFoundException caught
	main: Oops, throwable object caught
	f2: Oops, FileNotFoundException caught
	f2: finally block
	main: Demo2 ended

	Again f1 is called to get a new FileInputStream with a given file name. The thrown
	FileNotFoundException in f1 is caught in the following catch block. But this time an
	Error is thrown so that the method is terminated immediately. Because Error is not a
	subclass of Exception the first catch block does not match. But the second catch
	block matches all throwable objects.

	Calling f2 method will generate a FileNotFoundException too. This exception is caught
	in f2 and the method returns directly from the catch block. Remember, that the
	finally block is executed regardless whether an exception is caught or not.
*/
