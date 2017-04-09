
public class MultipleExceptions
	{	public static void main(String[] args)
			{	try {func1();}
				catch(RuntimeException ae)
					{	System.out.println("I am in arithmetic catch.");
						printExceptionChain(ae);
						System.out.println("=================================================");
						ae.printStackTrace();
					}
			}
		static void printExceptionChain(Throwable tre)
			{	while(tre!=null)
					{	System.out.println("---------------");
						System.out.println(tre.toString());
						tre=tre.getCause();
					}
			}
		static void func1()
			{	int [] a = new int[3];
				try {	func2();}
				catch(RuntimeException re)
					{	for(int i=0; i<=3; i++)
							{ 	if (i==3)
									throw new RuntimeException("Subscript going out.",re);
								a[i]=0;
								System.out.println(a[i]);
							}
					}
				System.out.println("I am below catch of func1()");
			}
		static void func2()
			{	int a=5, b=0;
				if (b==0)
					throw new RuntimeException("Value of b is zero.");
				int z=a/b;
				System.out.println("I am below catch of func2()");
			}
	}