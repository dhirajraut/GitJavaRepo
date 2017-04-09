/* Static class within outer class
 * 
 * Static class can be instantiated.
 * Its instance allows access to all its contents including private.
 * 
 * Outer class can be instantiated within inner class
 
 * private member of outer is accessible to inner through object of outer.
 * private member of inner is accessible to outer through object of inner.
 * 
 * An inner class class can extend outer.
 */
public class TestStaticInner10
	{	public static void main(String[] argv)
			{	Outer.StaticInner.staticStaticInnerMethod(); // Static method of static inner class accessible directly.
				Outer.StaticInner os = new Outer.StaticInner(); // Static class can be instantiated. 
				os.instanceStaticInnerMethod();	// Instance method of static inner class accessible through object
			}
	}

class SuperOuter
	{	private String so1="private in superOuter";
		public final String so5 = "final in superOuter";
		public static String so6 = "static in superOuter";
	}
class SuperInner
	{	private String si1="private in superInner";
		public final String si5 = "final in superInner";
		public static String si6 = "static in superInner";
	}
class Outer extends SuperOuter
	{	private String o1="private in Outer";
		public final String o5 = "final in Outer";
		public static String o6 = "static in Outer";
		
		public static class StaticInner extends SuperInner
		//public static class StaticInner extends Outer  An inner class can extend outer.
			{	private String i1="private in staticInner";
				public final String i5 = "final in staticInner";
				public static String i6 = "static in staticInner";
				
				public static void staticStaticInnerMethod()
					{ 	System.out.println("Accessible things from here:"+i6);
						System.out.println("Accessible things from Outer:"+Outer.o6);
						System.out.println("Accessible things from supperInner:"+si6);
						System.out.println("Accessible things from supperOuter:"+so6);
					}
				public void instanceStaticInnerMethod()
					{	System.out.println("Accessible things from here:");
							System.out.println(i1);
							System.out.println(i5);
							System.out.println(i6);
						System.out.println("Accessible things from superInner:");
							//System.out.println(si1);  private of superInner
							System.out.println(si5);
							System.out.println(si6);
						System.out.println("Accessible things from Outer:");
							// System.out.println(this.o1); Private of outer is not  directly accessible
							Outer o = new Outer();
							System.out.println(o.o1);	// Private of outer is accessible through object
							//System.out.println(o.so1);	Private of super of outer not accessible.
							System.out.println(o.o5);
							System.out.println(o.o6);
					}
			}
		
		public void instanceOuterMethod()
			{	System.out.println(StaticInner.i6);
				System.out.println(StaticInner.si6);
				StaticInner.staticStaticInnerMethod();
				StaticInner stin = new StaticInner();
				System.out.println(stin.i1);	// Private of inner accessible
				System.out.println(stin.i5);
				System.out.println(stin.i6);
				//System.out.println(stin.si1);	Private of super inner not accessible
				System.out.println(stin.si5);
				System.out.println(stin.si6);
				stin.instanceStaticInnerMethod();
				stin.staticStaticInnerMethod();
			}
	}