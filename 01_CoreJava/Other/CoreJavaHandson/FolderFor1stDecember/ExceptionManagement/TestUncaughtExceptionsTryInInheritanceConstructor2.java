/*  Exceptions in Constructor of superclass and constructor of subclass 
 * 	Case 1 |	Statement OtherExp1 Remark
 * 				Statement b=3 1		Remark
 * 				Statement OtherExp2 Remark
 * 				Statement b=0 2		Remark
 * 				Statement OtherExp3 Remark	=> Statement ArithExp2 Catched error.
 * 				Sequence executed : ArithExp2-Below2-Below3-Below1
 *  Case 2 |	Statement OtherExp1 Remark
 * 				Statement b=3 1		Remark
 * 				Statement ArithExp2 Remark
 * 				Statement b=0 2		Remark
 * 				Statement OtherExp3 Remark	=> Statement ArithExp3 Doesn't catch error
 * 												Instead ArithExp1 catched Error
 * 				Sequence Executed : ArithExp1-Below1
 *  Case 3 |	Statement ArithExp1 Remark
 * 				Statement b=3 1		Remark
 * 				Statement ArithExp2 Remark
 * 				Statement b=0 2		Remark
 * 				Statement OtherExp3 Remark	=> Statement ArithExp3 Doesn't catch error
 * 												Instead JVM catched Error
 * 				Sequence Executed : ----
 *  Case 4 |	Statement OtherExp1 Remark
 *  			Statement b=0 1		Remark
 *  			Statement OtherExp2 Remark
 *  			Statement b=3 2		Remark
 *  			Statement OtherExp3 Remark	=>Statement ArithExp3 catches error.
 *  			Sequence executed : Below2-Arith3-Below3-Below1
 *   Case 5 |	Statement OtherExp1 Remark
 *  			Statement b=0 1		Remark
 *  			Statement OtherExp2 Remark
 *  			Statement b=3 2		Remark
 *  			Statement ArithExp3 Remark	=>Statement ArithExp1 catches error.
 *  			Sequence executed : Below2-Arith1-Below1
 *  Case 6 |	Statement ArithExp1 Remark
 *  			Statement b=0 1		Remark
 *  			Statement OtherExp2 Remark
 *  			Statement b=3 2		Remark
 *  			Statement ArithExp3 Remark	=> JVM calls exception
 *  			Sequence executed : Below2-----
 */
public class TestUncaughtExceptionsTryInInheritanceConstructor2
	{	public static void main(String[] args)  //throws ArithmeticException
			{	try	{	SubClass2 sc = new SubClass2();	}
				//catch (ArithmeticException ae)					//-----------Statement ArithExp1
				catch (ArrayIndexOutOfBoundsException ae)		//-----------Statement OtherExp1
					{	System.out.println("I am in outer class.");	}
				System.out.println("I am below catch of outer class.");//-----Statement Below1
			}
	}

class SuperClass2
	{	SuperClass2()
			{	int a=5, c=0;
				//int b=0;										//-----------Statement b=0 1
				int b=3;										//-----------Statement b=3 1
				try {c=a/b;	}
				catch (ArithmeticException ae)				//-----------Statement ArithExp2
				//catch (ArrayIndexOutOfBoundsException ae)		//-----------Statement OtherExp2
					{	System.out.println("I am in Super class Constructor.");	}
				System.out.println("I am below super class catch.");//-----Statement Below2
			}
	}
class SubClass2 extends SuperClass2
	{	SubClass2()
			{ 	super();
				int a=5, c=0;
				int b=0;										//-----------Statement b=0 2
				//int b=3;										//-----------Statement b=3 2
				try {c=a/b;	}
				//catch (ArithmeticException ae)				//-----------Statement ArithExp3
				catch (ArrayIndexOutOfBoundsException ae)		//-----------Statement OtherExp3
					{	System.out.println("I am in Sub class Constructor.");	}
				System.out.println("I am below subclass catch");//-----Statement Below3
			}
	}
