/* Exceptions in nested function calls.
 * Observe, no 'throws' clause is used infront of methods catching exceptions.
 * Case 1 | Statement ArithExp1 Remark
 * 			Statement ArithExp2 Remark
 * 			Statement OtherExp3 Remark	=> Statement ArithExp3 catches error.
 * 			Sequence executed : ArithExp3-Below3-Below2-Below1
 * Case 2 | Statement ArithExp1 Remark
 * 			Statement OtherExp2 Remark
 * 			Statement ArithExp3 Remark  => Statement ArithExp2 catches error.
 * 			Sequence executed : ArithExp2-Below2-Below1
 * Case 3 | Statement OtherExp1 Remark
 * 			Statement ArithExp2 Remark
 * 			Statement ArithExp3 Remark  => Statement ArithExp1 catches error.
 * 			Sequence executed : ArithExp1-Below1
 * Case 4 : Statement ArithExp1 Remark
 * 			Statement ArithExp2 Remark
 * 			Statement ArithExp3 Remark  => Statement ArithExp1 catches error.
 * 			JVM traps error : java.lang.ArithmeticException : / by zero
 * 				at --- Gives complete track of place of error beginning 
 * 						from the place of occurance i.e. statement a/b.
 */
public class TestUncaughtExceptionsTryInNestedCalls1
	{	public static void main(String[] args)
			{	CallingClass sc = new CallingClass();
				try	{sc.exceptionDemo();	}
				catch (ArithmeticException ae)			//-----------Statement ArithExp1
				//catch(ArrayIndexOutOfBoundsException ae)	//-----------Statement OtherExp1
					{	System.out.println("I am in outer class.");
						ae.printStackTrace();	
					}
				System.out.println("I am below catch of outer class.");	//-----Statement Below1
			}
	}

class CallingClass
	{	void exceptionDemo()
			{	CalledClass sc = new CalledClass();
				try {sc.exceptionDemo();}
				//catch(ArithmeticException ae)				//-----------Statement ArithExp2
				catch(ArrayIndexOutOfBoundsException ae)	//-----------Statement OtherExp2
					{	System.out.println("I am in CallingClass Catch.");
						ae.printStackTrace();	
					}
				System.out.println("I am below CallingClass Catch.");	//-----Statement Below2
			}
	}
class CalledClass
	{	void exceptionDemo()
			{	int a=5, b=0, c=0;
				try {	c=a/b;
					}
				//catch(ArithmeticException ae)				//-----------Statement ArithExp3
				catch(ArrayIndexOutOfBoundsException ae)	//-----------Statement OtherExp3
					{	System.out.println("I am in CalledClass Catch.");
						ae.printStackTrace();
					}
				System.out.println("I am below CalledClass Catch.");	//-----Statement Below3
			}
	}