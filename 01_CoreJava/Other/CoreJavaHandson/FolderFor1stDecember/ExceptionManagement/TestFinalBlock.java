/* The final block: It is executed ...
 * 		Though no error occurs in the try catch block and thus no catch is executyed.
 * 		Error occurs but catch to pick the error does not exist.
 * 		Error occurs and is caught.
 * 		Return in try is executed on not occuring an error.
 * 		Return in catch is executed on occuring an error.
 * Finally is not executed if System.exit(0) is encountered. 
 * */
 
class DemoFinalBlock
	{	int x;
		int y;
		int z;
		DemoFinalBlock()
			{	x=5; y=2;	}
		public void callForFinal()
			{	try { 	z=x/y;
						System.out.println("Value of Z "+z);
						//return;
						//System.exit(0);
					}
				catch(ArrayIndexOutOfBoundsException eib)
					{	System.out.println(eib);	}
				catch(ArithmeticException ae)
					{	System.out.println(ae);	}
				catch(Exception e)
					{	System.out.println(e);	}
				finally
					{	System.out.println("I am in final.");}
			}
	}
public class TestFinalBlock
	{	public static void main(String[] args)
			{	DemoFinalBlock dfb = new DemoFinalBlock();
				dfb.callForFinal();
			}
	}
