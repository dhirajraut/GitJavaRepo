/* The try statements can be nested.  Each time try is entered, its exception context is pushed to the stack, thereby
 * pushing the generic first then subgeneric and so on so that the bottom most among exceptions come to the top in stack.
 * When error occurs, the stack is unwound to know the available catches.  The catches of nested try comes above the 
 * catches of outer try in the stack.  So, when error occurs, first the catches of inner try are poped for matching
 * and if matched then there is no question of poping other catches but if doesn't match, further catches of outer try
 * are poped for matching and if found then its fine otherwise at last, default handler is called for.  The inner catch
 * either may be present inside outer one or may be present inside the method which is being called inside outer catch.
 * In this situation also, the senario of catch execution is same.
 * 
 */
/* Exceptions within nested try.
 *  Case 1 |	Statement b=0 		Remark
 * 				Statement y=3		Remark
 * 				Statement OtherExp1	Remark
 * 				Statement OtherExp2	Remark	=> Statement ArithExp1 Catched error.
 * 				Sequence executed : ArithExp1-Below1-Below2-main
 *  Case 2 |	Statement b=0 		Remark
 * 				Statement y=3		Remark
 * 				Statement ArithExp1	Remark
 * 				Statement OtherExp2	Remark	=> Statement ArithExp2 Catched error.
 * 				Sequence executed : ArithExp2-Below2-main
 *  Case 3 |	Statement b=0 		Remark
 * 				Statement y=3		Remark
 * 				Statement ArithExp1	Remark
 * 				Statement ArithExp2	Remark	=> JVM Catched error.
 * 				Sequence executed : / by zero
 *  Case 4 |	Statement b=3 		Remark
 * 				Statement y=0		Remark
 * 				Statement ArithExp1	Remark
 * 				Statement ArithExp2	Remark	=> Statement ArithExp2 Catched error.
 * 				Sequence executed : ArithExp2-Below2-main
 *  Case 5 |	Statement b=3 		Remark
 * 				Statement y=0		Remark
 * 				Statement ArithExp1	Remark
 * 				Statement ArithExp2	Remark	=> JVM Catched error.
 * 				Sequence executed : / by zero
 */
public class TestUncaughtExceptionsTryWithinTry 
	{	public static void main(String[] args)
			{	functionCausingException();
				System.out.println("I am in main.");	//----------Statement main
			}
		public static void functionCausingException()
			{	int a=5, c=0;
				int b=0;								//----------Statement b=0
				//int b=3;								//----------Statement b=3
				try	{	
						c=a/b;
						{	int x=5, z=0;
							//int y=0;					//----------Statement y=0
							int y=3;					//----------Statement y=3
							try {	z=x/y;	}
							catch (ArithmeticException ae)//-----------Statement ArithExp1
							//catch (ArrayIndexOutOfBoundsException ae)//-----------Statement OtherExp1
								{	System.out.println("I am in inside try-try");	}
							System.out.println("I am in below try-try");//-----Statement Below1
						}
					}
				//catch (ArithmeticException ae)				//-----------Statement ArithExp2
				catch (ArrayIndexOutOfBoundsException ae)//-----------Statement OtherExp2
					{	System.out.println("I am in inside try.");	}
				System.out.println("I am below try.");			//-----Statement Below2
			}
	}
