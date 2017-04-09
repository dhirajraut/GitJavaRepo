/*
 * As per need of inner class, object of inner class must be created
 * 		in method/s of outer class, constructor here is creating objects
 * 		of inner class and assigning it to explicit reference present in
 * 		outer class. 
 */
public class TestNestInNest30
	{	public static void main(String[] argv)
			{	X ox = new X();
				System.out.println(ox.xy.yz.toString());
			}
	}

class X
	{	String  x = "|x in X|";
		Y xy;
		
		X()
			{	xy = new Y();	}
		
		class Y
			{	String x = "|x in Y|";
				String y = "|y in Y|";
				Z yz;
				
				Y()
					{ yz = new Z();	}
				
				class Z
					{	String x = "|x in Z|";
						String y = "|y in Z|";
						String z = "|z in Z|";
						
						public String toString()
							{	return X.this.x+Y.this.x+Y.this.y+Z.this.x+Z.this.y+Z.this.z;	}
					}
				
			}
	}