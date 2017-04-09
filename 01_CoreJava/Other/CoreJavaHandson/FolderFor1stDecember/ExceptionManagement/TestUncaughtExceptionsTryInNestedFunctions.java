
public class TestUncaughtExceptionsTryInNestedFunctions
	{	public static void main(String[] args)
			{	try { functionInner();	}
				catch (ArithmeticException ae)
					{	System.out.println("I am in outer catch block.");	}
				System.out.println("I am below catch.");
			}

		static void functionInner()
			{	try { functionInnerMost();	}
				catch (ArithmeticException ae)	//ArrayIndexOutOfBoundsException ae
					{	System.out.println("I am in inner catch.");	}
				System.out.println("I am in Inner below catch.");
			}
		static void functionInnerMost()
			{	int a=5, b=0, c=0;
				try { c = a/b;	}
				catch (ArrayIndexOutOfBoundsException ae)	//ArithmeticException ae
					{	System.out.println("I am in Innermost catch.");	}
				System.out.println("I am in Innermost below catch.");
			}
	}