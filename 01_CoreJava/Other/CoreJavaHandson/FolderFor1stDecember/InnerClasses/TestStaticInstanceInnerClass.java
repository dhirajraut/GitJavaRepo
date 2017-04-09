/* Inner classes :
 * The classes which are declared within other classes are known as Inner Classes.
 * The inner class is 'Helper Class' which assists the outer class in doing its work.
 * 
 ** Inner class have complete access to the fileds and methods of outer class for every
 *		access specifiers.
 ** The outer class can only access public, package or protected fields/methods of inner class.
 ** compiler creates different class files for outer and inner classes.
 *  	The inner class's .class file name is created -- outerclass$innerclass.class
 * 
 * Though the language allows deep nested classes, it is not recomended to use it. It
 * 		reduces Readability of a program.
 */
public class TestStaticInstanceInnerClass
	{	public static void main(String [] argv)
			{	System.out.println("Static a:"+StaticInstanceInnerClass.a);
				// Refering Static Inner class
				System.out.println("Static inner b:"+StaticInstanceInnerClass.staticInnerClass.b);
				StaticInstanceInnerClass.staticInnerClass siiSic=new StaticInstanceInnerClass.staticInnerClass();
				System.out.println("Package inner c:"+siiSic.c);

				// Refering Instance Inner class
				StaticInstanceInnerClass sii=new StaticInstanceInnerClass();
				StaticInstanceInnerClass.instanceInnerClass siiIic = sii.new instanceInnerClass();
				System.out.println("Instance inner d:"+siiIic.d);
			}
	}
class StaticInstanceInnerClass
	{	static int a=1;
		static class staticInnerClass
			{	static int b=a+1;
				int c=3;
			}
		class instanceInnerClass
			{	int d = a+3;	}
	}
