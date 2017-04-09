/* This program is expected to work on clone in inheritance
	to find difference between copy constructor and clone() function.
*/

class ObjectEntity implements Cloneable
	{ int n;

	  public ObjectEntity(int n)
		{ this.n = n;	}

	  public Object clone()	throws CloneNotSupportedException	// Shallow cloning
		{ return super.clone();	}
	  //------------------------------------
	  public void setVal(int n)
		{ this.n = n;	}

	  public int getVal()
		{ return n;	 }

	  public String toString()
		{ return (" "+n); 	}

	}

class BaseCloneInherit implements Cloneable
	{ int xb;
	  String nmb;
	  ObjectEntity oeb;

	  public Object clone()	throws CloneNotSupportedException	// Shallow clone
		{ Object o = super.clone();
		  return o;
		}

	  public Object deepClone() throws CloneNotSupportedException	// Deep cloning
		{ BaseCloneInherit o = (BaseCloneInherit)super.clone();
		  o.oeb = (ObjectEntity) this.oeb.clone();
		  return (Object) o;
		}

	  public void setValDeep(int xb, String nmb, ObjectEntity oeb)
		{ this.xb = xb;
		  this.nmb  = nmb;
		  this.oeb.setVal(oeb.getVal());
		}

	  public void setVal(int xb, String nmb, ObjectEntity oeb)
		{ this.xb = xb;
		  this.nmb  = nmb;
		  this.oeb = oeb;
		}

	  public String toString()
		{ return ( " Xb:"+xb+" Nmb:"+nmb+" Oeb:"+oeb);	}
	}

class DeriCloneInherit extends BaseCloneInherit implements Cloneable
	{ int xd;
	  String nmd;
	  ObjectEntity oed;

	  public Object clone() throws CloneNotSupportedException
		{ Object o = super.clone();				// Shallow clone
		  return o;
		}

	  public Object deepClone() throws CloneNotSupportedException	// Deep cloning
		{ DeriCloneInherit o = (DeriCloneInherit)super.clone();
		  o.oeb = (ObjectEntity) this.oeb.clone();
		  o.oed = (ObjectEntity) this.oed.clone();
		  return (Object) o;
		}

	  public void setValDeep(int xb, String nmb, ObjectEntity oeb,int xd, String nmd, ObjectEntity oed)
		{ super.setValDeep(xb, nmb, oeb);
		  this.xd = xd;
		  this.nmd  = nmd;
		  this.oed.setVal(oed.getVal());
		}

	  public void setVal(int xb, String nmb, ObjectEntity oeb,int xd, String nmd, ObjectEntity oed)
		{ super.setVal(xb, nmb, oeb);
		  this.xd = xd;
		  this.nmd  = nmd;
		  this.oed = oed;
		}

	  public String toString()
		{ return ( super.toString()+" Xd:"+xd+" Nmd:"+nmd+" Oed:"+oed);	}
	}

public class TestCloneInherit
	{ public static void main(String [] argv)
		{ 
		  ObjectEntity oem1 = new ObjectEntity(100);
		  System.out.println(" Value of oem1 object "+ oem1);

		  ObjectEntity oem2 = new ObjectEntity(200);
		  System.out.println(" Value of oem2 object "+ oem2);
		  System.out.println("------------------");

		  DeriCloneInherit d1 = new DeriCloneInherit();
		  d1.setVal(10, "BaseString1", oem1, 20, "DeriString1", oem2);
		  
		  System.out.println("Value of derived d1 before shallow change");
		  System.out.println(d1);
		  System.out.println("------------------");

		  oem1.setVal(300);oem2.setVal(400);
		  System.out.println("Value of derived d1 after shallow change of value N1=300 and N2=400");
		  System.out.println(d1);
  		  System.out.println("------------------");

		  //--------------------------------------Deep cloning
		  System.out.println("Deep cloning.");
		  try {
		  DeriCloneInherit d2 = (DeriCloneInherit)d1.deepClone();
		  System.out.println("Value of derived d2 before link object change");
		  System.out.println(d2);

		  oem1.setVal(500);oem2.setVal(600);
		  System.out.println("Value of derived d2 after link object change--No change expected.");
		  System.out.println(d2);

		  d2.setValDeep(30, "BaseString2", oem1, 40, "DeriString2", oem2);
		  System.out.println("Value of derived d2 after deep change--Complete change expected.");
		  System.out.println(d2);
			}
		  catch (CloneNotSupportedException ce)
			{ System.out.println("Clone not supported Exception.");	}
		}
	}