/* Why to use factory method?
	Why to create instance of String using 'new' while cloneing?
	Why to use cloneing if copy constructor is available?
	Why Class 'String' does not provide clone?
*/

class Piston implements Cloneable
	{ private int numRings; 
	  
	  public Piston(int numRings)
		{ this.numRings = numRings; 	}

	  public Object clone() throws CloneNotSupportedException
		{ return super.clone();	}

	  public void setRings(int n)
		{ numRings = n;	}

	  public int getRings()
		{ return(numRings);	}
	}

class Engine implements Cloneable
	{ private String objName;
	  private String engName;
	  private int numCyl;
	  private Piston pObj;
	  private final int n = 10;

	  Engine(String objName, String engName, int numCyl, Piston p)	// Ordinary Constructor
		{ System.out.println("Ordinary constructor invoked for "+objName);
		  this.objName = new String(objName);
		  this.engName = new String(engName);
		  this.numCyl = numCyl;
		  try {	  pObj = (Piston) p.clone();}
		  catch ( CloneNotSupportedException c)
			{ System.out.println("Piston clone not supported.");	}
		}

	  Engine(String objName, Engine e)	// Copy Constructor
		{ System.out.println("Copy constructor invoked for "+objName);
		  this.objName = new String(objName);
		  engName = new String(e.engName);
	 	  numCyl = e.numCyl;
		  try {	  pObj = (Piston) e.pObj.clone();}
		  catch ( CloneNotSupportedException c)
			{ System.out.println("Piston clone not supported.");	}
		}
	  public Engine getInstance(String msg)	// Create instance method
		{ System.out.println("Instance method invoked for "+msg);
		  
		  Engine e = new Engine(msg, engName, numCyl, pObj);
		  return(e);
		}

	  public Engine myClone(String objName)
		{ Engine e=null;
		  try { e = (Engine) super.clone();
				e.objName = objName;
			  	e.engName = new String(engName); 
				e.pObj = (Piston) pObj.clone();
		      }
		  catch (CloneNotSupportedException c)
			{ System.out.println("This object is not implemented as Clonable");
			  System.exit(0);
			}
		  return e;
		}

	  public String toString()
		{ return("ObjName: "+objName+" EngineName: "+engName+" Cylinders: "+numCyl+" Rings:"+pObj.getRings()+" Final: "+n);}

	  public void setEngName(String engName)
		{ this.engName = engName;	}

	  public void setNumCyl(int numCyl)
		{ this.numCyl = numCyl; }

	  public void setPistonRings(int numR)
		{ pObj.setRings(numR);}
	}

public class CloningSimple
	{ public static void main(String [] argv)
		{ Piston p = new Piston(4);
		  Engine e1 = new Engine("E1","Maruti", 5, p);	// Constructor
		  Engine e2 = new Engine("E2", e1);		// Copy Constructor
		  Engine e3 = e1.getInstance("E3");		// Factory Method
		  Engine e4 = e1.myClone("E4");			// Create clone
		  Engine e5 = e1;				// Reference

		  p.setRings(9);
		  System.out.println("Object values after cloning.");
		  System.out.println(e1);	// E1, Maruti, 5, 3
		  System.out.println(e2);	// E2, Maruti, 5, 3
		  System.out.println(e3);	// E3, Maruti, 5, 3
		  System.out.println(e4);	// E4, Maruti, 5, 3
		  System.out.println(e5);	// E1, Maruti, 5, 3

		  e1.setNumCyl(6);e1.setEngName("Santro");
		  System.out.println("Object values after changing E1");
		  System.out.println(e1);	// E1, Santro, 6, 3
		  System.out.println(e2);	// E2, Maruti, 5, 3
		  System.out.println(e3);	// E3, Maruti, 5, 3
		  System.out.println(e4);	// E4, Maruti, 5, 3
		  System.out.println(e5);	// E1, Santro, 6, 3
		}
	}
 