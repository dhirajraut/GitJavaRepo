
public class TestStaticInnerVsOuter05
	{	public static void main(String[] argv)
			{	// Calling static method
				Outer05.InnerStatic05.innerStaticMethod();
				OuterStatic05.outerStaticMethod05();
				
				// Calling non-static method
				Outer05.InnerStatic05 oi = new Outer05.InnerStatic05();// Object of inner class
				oi.innerNonStaticMethod();
				OuterStatic05 os = new OuterStatic05();
				os.outerNonStaticMethod();
			}
	}

class Outer05
	{	static class InnerStatic05
			{	static void innerStaticMethod()
					{	System.out.println("I am in inner static method.");	}
				void innerNonStaticMethod()
					{	System.out.println("I am in inner non-static method.");	}
			}
	}

class OuterStatic05
	{	static void outerStaticMethod05()
			{	System.out.println("I am in outer static method.");	}
		void outerNonStaticMethod()
			{	System.out.println("I am in outer non-static method.");	}
	}