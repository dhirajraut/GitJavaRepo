/* Comparing general object and anonymous inner class.
** If a class is to be written of few lines, instead of declaring its
* 		body seperately, making is inner is beneficial as it save from writting
* 		lot number of classes.  If class codes are to be used once, and no fear
* 		of redundancy of codes, make a class anonymous.  Otherwise declare with body.
**compiler creates different class files for outer and inner classes.
*  		The inner class's .class file name is created -- outerclass$innerclass.class
*/
public class AnonymousInnerClass
	{	public static void main(String[] args)
			{	// Creating object without InnerClass
				ChildClass cc = new ChildClass("Dog", "Barks");
				System.out.println("Display from super class using named object");
				cc.showAnimal();
				// This is anonymous inner class extending 'SuperClass'.
				// It doesn't have constructor as what name to give to constructor?
				new SuperClass()
					{	float avgWeight;
						void showAnimal(String animal, float avgWeight)
							{	super.animal=animal;
								this.avgWeight=avgWeight;
								System.out.println("Display from super class:");
								super.showAnimal();
								System.out.println("Display from inner class:");
								System.out.println("Avg Weight : "+avgWeight);
							}
					}.showAnimal("Cat", 5.0f);
			}
	}

class SuperClass
	{	String animal;
		
		SuperClass()
			{}
		SuperClass(String any)
			{	animal=any;	}
		void showAnimal()
			{	System.out.println("Animal Name : "+animal);	}
	}
class ChildClass extends SuperClass
	{	String sounds;
		ChildClass()
			{super(); }
		ChildClass(String any, String snd)
			{	super.animal=any;
				sounds=snd;
			}
		void showAnimal()
			{	super.showAnimal();
				System.out.println("Animal sounds :"+sounds);
			}
	}