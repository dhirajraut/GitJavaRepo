/*
 * The Object.clone() method is protected.  So, derived class must implement it.
 * The Object class though have a protected Object.clone() method, it does not implement
		Clonable interface
 * The derived class must implement Cloneable interface to get itself cloned.  Otherwise,
 		"CloneNotSupportedException" is thrown.
 * Cloning does not attract invokation of Constructor.

*/
public class TestAllClone extends Object
	{	public static void main(String[] args)
			{	// Testing simplest shallow cloning.
				CloneableObject boc1 = new CloneableObject();
			
				CloneableObject boc2 = boc1.myClone();
				System.out.println("boc2 "+boc2);
				
				// Checking cloning in Inheritance
				
			}
	}

class CloneableObject implements Cloneable
	{	int a = 10;
	
		public String toString()
			{	return "A:"+a;	}
		
		public CloneableObject myClone()
			{	Object o = null;
				try {	o = super.clone();}
				catch(CloneNotSupportedException cns)
					{	cns.printStackTrace();	}
				return (CloneableObject)o;
			}
	}

class BaseOfClone
{}
	